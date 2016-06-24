import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * http://pt.stackoverflow.com/questions/136598/modificar-elementos-do-array
 */
public class ConcatQueries {

    public static void main(String[] args) {
        int chave = 999;
        String[] arrayOriginal = {"10", "20", "30", "40"};
        String resultado = Arrays.stream(arrayOriginal).map(v -> String.format("(%d,'%s')", chave, v)).collect(Collectors.joining(" "));
        System.out.println(resultado);
    }
}
