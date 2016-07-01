import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * http://pt.stackoverflow.com/questions/137912/switch-case-muito-interessante-em-swift-quais-outras-linguagem-suportam-isso
 */
public class SwitchToFunctional {

    enum LifeStage {
        Infant(2), Child(12), Teenager(19), Adult(39), MiddleAged(60), Elderly(Integer.MAX_VALUE);
        private final int limite;
        LifeStage(int limite) {
            this.limite = limite;
        }
        public boolean match(int age) {
            return age <= limite;
        }
    }

    static class Coord {
        private final int x, y, z;
        private final String desc;

        Coord(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
            desc = x == 0 && y == 0 & z ==0 ? "Origin" :
                    x != 0 && y == 0 & z ==0 ? "On the x-axis." :
                    x == 0 && y != 0 & z ==0 ? "On the y-axis." :
                    x == 0 && y == 0 & z !=0 ? "On the z-axis" :
                    "Somewhere in space";
        }

        public String getDesc() {
            return desc;
        }
    }

    public static void main(String[] args) {
        int age = 33;
        Optional<LifeStage> stage = Arrays.stream(LifeStage.values()).filter(v -> v.match(age)).findFirst();
        System.out.println(stage.get());

        Map<String, String> animalCategories = new HashMap<>();
        String da = "Domestic Animal";
        animalCategories.put("Cat", da);
        animalCategories.put("Dog", da);
        String ntm = "Never touch me";
        animalCategories.put("Lion", ntm);
        animalCategories.put("Leopard", ntm);
        animalCategories.put("Pantera", ntm);

        String dog = "Dog";
        String category = animalCategories.get(dog);
        System.out.println(category);

        Coord c = new Coord(3, 0, 0);
        System.out.println(c.desc);

    }
}
