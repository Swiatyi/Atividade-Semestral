package provedor.azure;

import java.math.BigDecimal;
import provedor.FilaMensagens;

class AzureFila implements FilaMensagens {

    static final BigDecimal CUSTO_MENSAL = new BigDecimal("0.50");

    private final String id;

    AzureFila(int numero) {
        this.id = "sb-" + numero;
    }

    @Override
    public String getId() { return id; }

    @Override
    public BigDecimal getCustoMensal() { return CUSTO_MENSAL; }
}
