package provedor.azure;

import java.math.BigDecimal;
import provedor.MaquinaVirtual;

class AzureMaquinaVirtual implements MaquinaVirtual {

    static final BigDecimal CUSTO_POR_HORA = new BigDecimal("0.12");

    private final String id;

    AzureMaquinaVirtual(int numero) {
        this.id = "vm-" + numero;
    }

    @Override
    public String getId() { return id; }

    @Override
    public BigDecimal getCustoPorHora() { return CUSTO_POR_HORA; }
}
