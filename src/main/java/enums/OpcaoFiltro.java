package enums;

import java.util.Arrays;
import java.util.List;
import servicos.interfaces.TemId;

public enum OpcaoFiltro implements TemId {

    NOME(1), ID(2), PRECO(3);

    private int id;

    private OpcaoFiltro(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static List<OpcaoFiltro> getElementos() {
        return Arrays.asList(OpcaoFiltro.values());
    }
}
