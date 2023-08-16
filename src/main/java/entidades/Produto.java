package entidades;

import enums.Categoria;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import servicos.interfaces.TemId;

public class Produto implements TemId{
    
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private static int incrementoId = 1;

    private Integer id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaAtualizacao;
    private String nome;
    private Categoria categoria;
    private BigDecimal preco;
    private Integer versao;
    private Integer quantidade;

    public Produto() {
    }

    public Produto(LocalDateTime dataCriacao, LocalDateTime dataUltimaAtualizacao, String nome, Categoria categoria, BigDecimal preco, Integer versao) {
        this.id = incrementoId;
        this.dataCriacao = dataCriacao;
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.versao = versao;
        
        //Quantidade é incrementada ao adicionar na lista
        this.quantidade = 1;
    }

    public static Integer getIncremento() {
        return incrementoId;
    }

    @Override
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


    public int getVersao() {
        return versao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Integer incrementarQuantidade() {
        return this.quantidade++;
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
