package exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solucao {

    public static void main(String[] args) {
        longestSubarray(Arrays.asList(0, 1, 2, 1, 2, 3));
    }

    public static int longestSubarray(List<Integer> arr) {

        List<Integer> subarray = new ArrayList<>();
        Set<Integer> numerosDiferentes = new HashSet<>();

        for (int i = 0; i < arr.size(); i++) {

            int atual = arr.get(i);

            int proximo = 0;
            if (i < arr.size() - 1) {
                proximo = arr.get(i + 1);
            }

            if (((proximo - atual <= 1) || proximo == 0) && atual != 0) {

                subarray.add(atual);

                if (numerosDiferentes.size() != 2 && !numerosDiferentes.contains(atual)) {
                    numerosDiferentes.add(atual);
                }

            }
            
            else {
                if (!subarray.isEmpty()) {
                    subarray.clear();
                }
            }

        }

        return subarray.size();
    }

}
