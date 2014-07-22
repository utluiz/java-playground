package br.com.starcode.genericarray;

import java.util.ArrayList;
import java.util.List;

public class TestGenericArray {

    public static void main(String[] args) {
        List<double[]> l = new ArrayList<>();
        l.add(new double[] {1.1, 2.2});
        System.out.println(l.get(0)[0]);
        
    }
    
}
