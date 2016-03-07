import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class StreamReduceExample {

    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3);
        int r = numeros.stream().mapToInt(Integer::intValue).sum();
        System.out.println(r);

        int[] numerosArray = new int[] { 1, 2, 3 };
        System.out.println(IntStream.of(numerosArray).sum());
    }
}
