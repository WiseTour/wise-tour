package tour.wise.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class Chegada_Turistas_Internacionais_Brasil_Anual_DAO {
    private JdbcTemplate connection; // Conexão com o banco

    public Chegada_Turistas_Internacionais_Brasil_Anual_DAO(JdbcTemplate connection) {
        this.connection = connection;
    }

    public void insertChegadasAnuais() {
        String sql = """
        INSERT INTO Chegadas_Turistas_Internacionais_Brasil_Anual (ano, chegadas, fk_uf_sigla, fk_fonte, fk_pais)
        SELECT 
            ano,
            SUM(chegadas) AS total_chegadas,
            fk_uf_sigla,
            fk_fonte,
            fk_pais
        FROM 
            Chegadas_Turistas_Internacionais_Brasil_Mensal
        GROUP BY 
            ano, fk_uf_sigla, fk_fonte, fk_pais
    """;

        try {
            int linhasAfetadas = connection.update(sql);
            System.out.println("\n[INFO] Processamento de chegadas anuais concluído.");
            System.out.println("[INFO] Total de registros agregados e inseridos: " + linhasAfetadas);
        } catch (DataAccessException e) {
            System.err.println("[ERRO] Falha ao inserir dados agregados de chegadas anuais: " + e.getMessage());
        }
    }

}
