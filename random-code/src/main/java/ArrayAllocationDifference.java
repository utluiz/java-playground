import java.util.ArrayList;
import java.util.List;


public class ArrayAllocationDifference {

    public static float[][] allocate1() {
        float[][] res = new float[100000][10];
        return res;
    }
    
    public static float[][] allocate2() {
        float[][] res = new float[10][100000];
        return res;
    }
    
    public static void noise(float[][] r) {
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r[i].length; j++) {
                r[i][j] = 10; //(float) Math.random();
            }
        }
    }
    
    public static long measure1() {
        long t1 = System.currentTimeMillis();
        float[][] r = allocate1();
        long t2 = System.currentTimeMillis();
        noise(r);
        return t2 - t1;
    }
    
    public static long measure2() {
        long t1 = System.currentTimeMillis();
        float[][] r = allocate2();
        long t2 = System.currentTimeMillis();
        noise(r);
        return t2 - t1;
    }
    
    public static List<Long> test1(int total) {
        List<Long> measures = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            //System.out.println("*");
            measures.add(measure1());
        }
        return measures;
    }      
    public static List<Long> test2(int total) {
        List<Long> measures = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            measures.add(measure2());
        }
        return measures;
    } 
    public static void main(String[] args) {
        //warm up
        test1(5);
        test2(5);
        test2(5);
        test1(5);
        
        //actual test
        printMeasures(test1(1000));
        System.out.println("-----");
        printMeasures(test2(1000));
    }
    
    public static void printMeasures(List<Long> measures) {
        for (Long time : measures) {
            System.out.println(time);
        }
    }
    
}
