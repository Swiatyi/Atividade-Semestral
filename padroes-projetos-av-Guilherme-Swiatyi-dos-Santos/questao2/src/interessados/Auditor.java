package interessados;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import leilao.Interessado;
import leilao.Lance;
import util.Dinheiro;

public class Auditor implements Interessado {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final List<String> registros = new ArrayList<>();

    @Override
    public void atualizar(Lance lance) {
        registros.add("[" + lance.getDataHora().format(FORMATO) + "] lote '" + lance.getNomeLote()
                + "' | " + lance.getLicitante().getNome() + " | " + Dinheiro.formatar(lance.getValor()));
    }

    public String imprimirRegistros() {
        StringBuilder sb = new StringBuilder("--- REGISTRO DO AUDITOR (" + registros.size() + " lances) ---");
        for (String r : registros) {
            sb.append('\n').append(r);
        }
        return sb.toString();
    }
}
