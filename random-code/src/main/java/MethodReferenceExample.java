import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferenceExample {

    public static void medirTempo(Runnable rotinaDemorada, Supplier<Long> timestampSupplier) {
        long inicio = timestampSupplier.get();
        rotinaDemorada.run();
        System.out.format("Tempo: %d ms", timestampSupplier.get() - inicio);
    }

    public static int tamanho(Supplier<Integer> s) {
        return s.get();
    }

    public static int tamanho(Function<String, Integer> f) {
        return f.apply("Hello, Instance Method (2)!");
    }

    public static String newHello(Function<String, String> f) {
        return f.apply("Hello, Constructor!");
    }


    public static void main(String[] args) {

        Runnable rotinaDemorada = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        medirTempo(rotinaDemorada, System::currentTimeMillis);

        String s = "Hello, Instance Method!";
        System.out.println(tamanho(s::length));

        System.out.println(tamanho(String::length));

        int t = tamanho(String::length);

        System.out.println(newHello(String::new));
    }

}
