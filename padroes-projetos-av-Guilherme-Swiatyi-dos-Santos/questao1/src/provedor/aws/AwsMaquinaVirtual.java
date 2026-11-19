package provedor.aws;

import java.math.BigDecimal;
import provedor.MaquinaVirtual;

class AwsMaquinaVirtual implements MaquinaVirtual {

    static final BigDecimal CUSTO_POR_HORA = new BigDecimal("0.10");

    private final String id;

    AwsMaquinaVirtual(int numero) {
        this.id = "i-" + numero;
    }

    @Override
    public String getId() { return id; }

    @Override
    public BigDecimal getCustoPorHora() { return CUSTO_POR_HORA; }
}
