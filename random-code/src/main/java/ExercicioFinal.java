import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ExercicioFinal {

    private final static List<Soma> listaSoma = new ArrayList<Soma>(0);
    private static final int LIMITE = 10;

    public static void main(final String[] args) throws InterruptedException {
        for (int i = 0; i < LIMITE; i++) {
            listaSoma.add(new Soma(10));
        }

        int i = 0;
        for (final Soma soma : listaSoma) {
            Thread t = new Thread(soma, "T" + i++);
            t.start();
        }

        exibirResultado();
    }

    private static void exibirResultado() throws InterruptedException {
        for (final Soma s : listaSoma) {
            synchronized (s) {
                if (!s.foiExecutado()) {
                    s.wait();
                }
            }
        }
        Soma.exibe();
    }
}

class Soma implements Runnable {
    private static int resultado;
    private static AtomicInteger res = new AtomicInteger();
    private final int valor;
    private boolean executou = false;

    public Soma(final int valor) {
        this.valor = valor;
            //System.out.println("executou " + executou);
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                Thread.sleep(1);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (Soma.class) {
                resultado += valor;
            }
            res.addAndGet(valor);
            executou = true;
            //System.out.println("executou " + executou);

            this.notify();
        }
    }

    public boolean foiExecutado() {
        return executou;
    }

    public static void exibe() {
        System.out.println(res.get());
        System.out.println(resultado);
    }
}