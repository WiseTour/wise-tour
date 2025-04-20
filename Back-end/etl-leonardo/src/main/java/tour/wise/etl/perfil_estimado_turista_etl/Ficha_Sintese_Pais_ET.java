package tour.wise.etl.perfil_estimado_turista_etl;

import org.apache.poi.ss.usermodel.Workbook;
import tour.wise.model.perfil_estimado_turistas.fichas_sintese.Ficha_Sintese_Pais;
import tour.wise.model.perfil_estimado_turistas.fichas_sintese.ficha_sintese_brasil.*;
import tour.wise.service.Service;
import tour.wise.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static tour.wise.service.Service.loadWorkbook;

public class Ficha_Sintese_Pais_ET extends Ficha_Sintese_Brasil_ET {

    Util util = new Util();
    Service service = new Service();

    Workbook workbook;

    @Override
    public void extractTransform(String fileName) throws IOException {

        // EXTRACT

        workbook = loadWorkbook(fileName);

        List<List<List<List<Object>>>> data = new ArrayList<>();
        for(Integer i = 1; i < service.getSheetNumber(fileName); i++ ){
            for(Integer j = 0; j <= 4; j++){
                data.add(extract(
                        workbook,
                        fileName,
                        i,
                        List.of(1, 3+j),
                        List.of(10, 12+j),
                        List.of("string", "numeric")));

            }

        }

        workbook.close();

        // TRANSFORM

        List<Ficha_Sintese_Brasil> fichas_sintese_por_pais = new ArrayList<>();

        for (List<List<List<Object>>> datum : data) {
            fichas_sintese_por_pais.add(transform(datum));
        }

        System.out.println();
        System.out.println("Ficha Sintese por Pais");

        for (Ficha_Sintese_Brasil ficha_sintese_pais : fichas_sintese_por_pais) {
            System.out.println(ficha_sintese_pais);
        }



    }


    public List<List<List<Object>>> extract(Workbook workbook, String fileName, Integer sheetNumber, List<Integer> leftColluns, List<Integer> rightColluns, List<String> collunsType) throws IOException  {

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


    @Override
    public Ficha_Sintese_Pais transform(List<List<List<Object>>> data) {
        return new Ficha_Sintese_Pais(
                extractNomePais(data, 0),
                transformAno(data, 1),
                transformMotivosViagem(data, 2),
                transformComposicoesGrupo(data, 4),
                transformGastosMedioMotivo(data, 5),
                transformDestinosMaisVisitadosPorMotivo(data, 6),
                transformFontesInformacao(data, 9),
                transformUsosAgenciaViagem(data, 10)
        );
    }

    protected String extractNomePais(List<List<List<Object>>> data, Integer index) {
        return data.get(index).get(0).get(0).toString();
    }


}
