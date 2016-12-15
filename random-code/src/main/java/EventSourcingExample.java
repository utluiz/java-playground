import java.util.ArrayList;
import java.util.List;

/**
 * http://pt.stackoverflow.com/questions/160295/como-posso-saber-quais-os-objetos-do-arraylist-est%C3%A3o-sendo-modificados-ou-exclu%C3%AD
 */
public class EventSourcingExample {

    static class Pessoa {
        private final String nome;
        private final int idade;

        public Pessoa(String nome, int idade) {
            this.nome = nome;
            this.idade = idade;
        }

        public String getNome() { return nome; }
        public int getIdade() { return idade; }

        @Override
        public String toString() {
            return nome + " / " + idade;
        }
    }

    interface Event {
        void apply(List<Pessoa> lista);
    }

    static class AddPessoaEvent implements Event {
        private final Pessoa p;
        public AddPessoaEvent(Pessoa p) {
            this.p = p;
        }
        @Override
        public void apply(List<Pessoa> lista) {
            System.out.println("* Adicionando " + p);
            lista.add(p);
        }
        @Override
        public String toString() {
            return "Add " + p.getNome();
        }
    }

    static class RemovePessoaEvent implements Event {
        private final int index;
        public RemovePessoaEvent(int index) {
            this.index = index;
        }
        @Override
        public void apply(List<Pessoa> lista) {
            System.out.println("* Removendo " + index);
            lista.remove(index);
        }
        @Override
        public String toString() {
            return "Remove " + index;
        }
    }

    static class AtualizaNomePessoaEvent implements Event {
        private final int index;
        private final String nome;
        AtualizaNomePessoaEvent(int index, String nome) {
            this.index = index;
            this.nome = nome;
        }
        @Override
        public void apply(List<Pessoa> lista) {
            System.out.println("* Atualizando " + index + " com nome " + nome);
            lista.set(index, new Pessoa(nome, lista.get(index).idade));
        }
        @Override
        public String toString() {
            return "Atualiza " + index + " com nome " + nome;
        }
    }

    public static class PessoasManager {
        private final List<Pessoa> listaDePessoas = new ArrayList<>();
        private final List<Event> eventos = new ArrayList<>();
        public void add(Pessoa p) {
            AddPessoaEvent e = new AddPessoaEvent(p);
            eventos.add(e);
            e.apply(listaDePessoas);
        }
        public void remove(int index) {
            RemovePessoaEvent e = new RemovePessoaEvent(index);
            eventos.add(e);
            e.apply(listaDePessoas);
        }
        public void atualizaNome(int index, String nome) {
            AtualizaNomePessoaEvent e = new AtualizaNomePessoaEvent(index, nome);
            eventos.add(e);
            e.apply(listaDePessoas);
        }

        public List<Pessoa> getListaDePessoas() {
            return listaDePessoas;
        }

        public List<Event> getEventos() {
            return eventos;
        }

        public void replay(List<Event> eventos) {
            for (Event e : eventos) {
                this.eventos.add(e);
                e.apply(listaDePessoas);
            }
        }
    }

    public static void main(String[] args) {
        PessoasManager pm = new PessoasManager();
        pm.add(new Pessoa("Joao", 29));
        pm.add(new Pessoa("Ana", 21));
        pm.add(new Pessoa("Maria", 25));

        pm.atualizaNome(0, "Jao Carlos");

        pm.remove(2);
        System.out.println("Pessoas " + pm.getListaDePessoas());
        System.out.println("Eventos " + pm.getEventos());

        PessoasManager pm2 = new PessoasManager();
        pm2.replay(pm.getEventos());
        System.out.println("Pessoas na nova lista " + pm.getListaDePessoas());
        System.out.println("Eventos na nova lista " + pm.getEventos());
    }

}
