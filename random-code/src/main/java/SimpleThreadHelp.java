/**
 * http://pt.stackoverflow.com/questions/170956/retornar-para-main-ap%C3%B3s-usar-m%C3%A9todo-start
 */
public class SimpleThreadHelp {
    private static long startTime = System.currentTimeMillis();

    public static void main(String[] args) throws InterruptedException {
        Esquiador gerenciador = new Esquiador();

        gerenciador.start();
        gerenciador.join();

        System.out.println("acabou");
        long endTime = System.currentTimeMillis();
        System.out.println("It took " + (int) (endTime - startTime) + " milliseconds");
    }
}


class Esquiador extends Thread {
    static boolean todosEntraram = false;

    public Esquiador() {
        super("Esquiador");
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("O esquiador está dormindo.");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Esquiador.todosEntraram = true;
    }
}
