package provedor.aws;

import java.util.concurrent.atomic.AtomicInteger;
import provedor.Armazenamento;
import provedor.FilaMensagens;
import provedor.MaquinaVirtual;
import provedor.Precos;
import provedor.ProvedorFactory;

public class AwsFactory implements ProvedorFactory {

    private final AtomicInteger sequenciaMaquinas = new AtomicInteger();
    private final AtomicInteger sequenciaArmazenamentos = new AtomicInteger();
    private final AtomicInteger sequenciaFilas = new AtomicInteger();

    @Override
    public String getNome() { return "Aws"; }

    @Override
    public MaquinaVirtual criarMaquinaVirtual() {
        return new AwsMaquinaVirtual(sequenciaMaquinas.incrementAndGet());
    }

    @Override
    public Armazenamento criarArmazenamento() {
        return new AwsArmazenamento(sequenciaArmazenamentos.incrementAndGet());
    }

    @Override
    public FilaMensagens criarFila() {
        return new AwsFila(sequenciaFilas.incrementAndGet());
    }

    @Override
    public Precos getPrecos() {
        return new Precos(AwsMaquinaVirtual.CUSTO_POR_HORA,
                AwsArmazenamento.CUSTO_POR_GB_MES,
                AwsFila.CUSTO_MENSAL);
    }
}
