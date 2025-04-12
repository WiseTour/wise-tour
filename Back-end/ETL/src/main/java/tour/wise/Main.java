package tour.wise;
import tour.wise.ConexaoBanco;
import tour.wise.Cadastro;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;


import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ConexaoBanco conexaoBanco = new ConexaoBanco();
        JdbcTemplate connection = conexaoBanco.getConnection();

        connection.execute("""
                DROP TABLE IF EXISTS cadastro; 
                """);

        connection.execute("""
                CREATE TABLE cadastro (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nome VARCHAR(45) NOT NULL,
                    dataNascimento DATE NOT NULL,
                    cpf CHAR(11) NOT NULL,
                    idade INT NOT NULL
                ); 
                """);

        // Inserindo alguns cadastros

        connection.update("INSERT INTO cadastro (nome, dataNascimento, cpf, idade) VALUES (?, ?, ?, ?)",
                "Priscila Almeida Campos", "1999-09-21", "12345678901", 25);

        connection.update("INSERT INTO cadastro (nome, dataNascimento, cpf, idade) VALUES (?, ?, ?, ?)",
                "João Pedro Brito", "1984-03-23", "09876543211", 41);


        // Listando todas as pessoas cadastradas

        List<Cadastro> pessoasCadastradas = connection.query("SELECT * FROM cadastro", new BeanPropertyRowMapper<>(Cadastro.class));

        System.out.println("Pessoas cadastradas no site:");

        for (Cadastro cadastro : pessoasCadastradas) {
            System.out.println(cadastro);
        }

        // Inserindo uma nova pessoa a partir de um objeto

        Cadastro novoCadastro = new Cadastro(null, "Pâmela Jacinto Pereira", "2001-08-19", "11223344556", 24);

        connection.update("INSERT INTO cadastro (nome, dataNascimento, cpf, idade) VALUES (?, ?, ?, ?)",
                novoCadastro.getNome(), novoCadastro.getDataNascimento(), novoCadastro.getCpf(), novoCadastro.getIdade());

        System.out.println("\nPessoas no banco de dados após inserção de novo cadastro:");

        pessoasCadastradas = connection.query("SELECT * FROM cadastro", new BeanPropertyRowMapper<>(Cadastro.class));

        for (Cadastro cadastro : pessoasCadastradas) {
            System.out.println(cadastro);
        }

        // Atualizando um cadastro

        connection.update("UPDATE cadastro SET nome = ?, dataNascimento = ?, cpf = ?, idade = ? WHERE id = ?",
                "Lucas Gomes Mendes", "2001-12-03", "10293847565", 23, 5);

        System.out.println("\nPessoas no banco de dados após atualização de cadastro:");

        pessoasCadastradas = connection.query("SELECT * FROM cadastro", new BeanPropertyRowMapper<>(Cadastro.class));

        for (Cadastro cadastro : pessoasCadastradas) {
            System.out.println(cadastro);
        }

        // Deletando um cadastro

        connection.update("DELETE FROM cadastro WHERE id = ?", 5);

        System.out.println("\nPessoas no banco de dados após exclusão de cadastro:");

        pessoasCadastradas = connection.query("SELECT * FROM cadastro", new BeanPropertyRowMapper<>(Cadastro.class));

        for (Cadastro cadastro : pessoasCadastradas) {
            System.out.println(cadastro);
        }

        // Busca personalizada

        System.out.println("\nCadastro de pessoas menores de 24 anos no banco de dados:");

        pessoasCadastradas = connection.query("SELECT * FROM cadastro WHERE idade = ?", new BeanPropertyRowMapper<>(Cadastro.class), 24);

        for (Cadastro cadastro : pessoasCadastradas) {
            System.out.println(cadastro);
        }

        // Buscar um cadastro pelo ID

        Cadastro cadastroEncontrado = connection.queryForObject("SELECT * FROM cadastro WHERE id = ?", new BeanPropertyRowMapper<>(Cadastro.class), 1);
        System.out.println("\nCadastro com ID 1: " + cadastroEncontrado);

        // Obs: se sua query retornar nenhum ou mais de um item, ao executar, uma exceção será lançada.

        // throws InterruptedException - é uma exceção verificada (checked exception) que ocorre
        // quando uma thread dormindo, esperando ou bloqueada é interrompida antes de terminar a sua espera.

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
