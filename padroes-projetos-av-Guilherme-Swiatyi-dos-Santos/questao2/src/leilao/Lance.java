package leilao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class Lance {

    private final String nomeLote;
    private final Licitante licitante;
    private final BigDecimal valor;
    private final LocalDateTime dataHora;

    Lance(String nomeLote, Licitante licitante, BigDecimal valor) {
        this.nomeLote = nomeLote;
        this.licitante = licitante;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
    }

    public String getNomeLote() { return nomeLote; }
    public Licitante getLicitante() { return licitante; }
    public BigDecimal getValor() { return valor; }
    public LocalDateTime getDataHora() { return dataHora; }
}
