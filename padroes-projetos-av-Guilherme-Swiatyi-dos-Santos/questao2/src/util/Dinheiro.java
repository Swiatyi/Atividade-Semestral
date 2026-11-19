package util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public final class Dinheiro {

    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");

    private Dinheiro() {
    }

    public static BigDecimal arredondar(BigDecimal valor) {
        return valor.setScale(2, RoundingMode.HALF_UP);
    }

    public static String formatar(BigDecimal valor) {
        return String.format(PT_BR, "R$ %,.2f", valor);
    }

    public static BigDecimal de(String valor) {
        return arredondar(new BigDecimal(valor));
    }
}
