public class PermitationsExample {

    public static void main(String[] args) {
        int[] conjunto = {0, 0, 0};
        permutar(conjunto, 0);
    }

    public static void permutar(int[] conjunto, int p) {
        if (p == 3) {
            System.out.printf("{%d, %d, %d}%n", conjunto[0], conjunto[1], conjunto[2]);
        } else {
            do {
                permutar(conjunto, p + 1);
                conjunto[p]++;
            } while (conjunto[p] < 3);
            conjunto[p] = 0;
        }
    }

}
