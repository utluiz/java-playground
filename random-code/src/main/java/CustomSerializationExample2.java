import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * http://pt.stackoverflow.com/questions/114769/separa%C3%A7%C3%A3o-de-bytes
 */
public class CustomSerializationExample2 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<PessoaMOD> pessoas = new ArrayList<>();
        pessoas.add(new PessoaMOD(1, "Pessoa 1"));
        pessoas.add(new PessoaMOD(2, "Pessoa 2"));
        pessoas.add(new PessoaMOD(3, "Pessoa 3"));
        pessoas.add(new PessoaMOD(4, "Pessoa 4"));
        pessoas.add(new PessoaMOD(5, "Pessoa 5"));
        pessoas.add(new PessoaMOD(6, "Pessoa 6"));
        pessoas.add(new PessoaMOD(7, "Pessoa 7"));
        pessoas.add(new PessoaMOD(8, "Pessoa 8"));

        Mensagem mensagem = new MensagemGravarPessoa(pessoas);

        byte[] bytes = serializarPessoas(mensagem);
        Mensagem mensagemNova = desserializarPessoas(bytes);

        if (!mensagem.equals(mensagemNova)) {
            throw new IOException("O programa não conseguiu reconstruir os dados!");
        }

    if (mensagem instanceof MensagemGravarPessoa) {
        //gravar a pessoa aqui
    }

    }

    public static Mensagem desserializarPessoas(byte[] bytes) throws IOException, ClassNotFoundException {
        ObjectInputStream entrada = new ObjectInputStream(new ByteArrayInputStream(bytes));
        return (Mensagem) entrada.readObject();
    }

    public static byte[] serializarPessoas(Mensagem p) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(p);
        return bos.toByteArray();
    }

    public enum Metodo {
        GRAVAR
    }

    static public abstract class Mensagem implements Serializable {
        private Metodo metodo;
        private Object conteudo;
        public Mensagem(Metodo metodo, Object conteudo) {
            this.metodo = metodo;
            this.conteudo = conteudo;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Mensagem that = (Mensagem) o;
            return metodo == that.metodo &&
                    this.conteudo.equals(that.conteudo);
        }
        @Override
        public int hashCode() {
            return Objects.hash(metodo, conteudo);
        }
    }

    public static class MensagemGravarPessoa extends Mensagem {
        public MensagemGravarPessoa(List<PessoaMOD> pessoas) {
            super(Metodo.GRAVAR, pessoas);
        }
    }

    public static class PessoaMOD implements Serializable {
        private String nome;
        private int id;
        public PessoaMOD(int id, String nome) {
            this.id = id;
            this.nome = nome;
        }
        public String getNome() {
            return nome;
        }
        public int getId() {
            return id;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PessoaMOD pessoaMOD = (PessoaMOD) o;
            return id == pessoaMOD.id &&
                    Objects.equals(nome, pessoaMOD.nome);
        }
        @Override
        public int hashCode() {
            return Objects.hash(nome, id);
        }
    }
}
