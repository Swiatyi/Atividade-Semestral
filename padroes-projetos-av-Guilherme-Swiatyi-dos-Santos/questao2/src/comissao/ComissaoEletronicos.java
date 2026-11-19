package comissao;

import java.math.BigDecimal;
import util.Dinheiro;

public class ComissaoEletronicos implements RegraComissao {

    private static final BigDecimal MINIMO = new BigDecimal("50.00");

    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        BigDecimal comissao = Dinheiro.arredondar(valorVenda.multiply(new BigDecimal("0.12")));
        return comissao.max(MINIMO);
    }

    @Override
    public String getDescricao() { return "Eletrônicos: 12% do valor, mínimo de R$ 50,00"; }
}
