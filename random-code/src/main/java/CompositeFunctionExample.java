import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CompositeFunctionExample {

    public static void main(String[] args) {
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa("Dener", 24, "Masculino", "Cruzeiro"));
        pessoas.add(new Pessoa("Janaina", 22, "Feminino", "Campos do Jordão"));
        pessoas.add(new Pessoa("Marciele", 17, "Feminino", "Campos do Jordão"));

//        List<Pessoa> resultadoPesquisa = pessoas
//                .stream()
//                .filter(pessoa -> pessoa.getIdade() >= 18 && pessoa.getGenero().equals("Feminino"))
//                .collect(Collectors.toList());
//        resultadoPesquisa.forEach(p -> System.out.println(p.getNome()));

    Predicate<Pessoa> maior = p -> p.getIdade() > 18;
    Predicate<Pessoa> feminino = p -> p.getGenero().equals("Feminino");

    Predicate<Pessoa> filtroComTudoQueEuQuero = maior.and(feminino);

    List<Pessoa> resultadoPesquisa = pessoas.stream().filter(filtroComTudoQueEuQuero).collect(Collectors.toList());
    resultadoPesquisa.forEach(p -> System.out.println(p.getNome()));


        System.out.println("\nQuantidade de mulheres acima de 18 anos: " + resultadoPesquisa.size());

    }

    public static class Pessoa
    {
        private String nome;
        private int idade;
        private String genero;
        private String cidade;

        public Pessoa(String nome, int idade, String genrero, String cidade)
        {
            this.nome = nome;
            this.idade = idade;
            this.genero = genrero;
            this.cidade = cidade;
        }

        public Pessoa()
        {

        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getIdade() {
            return idade;
        }

        public void setIdade(int idade) {
            this.idade = idade;
        }

        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }

        public String getCidade() {
            return cidade;
        }

        public void setCidade(String cidade) {
            this.cidade = cidade;
        }
    }
}
