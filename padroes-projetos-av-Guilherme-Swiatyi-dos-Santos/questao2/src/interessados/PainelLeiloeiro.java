package interessados;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import leilao.Interessado;
import leilao.Lance;
import util.Dinheiro;

public class PainelLeiloeiro implements Interessado {

    private static final class Placar {
        BigDecimal maiorLance = BigDecimal.ZERO;
        int totalLances = 0;
    }

    private final Map<String, Placar> porLote = new LinkedHashMap<>();

    @Override
    public void atualizar(Lance lance) {
        Placar p = porLote.computeIfAbsent(lance.getNomeLote(), k -> new Placar());
        p.totalLances++;
        if (lance.getValor().compareTo(p.maiorLance) > 0) {
            p.maiorLance = lance.getValor();
        }
        System.out.println("   [PAINEL] lote '" + lance.getNomeLote() + "' | maior lance: "
                + Dinheiro.formatar(p.maiorLance) + " | total de lances: " + p.totalLances);
    }
}
