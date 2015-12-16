import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

/**
 * TODO: Document this class / interface here
 *
 * @since v7.0
 */
public class ObservadorDeArquivos {

    public static void main(String[] args) throws IOException {
    //define pasta a ser observada
    Path pastaOrigem = Files.createDirectories(Paths.get("origem"));
    //define pasta onde os arquivos sendo processados serão movidos
    Path pastaProcessando = Files.createDirectories(pastaOrigem.resolve("processando"));
    //define pasta onde os arquivos já processados serão movidos
    Path pastaProcessados = Files.createDirectories(pastaOrigem.resolve("processados"));

    //monitor da pasta
    WatchService watcher = FileSystems.getDefault().newWatchService();

    //registra para receber eventos de criação de arquivos no diretório
    pastaOrigem.register(watcher, ENTRY_CREATE);

    //loop infinito
    while (true) {

        WatchKey wk = null;
        try {
            //aguarda algum evento ocorrer
            System.out.printf("Aguardando arquivos em %s", pastaOrigem);
            wk = watcher.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //percorre eventos recebidos
        for (WatchEvent<?> event : wk.pollEvents()) {

            //ignora se ocorreu um problema
            if (event.kind() == OVERFLOW) continue;

            //pega nome do arquivo criado
            WatchEvent<Path> ev = (WatchEvent<Path>) event;
            Path nomeArquivo = ev.context();
            Path arquivoOrigem = pastaOrigem.resolve(nomeArquivo);
            System.out.printf("Arquivo criado: %s\n", arquivoOrigem);


            //move arquivo para "processando"
            Path arquivoProcessando = pastaProcessando.resolve(nomeArquivo);
            System.out.printf("Movendo para: %s\n", arquivoProcessando);
            Files.move(arquivoOrigem, arquivoProcessando);

            //processa arquivo
            System.out.printf("Processando arquivo %s\n", arquivoProcessando);

            //move para "processados"
            Path arquivoProcessado = pastaProcessados.resolve(nomeArquivo);
            System.out.printf("Movendo para: %s\n", arquivoProcessado);
            Files.move(arquivoProcessando, arquivoProcessado);

        }

        //prepara o próximo evento ou sai caso algum erro ocorra, como o diretório não existir mais
        if (!wk.reset()) {
            break;
        }
    }

    //encerramento manual, caso neccesário
    //watcher.close();
    }

}
