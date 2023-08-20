package services;

import entities.Produto;
import enums.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.*;
import exceptions.ServiceException;
import interfaces.TemId;

public class ProdutoService {
    
    private static Scanner sc = new Scanner(System.in);
    private List<Produto> produtos = new ArrayList<>();

    public ProdutoService() {
        produtos.add(new Produto(LocalDateTime.now(), LocalDateTime.now(), "Banana", Categoria.ALIMENTICIO, new BigDecimal("1"), 1));
        produtos.add(new Produto(LocalDateTime.now(), LocalDateTime.now(), "Amora", Categoria.ALIMENTICIO, new BigDecimal("2"), 1));
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public Produto criarProduto() {

        System.out.println();
        System.out.println("Criando produto...");

        String nome = lerOpcString("nome");
        
        //Verifica se é um produto já existente
        Produto produtoRepetido = produtoRepetido(nome);
        
        //Existe produto com esse nome
        if (produtoRepetido != null) {

            if (novoNome()) {

                String nomeAnterior = nome;

                while (nome.equalsIgnoreCase(nomeAnterior)) {
                    
                    nome = lerOpcString("novo nome");
                    boolean teveAlteracao = !nome.equalsIgnoreCase(nomeAnterior);    
                    System.out.println(teveAlteracao ? "Novo Produto!": "Nome não alterado!");    
                }
                
            } else {
                produtoRepetido.incrementarQuantidade();
                return produtoRepetido;
            }

        }
        
        System.out.println("Opções de categoria para o produto: ");
        exibirOpcoes(Categoria.getElementos());
        
        System.out.print("Sua escolha: ");
        int opcCategoria = lerOpcaoInt(Categoria.getElementos().size());
        
        Categoria categoria = Categoria.getElementos().get(opcCategoria - 1);
        BigDecimal preco = lerPreco();

        return new Produto(LocalDateTime.now(), LocalDateTime.now(), nome, categoria, preco, 1);
    }
    
    public Produto filtrarProduto(){
        
        if (produtos.isEmpty()) {
            throw new ServiceException("Sem produtos!");
        }
        
        System.out.println();
        System.out.println("Escolha opção de busca do produto: ");
        exibirOpcoes(OpcaoFiltro.getElementos());
        
        System.out.print("Sua escolha: ");
        int opcFiltro = lerOpcaoInt(OpcaoFiltro.getElementos().size());
        
        String tipoDado = OpcaoFiltro.getElementos().get(opcFiltro - 1).toString();
        tipoDado = tipoDado.substring(0, 1).toUpperCase().concat(tipoDado.substring(1).toLowerCase());
        
        FilterService filtro = new FilterService(this);
        Produto produto = filtro.filtrar(produtos, OpcaoFiltro.getElementos().get(opcFiltro - 1));
        
        if (produto == null){
             throw new ServiceException("Não existe produto com esse " +tipoDado+ "!");
        }
        
        return produto;
        
    }
    
    public Produto alterarProduto(Produto produto) {

        //Opções de alteração sem o ID
        List<OpcaoFiltro> lista = new ArrayList<>(OpcaoFiltro.getElementos());
        lista.remove(OpcaoFiltro.ID);

        Produto produtoNovo = null;
        
        System.out.println();
        System.out.println("Escolha atributo a ser alterado");

        exibirOpcoes(lista);
        System.out.print("Sua escolha: ");

        if (lerOpcaoInt(lista.size()) == OpcaoFiltro.NOME.getId()) {
            String nome = lerOpcString("nome");
            produtoNovo = novoProdutoAlterado(produto, nome);
        }
        else {
            BigDecimal preco = lerPreco();
            produtoNovo = novoProdutoAlterado(produto, preco);
        }

        return produtoNovo;
    }
    
    private Produto novoProdutoAlterado (Produto produtoAntigo, String nome){
        return new Produto(produtoAntigo.getDataCriacao()
                , LocalDateTime.now(), 
                nome, produtoAntigo.getCategoria(),
                produtoAntigo.getPreco(),
                produtoAntigo.novaVersao());
    }
    
    private Produto novoProdutoAlterado (Produto produtoAntigo, BigDecimal preco){
        return new Produto(produtoAntigo.getDataCriacao()
                , LocalDateTime.now(), 
                produtoAntigo.getNome(), produtoAntigo.getCategoria(),
                preco,
                produtoAntigo.novaVersao());
    }
    
    
    public OpcaoExibicao lerOpcExibicao (){
        
        //Se vazia ou tiver um produto não há comparação possível.
        if (produtos.isEmpty() || produtos.size() == 1){
            throw new ServiceException("Não é possível realizar essa ação!");
        } 
        
        System.out.println();
        System.out.println("Como deseja organizar os produtos? ");
        
        List<String> listaOpcoes = formatarOpcoes(OpcaoExibicao.getElementos());
        
        exibirOpcoes(listaOpcoes);
        System.out.print("Sua escolha: ");
        
        int opc = lerOpcaoInt(6);
        return OpcaoExibicao.getElementos().get(opc - 1);
    }
    
     private List<String> formatarOpcoes(List<? extends TemId> listaEnums){
        
        List<String> opcoesFormat = new ArrayList<>();
        for (TemId t : listaEnums) {
            
            String s = t.toString();
            if (s.contains("_")){
                s = s.replace("_", " ");
            } 
            
            opcoesFormat.add("Por " + s);
        }
        
        return opcoesFormat;
    }
    
   
    
    public OpcaoExibicao lerOpcOrdenacao (){
        
        System.out.println();
        
        System.out.println("Escolha ordem: ");
        exibirOpcoes(OpcaoExibicao.getOrdenacoes());
        
        //True se crescente
        int opc = lerOpcaoInt(2);
        return OpcaoExibicao.getOrdenacoes().get(opc - 1);
    }
    

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }
    
