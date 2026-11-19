package ambiente;

import provedor.ProvedorFactory;

public final class Solicitacao {

    private final String nomeAmbiente;
    private final ProvedorFactory provedor;
    private final int quantidadeMaquinas;
    private final int gbArmazenamento;

    public Solicitacao(String nomeAmbiente, ProvedorFactory provedor,
                       int quantidadeMaquinas, int gbArmazenamento) {
        if (quantidadeMaquinas < 0 || gbArmazenamento < 0) {
            throw new IllegalArgumentException("Quantidades não podem ser negativas.");
        }
        this.nomeAmbiente = nomeAmbiente;
        this.provedor = provedor;
        this.quantidadeMaquinas = quantidadeMaquinas;
        this.gbArmazenamento = gbArmazenamento;
    }

    public String getNomeAmbiente() { return nomeAmbiente; }
    public ProvedorFactory getProvedor() { return provedor; }
    public int getQuantidadeMaquinas() { return quantidadeMaquinas; }
    public int getGbArmazenamento() { return gbArmazenamento; }
}
