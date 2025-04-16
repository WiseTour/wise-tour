package tour.wise.etl;

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

        List<String> filesName = util.getAllFilesName("../../database/dados-originais/demanda-turistica-internacional/demanda_turistica_internacional_-_fichas_sinteses_2015-2019/");

        // Listando os caminhos de cada arquivo
        List<Path> paths = new ArrayList<>();
        for (String fileName : filesName) {
            paths.add(Path.of(fileName));
        }

        // Carregando os arquivos excel

        List<InputStream> excelFiles = new ArrayList<>();

        for (Path path : paths) {
            excelFiles.add(Files.newInputStream(path));
        }

        // Extraindo dados dos arquivos
        List<List<Object>> data_perfil = service.extract(filesName.get(4), excelFiles.get(4), 0, 0, 2, List.of("String", "String"));


        // Fechando o arquivo após a extração
        for (InputStream excelFile : excelFiles) {
            excelFile.close();
        }

        // Exibindo dados

        System.out.println();
        System.out.println("Dados transformados:");


    }



}
