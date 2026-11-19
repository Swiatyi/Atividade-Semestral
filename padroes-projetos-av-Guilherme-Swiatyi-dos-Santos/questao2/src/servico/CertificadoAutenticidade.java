package servico;

import java.math.BigDecimal;

public class CertificadoAutenticidade extends ServicoAdicional {

    public CertificadoAutenticidade(Fatura fatura) { super(fatura); }

    @Override
    protected String descricaoServico() { return "Certificado de autenticidade"; }

    @Override
    protected BigDecimal custoServico() { return new BigDecimal("300.00"); }
}
