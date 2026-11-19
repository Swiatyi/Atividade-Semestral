package provedor.aws;

import java.math.BigDecimal;
import provedor.Armazenamento;

class AwsArmazenamento implements Armazenamento {

    static final BigDecimal CUSTO_POR_GB_MES = new BigDecimal("0.023");

    private final String id;

    AwsArmazenamento(int numero) {
        this.id = "s3://" + numero;
    }

    @Override
    public String getId() { return id; }

    @Override
    public BigDecimal getCustoPorGbMes() { return CUSTO_POR_GB_MES; }
}
