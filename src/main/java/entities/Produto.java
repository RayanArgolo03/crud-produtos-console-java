package entities;

import enums.Categoria;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import interfaces.TemId;

public class Produto implements TemId{
    
    private static int incremento = 1;
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    private int id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaAtualizacao;
    private String nome;
    private Categoria categoria;
    private BigDecimal preco;
    private int versao;
    private int quantidade;

    public Produto() {
    }

    public Produto(LocalDateTime dataCriacao, LocalDateTime dataUltimaAtualizacao, String nome, Categoria categoria, BigDecimal preco, int versao) {
        id = incrementoId();
        this.dataCriacao = dataCriacao;
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.versao = versao;
        this.quantidade = 1;
    }

   
    @Override
    public int getId() {
        return id;
    }
    
    private static int incrementoId(){
        return incremento++;
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

    public int getVersao() {
        return versao;
    }

    public int getQuantidade() {
        return quantidade;
    }
    

    public void incrementarQuantidade() {
        this.quantidade++;
    }
    
    public int novaVersao (){
        return this.versao++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("ID ").append(id).append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Categoria: ").append(categoria.toString()).append("\n");
        sb.append("Preço: ").append(String.format("R$ %.2f", preco)).append("\n");
        sb.append("Criado em: ").append(dtf.format(dataCriacao)).append("\n");
        sb.append("Última Atualização: ").append(dtf.format(dataUltimaAtualizacao)).append("\n");
        sb.append("Versão do produto: ").append(versao).append("\n");
        sb.append("Quantidade em estoque: ").append(quantidade);
        return sb.toString();
    }
    

}
