package tour.wise;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import tour.wise.dao.Data_Base;
import tour.wise.etl.Chegada_Turistas_Internacionais_Brasil_ETL;
import tour.wise.etl.perfil_estimado_turista_etl.Perfil_Estimado_Turistas_ETL;
import tour.wise.model.chegada_turistas_internacionais_brasil.Chegada_Turistas_Internacionais_Brasil;
import tour.wise.model.perfil_estimado_turistas.Perfil_Estimado_Turistas;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {

        Data_Base data_base = new Data_Base();

        JdbcTemplate connection = data_base.getConnection();


//        Perfil_Estimado_Turistas_ETL relatorioTurismoBrasilEtl = new Perfil_Estimado_Turistas_ETL();
//
//        List<Perfil_Estimado_Turistas> perfies = relatorioTurismoBrasilEtl.extractTransform("Ministerio do Turismo","Ministerio do Turismo");
//
//        Integer totalChegadas = 100000000;
//
//        Integer total_chegadas = 0;
//
//        for (Perfil_Estimado_Turistas perfil : perfies) {
//
//            total_chegadas += perfil.getTotal_chegadas();
//            System.out.println();
//            System.out.println(perfil);
//        }
//
//        System.out.println();
//        System.out.println("Total de perfies = " + perfies.size());
//        System.out.println();
//        System.out.println("Total de chegadas = " + totalChegadas);
//        System.out.println();
//        System.out.println("Total de chegadas para os estados da lista = " + total_chegadas);
//        System.out.println();
//        System.out.println("Total de chegadas para de outros estados = " + (totalChegadas - total_chegadas));



        Chegada_Turistas_Internacionais_Brasil_ETL chegada_turistas_internacionais_brasil_etl = new Chegada_Turistas_Internacionais_Brasil_ETL();

        List<Chegada_Turistas_Internacionais_Brasil> chegadas = chegada_turistas_internacionais_brasil_etl.extractTransform(
                "../../database/dados-originais/chegada_turistas_ministerio_turismo/chegadas_2019.xlsx",
                0,
                0,
                12,
                List.of("String", "Numeric", "String", "Numeric", "String", "Numeric", "String", "Numeric", "Numeric", "String", "Numeric", "Numeric"),
                "Ministério do Turismo",
                2019

        );

        String fonteTitulo = "chegadas_2019";

        String sqlCheck = "SELECT COUNT(*) FROM Fonte WHERE titulo_arquivo_fonte = ? ";
        Integer count1 = connection.queryForObject(sqlCheck, Integer.class, fonteTitulo);

        if (count1 != null && count1 == 0) {
            connection.update(
                    "INSERT INTO Fonte (titulo_arquivo_fonte, edicao, orgao_emissor, url_origem, data_coleta) VALUES (?, ?, ?, ?, NOW())",
                    "chegadas_2019",
                    "2019",
                    "Ministério do Turismo",
                    "https://dados.gov.br/dados/conjuntos-dados/estimativas-de-chegadas-de-turistas-internacionais-ao-brasil"
            );
        } else {

            System.out.println("Fonte já cadastrada.");

// Queries auxiliares
            String sqlFonte = "SELECT id_fonte FROM Fonte WHERE titulo_arquivo_fonte = ?";
            String sqlPais = "SELECT sigla FROM Pais WHERE pais = ?";
            String sqlExistente = "SELECT COUNT(*) FROM Chegadas_Turistas_Internacionais_Brasil_Mensal " +
                    "WHERE mes = ? AND ano = ? AND fk_uf_sigla = ? AND fk_pais = ?";

// SQL de inserção (ordem igual à do DESCRIBE)
            String sqlInsert = "INSERT INTO Chegadas_Turistas_Internacionais_Brasil_Mensal " +
                    "(mes, ano, chegadas, via_acesso, fk_uf_sigla, fk_fonte, fk_pais) VALUES (?, ?, ?, ?, ?, ?, ?)";

// Obter ID da fonte uma vez
            Integer fonteId = null;
            try {
                fonteId = connection.queryForObject(sqlFonte, Integer.class, fonteTitulo);
            } catch (EmptyResultDataAccessException e) {
                System.out.println("Fonte não encontrada para o título: " + fonteTitulo);
            }

            if (fonteId != null) {
                Integer finalFonteId = fonteId;
                connection.batchUpdate(sqlInsert, new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Chegada_Turistas_Internacionais_Brasil chegada = chegadas.get(i);

                        String paisNome = chegada.getPais_origem().getPais();
                        String paisSigla = null;

                        try {
                            paisSigla = connection.queryForObject(sqlPais, String.class, paisNome);
                        } catch (EmptyResultDataAccessException e) {
                            System.out.println("País não encontrado: " + paisNome);
                        }

                        if (paisSigla != null) {
                            // Verifica se o registro já existe
                            int count = connection.queryForObject(sqlExistente, Integer.class,
                                    chegada.getMes(),
                                    chegada.getAno(),
                                    chegada.getDestino().getSigla(),
                                    paisSigla
                            );

                            if (count == 0) {
                                ps.setInt(1, chegada.getMes());
                                ps.setInt(2, chegada.getAno());
                                ps.setInt(3, chegada.getChegadas().intValue());
                                ps.setString(4, chegada.getVia_acesso());
                                ps.setString(5, chegada.getDestino().getSigla());
                                ps.setInt(6, finalFonteId);
                                ps.setString(7, paisSigla);
                            } else {
                                System.out.println("Chegada já cadastrada: " + chegada.getMes() + "/" + chegada.getAno() +
                                        " - UF: " + chegada.getDestino().getSigla() + " - País: " + paisSigla);

                                // Preenche com nulls para pular inserção
                                for (int col = 1; col <= 7; col++) {
                                    ps.setNull(col, Types.NULL);
                                }
                            }
                        } else {
                            System.out.println("Sigla do país não encontrada para: " + paisNome);
                            for (int col = 1; col <= 7; col++) {
                                ps.setNull(col, Types.NULL);
                            }
                        }
                    }

                    @Override
                    public int getBatchSize() {
                        return chegadas.size();
                    }
                });
            } else {
                System.out.println("Impossível prosseguir: fonte não encontrada.");
            }





        }

    }
}
