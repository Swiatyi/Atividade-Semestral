package auditoria;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class RegistroAuditoria {

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

    private final LocalDateTime dataHora;
    private final String ambiente;
    private final String provedor;
    private final ResultadoProvisionamento resultado;
    private final String motivo;

    RegistroAuditoria(LocalDateTime dataHora, String ambiente, String provedor,
                      ResultadoProvisionamento resultado, String motivo) {
        this.dataHora = dataHora;
        this.ambiente = ambiente;
        this.provedor = provedor;
        this.resultado = resultado;
        this.motivo = motivo;
    }

    public LocalDateTime getDataHora() { return dataHora; }
    public String getAmbiente() { return ambiente; }
    public String getProvedor() { return provedor; }
    public ResultadoProvisionamento getResultado() { return resultado; }
    public String getMotivo() { return motivo; }

    @Override
    public String toString() {
        return String.format("[%s] ambiente=%s | provedor=%s | resultado=%s | motivo=%s",
                dataHora.format(FORMATO), ambiente, provedor, resultado, motivo);
    }
}
