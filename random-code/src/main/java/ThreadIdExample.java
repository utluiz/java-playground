public class ThreadIdExample {

    // Matriz A
    public static int a[][] = { { 5, 4 }, { 3, 3 }, { 2, 4 } };
    // Matriz B
    public static int b[][] = { { 3, 2 }, { 8, 2 }, { 5, 8 } };

    // Numero de threads
    public static final int NUMERO_DE_THREAD = (a.length * b[0].length);
    // Matriz resultante
    public static int r[][] = new int[3][3];

    public static void main(String[] args) throws InterruptedException {

        int indice_thread = 0;
        Thread[] thread = new Thread[NUMERO_DE_THREAD];
        for (int linha = 0; linha < a.length; linha++) {
            for (int coluna = 0; coluna < b[0].length; coluna++) {
                thread[indice_thread] = new Thread(new GeraMatrizThread(linha,
                        coluna, a, b, r));
                thread[indice_thread].start();
                thread[indice_thread].join();
                ++indice_thread;
            }
        }

        indice_thread = 0;
        for (int linha = 0; linha < a.length; linha++) {
            for (int coluna = 0; coluna < b[0].length; coluna++) {
                System.out.print("[" + r[linha][coluna] + "] ");

                System.out.println(thread[indice_thread].getId());
                ++indice_thread;
            }
            System.out.print("\n");
        }
    }

    private static class GeraMatrizThread implements Runnable {
        public GeraMatrizThread(int linha, int coluna, int[][] a, int[][] b, int[][] r) {
        }

        @Override
        public void run() {

        }
    }
}
