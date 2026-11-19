package servico;

import java.math.BigDecimal;

public class EntregaExpressa extends ServicoAdicional {

    public EntregaExpressa(Fatura fatura) { super(fatura); }

    @Override
    protected String descricaoServico() { return "Entrega expressa"; }

    @Override
    protected BigDecimal custoServico() { return new BigDecimal("450.00"); }
}
