import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * http://pt.stackoverflow.com/questions/136380/como-verificar-quais-n%C3%BAmeros-s%C3%A3o-primos-numa-sequ%C3%AAncia-gerada-a-partir-de-uma-f%C3%B3
 */
public class StrangePrimeFormulaWithBigInteger {

    public static void main(String[] args) {
        List<BigInteger> primeiraLista = new ArrayList();
        List<BigInteger> segundaLista = new ArrayList();
        BigInteger limit = new BigInteger("100");
        BigInteger n37 = new BigInteger("37");
        BigInteger n8 = new BigInteger("8");
        for (BigInteger a = BigInteger.ONE; a.compareTo(limit) <= 0; a = a.add(BigInteger.ONE)) {
            BigInteger c = a.add(a).add(n37);
            if (ehPrimo(c)) {
                primeiraLista.add(c);
            } else {
                BigInteger d = c.add(n8);
                if (ehPrimo(d)) {
                    segundaLista.add(d);
                }
            }
        }

        System.out.printf("Primeira lista %s%n", primeiraLista);
        System.out.printf("Segunda lista %s%n", segundaLista);
    }

    public static boolean ehPrimo(BigInteger n) {
        BigInteger raiz = sqrt(n);
        for (BigInteger k = new BigInteger("2"); k.compareTo(raiz) <= 0; k = k.add(BigInteger.ONE)) {
            if (n.mod(k).equals(BigInteger.ZERO)) return false;
        }
        return true;
    }

    /**
     * From http://stackoverflow.com/a/16804098/1683070
     */
    public static BigInteger sqrt(BigInteger x) {
        BigInteger div = BigInteger.ZERO.setBit(x.bitLength()/2);
        BigInteger div2 = div;
        // Loop until we hit the same value twice in a row, or wind
        // up alternating.
        for(;;) {
            BigInteger y = div.add(x.divide(div)).shiftRight(1);
            if (y.equals(div) || y.equals(div2))
                return y;
            div2 = div;
            div = y;
        }
    }

}
