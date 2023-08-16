package enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public enum OpcoesCrud {
    
    CRIAR (1), BUSCAR (2), ALTERAR (3), DELETAR (4), EXIBIR (5), SAIR (6);
    
    private int id;

    private OpcoesCrud(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public static List<OpcoesCrud> getOpcoesCrud(){
        List<OpcoesCrud> lista = new ArrayList<>(Arrays.asList(OpcoesCrud.values()));
        lista.remove(SAIR);
        return lista;
    }
}
