package leilao;

import java.math.BigDecimal;

public final class Venda {

    private final String nomeLote;
    private final Licitante comprador;
    private final BigDecimal valorVenda;

    Venda(String nomeLote, Licitante comprador, BigDecimal valorVenda) {
        this.nomeLote = nomeLote;
        this.comprador = comprador;
        this.valorVenda = valorVenda;
    }

    public String getNomeLote() { return nomeLote; }
    public Licitante getComprador() { return comprador; }
    public BigDecimal getValorVenda() { return valorVenda; }
}
