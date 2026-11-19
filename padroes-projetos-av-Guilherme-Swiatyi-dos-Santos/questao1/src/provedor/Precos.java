package provedor;

import java.math.BigDecimal;

public final class Precos {

    public static final int HORAS_NO_MES = 730;

    private final BigDecimal custoHoraMaquina;
    private final BigDecimal custoGbMesArmazenamento;
    private final BigDecimal custoMensalFila;

    public Precos(BigDecimal custoHoraMaquina, BigDecimal custoGbMesArmazenamento,
                  BigDecimal custoMensalFila) {
        this.custoHoraMaquina = custoHoraMaquina;
        this.custoGbMesArmazenamento = custoGbMesArmazenamento;
        this.custoMensalFila = custoMensalFila;
    }

    public BigDecimal custoMensal(int qtdMaquinas, int gbArmazenamento) {
        BigDecimal maquinas = custoHoraMaquina
                .multiply(BigDecimal.valueOf(HORAS_NO_MES))
                .multiply(BigDecimal.valueOf(qtdMaquinas));
        BigDecimal armazenamento = custoGbMesArmazenamento.multiply(BigDecimal.valueOf(gbArmazenamento));
        return maquinas.add(armazenamento).add(custoMensalFila);
    }
}
