public class ThreadIdExample2 {

    // Matriz A
    public static int a[][] = { { 5, 4 }, { 3, 3 }, { 2, 4 } };
    // Matriz B
    public static int b[][] = { { 3, 2 }, { 8, 2 }, { 5, 8 } };

    // Matriz resultante
    public static int r[][] = new int[3][3];

    public static void main(String[] args) throws InterruptedException {
        Thread[][] thread = new Thread[a.length][b[0].length];
        for (int linha = 0; linha < a.length; linha++) {
            for (int coluna = 0; coluna < b[0].length; coluna++) {
                Thread t = new Thread(new GeraMatrizThread(linha, coluna, a, b, r));
                t.start();
                thread[linha][coluna] = t;
            }
        }

        for (int linha = 0; linha < a.length; linha++) {
            for (int coluna = 0; coluna < b[0].length; coluna++) {
                System.out.print("[" + r[linha][coluna] + "] ");
                thread[linha][coluna].join();
                System.out.println(thread[linha][coluna].getId());
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
