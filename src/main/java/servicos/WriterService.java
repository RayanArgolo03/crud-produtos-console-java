package servicos;

import entidades.Produto;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public final class WriterService {

    //Lê toString do produto e escreve no arquivo
    public static void escreverRegistro(String acao, Produto produto, String caminho) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, true))) {
            writer.newLine();
            writer.write("-------- EVENTO -------");
            writer.newLine();
            writer.write(acao);
            writer.write(produto.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Arquivo inexistente! Confira arquivo txt.produto");
        }
    }

    //Quantidade aumentada
    public static void escreverRegistro(String acao, String caminho) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, true))) {
            writer.newLine();
            writer.write("-------- EVENTO -------");
            writer.newLine();
            writer.write(acao);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Arquivo inexistente! Confira arquivo txt.produto");
        }
    }

    public static void limparLinhas(Scanner sc, int linha) {
        for (int i = 0; i < linha; i++) {
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
        }
    }

}
