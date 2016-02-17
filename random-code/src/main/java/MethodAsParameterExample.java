import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MethodAsParameterExample {

    static class Sorting {

        public static void staticSort(String[] vetor) {
            Arrays.sort(vetor);
        }

        public void sort(String[] vetor) {
            Arrays.sort(vetor);
        }

    }

    public static long executar(Consumer<String[]> metodo, String[] vetor) {
        long initialTime = System.nanoTime();
        metodo.accept(vetor);
        return System.nanoTime() - initialTime;
    }

    public static long executar(Runnable instancia, String[] vetor) {
        long initialTime = System.nanoTime();
        //instancia.run(vetor);
        return System.nanoTime() - initialTime;
    }

    public static long executar(Method metodo, String[] vetor) {
        long initialTime = System.nanoTime();
        try {
            metodo.invoke(null, new Object[] { vetor });
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return System.nanoTime() - initialTime;
    }

    public static void main(String[] args) {
    String[] vetor = { "Maria", "José" };

    executar(Sorting::staticSort, vetor);

    Sorting instancia = new Sorting();
    executar(instancia::sort, vetor);

    Method metodo = null;
    try {
        metodo = Sorting.class.getDeclaredMethod("staticSort", String[].class);
    } catch (NoSuchMethodException e) {
        throw new RuntimeException(e);
    }
    executar(metodo, vetor);
    System.out.println(Arrays.stream(vetor).collect(Collectors.joining(", ")));
    }

}
