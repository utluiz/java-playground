import java.util.Scanner;

/**
 * http://pt.stackoverflow.com/questions/48846/converter-string-para-inteiro-java
 */
public class ScannerInputExample {

    public static int readInt(final Scanner scanner) {
        for (;;) {
            final String linhaDigitada = scanner.nextLine();
            try {
                final int numeroInteiro = Integer.parseInt(linhaDigitada);
                return numeroInteiro;
            } catch (NumberFormatException e) {
                System.out.println("Número inteiro inválido! Tente novamente.");
            }
        }
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int primeiroNumeroDigitado = scanner.nextInt();
        System.out.format("Primeiro número digitado: %d%n", primeiroNumeroDigitado);
        final int segundoNumeroDigitado = scanner.nextInt();
        System.out.format("Segundo número digitado: %d%n", segundoNumeroDigitado);
        scanner.nextLine();

        final int terceiroNumeroDigitado = readInt(scanner);
        System.out.format("Terceiro número digitado: %d%n", terceiroNumeroDigitado);
    }

}
