import java.util.ArrayList;
import java.util.List;

public class ArrayClassExample {

    public static void main(String[] args) {

    String[] array = new String[10];
    System.out.println(array instanceof Object);

    List<String> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    System.out.println(list1.getClass().getName());
    System.out.println(list2.getClass().getName());

    System.out.println(list1.getClass().isAssignableFrom(list2.getClass()));

    String[] array1 = new String[10];
    Integer[] array2 = new Integer[10];
    System.out.println(array1.getClass().getName());
    System.out.println(array2.getClass().getName());
    System.out.println(array1.getClass().isAssignableFrom(array2.getClass()));

    String[] array3 = new String[10];
    System.out.println(array1.getClass().isInstance(array3));

    }
}
