package app;

import ambiente.ProvisionadorAmbiente;
import ambiente.Solicitacao;
import auditoria.HistoricoAuditoria;
import java.util.Arrays;
import java.util.List;
import provedor.ProvedorFactory;
import provedor.aws.AwsFactory;
import provedor.azure.AzureFactory;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ProvedorFactory aws = new AwsFactory();     // compartilhada pelas solicitações Aws
        ProvedorFactory azure = new AzureFactory();

        ProvisionadorAmbiente provisionador = new ProvisionadorAmbiente();

        List<Solicitacao> solicitacoes = Arrays.asList(
                new Solicitacao("loja-web",        aws,   3, 100),  // Aws   -> aceito
                new Solicitacao("analytics",       aws,   2,  50),  // Aws   -> aceito
                new Solicitacao("data-lake",       azure, 2, 200),  // Azure -> aceito
                new Solicitacao("cluster-gigante", aws,  10, 500)   // Aws   -> RECUSADO (orçamento)
        );

        Thread[] threads = new Thread[solicitacoes.size()];
        for (int i = 0; i < threads.length; i++) {
            Solicitacao s = solicitacoes.get(i);
            threads[i] = new Thread(() -> provisionador.provisionar(s), "prov-" + s.getNomeAmbiente());
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }

        System.out.println();
        HistoricoAuditoria.getInstancia().imprimir();
    }
}
