package leilao;

import comissao.RegraComissao;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import util.Dinheiro;

public class Lote implements SujeitoLance {

    private static final BigDecimal FATOR_MINIMO = new BigDecimal("1.05");

    private final String nome;
    private final Categoria categoria;
    private final BigDecimal precoInicial;
    private final BigDecimal precoReserva;
    private BigDecimal lanceAtual;
    private StatusLote status = StatusLote.ABERTO;
    private Venda venda;

    private final List<Lance> lances = new ArrayList<>();
    private final List<Interessado> interessados = new ArrayList<>();
    private RegraComissao regraComissao;

    public Lote(String nome, Categoria categoria, BigDecimal precoInicial, BigDecimal precoReserva) {
        this.nome = Objects.requireNonNull(nome);
        this.categoria = Objects.requireNonNull(categoria);
        this.precoInicial = precoInicial;
        this.precoReserva = precoReserva;
        this.lanceAtual = precoInicial;                       // RF01
        this.regraComissao = categoria.getRegraPadrao();      // começa com a regra da categoria
    }

    // ---------------- Observer ----------------

    @Override
    public void acompanhar(Interessado interessado) {
        if (!interessados.contains(interessado)) {
            interessados.add(interessado);
        }
    }

    @Override
    public void deixarDeAcompanhar(Interessado interessado) {
        interessados.remove(interessado);
    }

    private void notificar(Lance lance) {
        for (Interessado i : new ArrayList<>(interessados)) {
            if (i != lance.getLicitante()) {
                i.atualizar(lance);
            }
        }
    }

    // ---------------- Lances (RF02) ----------------
    public ResultadoLance receberLance(Licitante licitante, BigDecimal valor) {
        if (status != StatusLote.ABERTO) {
            return ResultadoLance.recusado("o lote '" + nome + "' já está encerrado");
        }
        BigDecimal minimo = lanceAtual.multiply(FATOR_MINIMO).setScale(2, RoundingMode.CEILING);
        if (valor.compareTo(minimo) < 0) {
            return ResultadoLance.recusado("o lance deve ser pelo menos 5% maior que o lance atual de "
                    + Dinheiro.formatar(lanceAtual) + " (mínimo " + Dinheiro.formatar(minimo) + ")");
        }
        Lance lance = new Lance(nome, licitante, valor);
        lances.add(lance);
        lanceAtual = valor;
        notificar(lance);
        return ResultadoLance.aceito("lance registrado");
    }

    // ---------------- Encerramento (RF05) ----------------
    public Optional<Venda> encerrar() {
        if (status != StatusLote.ABERTO) {
            throw new IllegalStateException("O lote '" + nome + "' já foi encerrado.");
        }
        boolean vendido = !lances.isEmpty() && lanceAtual.compareTo(precoReserva) >= 0;
        if (vendido) {
            Lance vencedor = lances.get(lances.size() - 1);
            venda = new Venda(nome, vencedor.getLicitante(), lanceAtual);
            status = StatusLote.VENDIDO;
        } else {
            status = StatusLote.NAO_VENDIDO;
        }
        return Optional.ofNullable(venda);
    }

    // ---------------- Strategy (RF06 / RF07) ----------------
    public RegraComissao getRegraComissao() {
        return regraComissao;
    }
    public void setRegraComissao(RegraComissao nova) {
        if (status != StatusLote.ABERTO) {
            throw new IllegalStateException("A regra de comissão só pode ser trocada antes do encerramento.");
        }
        this.regraComissao = Objects.requireNonNull(nova);
    }

    // ---------------- Consultas ----------------

    public String getNome() { return nome; }
    public Categoria getCategoria() { return categoria; }
    public BigDecimal getPrecoInicial() { return precoInicial; }
    public BigDecimal getPrecoReserva() { return precoReserva; }
    public BigDecimal getLanceAtual() { return lanceAtual; }
    public StatusLote getStatus() { return status; }
    public List<Lance> getLances() { return Collections.unmodifiableList(lances); }
}
