import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * http://pt.stackoverflow.com/questions/136147/localizar-e-abrir-arquivo-txt-acrescentar-informa%C3%A7%C3%B5es-e-salvar-em-java
 */
public class AppendToFileExample {

    public static void main(String[] args) throws IOException {
        Path arquivo = Files.createTempFile("pre", ".tmp");

        String content1 = "Content1\n";
        String content2 = "Content2\n";

        Files.write(arquivo, content1.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        Files.write(arquivo, content2.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

        String finalContent = new String(Files.readAllBytes(arquivo), StandardCharsets.UTF_8);
        System.out.println(finalContent);
    }
}
