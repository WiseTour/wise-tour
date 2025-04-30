package tour.wise;
import tour.wise.ConexaoBanco;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;


import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ConexaoBanco conexaoBanco = new ConexaoBanco();
        JdbcTemplate connection = conexaoBanco.getConnection();


        // Inserindo alguns cadastros

        connection.update("INSERT INTO Pais (pais) VALUES (?)",
                "Portugal");

//        Log log = new Log();
//        Random random = new Random();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//
//        log.showLog("Iniciando o processo ETL...", "INFO", formatter);
//        Thread.sleep((random.nextInt(5) + 1) * 1000L);
//
//        log.showLog("Extraindo dados...", "INFO", formatter);
//        Thread.sleep((random.nextInt(5) + 1) * 1000L);
//
//        log.showLog("Transformando dados...", "INFO", formatter);
//        Thread.sleep((random.nextInt(5) + 1) * 1000L);
//
//        // Simulação de um WARNING
//        log.showLog("Poucos dados extraídos, alguns dados podem estar faltando. Verifique a origem.", "WARNING", formatter);
//        Thread.sleep((random.nextInt(5) + 1) * 1000L);
//
//        log.showLog("Carregando dados no banco...", "INFO", formatter);
//        Thread.sleep((random.nextInt(5) + 1) * 1000L);
//
//        // Simulação de um ERRO
//        log.showLog("Falha ao carregar os dados. Banco de dados não encontrado.", "ERROR", formatter);
//
//        log.showLog("Processo ETL encerrado!", "INFO", formatter);
    }
}