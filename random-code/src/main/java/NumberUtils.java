
public class NumberUtils {

    public static boolean isNumber(char character) {
        return character >= '0' && character <= '9';
    }
    
    public static boolean startsWithNumber(String s) {
        return s != null && s.matches("^\\d.*$");
    }

    public static void main(String[] args) {
        System.out.println(startsWithNumber("a"));
        System.out.println(startsWithNumber("0"));
        System.out.println(startsWithNumber("1"));
        System.out.println(startsWithNumber("8"));
        System.out.println(startsWithNumber("9"));
        System.out.println(startsWithNumber(" "));
        System.out.println(startsWithNumber(""));
        System.out.println(startsWithNumber("ab"));
        System.out.println(startsWithNumber("0b"));
        System.out.println(startsWithNumber("1b"));
        System.out.println(startsWithNumber("8b"));
        System.out.println(startsWithNumber("9b"));
        System.out.println(startsWithNumber(" b"));
        System.out.println(startsWithNumber(""));
        System.out.println(startsWithNumber(null));
    }

}
