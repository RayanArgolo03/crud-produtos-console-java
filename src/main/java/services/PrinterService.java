
package services;

import entities.Produto;
import java.util.List;

public class PrinterService {
    
    public static void print (List<? extends Produto> lista){
        
        System.out.println("Info dos produtos organizados!");
        
        lista.forEach(produto -> {
            System.out.println(produto.toString());
        });
    }
    
}
