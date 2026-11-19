package ambiente;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import provedor.Armazenamento;
import provedor.FilaMensagens;
import provedor.MaquinaVirtual;

public final class Ambiente {

    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");

    private final String nome;
    private final String provedor;
    private final String regiao;
    private final List<MaquinaVirtual> maquinas;
    private final Armazenamento armazenamento;
    private final FilaMensagens fila;
    private final BigDecimal custoMensalEstimado;

    Ambiente(String nome, String provedor, String regiao, List<MaquinaVirtual> maquinas,
             Armazenamento armazenamento, FilaMensagens fila, BigDecimal custoMensalEstimado) {
        this.nome = nome;
        this.provedor = provedor;
        this.regiao = regiao;
        this.maquinas = Collections.unmodifiableList(new ArrayList<>(maquinas));
        this.armazenamento = armazenamento;
        this.fila = fila;
        this.custoMensalEstimado = custoMensalEstimado;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getCustoMensalEstimado()
    {
        return custoMensalEstimado;
    }


    public String getResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Ambiente provisionado ---\n");
        sb.append("Ambiente : ").append(nome).append('\n');
        sb.append("Provedor : ").append(provedor).append('\n');
        sb.append("Região   : ").append(regiao).append('\n');
        for (MaquinaVirtual vm : maquinas) {
            sb.append("Máquina  : ").append(vm.getId()).append('\n');
        }
        sb.append("Storage  : ").append(armazenamento.getId()).append('\n');
        sb.append("Fila     : ").append(fila.getId()).append('\n');
        sb.append("Custo/mês: ").append(formatar(custoMensalEstimado));
        return sb.toString();
    }

    static String formatar(BigDecimal valor) {
        return String.format(PT_BR, "R$ %.2f", valor);
    }
}
