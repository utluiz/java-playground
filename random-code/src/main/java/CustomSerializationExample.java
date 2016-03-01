import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * http://pt.stackoverflow.com/questions/114769/separa%C3%A7%C3%A3o-de-bytes
 */
public class CustomSerializationExample {

    public static void main(String[] args) throws IOException {
//        List<PessoaMOD> pessoas = new ArrayList<>();
//        pessoas.add(new PessoaMOD(1, "Pessoa 1"));
//        pessoas.add(new PessoaMOD(2, "Pessoa 2"));
//        pessoas.add(new PessoaMOD(3, "Pessoa 3"));
//        pessoas.add(new PessoaMOD(4, "Pessoa 4"));
//        pessoas.add(new PessoaMOD(5, "Pessoa 5"));
//        pessoas.add(new PessoaMOD(6, "Pessoa 6"));
//        pessoas.add(new PessoaMOD(7, "Pessoa 7"));
//        pessoas.add(new PessoaMOD(8, "Pessoa 8"));
//
//        byte[] bytes = serializarPessoas(pessoas);
//        List<PessoaMOD> novasPessoas = desserializarPessoas(bytes);
//
//        if (!pessoas.equals(novasPessoas)) {
//            throw new IOException("O programa não conseguiu reconstruir os dados!");
//        }


        ServerSocket servidor = new ServerSocket(12345);
        System.out.println("Servidor ouvindo a porta 12345");
        while (true) {
            Socket cliente = servidor.accept();
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());

            int tamanhoMsg = entrada.readInt();
            System.out.println("Tam. Msg.: " + tamanhoMsg);

            byte[] bytes = new byte[tamanhoMsg];
            ByteArrayOutputStream baos = new ByteArrayOutputStream(tamanhoMsg);
            entrada.readFully(bytes);
            System.out.println("Bytes size: " + bytes.length);

            DataInputStream entrada2 = new DataInputStream(new ByteArrayInputStream(bytes));

            int tamanhoLista = entrada2.readInt();

            int id;
            String nome;
            String cpf;
            String rg;
            String endereco;
            String numero;
            String complemento;
            String bairro;
            String cidade;
            String estado;
            String telefone1;
            String telefone2;
            String email;
            char perfil;

            for (int i = 0; i < tamanhoLista; i++) {
                id = entrada2.readInt();
                nome = entrada2.readUTF();
                cpf = entrada2.readUTF();
                rg = entrada2.readUTF();
                endereco = entrada2.readUTF();
                numero = entrada2.readUTF();
                complemento = entrada2.readUTF();
                bairro = entrada2.readUTF();
                cidade = entrada2.readUTF();
                estado = entrada2.readUTF();
                telefone1 = entrada2.readUTF();
                telefone2 = entrada2.readUTF();
                email = entrada2.readUTF();
                perfil = entrada2.readChar();

                System.out.println("Id: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Cpf: " + cpf);
                System.out.println("Rg: " + rg);
                System.out.println("Endereço: " + endereco);
                System.out.println("Número: " + numero);
                System.out.println("Complemento: " + complemento);
                System.out.println("Bairro: " + bairro);
                System.out.println("Cidade: " + cidade);
                System.out.println("Estado: " + estado);
                System.out.println("Telefone 1: " + telefone1);
                System.out.println("Telefone 2: " + telefone2);
                System.out.println("Email: " + email);
                System.out.println("Perfil: " + perfil);
                System.out.println("");
            }
            entrada.close();
            entrada2.close();
            cliente.close();
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
