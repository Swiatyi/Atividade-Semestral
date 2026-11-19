package servico;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import leilao.Venda;

public class FaturaBase implements Fatura {

    private final Venda venda;

    public FaturaBase(Venda venda) {
        this.venda = venda;
    }

    @Override
    public String getNomeLote() { return venda.getNomeLote(); }

    @Override
    public BigDecimal getValorVenda() { return venda.getValorVenda(); }

    @Override
    public List<LinhaFatura> getServicos() { return Collections.emptyList(); }

    @Override
    public BigDecimal getTotal() { return venda.getValorVenda(); }
}
