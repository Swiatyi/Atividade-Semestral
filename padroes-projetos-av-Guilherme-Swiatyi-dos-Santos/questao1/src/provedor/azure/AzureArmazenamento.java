package provedor.azure;

import java.math.BigDecimal;
import provedor.Armazenamento;

class AzureArmazenamento implements Armazenamento {

    static final BigDecimal CUSTO_POR_GB_MES = new BigDecimal("0.02");

    private final String id;

    AzureArmazenamento(int numero) {
        this.id = "blob://" + numero;
    }

    @Override
    public String getId() { return id; }

    @Override
    public BigDecimal getCustoPorGbMes() { return CUSTO_POR_GB_MES; }
}
