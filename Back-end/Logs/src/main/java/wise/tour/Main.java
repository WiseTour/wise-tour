package wise.tour;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("-------------- LOGS V2.0 WISE TOUR ---------------");
        Log log = new Log(); // Instanciando o objeto da classe Log
        Random random = new Random(); // Instanciando o objeto para gerar números aleatórios

        log.showLog("Iniciando o processo ETL...", "INFO");
        Thread.sleep((random.nextInt(5) + 1) * 1000L);

        log.showLog("Extraindo dados...", "INFO");
        Thread.sleep((random.nextInt(5) + 1) * 1000L);

        log.showLog("Transformando dados...", "INFO");
        Thread.sleep((random.nextInt(5) + 1) * 1000L);

        log.showLog("Poucos dados extraídos, alguns dados podem estar faltando. Verifique a origem.", "WARNING");
        Thread.sleep((random.nextInt(5) + 1) * 1000L);

        log.showLog("Carregando dados no banco...", "INFO");
        Thread.sleep((random.nextInt(5) + 1) * 1000L);

        log.showLog("Falha ao carregar os dados. Banco de dados não encontrado.", "ERROR");

        log.showLog("Processo ETL encerrado!", "INFO");
    }
}