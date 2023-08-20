
package services;

import entities.Produto;
import enums.OpcaoExibicao;
import java.util.Comparator;

public class ComparatorService implements Comparator<Produto> {
    
    private OpcaoExibicao opcExibicao;
    private OpcaoExibicao formaOrdenacao;

    public ComparatorService(OpcaoExibicao opcExibicao, OpcaoExibicao formaOrdenacao) {
        this.opcExibicao = opcExibicao;
        this.formaOrdenacao = formaOrdenacao;
    }
    
    
    @Override
    public int compare(Produto produto1, Produto produto2) {
        int comparacao = tipoComparacao(produto1, produto2);
        
        //Caso decrescente, comparação negativa para ordenar ao "contrário"
        if (formaOrdenacao == OpcaoExibicao.DECRESCENTE){
            return  -comparacao;
        }
        
        return comparacao; 
    }
    
    private int tipoComparacao(Produto produto1, Produto produto2) {
        
        int comparacao = 0;

        switch (opcExibicao) {
            case NOME:
                comparacao = produto1.getNome().compareTo(produto2.getNome());
                break;
            case PRECO:
                comparacao = produto1.getPreco().compareTo(produto2.getPreco());
                break;
            case CATEGORIA:
                comparacao = produto1.getCategoria().compareTo(produto2.getCategoria());
                break;
            case QUANTIDADE:
                comparacao = Integer.compare(produto1.getQuantidade(), produto2.getQuantidade());
                break;
            case DATA_CRIACAO:
                comparacao = produto1.getDataCriacao().compareTo(produto2.getDataCriacao());
                break;
            case DATA_ATUALIZACAO:
                comparacao = produto1.getDataUltimaAtualizacao().compareTo(produto2.getDataUltimaAtualizacao());
                break;
        }

        
        return comparacao;
    }
    
}
