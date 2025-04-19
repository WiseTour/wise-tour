package tour.wise.etl;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tour.wise.model.perfil_estimado_turista.relatorio_turismo_brasil.ficha_sintese_brasil.*;
import tour.wise.service.Service;
import tour.wise.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static tour.wise.service.Service.loadWorkbook;

public class Relatorio_Turismo_Brasil_ETL {

    Util util = new Util();
    Service service = new Service();

    String fileNameFichaSintesePais = "../../database/dados-originais/demanda-turistica-internacional/demanda_turistica_internacional_-_fichas_sinteses_2015-2019/05 - Ficha Síntese Países 2015-2019_DIVULGAÇÃO.xlsx";
    String fileNameFichaSinteseBrasil = "../../database/dados-originais/demanda-turistica-internacional/demanda_turistica_internacional_-_fichas_sinteses_2015-2019/01 - Ficha Síntese Brasil - 2015-2019_DIVULGAÇÃO.xlsx";
    String fileNameFichaSinteseEstado = "../../database/dados-originais/demanda-turistica-internacional/demanda_turistica_internacional_-_fichas_sinteses_2015-2019/06 - Ficha Síntese UF 2015-2019_DIVULGAÇÃO.xlsx";

    Workbook workbook;

    public void exe() throws IOException {
//        workbook = loadWorkbook(fileNameFichaSintesePais);
//
//        List<List<List<List<Object>>>> fichasSintesePais = new ArrayList<>();
//        for(Integer i = 1; i < service.getSheetNumber(fileNameFichaSintesePais); i++ ){
//            for(Integer j = 0; j <= 4; j++){
//                fichasSintesePais.add(extractFichaSintesePais(
//                        workbook,
//                        fileNameFichaSintesePais,
//                        i,
//                        List.of(1, 3+j),
//                        List.of(10, 12+j),
//                        List.of("string", "numeric")));
//
//            }
//
//        }
//
//        workbook.close();
//
//
//        ZipSecureFile.setMinInflateRatio(0.0001);
//
//        workbook = loadWorkbook(fileNameFichaSinteseBrasil);
//
//        List<List<List<List<Object>>>> fichasSinteseBrasil = new ArrayList<>();
//        for(Integer j = 0; j <= 4; j++){
//            fichasSinteseBrasil.add(extractFichaSinteseBrasil(
//                    workbook,
//                    1,
//                    List.of(1, 3+j),
//                    List.of(10, 12+j),
//                    List.of("string", "numeric")));
//
//        }
//
//        workbook.close();
//
//
//
//        workbook = loadWorkbook(fileNameFichaSinteseEstado);
//
//        List<List<List<List<Object>>>> fichasSinteseEstado = new ArrayList<>();
//        for(Integer i = 1; i < service.getSheetNumber(fileNameFichaSinteseEstado); i++ ){
//            for(Integer j = 0; j <= 4; j++){
//                fichasSinteseEstado.add(extractFichaSintesePais(
//                        workbook,
//                        fileNameFichaSinteseEstado,
//                        i,
//                        List.of(1, 3+j),
//                        List.of(10, 12+j),
//                        List.of("string", "numeric")));
//
//            }
//
//        }
//
//        workbook.close();
//
//        System.out.println();
//        System.out.println("Fichas Sinteses por Pais");
//
//        for (List<List<List<Object>>> fichaSintesePais : fichasSintesePais) {
//            System.out.println(fichaSintesePais);
//        }
//
//        System.out.println();
//        System.out.println("Fichas Sinteses por Estado");
//
//        for (List<List<List<Object>>> fichaSinteseEstado : fichasSinteseEstado) {
//            System.out.println(fichaSinteseEstado);
//        }
//
//        System.out.println();
//        System.out.println("Ficha Sintese Brasil");
//
//
//        for (List<List<List<Object>>> fichaSinteseBrasil : fichasSinteseBrasil) {
//            System.out.println(fichaSinteseBrasil);
//        }
//
//
//    // TRANSFORM

        Ficha_Sintese_Brasil_ETL ficha_sintese_brasil_etl = new Ficha_Sintese_Brasil_ETL();

        ficha_sintese_brasil_etl.exe();

    }



