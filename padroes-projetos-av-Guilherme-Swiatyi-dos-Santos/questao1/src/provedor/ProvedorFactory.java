package provedor;

public interface ProvedorFactory {

    String getNome();

    MaquinaVirtual criarMaquinaVirtual();

    Armazenamento criarArmazenamento();

    FilaMensagens criarFila();

    Precos getPrecos();
}
