package controle;

import entidades.Produto;
import enums.*;
import java.util.List;
import servicos.*;

public class ProdutoController {
    
    //Caminho do arquivo para registros
    public static final String PATH = "C:\\Users\\Acer\\Documents\\NetBeansProjects\\Exercicios\\src\\main\\java\\servicos\\txt\\produto\\produto.txt";

    private ProdutoService produtoService;
    private static ProdutoController instance;

    private ProdutoController() {
    }

    //Injeção de dependência
    public void setProdutoService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    
    public static ProdutoController getInstance() {
        if (instance == null) {
            instance = new ProdutoController();
        }
        return instance;
    }

    public void gerenciarOpcao(int opc) {

        List<OpcaoCrud> opcoesCrud = OpcaoCrud.getElementos();

        OpcaoCrud opcaoEscolhida = null;
        for (OpcaoCrud opcao : opcoesCrud) {

            if (opcao.getId() == opc) {
                opcaoEscolhida = opcao;
                break;
            }
        }

        switch (opcaoEscolhida) {
            case CRIAR:

                Produto produto = produtoService.criarProduto();
                
                //Produto já existe
                if (produto.getQuantidade() > 1) {
                    WriterService.escreverRegistro("Novo/a " + produto.getNome() + "!", PATH);
                }
                else {
                    produtoService.addProduto(produto);
                    WriterService.escreverRegistro("Produto adicionado!" + produto.toString(), PATH);
                }
                
                
                break;
            case BUSCAR:

                break;
//            case ALTERAR:
//
//                Produto produtoAntigo = produtoService.filtrarProduto();
//                Produto produtoNovo = produtoService.alterarProduto(produtoAntigo);
//                produtoService.alterarRegistro(produtoAntigo, produtoNovo);
//
//                break;
            case DELETAR:

                break;
            case EXIBIR:

                break;
        }

    }
    
    
}
