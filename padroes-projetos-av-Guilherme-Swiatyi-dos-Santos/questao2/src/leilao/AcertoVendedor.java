package leilao;

import comissao.RegraComissao;
import java.math.BigDecimal;
import util.Dinheiro;

public final class AcertoVendedor {

    private final Venda venda;
    private final RegraComissao regra;
    private final BigDecimal comissao;

    public AcertoVendedor(Venda venda, RegraComissao regra) {
        this.venda = venda;
        this.regra = regra;
        this.comissao = regra.calcular(venda.getValorVenda());
    }

    public BigDecimal getComissao() { return comissao; }

    public BigDecimal getLiquido() { return venda.getValorVenda().subtract(comissao); }

    public String imprimir() {
        return "--- ACERTO DO VENDEDOR: " + venda.getNomeLote() + " ---\n"
                + "Valor de venda     : " + Dinheiro.formatar(venda.getValorVenda()) + "\n"
                + "Comissão cobrada   : " + Dinheiro.formatar(comissao) + "  (" + regra.getDescricao() + ")\n"
                + "Líquido a repassar : " + Dinheiro.formatar(getLiquido());
    }
}
