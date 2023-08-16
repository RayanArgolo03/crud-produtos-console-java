package enums;

import java.util.Arrays;
import java.util.List;
import servicos.interfaces.TemId;

public enum OpcaoCrud implements TemId {

    CRIAR(1), BUSCAR(2), ALTERAR(3), DELETAR(4), EXIBIR(5), SAIR(6);

    private int id;

    private OpcaoCrud(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static List<OpcaoCrud> getElementos() {
        return Arrays.asList(OpcaoCrud.values());

    }

}
