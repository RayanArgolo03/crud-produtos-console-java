package servicos;

import controle.ProdutoController;
import entidades.Produto;
import enums.Categoria;
import enums.OpcoesCrud;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import servicos.exceptions.ServicoException;
import servicos.interfaces.TemId;

public class CrudProdutoService implements CrudService {
    
    private static Scanner sc = new Scanner(System.in);

    //Instância única
    private static CrudProdutoService instancia;

    //Controller das ações
    private ProdutoController pc = ProdutoController.getInstance();

    private CrudProdutoService() {
    }

    public static CrudProdutoService getInstance() {

        if (instancia == null) {
            instancia = new CrudProdutoService();
        }

        return instancia;
    }

    public ProdutoController getPc() {
        return pc;
    }

    @Override
    public void gerenciarOpcao(int opc) {

        List<OpcoesCrud> opcoesCrud = OpcoesCrud.getOpcoesCrud();

        OpcoesCrud opcaoEscolhida = null;
        for (OpcoesCrud opcao : opcoesCrud) {

            if (opcao.getId() == opc) {
                opcaoEscolhida = opcao;
                break;
            }
        }

        switch (opcaoEscolhida) {
            
            case CRIAR:
                
                Produto produto = criarProduto();
                pc.criarRegistro(produto);
                
                break;
            case BUSCAR:

                break;
            case ALTERAR:

                break;
            case DELETAR:

                break;
            case EXIBIR:

                break;
        }

    }
    
    
    private Produto criarProduto (){
        
        System.out.println();
        System.out.println("Criando produto...");
        
        String nome = lerOpcString ("nome");
        
        //Quantidade será incrementada
        if (pc.produtoExiste(nome)) {
            
            if (novoProduto()){
                
                String nomeAnterior = nome;
                
                while (nome.equalsIgnoreCase(nomeAnterior)){
                    
                    System.out.println();
                    nome = lerOpcString("novo nome");
                    if (nome.equalsIgnoreCase(nomeAnterior)) System.out.println("Inválido!");
                }
            }
            
            else {
                
                //Retorna produto já existente
                return new Produto(nome);
            }
            
        }
        
        Categoria categoria = gerarObj ("categoria para o produto",  Categoria.getCategorias());
        BigDecimal preco = lerPreco ();
        
        System.out.println();
        System.out.println(" 1 - Incluir tags no produto");
        System.out.println(" 2 - NÃO incluir tags no produto");
        System.out.print("Sua escolha: ");
        
                
        //Adiciona tags ao produto
        
        int opc = lerOpcaoInt(2);
        List<String> tags = (opc == 1 ) ? lerTags () : new ArrayList<>();
        
        return new Produto(LocalDateTime.now(), LocalDateTime.now(), nome, categoria, preco, tags, 1);
    }
    
    
    private boolean novoProduto() {
        
        System.out.println();
        System.out.println("Produto já existente! ");
        System.out.println("1 - Adicionar mais um deste mesmo produto");
        System.out.println("2 - Adicionar produto diferente");
        System.out.print("Sua escolha: ");
        
        //True se desejar criar novo produto
        return lerOpcaoInt(2) == 2;
        
    }
    
    private List<String> lerTags (){
        
        int count = 1;
        List<String> tags = new ArrayList<>();
            
        char opc = 's';
        while (opc != 'n'){
            
            System.out.println();
            System.out.print("Digite tag " + count++ + " do produto: ");
            
            
            String tag = sc.next();
            
            tags.add(tag);
            System.out.print("Continuar adicionando? ");
            
            opc = sc.next().toLowerCase().charAt(0);
        
        }
        
        return tags;
    }
    
    private BigDecimal lerPreco(){
        
        System.out.println();
        System.out.print("Digite preço do produto: ");
        
        String preco = sc.next();
        
        //Substitui por vírgula
        return precoValido(preco);
    }
    
    private BigDecimal precoValido (String preco){
        if (preco.matches( "[^0-9.,]+")) throw new ServicoException("Preço deve conter apenas números (Reais e centavos)");
        if (preco.contains(",")) preco = preco.replace(",", ".");
        return new BigDecimal(preco);
    }
    
    
    private <T extends TemId> T gerarObj (final String tipoDado, List<T> lista){
        
        System.out.println();
        
        System.out.println("Opções de " +tipoDado+ ":");
        
        for (T t : lista) {
            System.out.println(t.getId() + " - " +t);
        }
        
        System.out.print("Sua escolha: ");
        int opc = lerOpcaoInt(lista.size());
        
        //Retorna objeto do index
        return lista.get(opc - 1);
    }
    
    private String lerOpcString(final String tipoDado) {
        
        System.out.print("Digite " + tipoDado + " do produto: ");
        
        String s = sc.next(); 
        
        //Formatando nome do produto - primeira letra maiúscula
        s = s.substring(0, 1).toUpperCase().concat(s.substring(1).toLowerCase());
        
        if (!soString(s)) throw new ServicoException(tipoDado+ " deve conter apenas caracteres alfabéticos!");
        
        return s;
    }
    
    private int lerOpcaoInt (final int total){
        int opc = Integer.parseInt(sc.next());
        if (!soInt(opc) || opc < 0 || opc > total) throw new ServicoException("Opção inválida!");
        return opc;
    }
    
    //True se só int
    private boolean soInt (final int n){
        return Pattern.compile("[Z0-9]+").matcher(String.valueOf(n)).matches();
    }
    
    //True se só string
    private boolean soString (final String s){
        return Pattern.compile("^[a-zA-ZÀ-ÿ,\". ]+$").matcher(s).matches();
    }

}
