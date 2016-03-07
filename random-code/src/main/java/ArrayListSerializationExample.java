import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListSerializationExample {

    public static void main(String[] args) {

    List<Integer> lista = Arrays.asList(1, 2, 3);
    System.out.println(lista);

    String texto = lista.stream().map(String::valueOf).collect(Collectors.joining(","));
    System.out.println(texto);

    List<Integer> novaLista = Arrays.stream(texto.split(",")).map(Integer::valueOf).collect(Collectors.toList());
    System.out.println(novaLista);

    }

}
