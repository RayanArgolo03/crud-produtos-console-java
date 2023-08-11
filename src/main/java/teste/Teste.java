package teste;

import enums.OpcoesCrud;
import java.util.*;
import servicos.*;

public class Teste {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        //Singleton - Produto concreto de interface
        CrudService cs = CrudProdutoService.getInstance();
        
        printTitulo();

        int opc = 0;
        do {

            //IMPLEMENTAR LÓGICA DE ALTERAÇÃO DE PRODUTO
            //IMPLEMENTAR LÓGICA DE ALTERAÇÃO DE PRODUTO
            //IMPLEMENTAR LÓGICA DE ALTERAÇÃO DE PRODUTO
            //IMPLEMENTAR LÓGICA DE ALTERAÇÃO DE PRODUTO
            //IMPLEMENTAR LÓGICA DE ALTERAÇÃO DE PRODUTO
            //IMPLEMENTAR LÓGICA DE ALTERAÇÃO DE PRODUTO
            //IMPLEMENTAR LÓGICA DE ALTERAÇÃO DE PRODUTO
            //IMPLEMENTAR LÓGICA DE ALTERAÇÃO DE PRODUTO
            
            
            //Recebe entidade do crud
            final String TIPO_ENTIDADE = "PRODUTO";

            System.out.println("Escolha sua opção:  ");
            
            System.out.println();
            printOpcoesCrud(TIPO_ENTIDADE);

            System.out.print("Sua escolha: ");

            try {
                opc = Integer.parseInt(sc.next());

                while (opc < 1 || opc > 6) {

                    System.out.println();
                    System.out.println("Inválida!");

                    printOpcoesCrud(TIPO_ENTIDADE);

                    System.out.print("Sua escolha: ");
                    opc = Integer.parseInt(sc.next());

                }

                if (opc == 6) {
                    
                    System.out.println();
                    System.out.println("Obrigado por utilizar!");
                    break;
                    
                }

                cs.gerenciarOpcao(opc);

            } catch (RuntimeException e) {
                System.out.println();
                System.out.println("Erro! " + e.getMessage());
            }

        } while (true);

    }

    private static void printTitulo() {

        System.out.println();

        System.out.println("    ------        ---------       --------"
                + "");
        System.out.println("        ------  CRUD CONSOLE -------    ");
        System.out.println("    ------        ---------       --------"
                + "");

        System.out.println();
    }

    private static void printOpcoesCrud(final String TIPO_ENTIDADE) {

        List<OpcoesCrud> opcoescrud = OpcoesCrud.getOpcoesCrud();

        for (int i = 0; i < opcoescrud.size(); i++) {

            OpcoesCrud opc = opcoescrud.get(i);
            String opcaoStr = String.format("%d%s%s %s", opc.getId(), " - ", opc, TIPO_ENTIDADE);

            //Se for opc exibir, são "produtos"
            if (opc.equals(OpcoesCrud.EXIBIR)) {
                opcaoStr += "S";
            }
            
            System.out.println(opcaoStr);
        }
        
        System.out.println("6 - " +OpcoesCrud.SAIR.toString());

    }

}
