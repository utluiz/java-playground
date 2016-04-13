import java.util.regex.Pattern;

public class PasswordRequirements {
    public static void main(String[] args) {
        String regex = "((?=.*\\d)(?=.*[a-zA-Z])(?=.*[@#$%])[a-zA-Z0-9@$$%]{6,20})";
        System.out.println("ipe@1234".matches(regex));
        System.out.println("ipe@1é234".matches(regex));
        System.out.println("i@1".matches(regex));
        System.out.println("ipe1234".matches(regex));
        System.out.println("@1234".matches(regex));
        System.out.println("ipe@".matches(regex));
    }
}
