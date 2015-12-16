package queuetree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;

public class WaitProducerExample {

    //exemplo de sequência de leitura
    final List<String> leituraFake = Arrays.asList("v=raiz,v=elemento_1,v=elemento_3", "v=raiz", "v=raiz,v=elemento_1");

    //fila de processamento
    final LinkedBlockingDeque<String> fila = new LinkedBlockingDeque<>();

    //lista de nós pendentes, key -> nó pai, value -> nós aguardando leitura
    final ConcurrentHashMap<String, List<String>> listaDeEspera = new ConcurrentHashMap<>();

    //chaves dos nós inseridos
    final ConcurrentHashMap<String, Long> inseridos = new ConcurrentHashMap<>();

    //sequência de ids fake
    final AtomicLong ultimoIdInseridoNoBanco = new AtomicLong();

    //persiste um elemento e retorna o novo ID
    Long persisteElemento(String caminhoNo, Long idPai) {
        final Long novoId = ultimoIdInseridoNoBanco.incrementAndGet();
        System.out.printf("Persistindo %s | id pai = %d | novo id = %d%n", caminhoNo, idPai, novoId);
        return novoId;
    }

    //recupera o caminho do pai, dado o caminho do filho
    String getPai(String caminhoNo) {
        final int v = caminhoNo.lastIndexOf(',');
        return v > 0 ? caminhoNo.substring(0, v) : "";
    }

    void escreveArvore() {
        //faz uma leitura fake de um bloco desordenado de nós
        fila.addAll(leituraFake);

        //percorre fila até acabarem os elementos
        while (!fila.isEmpty()) {
            try {
                //caminho do nó
                final String no = fila.take();
                //caminho do pai
                final String pai = getPai(no);

                System.out.printf("Processando nó %s, pai = %s%n", no, pai);

                //verifica se o pai já foi inserido
                //sincronizações separadas são necessárias porque em uma thread o caminho do nó pai é usado para guardar
                //o nó atual na lista de espera, enquanto em outra thread o nó atual funciona como pai, consomindo a lista de espera
                final Long idPai = inseridos.get(pai);
                if (pai.isEmpty() || idPai != null) {
                    //se for raiz (caminho do pai vazio) ou se o pai foi encontrado, persiste o elemento e salva o ID inserido
                    inseridos.put(no, persisteElemento(no, idPai));
                    //sincroniza o nó para evitar que outra thread possa adicionar elementos na espera enquanto estiver consumindo
                    synchronized (no) {
                        //verifica se tinha alguém esperando
                        List<String> filhosEsperando = listaDeEspera.remove(no);
                        if (filhosEsperando == null) {
                            System.out.printf("Nenhum filho aguardando...%n");
                        } else {
                            //coloca de volta na fila principal (poderia processar imediatamente, mas acabaria em um algoritmo recursivo)
                            System.out.printf("Encontrado(s) %d filho(s) aguardando que já podem voltar para a fila: %s%n", filhosEsperando.size(), filhosEsperando);
                            fila.addAll(filhosEsperando);
                        }
                    }
                } else {
                    //senão coloca o nó na lista de espera associada com o caminho do pai
                    //sincroniza no pai para evitar que elementos sejam adicionados na lista de espera enquanto ela está sendo consumida
                    //se o pai for adicionado em algum momento após a verificação, o elemento estaria pronto para ser inserido, portanto deve voltar para a fila
                    synchronized (pai) {
                        //verifica novamente se não foi inserido, pois em caso de concorrência o estado pode mudar de uma linha para outra
                        if (inseridos.get(pai) == null) {
                            System.out.printf("Pai não encontrado, aguardando na lista de espera...%n");
                            listaDeEspera.compute(pai, (k, v) -> {
                                if (v == null) v = new ArrayList<>();
                                v.add(no);
                                return v;
                            });
                        } else {
                            //caso o pai tenha sido inserido entre a verificação acima e o bloco sincronizado, recoloca na fila normal ao invés da lista de espera
                            fila.add(no);
                        }
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //verifica se nada ficou esquecida na espera
        if (!listaDeEspera.isEmpty()) {
            System.out.printf("Ops... você esqueceu de alguma coisa, não?!%n");
        }
    }

    public static void main(String[] args) {
        new WaitProducerExample().escreveArvore();
    }
}
