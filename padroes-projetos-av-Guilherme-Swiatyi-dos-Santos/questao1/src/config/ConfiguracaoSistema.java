package config;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class ConfiguracaoSistema {

    private static final String ARQUIVO = "config.properties";

    private final String regiaoPadrao;
    private final BigDecimal orcamentoMensalMaximo;

    private ConfiguracaoSistema() {
        Properties props = new Properties();
        Path caminho = localizarArquivo();
        try (InputStream in = Files.newInputStream(caminho)) {
            props.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Não foi possível ler " + caminho, e);
        }
        this.regiaoPadrao = obrigatoria(props, "regiao.padrao");
        this.orcamentoMensalMaximo = new BigDecimal(obrigatoria(props, "orcamento.mensal.maximo"));
        System.out.println("[Config] " + caminho.toAbsolutePath() + " lido (única leitura).");
    }

    private static final class Holder {
        private static final ConfiguracaoSistema INSTANCIA = new ConfiguracaoSistema();
    }

    public static ConfiguracaoSistema getInstancia() {
        return Holder.INSTANCIA;
    }

    public String getRegiaoPadrao() {
        return regiaoPadrao;
    }

    public BigDecimal getOrcamentoMensalMaximo() {
        return orcamentoMensalMaximo;
    }

    private static Path localizarArquivo() {
        Path atual = Paths.get(ARQUIVO);
        if (Files.exists(atual)) {
            return atual;
        }
        return Paths.get("questao1", ARQUIVO);
    }

    private static String obrigatoria(Properties props, String chave) {
        String valor = props.getProperty(chave);
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalStateException("Propriedade obrigatória ausente: " + chave);
        }
        return valor.trim();
    }
}
