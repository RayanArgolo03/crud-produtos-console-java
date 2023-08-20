package services;

import entities.Produto;
import java.io.*;
import java.util.List;

public final class WriterService {
    
    //Caminho do arquivo para registros
    public static final String PATH = "C:\\Users\\Acer\\Documents\\NetBeansProjects\\Exercicios\\src\\main\\java\\txt\\produto\\produto.txt";

    //Lê toString do produto e escreve no arquivo
    public static void escreverRegistro(String acao) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true))) {
            writer.newLine();
            writer.write("-------- EVENTO -------");
            writer.newLine();
            writer.write(acao);
            writer.newLine();
            
            System.out.println();
            System.out.println("Feito! Vefifique arquivo produto.txt" );
        } catch (IOException e) {
            System.out.println("Arquivo inexistente! Confira arquivo txt.produto");
        }
    }
    
//Lê toString do produto e escreve no arquivo
    public static void escreverRegistro(String acao, List<? extends Produto> lista){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true))) {
            writer.newLine();
            writer.write("-------- EVENTO -------");
            writer.newLine();
            writer.write(acao);
            writer.newLine();
            
            for (Produto produto : lista) {
                writer.write(produto.toString());
                writer.newLine();
            }
            
            System.out.println();
            System.out.println("Feito! Vefifique arquivo produto.txt" );
        } catch (IOException e) {
            System.out.println("Arquivo inexistente! Confira arquivo txt.produto");
        }
    }

}
