package app;

import comissao.ComissaoArte;
import comissao.ComissaoEletronicos;
import interessados.Auditor;
import interessados.PainelLeiloeiro;
import interessados.Participante;
import java.util.Optional;
import leilao.AcertoVendedor;
import leilao.Categoria;
import leilao.Lote;
import leilao.Venda;
import servico.CertificadoAutenticidade;
import servico.EmbalagemEspecial;
import servico.EntregaExpressa;
import servico.Fatura;
import servico.FaturaBase;
import servico.ImpressoraFatura;
import servico.SeguroTransporte;
import util.Dinheiro;

public class Main {

    private static void titulo(String texto) {
        System.out.println("\n==================== " + texto + " ====================");
    }

    public static void main(String[] args) {

        // ---------- Lotes de duas categorias distintas ----------
        Lote quadro = new Lote("Quadro Paisagem Azul",
                new Categoria("Arte", new ComissaoArte()),
                Dinheiro.de("10000"), Dinheiro.de("15000"));
        Lote notebook = new Lote("Notebook Gamer X",
                new Categoria("Eletrônicos", new ComissaoEletronicos()),
                Dinheiro.de("3000"), Dinheiro.de("4000"));

        // ---------- Participantes, painel e auditor ----------
        Participante ana = new Participante("Ana");
        Participante bruno = new Participante("Bruno");
        Participante carla = new Participante("Carla");
        PainelLeiloeiro painel = new PainelLeiloeiro();
        Auditor auditor = new Auditor();

        titulo("ACOMPANHAMENTO");
        quadro.acompanhar(ana);
        quadro.acompanhar(bruno);
        quadro.acompanhar(carla);
        quadro.acompanhar(painel);
        quadro.acompanhar(auditor);
        notebook.acompanhar(bruno);
        notebook.acompanhar(carla);
        notebook.acompanhar(painel);
        notebook.acompanhar(auditor);
        System.out.println("Quadro: Ana, Bruno, Carla, painel e auditor. Notebook: Bruno, Carla, painel e auditor.");

        // ---------- Lances no quadro ----------
        titulo("LANCES NO QUADRO (inicial R$ 10.000,00)");
        ana.darLance(quadro, Dinheiro.de("10200"));     // RECUSADO (mínimo 10.500)
        ana.darLance(quadro, Dinheiro.de("10500"));     // aceito (exatamente +5%)
        bruno.darLance(quadro, Dinheiro.de("11025"));   // aceito

        titulo("CARLA DEIXA DE ACOMPANHAR O QUADRO");
        quadro.deixarDeAcompanhar(carla);
        System.out.println("Carla não receberá mais avisos do quadro.");

        titulo("MAIS LANCES NO QUADRO");
        ana.darLance(quadro, Dinheiro.de("16000"));     // aceito; Carla NÃO é avisada

        // ---------- Lances no notebook ----------
        titulo("LANCES NO NOTEBOOK (inicial R$ 3.000,00)");
        bruno.darLance(notebook, Dinheiro.de("3150"));  // aceito
        carla.darLance(notebook, Dinheiro.de("3200"));  // RECUSADO (mínimo 3.307,50)
        carla.darLance(notebook, Dinheiro.de("3307.50"));// aceito (ainda abaixo da reserva)

        // ---------- Campanha: troca de regra em tempo de execução ----------
        titulo("CAMPANHA: troca da regra de comissão do quadro");
        System.out.println("Regra antes : " + quadro.getRegraComissao().getDescricao());
        quadro.setRegraComissao(new ComissaoEletronicos());   // mesmo lote, sem recriar
        System.out.println("Regra agora : " + quadro.getRegraComissao().getDescricao());

        // ---------- Encerramentos ----------
        titulo("ENCERRAMENTO DO QUADRO");
        Optional<Venda> vendaQuadro = quadro.encerrar();
        System.out.println("Status: " + quadro.getStatus());
        bruno.darLance(quadro, Dinheiro.de("20000"));          // RECUSADO: encerrado

        titulo("ENCERRAMENTO DO NOTEBOOK");
        Optional<Venda> vendaNotebook = notebook.encerrar();
        System.out.println("Status: " + notebook.getStatus() + " (maior lance "
                + Dinheiro.formatar(notebook.getLanceAtual()) + " < reserva "
                + Dinheiro.formatar(notebook.getPrecoReserva()) + ")");

        // ---------- Acerto do vendedor e fatura do comprador ----------
        if (vendaQuadro.isPresent()) {
            Venda venda = vendaQuadro.get();

            titulo("ACERTO DO VENDEDOR (regra vigente no encerramento)");
            System.out.println(new AcertoVendedor(venda, quadro.getRegraComissao()).imprimir());

            titulo("FATURA COM SERVIÇOS ADICIONAIS (comprador: " + venda.getComprador().getNome() + ")");
            Fatura fatura = new FaturaBase(venda);
            fatura = new SeguroTransporte(fatura);
            fatura = new EmbalagemEspecial(fatura);
            fatura = new CertificadoAutenticidade(fatura);
            fatura = new EmbalagemEspecial(fatura);   // contratada pela segunda vez
            fatura = new EntregaExpressa(fatura);
            new ImpressoraFatura().imprimir(fatura);

            titulo("MESMA IMPRESSORA, FATURA SEM SERVIÇOS (RNF04)");
            new ImpressoraFatura().imprimir(new FaturaBase(venda));
        }
        System.out.println("\nNotebook vendido? " + vendaNotebook.isPresent());

        titulo("AUDITORIA");
        System.out.println(auditor.imprimirRegistros());
    }
}
