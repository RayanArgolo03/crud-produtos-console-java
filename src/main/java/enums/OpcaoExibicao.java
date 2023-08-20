package enums;

import interfaces.TemId;
import java.util.*;

public enum OpcaoExibicao implements TemId {

    NOME(1),
    PRECO(2),
    CATEGORIA(3),
    QUANTIDADE(4),
    DATA_CRIACAO(5),
    DATA_ATUALIZACAO(6),
    CRESCENTE(7),
    DECRESCENTE(8);

    private OpcaoExibicao(int id) {
        this.id = id;
    }

    private int id;

    @Override
    public int getId() {
        return id;
    }

    public static List<OpcaoExibicao> getElementos() {
        return Arrays.asList(OpcaoExibicao.values()).stream()
                .filter(e -> !e.equals(CRESCENTE) && !e.equals(DECRESCENTE))
                .toList();

    }

    public static List<OpcaoExibicao> getOrdenacoes() {
        return Arrays.asList(CRESCENTE, DECRESCENTE);
    }

}
