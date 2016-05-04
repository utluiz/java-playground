import java.util.ArrayDeque;
import java.util.Deque;

/**
 * http://pt.stackoverflow.com/questions/126674/caminhamento-por-n%C3%ADvel-em-%C3%A1rvore-bin%C3%A1ria
 * https://pt.wikipedia.org/wiki/%C3%81rvore_bin%C3%A1ria_de_busca
 */
public class WalkTreeLevels {


    public static void main(String[] args) {

        No no4 = new No(null, null, "4");
        No no7 = new No(null, null, "7");
        No no13 = new No(null, null, "13");

        No no1 = new No(null, null, "1");
        No no6 = new No(no4, no7, "6");
        No no14 = new No(no13, null, "14");

        No no3 = new No(no1, no6, "3");
        No no10 = new No(null, no14, "10");

        No root = new No(no3, no10,  "8");

        System.out.println("Expected: 8, 3, 10, 1, 6, 14, 4, 7, 13");
        System.out.print("Obtained: ");
        new WalkTreeLevels().walkLevels(root);
    }

    public void walkLevels(No no) {
        if (no == null) throw new IllegalArgumentException("Tree node cannot be null!");
        Deque<No> fila = new ArrayDeque<>();
        fila.add(no);
        while (!fila.isEmpty()) {
            No atual = fila.removeFirst();
            System.out.printf("%s, ", atual.getConteudo());
            if (atual.getFilhoEsquerda() != null) fila.add(atual.getFilhoEsquerda());
            if (atual.getFilhoDireita() != null) fila.add(atual.getFilhoDireita());
        }
    }

    static class No {
        private No filhoDireita;
        private No filhoEsquerda;
        private String conteudo;

        public No(No filhoEsquerda, No filhoDireita, String conteudo) {
            this.filhoDireita = filhoDireita;
            this.filhoEsquerda = filhoEsquerda;
            this.conteudo = conteudo;
        }

        public No getFilhoDireita() {
            return filhoDireita;
        }

        public No getFilhoEsquerda() {
            return filhoEsquerda;
        }

        public String getConteudo() {
            return conteudo;
        }

        @Override
        public String toString() {
            return "No{" +
                    "'" + conteudo + '\'' +
                    '}';
        }
    }
}
