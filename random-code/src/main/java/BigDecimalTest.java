import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalTest {
    public static void main(String[] args) {
    BigDecimal R = new BigDecimal("2.791654").setScale(20);
    BigDecimal x = new BigDecimal("3.2").setScale(20);
    BigDecimal w = new BigDecimal("0.00239").setScale(20);
    BigDecimal t = new BigDecimal("365").setScale(20);

    //R.x - w.t
    BigDecimal dividendo = R.multiply(x).subtract(w.multiply(t));

    BigDecimal D = new BigDecimal("0.000399").setScale(20);
    BigDecimal dois = new BigDecimal("2").setScale(20);
    BigDecimal meio = new BigDecimal("0.5").setScale(20);

    //2 . (D.R.t) ^ 1/2
    BigDecimal divisor = new BigDecimal(Math.sqrt(D.multiply(R).multiply(t).doubleValue())).multiply(dois);

    BigDecimal segundoTermo = dividendo.divide(divisor, RoundingMode.HALF_DOWN);
    System.out.println("valor do segundo termo pfv:" + segundoTermo);
    }

}
