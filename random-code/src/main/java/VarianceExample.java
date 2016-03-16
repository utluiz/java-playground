import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class VarianceExample {

    static class Animal {
        void lavar() {

        }
    }
    static class Gato extends Animal { }
    static class Cachorro extends Animal { }



    public static void main(String[] args) {
    //covariance
    List<Animal> animais = new ArrayList<>();
    List<Gato> gatos = new ArrayList<>();
    List<Cachorro> cachorros = new ArrayList<>();

    animais.addAll(gatos);
    animais.addAll(cachorros);

    //contravariance
    gatos.forEach(Animal::lavar);
    cachorros.forEach(Animal::lavar);

    Collections.copy(animais, gatos);
    Collections.copy(animais, cachorros);



    }
}
