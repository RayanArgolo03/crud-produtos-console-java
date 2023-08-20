package enums;

import java.util.Arrays;
import java.util.List;
import interfaces.TemId;

public enum Categoria implements TemId {

    ELETRONICO(1), ALIMENTICIO(2), HIGIENICO(3);

    private int id;

    private Categoria(int id) {
        this.id = id;
    }
    
    @Override
    public int getId() {
        return id;
    }
    
    public static List<Categoria> getElementos() {
        return Arrays.asList(Categoria.values());
    }
}
