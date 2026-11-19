package provedor.aws;

import java.math.BigDecimal;
import provedor.FilaMensagens;

class AwsFila implements FilaMensagens {

    static final BigDecimal CUSTO_MENSAL = new BigDecimal("0.40");

    private final String id;

    AwsFila(int numero) {
        this.id = "sqs-" + numero;
    }

    @Override
    public String getId() { return id; }

    @Override
    public BigDecimal getCustoMensal() { return CUSTO_MENSAL; }
}
