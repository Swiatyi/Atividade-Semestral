package servico;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class ServicoAdicional implements Fatura {

    private final Fatura decorada;

    protected ServicoAdicional(Fatura decorada) {
        this.decorada = decorada;
    }

    protected abstract String descricaoServico();
    protected abstract BigDecimal custoServico();

    @Override
    public String getNomeLote() { return decorada.getNomeLote(); }

    @Override
    public BigDecimal getValorVenda() { return decorada.getValorVenda(); }

    @Override
    public List<LinhaFatura> getServicos() {
        List<LinhaFatura> linhas = new ArrayList<>(decorada.getServicos());
        linhas.add(new LinhaFatura(descricaoServico(), custoServico()));
        return linhas;
    }

    @Override
    public BigDecimal getTotal() {
        return decorada.getTotal().add(custoServico());
    }
}
