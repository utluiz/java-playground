import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class RedirectSystemOut {


    public static void main(String[] args) {

    PrintStream original = System.out;
    StringBuilder sb = new StringBuilder();
    System.setOut(new PrintStream(new OutputStream() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        @Override
        public void write(int b) throws IOException {
            baos.write(b);
        }
        @Override
        public void flush() throws IOException {
            sb.append(baos.toString());
            baos.reset();
        }
        @Override
        public void close() throws IOException {
            sb.append(baos.toString());
            baos.reset();
        }
    }, true));

    System.out.print("Capture ");
    System.out.println("Isto ");
    System.out.format("Para %s!", "Mim");

    System.setOut(original);
    System.out.format("Resultado capturado:%n%s", sb);

    }
}