    private List<List<List<Object>>> extractFichaSintesePais(Workbook workbook, String fileName, Integer sheetNumber, List<Integer> leftColluns, List<Integer> rightColluns, List<String> collunsType) throws IOException {

        String sheetName = service.getSheetName(fileName, sheetNumber);
        String pais = sheetName.split("\\s+", 2)[1]; // divide no primeiro espaço

        // Parâmetros das seções a serem lidas
        List<int[]> ranges = List.of(
                new int[]{5, 5},
                new int[]{7, 9},
                new int[]{11, 17},
                new int[]{29, 33},
                new int[]{35, 37},
                new int[]{46, 50},
                new int[]{52, 56},
                new int[]{58, 62},
                new int[]{69, 76}
        );

        // Lista para consolidar todos os blocos de dados
        List<List<List<Object>>> data = new ArrayList<>();
        data.add(List.of(List.of(pais)));

        // Leitura dos dados e consolidação
        for (int[] range : ranges) {

            data.add(
                    service.extractRange(
                            workbook,
                            sheetNumber,
                        range[0],
                        range[1],
                            leftColluns,
                            collunsType,
                        Function.identity()
                    )
            );

        }

        // Parâmetros das seções a serem lidas
        ranges = List.of(
                new int[]{7, 9},
                new int[]{27, 28},
                new int[]{30, 36}
        );

        // Leitura dos dados e consolidação
        for (int[] range : ranges) {

            data.add(
                    service.extractRange(
                            workbook,
                            sheetNumber,
                            range[0],
                            range[1],
                            rightColluns,
                            collunsType,
                            Function.identity()
                    )
            );

        }


        for (List<List<Object>> datum : data) {
            System.out.println(datum);
        }

        System.out.println("\nETL finalizado com sucesso.");

        return data;
    }

    private List<List<List<Object>>> extractFichaSinteseBrasil(Workbook workbook, Integer sheetNumber, List<Integer> leftColluns, List<Integer> rightColluns, List<String> collunsType) throws IOException {

        // Parâmetros das seções a serem lidas
        List<int[]> ranges = List.of(
                new int[]{5, 5},
                new int[]{7, 9},
                new int[]{11, 16},
                new int[]{28, 32},
                new int[]{34, 36},
                new int[]{45, 49},
                new int[]{51, 55},
                new int[]{57, 61},
                new int[]{64, 71},
                new int[]{73, 75}
        );

        // Lista para consolidar todos os blocos de dados
        List<List<List<Object>>> data = new ArrayList<>();
        data.add(List.of(List.of("Brasil")));

        // Leitura dos dados e consolidação
        for (int[] range : ranges) {

            data.add(
                    service.extractRange(
                            workbook,
                            sheetNumber,
                            range[0],
                            range[1],
                            leftColluns,
                            collunsType,
                            Function.identity()
                    )
            );

        }

        // Parâmetros das seções a serem lidas
        ranges = List.of(
                new int[]{23, 24},
                new int[]{26, 31}
        );

        // Leitura dos dados e consolidação
        for (int[] range : ranges) {

            data.add(
                    service.extractRange(
                            workbook,
                            sheetNumber,
                            range[0],
                            range[1],
                            rightColluns,
                            collunsType,
                            Function.identity()
                    )
            );

        }


        for (List<List<Object>> datum : data) {
            System.out.println(datum);
        }

        System.out.println("\nETL finalizado com sucesso.");

        return data;
    }

    private Ficha_Sintese_Brasil ficha_sintese_brasil;

    private List<List<List<Object>>> extractFichaSinteseEstado(Workbook workbook, String fileName, Integer sheetNumber, List<Integer> leftColluns, List<Integer> rightColluns, List<String> collunsType) throws IOException {

        String sheetName = service.getSheetName(fileName, sheetNumber);
        String estado = sheetName.split("\\s+", 2)[1]; // divide no primeiro espaço

        // Parâmetros das seções a serem lidas
        List<int[]> ranges = List.of(
                new int[]{5, 5},
                new int[]{7, 16},
                new int[]{18, 20},
                new int[]{22, 27},
                new int[]{39, 43},
                new int[]{45, 47},
                new int[]{81, 83}
        );

        // Lista para consolidar todos os blocos de dados
        List<List<List<Object>>> data = new ArrayList<>();
        data.add(List.of(List.of(estado)));

        // Leitura dos dados e consolidação
        for (int[] range : ranges) {

            data.add(
                    service.extractRange(
                            workbook,
                            sheetNumber,
                            range[0],
                            range[1],
                            leftColluns,
                            collunsType,
                            Function.identity()
                    )
            );

        }

        // Parâmetros das seções a serem lidas
        ranges = List.of(
                new int[]{7, 14},
                new int[]{32, 33},
                new int[]{35, 40}
        );

        // Leitura dos dados e consolidação
        for (int[] range : ranges) {

            data.add(
                    service.extractRange(
                            workbook,
                            sheetNumber,
                            range[0],
                            range[1],
                            rightColluns,
                            collunsType,
                            Function.identity()
                    )
            );

        }


        for (List<List<Object>> datum : data) {
            System.out.println(datum);
        }

        System.out.println("\nETL finalizado com sucesso.");

        return data;
    }
}
