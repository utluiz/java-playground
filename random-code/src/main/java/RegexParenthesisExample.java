import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * http://pt.stackoverflow.com/questions/172741/como-indicar-numa-regex-que-os-s%C3%ADmbolos-e-os-par%C3%AAnteses-s%C3%A3o-uma-das-al
 */
public class RegexParenthesisExample {
    public static void main(String[] args) {
        String s = "Em primeiro lugar, quem é 1º colocado, em segundo quem é segundo (que é Rubinho)";
        Matcher matcher = Pattern.compile("\\(([^()]+)\\)").matcher(s);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        }

        String s2 = "Em primeiro lugar, quem é 1º colocado, em segundo quem é segundo (que é'\" Rubinho')";
        Matcher matcher2 = Pattern.compile("\\(([A-Za-z0-9çãàáâéêíóôõúÂÃÁÀÉÊÍÓÔÕÚÇ\"'!?$%:;,º°ª ]+')\\)").matcher(s2);
        if (matcher2.find()) {
            System.out.println(matcher2.group(1));
        }

        String caracteres = Pattern.quote("A-Za-z0-9çãàáâéêíóôõúÂÃÁÀÉÊÍÓÔÕÚÇ\"'!?$%:;,º°ª ");
        System.out.println(caracteres);
    }
}
