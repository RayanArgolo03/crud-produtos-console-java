package enums;

import java.util.Arrays;
import java.util.List;
import servicos.interfaces.TemId;

public enum Categoria implements TemId {

    ELETRONICOS(1), ALIMENTICIOS(2), HIGIENICOS(3);

    private int id;

    private Categoria(int id) {
        this.id = id;
    }

    public static List<Categoria> getCategorias() {
        return Arrays.asList(Categoria.values());
    }

    @Override
    public int getId() {
        return id;
    }
}
