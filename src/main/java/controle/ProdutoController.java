package controle;

import entidades.Produto;
import java.util.ArrayList;
import java.util.List;
import servicos.ProdutoComparator;
import servicos.WriterService;

public class ProdutoController implements Controller<Produto> {

    private static final ProdutoController INSTANCE = new ProdutoController();
    private List<Produto> produtos = new ArrayList<>();

    private ProdutoController() {
    }

    public static ProdutoController getInstance() {
        return INSTANCE;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public void criarRegistro(Produto produto) {
        
        //Caminho TXT
        String caminho = "C:\\Users\\Acer\\Documents\\NetBeansProjects\\Exercicios\\src\\main\\java\\servicos\\txt\\produto\\produto.txt";
        
        if (!produtos.isEmpty() && produtoExiste(produto.getNome())){
            incrementarQtd(produto.getNome());
            WriterService.escreverRegistro("Nova/o " +produto.getNome()+ " adicionado", caminho);
        }
        
        else {
           produtos.add(produto);
           WriterService.escreverRegistro("Produto adicionado!", produto, caminho);
        }
        
        System.out.println();
        System.out.println("Feito! Informações no arquivo produto.txt");
        System.out.println();
    }
    
    
    public boolean produtoExiste(final String s) {
        return produtos.stream()
                .anyMatch(p -> p.getNome().equalsIgnoreCase(s));
    }
    
    ///Aumenta quantidade daquele produto no estoque
    private void incrementarQtd(final String nome) {
        produtos.stream().
                filter(p -> p.getNome().equals(nome))
                .forEach(produto -> produto.incrementarQuantidade());
    }

    
    @Override
    public void buscarRegistro(int id) {
    }

    @Override
    public void alterarRegistro(Produto produtoAntigo, Produto produtoNovo) {
    }

    @Override
    public void deletarRegistro(Produto produto) {
    }

    
    //Chama produto comparator
    @Override
    public void classificarRegistros(int opc) {
        produtos.sort(new ProdutoComparator(opc));
    }
}
