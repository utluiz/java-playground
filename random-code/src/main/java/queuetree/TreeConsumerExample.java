package queuetree;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class TreeConsumerExample {

    //cria mapa de atributos a partir dos parâmetros: chave, valor, chave, valor...
    Map<String, Object> mapOf(Object... args) {
        Map<String, Object> m = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            m.put((String) args[i], args[i+1]);
        }
        return m;
    }

    final Map<String, Map<String, Object>> arvore = new HashMap<>();
    final LinkedBlockingDeque<ElementoArvore> fila = new LinkedBlockingDeque<>();
    final AtomicLong ultimoIdInseridoNoBanco = new AtomicLong();
    final AtomicLong quantidadeElementosProcessados = new AtomicLong();

    //inicia arvore
    TreeConsumerExample() {
        arvore.put("v=raiz", mapOf("nome_atributo_1", "valor_1", "nome_atributo_2", "valor_2"));
        arvore.put("v=raiz,v=elemento_1", mapOf("nome_atributo_3", "valor_3"));
        arvore.put("v=raiz,v=elemento_2", mapOf("nome_atributo_4", "valor_4"));
        arvore.put("v=raiz,v=elemento_1,v=elemento_3", mapOf("nome_atributo_5", "valor_5", "nome_atributo_6", "valor_6", "nome_atributo_7", "valor_7"));
    }

    //armazena informacoes de um elemento da árvore na fila
    static class ElementoArvore {
        final String caminhoNo;
        final Map<String, Object> valores;
        final Long idPai;
        public ElementoArvore(String caminhoNo, Map<String, Object> valores, Long idPai) {
            this.caminhoNo = caminhoNo;
            this.valores = valores;
            this.idPai = idPai;
        }
        public Map<String, Object> getValores() {
            return valores;
        }
        public Long getIdPai() {
            return idPai;
        }
        public String getCaminhoNo() {
            return caminhoNo;
        }
    }

    //persiste um elemento e retorna o novo ID
    Long persisteElemento(ElementoArvore elemento) {
        Long novoId = ultimoIdInseridoNoBanco.incrementAndGet();
        System.out.printf("Persistindo %s | valores = %s | id pai = %d, novo id = %d%n", elemento.getCaminhoNo(),
                elemento.getValores(), elemento.getIdPai(), novoId);
        return novoId;
    }

    //recupera os filhos de um elemento a partir do caminho
    Collection<String> filhosDiretos(String caminhoNo) {
        return arvore.keySet().stream()
                .filter(k -> k.length() > caminhoNo.length() && k.startsWith(caminhoNo) && k.substring(caminhoNo.length() + 1).indexOf(',')  < 0)
                .collect(Collectors.toList());
    }

    //thread que retira um elemento da fila, processa e adiciona os filhos
    class ConsumidorProdutor extends Thread {
        final Integer threadNumber;

        public ConsumidorProdutor(Integer threadNumber) {
            this.threadNumber = threadNumber;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    //recupera elemento da fila para processar
                    final ElementoArvore e = fila.take();

                    //verifica poison object
                    if (e.getCaminhoNo() == null) {
                        //se null, fim da fila, recoloca o elemento para acordar outras threads e finaliza a thread atual
                        fila.add(e);
                        System.out.printf("Fim da fila sinalizado. Finalizando thread %d%n", threadNumber);
                        break;
                    }

                    //procesa elemento
                    System.out.printf("Processando elemento %s na thread %d%n", e.getCaminhoNo(), threadNumber);
                    final Long novoId = persisteElemento(e);

                    //insere filhos para processamento usando o ID inserido
                    fila.addAll(filhosDiretos(e.getCaminhoNo()).stream()
                            .map(caminhoFilho -> new ElementoArvore(caminhoFilho, arvore.get(caminhoFilho), novoId))
                            .collect(Collectors.toList()));

                    //verifica final do processamento
                    if (quantidadeElementosProcessados.incrementAndGet() >= arvore.size()) {
                        //elemento demarcando fim do processamento
                        //isso faz com que as threads sejam "acordadas" e terminem
                        fila.add(new ElementoArvore(null, null, null));
                        System.out.printf("Fim da fila encontrado. Finalizando thread %d%n", threadNumber);
                        break;
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void escreveArvore(String caminhoNo) {
        //cria pool de threads
        final ExecutorService threadPool = Executors.newFixedThreadPool(8);

        for (int i = 0; i < 8; i++) {
            threadPool.execute(new ConsumidorProdutor(i+1));
        }

        //coloca o elemento raiz na fila para iniciar o processamento
        try {
            fila.put(new ElementoArvore(caminhoNo, arvore.get(caminhoNo), null));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //aguarda término
            threadPool.shutdown();

    }

    public static void main(String[] args) {
        new TreeConsumerExample().escreveArvore("v=raiz");
    }
}
