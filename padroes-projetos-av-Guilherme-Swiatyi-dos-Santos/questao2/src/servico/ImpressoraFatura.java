package servico;

import util.Dinheiro;

public class ImpressoraFatura {

    public String formatar(Fatura fatura) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- FATURA DO COMPRADOR ---\n");
        sb.append("Lote           : ").append(fatura.getNomeLote()).append('\n');
        sb.append("Valor de venda : ").append(Dinheiro.formatar(fatura.getValorVenda())).append('\n');
        for (LinhaFatura linha : fatura.getServicos()) {
            sb.append("  + ").append(linha.getDescricao())
              .append(": ").append(Dinheiro.formatar(linha.getCusto())).append('\n');
        }
        sb.append("TOTAL A PAGAR  : ").append(Dinheiro.formatar(fatura.getTotal()));
        return sb.toString();
    }

    public void imprimir(Fatura fatura) {
        System.out.println(formatar(fatura));
    }
}
