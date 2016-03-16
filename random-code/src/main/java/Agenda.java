import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

public class Agenda {
    //Define um vetor de objetos do tipo contato
    private Contato[] lstContatos;
    private int qtd;

    public void inicializar(){
        lstContatos = new Contato[10];
        qtd = 0;
    }

    public boolean inserir(Contato novoContato){
        // Verificar se não está cheio

        if (qtd>=10){ //se está cheio
            return false; //falhou
        }

        //atribuir o novoContato em uma posição livre
        else{
            lstContatos[qtd]= novoContato;
            qtd++;
            return true;
        }
    }

    public boolean remover(Contato contato){
        return false;
    }

    public Contato[] pesquisarNomesParecidos(String nome) {
        return Arrays.stream(lstContatos, 0, qtd)
                .filter(c -> c.getNome().matches("(?i:.*" + Pattern.quote(nome) + ".*)"))
                .toArray(Contato[]::new);
    }

    public Contato[] pesquisarNomesIguais(String nome) {
        return Arrays.stream(lstContatos, 0, qtd)
                .filter(c -> c.getNome().equalsIgnoreCase(nome))
                .toArray(Contato[]::new);
    }

    public Optional<Contato> encontrarNomeIgual(String nome) {
        return Arrays.stream(lstContatos, 0, qtd)
                .filter(c -> c.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public Contato encontrarNomeIgualOuRetornaNulo(String nome) {
        return Arrays.stream(lstContatos, 0, qtd)
                .filter(c -> c.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public Contato pesquisarUmExatoOuNuloSimples(String nome) {
        for (int i = 0; i <= qtd; i++) {
            if (nome.equalsIgnoreCase(lstContatos[i].getNome())) {
                return lstContatos[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        agenda.inicializar();
        agenda.inserir(new Contato("fer"));
        agenda.inserir(new Contato("fernanda"));

        System.out.println(agenda.pesquisarNomesParecidos("x").length);
        System.out.println(agenda.pesquisarNomesParecidos("er").length);
        System.out.println(agenda.pesquisarNomesParecidos("ern").length);

    Optional<Contato> contato = agenda.encontrarNomeIgual("luiz");
    if (contato.isPresent()) {
        contato.get().getNome();
    } else {
        //não encontrado
    }

        System.out.println(agenda.pesquisarNomesIguais("fer").length);

        System.out.println(agenda.encontrarNomeIgual("fer").get());
        System.out.println(agenda.encontrarNomeIgual("fernanda").get());

        System.out.println(agenda.encontrarNomeIgualOuRetornaNulo("fer"));
        System.out.println(agenda.encontrarNomeIgualOuRetornaNulo("fernanda"));


        System.out.println(agenda.pesquisarUmExatoOuNuloSimples("fer"));

    }

    private static class Contato {
        private String nome;

        private Contato(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }

        @Override
        public String toString() {
            return "Contato{" +
                    "nome='" + nome + '\'' +
                    '}';
        }
    }
}