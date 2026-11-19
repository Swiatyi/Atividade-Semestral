package auditoria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class HistoricoAuditoria {

    private static final class Holder {
        private static final HistoricoAuditoria INSTANCIA = new HistoricoAuditoria();
    }

    private final List<RegistroAuditoria> registros = new ArrayList<>();

    private HistoricoAuditoria() {
    }

    public static HistoricoAuditoria getInstancia() {
        return Holder.INSTANCIA;
    }

    public synchronized void registrar(String ambiente, String provedor,
                                       ResultadoProvisionamento resultado, String motivo) {
        registros.add(new RegistroAuditoria(LocalDateTime.now(), ambiente, provedor, resultado, motivo));
    }

    public synchronized List<RegistroAuditoria> getRegistros() {
        return Collections.unmodifiableList(new ArrayList<>(registros));
    }

    public void imprimir() {
        List<RegistroAuditoria> copia = getRegistros();
        System.out.println("=== HISTÓRICO DE AUDITORIA (" + copia.size() + " registros) ===");
        for (RegistroAuditoria r : copia) {
            System.out.println(r);
        }
    }
}
