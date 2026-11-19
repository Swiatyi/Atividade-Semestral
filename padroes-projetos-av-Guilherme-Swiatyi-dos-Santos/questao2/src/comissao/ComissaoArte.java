package comissao;

import java.math.BigDecimal;
import util.Dinheiro;

public class ComissaoArte implements RegraComissao {

    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        return Dinheiro.arredondar(valorVenda.multiply(new BigDecimal("0.20")));
    }

    @Override
    public String getDescricao() { return "Arte: 20% do valor"; }
}
