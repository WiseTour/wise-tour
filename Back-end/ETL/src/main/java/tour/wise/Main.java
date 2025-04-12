package tour.wise;

import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ConexaoBanco conexaoBanco = new ConexaoBanco();
        JdbcTemplate connection = conexaoBanco.getConnection();

        connection.execute("""
                CREATE TABLE filme (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nome VARCHAR(255) NOT NULL,
                    ano INT NOT NULL,
                    genero VARCHAR(255) NOT NULL,
                    diretor VARCHAR(255) NOT NULL
                )
                """);

        // Inserindo alguns filmes

        connection.update("INSERT INTO filme (nome, ano, genero, diretor) VALUES (?, ?, ?, ?)",
                "Matrix", 1999, "Ficção Científica", "Lana Wachowski, Lilly Wachowski");

        connection.update("INSERT INTO filme (nome, ano, genero, diretor) VALUES (?, ?, ?, ?)",
                "O Poderoso Chefão", 1972, "Drama", "Francis Ford Coppola");

        connection.update("INSERT INTO filme (nome, ano, genero, diretor) VALUES (?, ?, ?, ?)",
                "O Senhor dos Anéis: O Retorno do Rei", 2003, "Fantasia", "Peter Jackson");

        connection.update("INSERT INTO filme (nome, ano, genero, diretor) VALUES (?, ?, ?, ?)",
                "Forrest Gump", 1994, "Drama", "Robert Zemeckis");

        // Listando todos os filmes

        List<Filme> filmesDoBanco = connection.query("SELECT * FROM filme", new BeanPropertyRowMapper<>(Filme.class));

        System.out.println("Filmes no banco de dados:");

        for (Filme filme : filmesDoBanco) {
            System.out.println(filme);
        }

        // Inserindo um novo filme a partir de um objeto

        Filme novoFilme = new Filme(null, "Vingadores: Ultimato", 2019, "Ação", "Anthony Russo, Joe Russo");

        connection.update("INSERT INTO filme (nome, ano, genero, diretor) VALUES (?, ?, ?, ?)",
                novoFilme.getNome(), novoFilme.getAno(), novoFilme.getGenero(), novoFilme.getDiretor());

        System.out.println("\nFilmes no banco de dados após inserção de novo filme:");

        filmesDoBanco = connection.query("SELECT * FROM filme", new BeanPropertyRowMapper<>(Filme.class));

        for (Filme filme : filmesDoBanco) {
            System.out.println(filme);
        }

        // Atualizando um filme

        connection.update("UPDATE filme SET nome = ?, ano = ?, genero = ?, diretor = ? WHERE id = ?",
                "Shrek", 2001, "Animação", "Andrew Adamson, Vicky Jenson", 5);

        System.out.println("\nFilmes no banco de dados após atualização de filme:");

        filmesDoBanco = connection.query("SELECT * FROM filme", new BeanPropertyRowMapper<>(Filme.class));

        for (Filme filme : filmesDoBanco) {
            System.out.println(filme);
        }

        // Deletando um filme

        connection.update("DELETE FROM filme WHERE id = ?", 5);

        System.out.println("\nFilmes no banco de dados após exclusão de filme:");

        filmesDoBanco = connection.query("SELECT * FROM filme", new BeanPropertyRowMapper<>(Filme.class));

        for (Filme filme : filmesDoBanco) {
            System.out.println(filme);
        }

        // Busca personalizada

        System.out.println("\nFilmes de drama no banco de dados:");

        filmesDoBanco = connection.query("SELECT * FROM filme WHERE genero = ?", new BeanPropertyRowMapper<>(Filme.class), "Drama");

        for (Filme filme : filmesDoBanco) {
            System.out.println(filme);
        }

        // Buscar um filme pelo ID

        Filme filmeEncontrado = connection.queryForObject("SELECT * FROM filme WHERE id = ?", new BeanPropertyRowMapper<>(Filme.class), 1);
        System.out.println("\nFilme com ID 1: " + filmeEncontrado);

        // Obs: se sua query retornar nenhum ou mais de um item, ao executar, uma exceção será lançada.

        // throws InterruptedException - é uma exceção verificada (checked exception) que ocorre
        // quando uma thread dormindo, esperando ou bloqueada é interrompida antes de terminar a sua espera.

        Log log = new Log();
        Random random = new Random();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        log.showLog("Iniciando o processo ETL...", "INFO", formatter);
        Thread.sleep((random.nextInt(5) + 1) * 1000L);

        log.showLog("Extraindo dados...", "INFO", formatter);
        Thread.sleep((random.nextInt(5) + 1) * 1000L);

        log.showLog("Transformando dados...", "INFO", formatter);
        Thread.sleep((random.nextInt(5) + 1) * 1000L);

        // Simulação de um WARNING
        log.showLog("Poucos dados extraídos, alguns dados podem estar faltando. Verifique a origem.", "WARNING", formatter);
        Thread.sleep((random.nextInt(5) + 1) * 1000L);

        log.showLog("Carregando dados no banco...", "INFO", formatter);
        Thread.sleep((random.nextInt(5) + 1) * 1000L);

        // Simulação de um ERRO
        log.showLog("Falha ao carregar os dados. Banco de dados não encontrado.", "ERROR", formatter);

        log.showLog("Processo ETL encerrado!", "INFO", formatter);
    }



}
