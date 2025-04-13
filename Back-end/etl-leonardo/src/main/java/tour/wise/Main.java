package tour.wise;

import tour.wise.unidades_federativa_regioes.Unidade_Federativa_Brasil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String fileName = "../../database/dados-originais/uf-regioes-brasil/uf-regioes-brasil-siglas.xlsx";

        // Carregando o arquivo excel
        Path caminho = Path.of(fileName);
        InputStream file1 = Files.newInputStream(caminho);
        InputStream file2 = Files.newInputStream(caminho);

        // Extraindo dos dados do arquivo
        ETL etl = new ETL();
        List<List<Object>> data_uf = etl.extract(fileName, file1, 1, 0, 3, List.of("String", "String", "String") );
        List<List<Object>> data_regioes = etl.extract(fileName, file2, 0, 0, 2, List.of("String", "String") );

        List<Unidade_Federativa_Brasil> Unidades_Federativa_Brasil = new ArrayList<>();

        for (List<Object> line : data_uf) {
            String nome = line.get(0).toString();
            String sigla_uf = line.get(1).toString();
            String sigla_regiao = line.get(2).toString();
            String regiao = "";

            for (List<Object> dataRegiao : data_regioes) {
                if(dataRegiao.get(1).equals(sigla_regiao)){
                    regiao = (String) dataRegiao.get(0);
                }
            }

            Unidade_Federativa_Brasil uf = new Unidade_Federativa_Brasil();
            uf.setUnidade_federativa(nome);
            uf.setSigla_uf(sigla_uf);
            uf.setRegiao(regiao);
            uf.setSigla(sigla_regiao);

            Unidades_Federativa_Brasil.add(uf);
        }

        // Fechando o arquivo após a extração
        file1.close();
        file2.close();

        System.out.println("Dados extraídos:");
        for (Unidade_Federativa_Brasil Unidade_Federativa_Brasil : Unidades_Federativa_Brasil) {
            System.out.println(Unidade_Federativa_Brasil);
        }
    }
}