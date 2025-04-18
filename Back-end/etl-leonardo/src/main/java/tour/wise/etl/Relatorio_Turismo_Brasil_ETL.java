package tour.wise.etl;

import tour.wise.dao.relatorio_turismo_brasil.Relatorio_Turismo_Brasil;
import tour.wise.service.Service;
import tour.wise.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Relatorio_Turismo_Brasil_ETL {

    Util util = new Util();

    Service service = new Service();

    public void exe() throws IOException {


        // Listando nomes dos arquivos

        String fileName = "../../database/dados-originais/demanda-turistica-internacional/demanda_turistica_internacional_-_fichas_sinteses_2015-2019/05 - Ficha Síntese Países 2015-2019_DIVULGAÇÃO.xlsx";

        // Listando os caminhos de cada arquivo

        Path path = Path.of(fileName);

        // Carregando os arquivos excel

        InputStream excelFile = Files.newInputStream(path);

        List<Object> data_perfil = new ArrayList<>();

        // Extraindo dados dos arquivos

        data_perfil.add(service.extract(fileName, excelFile, 0, 0, 2, List.of("String", "String")));

        List<Relatorio_Turismo_Brasil> pessoas = service.extractRange(
                fileName,
                excelFile,
                0, // número da planilha
                6, // começa da linha 2 (inclusive)
                10, // vai até a linha 10 (inclusive)
                List.of(1, 3, 4, 5, 6, 7), // colunas específicas: nome, idade, ativo (exemplo)
                List.of("string", "numeric", "numeric", "numeric", "numeric", "numeric"),
                linha -> new List<Object>((String) linha.get(0), (Double) linha.get(1), (Boolean) linha.get(2))
        );






        // Fechando o arquivo após a extração
        for (InputStream excelFile : excelFiles) {
            excelFile.close();
        }

        // Exibindo dados

        System.out.println();
        System.out.println("Dados transformados:");


    }



}
