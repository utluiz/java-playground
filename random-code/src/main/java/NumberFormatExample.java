import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberFormatExample {
    static Locale PT_BR = new Locale("pt", "BR");
    static NumberFormat cf = NumberFormat.getCurrencyInstance(PT_BR);
    static NumberFormat nf = NumberFormat.getInstance(PT_BR);

    public static void main(String[] args) throws ParseException {
        System.out.println(cf.format(12123.123));
        System.out.println(nf.format(12123.123));
        System.out.println(nf.parse("12123,123"));
        System.out.println(nf.parse("12123.123"));
    }

}
