package entidades;

import enums.Categoria;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Produto {
    
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private static int incrementoId = 1;

    private int id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaAtualizacao;
    private String nome;
    private Categoria categoria;
    private BigDecimal preco;
    private List<String> tags;
    private int versao;
    private int quantidade;

    public Produto(LocalDateTime dataCriacao, LocalDateTime dataUltimaAtualizacao, String nome, Categoria categoria, BigDecimal preco, List<String> tags, int versao) {
        this.id = incrementoId;
        this.dataCriacao = dataCriacao;
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        
        this.tags = (!tags.isEmpty()) ? tags :new ArrayList<>(); 
        this.versao = versao;
        
        //Quantidade é incrementada ao adicionar na lista
        this.quantidade = 1;
    }
    
    
    //Retorna nome do produto para aumentar quantidade
    public Produto (String nome){
        this.nome = nome;
    }
    

    public static int getIncremento() {
        return incrementoId;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public List<String> getTags() {
        return tags;
    }

    public int getVersao() {
        return versao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int incrementarQuantidade() {
        return this.quantidade++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("ID ").append(id).append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Categoria: ").append(categoria.toString()).append("\n");
        sb.append("Preço: ").append(String.format("R$ %.2f", preco)).append("\n");
        
        //Exibindo tags
        if (!tags.isEmpty()) {
            
            String auxTags = "";
            int i = 0;
            for (String tag : tags) {
                
                auxTags += tag;
                if (i != tags.size() - 1) auxTags += ", ";
                i++;
            }
            
            sb.append("Tags: ").append(auxTags);
        }
        
        else {
            sb.append("Sem tags!");
        }
        
        sb.append("\n");
        
        sb.append("Criado em: ").append(dtf.format(dataCriacao)).append("\n");
        sb.append("Última Atualização: ").append(dtf.format(dataUltimaAtualizacao)).append("\n");
        sb.append("Versão do produto: ").append(versao).append("\n");
        sb.append("Quantidade em estoque: ").append(quantidade);
        return sb.toString();
    }

    

}
