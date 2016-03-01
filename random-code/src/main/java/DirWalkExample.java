import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DirWalkExample {

    public static void main(String[] args) throws IOException {
        //Files.walk(Paths.get("/", "tmp"));

    Files.walk(Paths.get("/tmp"), 1, FileVisitOption.FOLLOW_LINKS)
            .filter(path -> path.toString().endsWith(".log"))
            .forEach(path -> System.out.printf("%s%n", path.toAbsolutePath().toString()));

    System.out.println("*****");
    Files.find(Paths.get("/tmp"), 1,
            (path, attributes) -> attributes.isRegularFile() && path.toString().endsWith(".log"),
            FileVisitOption.FOLLOW_LINKS
    ).forEach(path -> System.out.println(path.toAbsolutePath()));




    }

}
