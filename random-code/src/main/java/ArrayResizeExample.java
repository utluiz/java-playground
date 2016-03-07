import java.util.Arrays;

public class ArrayResizeExample {

    public static void main(String[] args) {
        Test[] array1 = create(10);
        Test[] array2 = create(20);

        Test[] array = new Test[10];
        array[0] = new Test();
        array[0].nome = "Eu";
        System.out.println(array.length);
        array = Arrays.copyOf(array, 20);
        System.out.println(array.length);
        System.out.println(array[0].nome);
    }

    static Test[] create(int size) {
        return new Test[size];
    }

    private static class Test {
        String nome;
    }
}
