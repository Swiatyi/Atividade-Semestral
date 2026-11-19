package servico;

import java.math.BigDecimal;

public class EmbalagemEspecial extends ServicoAdicional {

    public EmbalagemEspecial(Fatura fatura) { super(fatura); }

    @Override
    protected String descricaoServico() { return "Embalagem especial"; }

    @Override
    protected BigDecimal custoServico() { return new BigDecimal("180.00"); }
}
