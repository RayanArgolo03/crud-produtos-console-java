package controllers;

import services.WriterService;
import services.ProdutoService;
import entities.Produto;
import enums.*;
import java.util.ArrayList;
import java.util.List;
import services.ComparatorService;
import services.PrinterService;

public class ProdutoController {

    private ProdutoService produtoService;
    private static ProdutoController instance;

    private ProdutoController() {
    }

    public void setProdutoService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    
    public static ProdutoController getInstance() {
        if (instance == null) instance = new ProdutoController();
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
                
                //Produto já existe incrementa quantidade
                if (produto.getQuantidade() > 1) {
                    WriterService.escreverRegistro("Novo produto " + produto.getNome() + " adicionado!");
                }
                else {
                    produtoService.addProduto(produto);
                    WriterService.escreverRegistro("Produto adicionado!" + produto.toString());
                }
                
                break;
            case BUSCAR:
                
                Produto produtoFiltrado = produtoService.filtrarProduto();
                WriterService.escreverRegistro("Informações do produto buscado: " +produtoFiltrado.toString());

                break;
            case ALTERAR:
                Produto produtoAntigo = produtoService.filtrarProduto();
                Produto produtoNovo = produtoService.alterarProduto(produtoAntigo);
                produtoService.addProduto(produtoAntigo, produtoNovo);
                
                if (mudouNome(produtoAntigo, produtoNovo)){
                    WriterService.escreverRegistro("Alterado! "+produtoAntigo.getNome()+ " para " +produtoNovo.getNome());
                } 
                else {
                    WriterService.escreverRegistro("Alterado! " +produtoNovo.getNome()+  " agora custa " +produtoNovo.getPreco() );
                }
                
                break;
            case DELETAR:
                
                Produto produtoDeletado = produtoService.filtrarProduto();
                produtoService.removerProduto(produtoDeletado);
                WriterService.escreverRegistro("Produto " +produtoDeletado.getNome() +" excluído!");

                break;
            case EXIBIR:
                
                OpcaoExibicao lerOpcExibicao = produtoService.lerOpcExibicao();
                OpcaoExibicao formaOrdenacao = produtoService.lerOpcOrdenacao();
                
                List<Produto> produtosSort = new ArrayList<>(produtoService.getProdutos());
                produtosSort.sort(new ComparatorService(lerOpcExibicao, formaOrdenacao));
                
                PrinterService.print (produtosSort);
                
                String acao = String.format("Lista organizada por %s de forma %s", lerOpcExibicao.toString(), formaOrdenacao.toString());
                WriterService.escreverRegistro(acao, produtosSort);
                
                break;
        }

    }
    
    private boolean mudouNome (Produto produtoAntigo, Produto produtoNovo){
        return !produtoNovo.getNome().equalsIgnoreCase(produtoAntigo.getNome());
    }
    
}
