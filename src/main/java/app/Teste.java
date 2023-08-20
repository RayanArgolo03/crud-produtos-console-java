package app;

import services.ProdutoService;
import controllers.ProdutoController;
import enums.OpcaoCrud;
import java.util.*;

public class Teste {
    
    //TESTAR LÓGICA DE COMPARAÇÃO DE PRODUTO!!!

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        //Singleton e Injeção de Dependência
        ProdutoController produtoController = ProdutoController.getInstance();
        produtoController.setProdutoService(new ProdutoService());

        printTitulo();

        int opc = 0;
        do {
            String entidade = "PRODUTO";
            
            System.out.println();
            System.out.println("Escolha sua opção:  ");

            System.out.println();
            printOpcoesCrud(entidade);

            System.out.print("Sua escolha: ");

            try {
                opc = Integer.parseInt(sc.next());

                while (opc < 1 || opc > 6) {

                    System.out.println();
                    System.out.println("Inválida!");

                    printOpcoesCrud(entidade);

                    System.out.print("Sua escolha: ");
                    opc = Integer.parseInt(sc.next());

                }

                if (opc == 6) {

                    System.out.println();
                    System.out.println("Obrigado por utilizar!");
                    break;

                }

               produtoController.gerenciarOpcao(opc);

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

    private static void printOpcoesCrud(String entidade) {

        List<OpcaoCrud> opcoescrud = OpcaoCrud.getElementos();

        for (int i = 0; i < opcoescrud.size(); i++) {

            OpcaoCrud opc = opcoescrud.get(i);
            
            //Sair não recebe produto, exibir recebe produto + S
            String opcaoStr = (opc.equals(OpcaoCrud.SAIR))
                    ? String.format("%d%s%s", opc.getId(), " - ", opc)
                    : (opc.equals(OpcaoCrud.EXIBIR))
                    ? String.format("%d%s%s %s", opc.getId(), " - ", opc, entidade + "S")
                    : String.format("%d%s%s %s", opc.getId(), " - ", opc, entidade);

            System.out.println(opcaoStr);
        }

    }

}
