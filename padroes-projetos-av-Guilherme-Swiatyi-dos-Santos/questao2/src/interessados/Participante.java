package interessados;

import java.math.BigDecimal;
import leilao.Interessado;
import leilao.Lance;
import leilao.Licitante;
import leilao.Lote;
import leilao.ResultadoLance;
import util.Dinheiro;

public class Participante implements Interessado, Licitante {

    private final String nome;

    public Participante(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome() { return nome; }

    public ResultadoLance darLance(Lote lote, BigDecimal valor) {
        ResultadoLance r = lote.receberLance(this, valor);
        System.out.println("[" + nome + "] lance de " + Dinheiro.formatar(valor) + " no lote '"
                + lote.getNome() + "' -> " + (r.isAceito() ? "ACEITO" : "RECUSADO: " + r.getMensagem()));
        return r;
    }

    @Override
    public void atualizar(Lance lance) {
        System.out.println("   (aviso para " + nome + ") lote '" + lance.getNomeLote()
                + "': novo lance de " + Dinheiro.formatar(lance.getValor())
                + " por " + lance.getLicitante().getNome());
    }
}
