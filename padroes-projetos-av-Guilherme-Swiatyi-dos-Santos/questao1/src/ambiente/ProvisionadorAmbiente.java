package ambiente;

import auditoria.HistoricoAuditoria;
import auditoria.ResultadoProvisionamento;
import config.ConfiguracaoSistema;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import provedor.Armazenamento;
import provedor.FilaMensagens;
import provedor.MaquinaVirtual;
import provedor.ProvedorFactory;

public class ProvisionadorAmbiente {

    public Optional<Ambiente> provisionar(Solicitacao solicitacao) {
        ProvedorFactory fabrica = solicitacao.getProvedor();
        ConfiguracaoSistema config = ConfiguracaoSistema.getInstancia();
        HistoricoAuditoria auditoria = HistoricoAuditoria.getInstancia();

        // RF04 + RF05: estima o custo ANTES de criar qualquer recurso.
        BigDecimal custo = fabrica.getPrecos()
                .custoMensal(solicitacao.getQuantidadeMaquinas(), solicitacao.getGbArmazenamento());
        BigDecimal orcamento = config.getOrcamentoMensalMaximo();

        if (custo.compareTo(orcamento) > 0) {
            String motivo = "custo estimado " + Ambiente.formatar(custo)
                    + " ultrapassa o orçamento de " + Ambiente.formatar(orcamento);
            auditoria.registrar(solicitacao.getNomeAmbiente(), fabrica.getNome(),
                    ResultadoProvisionamento.RECUSADO, motivo);
            System.out.println("--- Solicitação RECUSADA: " + solicitacao.getNomeAmbiente()
                    + " (" + fabrica.getNome() + ") - " + motivo);
            return Optional.empty();
        }

        // Os três recursos saem da MESMA fábrica => sempre do mesmo provedor (RNF01).
        List<MaquinaVirtual> maquinas = new ArrayList<>();
        for (int i = 0; i < solicitacao.getQuantidadeMaquinas(); i++) {
            maquinas.add(fabrica.criarMaquinaVirtual());
        }
        Armazenamento armazenamento = fabrica.criarArmazenamento();
        FilaMensagens fila = fabrica.criarFila();

        Ambiente ambiente = new Ambiente(solicitacao.getNomeAmbiente(), fabrica.getNome(),
                config.getRegiaoPadrao(), maquinas, armazenamento, fila, custo);

        System.out.println(ambiente.getResumo());
        auditoria.registrar(solicitacao.getNomeAmbiente(), fabrica.getNome(),
                ResultadoProvisionamento.ACEITO,
                "custo estimado " + Ambiente.formatar(custo) + " dentro do orçamento");
        return Optional.of(ambiente);
    }
}