    //Sobrecarga para alteração de produto
    public void addProduto(Produto produtoAntigo, Produto produtoNovo) {
        produtos.set(produtos.indexOf(produtoAntigo), produtoNovo);
    }
    
    public void removerProduto (Produto produto){
        produtos.remove(produto);
    }
    
    protected BigDecimal lerPreco() {

        System.out.println();
        System.out.print("Digite preço do produto: ");

        String preco = sc.next();

        //Verifica validez do preço digitado
        return precoValido(preco);
    }
    
    protected String lerOpcString(String tipoDado){
        
        System.out.println();
        System.out.print("Digite " + tipoDado + " do produto: ");

        String s = sc.next();

        //Formatando nome do produto - primeira letra maiúscula
        s = s.substring(0, 1).toUpperCase().concat(s.substring(1).toLowerCase());

        if (!soString(s)) {
            throw new ServiceException(tipoDado + " deve conter apenas caracteres alfabéticos!");
        }

        return s;
    }

    private boolean novoNome() {

        System.out.println();
        System.out.println("Produto já existente! ");

        exibirOpcoes(Arrays.asList("Adicionar mais um deste mesmo produto", "Adicionar produto diferente"));
        System.out.print("Sua escolha: ");

        //True se desejar criar novo produto
        return lerOpcaoInt(2) == 2;

    }
    
    private <String> void exibirOpcoes(List<String> lista) {

        int count = 1;


        for (String s : lista) {
            System.out.println(count + " - " + s.toString());
            count++;
        }

    }
    
    
    public Produto produtoRepetido(String nome) { 
        
        Produto produto = null;
        
        if (!produtos.isEmpty()){
            
            produto = produtos.stream()
                    .filter(p -> p.getNome().equalsIgnoreCase(nome))
                    .findFirst()
                    .orElse (null);
        }
        
        return produto;
    }
    
    protected int lerOpcaoInt(int total) {
        int opc = Integer.parseInt(sc.next());
        if (opc < 0 || opc > total) {
            throw new ServiceException("Opção inválida!");
        }
        return opc;
    }
    
    //Sobrecarga para leitura de dados sem total
    protected int lerOpcaoInt(String tipoDado) {
        int opc = Integer.parseInt(sc.next());
        if (opc < 0) {
            throw new ServiceException("Valor do " +tipoDado+ "inválido!");
        }
        return opc;
    }

    
    //True se só string
    private boolean soString(String s) {
        return Pattern.compile("^[a-zA-ZÀ-ÿ,\". ]+$").matcher(s).matches();
    }

    
     private BigDecimal precoValido(String preco) {
        if (preco.matches("[^0-9.,]+")) {
            throw new ServiceException("Preço deve conter apenas números (Reais e centavos)");
        }
        if (preco.contains(",")) {
            preco = preco.replace(",", ".");
        }
        return new BigDecimal(preco);
    }
}
