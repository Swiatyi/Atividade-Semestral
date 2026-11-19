package servico;

import java.math.BigDecimal;
import util.Dinheiro;

public class SeguroTransporte extends ServicoAdicional {

    public SeguroTransporte(Fatura fatura) { super(fatura); }

    @Override
    protected String descricaoServico() { return "Seguro de transporte (1,5% do valor de venda)"; }

    @Override
    protected BigDecimal custoServico() {
        return Dinheiro.arredondar(getValorVenda().multiply(new BigDecimal("0.015")));
    }
}
