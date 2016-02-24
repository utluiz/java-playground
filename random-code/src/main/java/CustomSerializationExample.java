import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * http://pt.stackoverflow.com/questions/114769/separa%C3%A7%C3%A3o-de-bytes
 */
public class CustomSerializationExample {

    public static void main(String[] args) throws IOException {
        List<PessoaMOD> pessoas = new ArrayList<>();
        pessoas.add(new PessoaMOD(1, "Pessoa 1"));
        pessoas.add(new PessoaMOD(2, "Pessoa 2"));
        pessoas.add(new PessoaMOD(3, "Pessoa 3"));
        pessoas.add(new PessoaMOD(4, "Pessoa 4"));
        pessoas.add(new PessoaMOD(5, "Pessoa 5"));
        pessoas.add(new PessoaMOD(6, "Pessoa 6"));
        pessoas.add(new PessoaMOD(7, "Pessoa 7"));
        pessoas.add(new PessoaMOD(8, "Pessoa 8"));

        byte[] bytes = serializarPessoas(pessoas);
        List<PessoaMOD> novasPessoas = desserializarPessoas(bytes);

        if (!pessoas.equals(novasPessoas)) {
            throw new IOException("O programa não conseguiu reconstruir os dados!");
        }
    }

    public static List<PessoaMOD> desserializarPessoas(byte[] bytes) throws IOException {
        DataInputStream entrada = new DataInputStream(new ByteArrayInputStream(bytes));
        List<PessoaMOD> pessoas = new ArrayList<>();
        int quantidadePessoas = entrada.readInt();
        for (int i = 0; i < quantidadePessoas; i++) {
            int id = entrada.readInt();
            String nome = entrada.readUTF();
            pessoas.add(new PessoaMOD(id, nome));
        }
        return pessoas;
    }

    public static byte[] serializarPessoas(List<PessoaMOD> pessoas) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeInt(pessoas.size()); // tamanho da lista
        for (PessoaMOD p : pessoas) {
            dos.writeInt(p.getId()); // id da pessoa
            dos.writeUTF(p.getNome()); //Nome da pessoa
        }
        return bos.toByteArray();
    }

    private static class PessoaMOD {
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
