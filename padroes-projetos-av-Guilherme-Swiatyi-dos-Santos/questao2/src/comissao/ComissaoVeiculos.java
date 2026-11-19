package comissao;

import java.math.BigDecimal;
import util.Dinheiro;

public class ComissaoVeiculos implements RegraComissao {

    private static final BigDecimal TETO = new BigDecimal("5000.00");

    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        BigDecimal comissao = Dinheiro.arredondar(valorVenda.multiply(new BigDecimal("0.08")));
        return comissao.min(TETO);
    }

    @Override
    public String getDescricao() { return "Veículos: 8% do valor, limitada a R$ 5.000,00"; }
}
