import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordUpperCaseExample {

    public static final Locale pt_BR = Locale.ENGLISH; // new Locale("pt","BR");

    public static void main(String[] args) {
        String[] examples = { "douglas léonardo.smc", "áXGR - STS.SMC", "xpto.smc", "aRqUiVo_cOmPoStO.tXt", "aRqUiVo-cOmPoStO.tXt", "123nome.txt" };
        for (String e : examples) {
            System.out.println(upperCaseWords(e));
        }
    }

    public static String upperCaseWords(String phrase) {
        Matcher m = Pattern.compile("\\.?[\\p{IsAlphabetic}][\\w\\d&&[^_]]*", Pattern.UNICODE_CHARACTER_CLASS).matcher(phrase);
        StringBuffer sb  = new StringBuffer();
        while (m.find()) m.appendReplacement(sb, upperCaseFirst(m.group()));
        return m.appendTail(sb).toString();
    }

    public static String upperCaseFirst(String word) {
        return word.isEmpty() ? word :
                word.length() == 1 ? word.toUpperCase(pt_BR) :
                        word.startsWith(".") ? word.toLowerCase(pt_BR) : (word.substring(0, 1).toUpperCase(pt_BR) + word.substring(1).toLowerCase(pt_BR));
    }

}
