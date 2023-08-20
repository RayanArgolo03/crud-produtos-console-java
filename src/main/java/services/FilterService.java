package services;

import entities.Produto;
import enums.*;
import static enums.OpcaoFiltro.PRECO;
import java.math.BigDecimal;
import java.util.List;

public class FilterService {

    private ProdutoService produtoService;

    public FilterService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    protected Produto filtrar(List<? extends Produto> lista, OpcaoFiltro opc) {
        switch (opc) {

            case NOME:
                System.out.println();
                String nome = produtoService.lerOpcString("nome");
                return lista.stream()
                        .filter(produto -> produto.getNome().equalsIgnoreCase(nome))
                        .findFirst()
                        .orElse(null);

            case ID:

                System.out.println();
                System.out.print("ID: ");

                int id = produtoService.lerOpcaoInt(OpcaoFiltro.ID.toString());
                return lista.stream()
                        .filter(produto -> produto.getId() == id)
                        .findFirst()
                        .orElse(null);

            case PRECO:
                System.out.println();
                System.out.print("Preço: ");

                BigDecimal preco = produtoService.lerPreco();
                return lista.stream()
                        .filter(produto -> produto.getPreco().equals(preco))
                        .findFirst()
                        .orElse(null);
        }

        return null;
    }

}
