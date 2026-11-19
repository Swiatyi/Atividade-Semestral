package provedor.azure;

import java.util.concurrent.atomic.AtomicInteger;
import provedor.Armazenamento;
import provedor.FilaMensagens;
import provedor.MaquinaVirtual;
import provedor.Precos;
import provedor.ProvedorFactory;

public class AzureFactory implements ProvedorFactory {

    private final AtomicInteger sequenciaMaquinas = new AtomicInteger();
    private final AtomicInteger sequenciaArmazenamentos = new AtomicInteger();
    private final AtomicInteger sequenciaFilas = new AtomicInteger();

    @Override
    public String getNome() { return "Azure"; }

    @Override
    public MaquinaVirtual criarMaquinaVirtual() {
        return new AzureMaquinaVirtual(sequenciaMaquinas.incrementAndGet());
    }

    @Override
    public Armazenamento criarArmazenamento() {
        return new AzureArmazenamento(sequenciaArmazenamentos.incrementAndGet());
    }

    @Override
    public FilaMensagens criarFila() {
        return new AzureFila(sequenciaFilas.incrementAndGet());
    }

    @Override
    public Precos getPrecos() {
        return new Precos(AzureMaquinaVirtual.CUSTO_POR_HORA,
                AzureArmazenamento.CUSTO_POR_GB_MES,
                AzureFila.CUSTO_MENSAL);
    }
}
