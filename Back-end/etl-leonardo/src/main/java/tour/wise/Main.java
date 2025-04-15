package tour.wise;

import tour.wise.entity.base.data.Unidade_Federativa_Brasil;
import tour.wise.etl.Extract;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        // Listando nomes dos arquivos
        List<String> filesName = new ArrayList<>();
        filesName.add("../../database/dados-originais/uf-regioes-brasil/uf-regioes-brasil-siglas.xlsx");
        filesName.add("../../database/dados-originais/uf-regioes-brasil/uf-regioes-brasil-siglas.xlsx");
        filesName.add("../../database/dados-originais/paises-continentes-codigos-idiomas/paises-codigo.xls");


        // Listando os caminhos de cada arquivo
        List<Path> paths = new ArrayList<>();
        for (String fileName : filesName) {
            paths.add(Path.of(fileName));
        }

        // Carregando o arquivo excel
        List<InputStream> files = new ArrayList<>();
        for (Path path : paths) {
            files.add(Files.newInputStream(path));
        }

        Extract etl = new Extract();

        // Extraindo dados dos arquivos
        List<List<Object>> data_regioes = etl.extract(filesName.get(0), files.get(0), 0, 0, 2, List.of("String", "String"));
        List<List<Object>> data_uf = etl.extract(filesName.get(1), files.get(1), 1, 0, 3, List.of("String", "String", "String"));
        List<List<Object>> data_pais_contine = etl.extract(filesName.get(2), files.get(2), 0, 0, 2, List.of("String", "String"));

        // Transformando dados
        List<Regiao_Brasil> regioes_brasil = new ArrayList<>();

        for (List<Object> line : data_regioes) {
            regioes_brasil.add(new Regiao_Brasil(line.get(1).toString(), line.get(0).toString()));
        }

        List<Unidade_Federativa_Brasil> Unidades_Federativa_Brasil = new ArrayList<>();

        for (List<Object> line : data_uf) {
            String sigla = line.get(1).toString();
            String uf = line.get(0).toString();
            String sigla_regiao = line.get(2).toString();
            Regiao_Brasil regiao = new Regiao_Brasil();

            for (Regiao_Brasil regiao_brasil : regioes_brasil) {
                if (regiao_brasil.getSigla().equals(sigla_regiao)) {
                    regiao = regiao_brasil;
                }
            }


            Unidades_Federativa_Brasil.add(new Unidade_Federativa_Brasil(sigla, uf, regiao));
        }

        List<Continente> continentes = new ArrayList<>();






        // Fechando o arquivo após a extração
        for (InputStream file : files) {
            file.close();
        }

        // Exibindo dados

        System.out.println();
        System.out.println("Dados transformados:");

        System.out.println();
        for (Regiao_Brasil regiao_brasil : regioes_brasil) {
            System.out.println(regiao_brasil.toString());
        }

        System.out.println();
        for (Unidade_Federativa_Brasil unidade_federativa_brasil : Unidades_Federativa_Brasil) {
            System.out.println(unidade_federativa_brasil.toString());
        }

    }
}
