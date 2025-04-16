package wise.tour;

import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
      Log log = new Log();
      Random random = new Random();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

      try
      {
        log.showLog("Iniciando o processo ETL...", "INFO", "Início", formatter);
        Thread.sleep((random.nextInt(5) + 1) * 1000L);

        log.showLog("Extraindo dados...", "INFO", "Extração", formatter);
        Thread.sleep((random.nextInt(5) + 1) * 1000L);

        log.showLog("Transformando dados...", "INFO", "Transformação", formatter);
        Thread.sleep((random.nextInt(5) + 1) * 1000L);

        log.showLog("Poucos dados extraídos, alguns dados podem estar faltando. Verifique a origem.", "WARNING", "Extração", formatter);
        Thread.sleep((random.nextInt(5) + 1) * 1000L);

        log.showLog("Carregando dados no banco...", "INFO", "Carga", formatter);
        Thread.sleep((random.nextInt(5) + 1) * 1000L);

        log.showLog("Falha ao carregar os dados. Banco de dados não encontrado.", "ERROR", "Carga", formatter);

        log.showLog("Processo ETL encerrado!", "INFO", "Fim", formatter);
      } catch (InterruptedException e)
      {
        System.out.println("ERRO!");
      }

    }
}