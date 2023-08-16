
package servicos;

import entidades.Produto;
import java.util.Comparator;


public class ProdutoComparator implements Comparator<Produto>{


    private int opcComparacao;

    public ProdutoComparator(int opcComparacao) {
    }
    
    //Implementar lógica de comparação
    @Override
    public int compare(Produto produto1, Produto produto2) {
        return produto1.getPreco().compareTo(produto2.getPreco());
    }
    
}
