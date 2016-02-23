import java.util.HashMap;
import java.util.Map;

public class EnumExample {

    public static void main(String[] args) {


    new ProcessaAlgoComGenero().processaGenero(ProcessaAlgoComGenero.Genero.MASCULINO);



    }

    public enum Genero
    {
        MASCULINO(1), FEMININO(2);
        int codigo;

    static Map<Integer, Genero> cache = new HashMap<>();
    static {
        for (Genero g : values()) {
            cache.put(g.codigo, g);
        }
    }
    Genero(int codigo)
    {
        this.codigo = codigo;
    }

    public Genero ofCode(int codigo) {
        return cache.get(codigo);
    }

    public Genero ofCode2(int codigo) {
        if (MASCULINO.codigo == codigo) return MASCULINO;
        if (FEMININO.codigo == codigo) return FEMININO;
        return null; //ou lança uma exceção
    }
    }

    static public class ProcessaAlgoComGenero {

        public enum Genero
        {
            MASCULINO(1), FEMININO(2);
            int codigo;

            Genero(int codigo)
            {
                this.codigo = codigo;
            }

            public Genero byCode(int codigo) {
                for (Genero g : values()) {
                    if (g.codigo == codigo) {
                        return g;
                    }
                }
                return null; //ou lança uma exceção
            }
        }

        public void processaGenero(Genero g) {
            System.out.println(g.codigo);
        }
    }

    static public class EnumGenero {
        public static final EnumGenero MASCULINO = new EnumGenero(1), FEMININO = new EnumGenero(2);
        public int codigo;

        private EnumGenero(int codigo)
        {
            this.codigo = codigo;
        }
    }

}
