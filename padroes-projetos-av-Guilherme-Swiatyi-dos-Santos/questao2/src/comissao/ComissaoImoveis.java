package comissao;

import java.math.BigDecimal;
import util.Dinheiro;

public class ComissaoImoveis implements RegraComissao {

    private static final BigDecimal FIXO = new BigDecimal("1500.00");

    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        return Dinheiro.arredondar(valorVenda.multiply(new BigDecimal("0.03"))).add(FIXO);
    }

    @Override
    public String getDescricao() { return "Imóveis: 3% do valor + R$ 1.500,00 fixos"; }
}
