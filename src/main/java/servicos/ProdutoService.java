package servicos;

import entidades.Produto;
import enums.Categoria;
import enums.OpcaoFiltro;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.*;
import servicos.exceptions.ServicoException;
import servicos.interfaces.TemId;

public class ProdutoService  {
    
    private static Scanner sc = new Scanner(System.in);
    private List<Produto> produtos = new ArrayList<>();

    public ProdutoService() {
    }
    
    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public Produto criarProduto() {

        System.out.println();
        System.out.println("Criando produto...");

        String nome = lerOpcString("nome");
        Produto produtoRepetido = produtoRepetido(nome);
        
        //Existe produto com esse nome
        if (produtoRepetido != null) {

            if (novoNome()) {

                String nomeAnterior = nome;

                while (nome.equalsIgnoreCase(nomeAnterior)) {
                    
                    nome = lerOpcString("novo nome");
                    boolean teveAlteracao = !nome.equalsIgnoreCase(nomeAnterior);
                    
                    System.out.println(teveAlteracao ? "Novo Produto!": "Nome não alterado");
                    
                }
            } else {
                produtoRepetido.incrementarQuantidade();
                return produtoRepetido;
            }

        }
        
        exibirEnums("categoria para o produto", Categoria.getElementos());
        
        System.out.print("Sua escolha: ");
        int opcCategoria = lerOpcaoInt(Categoria.getElementos().size());
        
        Categoria categoria = Categoria.getElementos().get(opcCategoria - 1);
        BigDecimal preco = lerPreco();

        return new Produto(LocalDateTime.now(), LocalDateTime.now(), nome, categoria, preco, 1);
    }
    

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }
    

    private boolean novoNome() {

        System.out.println();
        System.out.println("Produto já existente! ");

        exibirOpcoes(Arrays.asList("Adicionar mais um deste mesmo produto", "Adicionar produto diferente"));
        System.out.print("Sua escolha: ");

        //True se desejar criar novo produto
        return lerOpcaoInt(2) == 2;

    }

//    //Filtra produto a ser alterado
//    public Produto filtrarProduto() {
//
//        if (pc.getProdutos().isEmpty()) {
//            throw new ServicoException("Sem produtos para alteração!");
//        }
//
//        System.out.println();
//        System.out.println("Como deseja filtrar o produto?");
//
//        exibirOpcoes(OpcoesFiltro.getElementos());
//        System.out.print("Sua escolha: ");
//        
//        int opc = lerOpcaoInt(OpcoesFiltro.getElementos().size());
//        
//        //Retorna dado passado da opção escolhida
//        Object dado = lerDadoFiltro (opc);
//
//        Produto produtoAntigo = FilterService.filtrar(pc.getProdutos(), dado);
//        
//        if (produtoAntigo == null) throw new ServicoException("Não existe produto com esse/a " +dado);
//        return produtoAntigo;
//    }
    
    private Object lerDadoFiltro (final int opc){
        
        //Converter opção para Enum pro switch consumir
        OpcaoFiltro of = null;
        for (OpcaoFiltro opcao : OpcaoFiltro.getElementos()) {
            if (opcao.getId() == opc){
                of = opcao;
                break;
            } 
        }
        
        
        Object dado = null;
        
        switch (of) {
            case NOME:
                dado = lerOpcString("nome");
                break;
            case ID:
                dado = lerOpcaoInt("ID");
                break;
            case PRECO:
                dado = lerPreco();
                break;
        }
        
        return dado;
    }
    
    private Produto alterarProduto(Produto produtoAntigo){
        return null;
    };

    private <String> void exibirOpcoes(List<String> lista) {

        int count = 1;
        System.out.println();
        for (String s : lista) {
            System.out.println(count + " - " + s.toString());
            count++;
        }
        
    }

    private BigDecimal lerPreco() {

        System.out.println();
        System.out.print("Digite preço do produto: ");

        String preco = sc.next();

        //Substitui por vírgula
        return precoValido(preco);
    }

    private BigDecimal precoValido(String preco) {
        if (preco.matches("[^0-9.,]+")) {
            throw new ServicoException("Preço deve conter apenas números (Reais e centavos)");
        }
        if (preco.contains(",")) {
            preco = preco.replace(",", ".");
        }
        return new BigDecimal(preco);
    }

   private <T extends TemId> void exibirEnums(final String tipoDado, List<T> lista) {
        System.out.println();

        System.out.println("Opções de " + tipoDado + ":");

        for (T t : lista) {
            System.out.println(t.getId() + " - " + t);
        }
    }

    private String lerOpcString(final String tipoDado){
        
        System.out.println();
        System.out.print("Digite " + tipoDado + " do produto: ");

        String s = sc.next();

        //Formatando nome do produto - primeira letra maiúscula
        s = s.substring(0, 1).toUpperCase().concat(s.substring(1).toLowerCase());

        if (!soString(s)) {
            throw new ServicoException(tipoDado + " deve conter apenas caracteres alfabéticos!");
        }

        return s;
    }

    private int lerOpcaoInt(final int total) {
        int opc = Integer.parseInt(sc.next());
        if (opc < 0 || opc > total) {
            throw new ServicoException("Opção inválida!");
        }
        return opc;
    }
    
    
    //Sobrecarga para leitura em filtragem
    private int lerOpcaoInt (final Object tipoDado){
        
        System.out.println();
        System.out.print("Digite " +tipoDado+ " do produto: ");
        
        int opc = Integer.parseInt(sc.next());
        
        return opc;
    };

    
    //True se só string
    private boolean soString(final String s) {
        return Pattern.compile("^[a-zA-ZÀ-ÿ,\". ]+$").matcher(s).matches();
    }
    
    
    
    public Produto produtoRepetido(final String nome) { 
        
        Produto produto = null;
        
        if (!produtos.isEmpty()){
            
            produto = produtos.stream()
                    .filter(p -> p.getNome().equalsIgnoreCase(nome))
                    .findFirst()
                    .orElse (null);
        }
        
        return produto;
    }
    
    ///Aumenta quantidade daquele produto no estoque
    public void incrementarQtd(final String nome) {
        produtos.stream().
                filter(p -> p.getNome().equals(nome))
                .forEach(p -> p.incrementarQuantidade());
    }

    
  
    public void buscarRegistro(int id) {
    }


    public void alterarRegistro(Produto produtoAntigo, Produto produtoNovo) {
        
        
        
    }


    public void deletarRegistro(Produto produto) {
    }

    
    //Chama produto comparator
 
    public void classificarRegistros(int opc) {
        produtos.sort(new ComparatorService(opc));
    }

}
