import java.util.ArrayList;
import java.util.List;

/**
 * http://pt.stackoverflow.com/questions/136380/como-verificar-quais-n%C3%BAmeros-s%C3%A3o-primos-numa-sequ%C3%AAncia-gerada-a-partir-de-uma-f%C3%B3
 */
public class StrangePrimeFormula {
    public static void main(String[] args) {
        List<Long> primeiraLista = new ArrayList();
        List<Long> segundaLista = new ArrayList();
        for (long a = 1; a < 100; a++) {
            long c = a + a + 37;
            if (ehPrimo(c)) {
                primeiraLista.add(c);
            } else {
                long d = c + 8;
                if (ehPrimo(d)) {
                    segundaLista.add(d);
                }
            }
        }

        System.out.printf("Primeira lista %s%n", primeiraLista);
        System.out.printf("Segunda lista %s%n", segundaLista);
    }

    public static boolean ehPrimo(long n) {
        long raiz = (long) Math.sqrt(n);
        for (long k = 2; k <= raiz; k++) {
            if (n % k == 0) return false;
        }
        return true;
    }
}
