import java.util.stream.IntStream;

public class PowExample {

    public static int power(int n, int p) {
        assert n >= 0 && p >= 0;
        if (n < 0 || p < 0) {
            throw new IllegalArgumentException("n ou p");
        }
        if (n < 0 || p < 0) {
            throw new ArithmeticException("n ou p");
        }
        return (int) Math.pow(n, p);
    }

    public static int power2(int n, int p) {
        int r = 1;
        for (int i = 0; i < p; i++) {
            r *= n;
        }
        return r;
    }
    public static int power3(int n, int p) {
        return IntStream.range(0, p).reduce(1, (a, b) -> a * n);
    }

    public static double power4(double n, double p) {
        return Math.pow(n, p);
    }


    public static void main(String[] args) {
        System.out.println(power4(10,0));
        System.out.println(power4(10,1));
        System.out.println(power4(10,2));
        System.out.println(power4(-1, 3));
    }

}
