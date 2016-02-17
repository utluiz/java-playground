import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * TODO: Document this class / interface here
 *
 * @since v7.0
 */
public class LambdaExample {

    public static void main(String[] args) {
        processamentoDemoradoAssimcrono(() -> System.out.println("fui..."));
        System.out.println(facaAlgumaCoisa(Arrays.asList("1", "2", "3"), s -> s + ":sufixo"));
        String data = loadHugeData().orElseGet(() -> loadBackupHugeData());
    }

    static Optional<String> loadHugeData() {
        return Optional.empty();
    }

    static String loadBackupHugeData() {
        return null;
    }

    static void processamentoDemoradoAssimcrono(MeChameQuandoAcabar callback) {
        new Thread() {
            @Override
            public void run() {
                //faz algo muito demorado
                callback.acabei();
            }
        }.start();
    }

    static List<String> facaAlgumaCoisa(List<String> lista, FacaIssoTambem m) {
        List<String> result = new ArrayList<>();
        for (String s : lista) {
            s = "prefixo:" + s;
            s = m.fazerAlgoAMais(s);
            result.add(s);
        }
        return result;
    }

    interface MeChameQuandoAcabar {
        void acabei();
    }

    interface FacaIssoTambem {
        String fazerAlgoAMais(String s);
    }

}
