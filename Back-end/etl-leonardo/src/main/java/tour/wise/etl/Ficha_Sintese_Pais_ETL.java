package tour.wise.etl;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Workbook;
import tour.wise.model.perfil_estimado_turista.relatorio_turismo_brasil.Ficha_Sintese_Pais;
import tour.wise.model.perfil_estimado_turista.relatorio_turismo_brasil.ficha_sintese_brasil.*;
import tour.wise.service.Service;
import tour.wise.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static tour.wise.service.Service.loadWorkbook;

public class Ficha_Sintese_Pais_ETL {

    Util util = new Util();
    Service service = new Service();

    Workbook workbook;

    public void exe(String fileName) throws IOException {

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





    private List<List<List<Object>>> extract(Workbook workbook, String fileName, Integer sheetNumber, List<Integer> leftColluns, List<Integer> rightColluns, List<String> collunsType) throws IOException  {

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



    private Ficha_Sintese_Pais transform(List<List<List<Object>>> data) {
        return new Ficha_Sintese_Pais(
                extractNomePais(data),
                extractAnoPesquisa(data),
                extractMotivosViagem(data),
                extractComposicaoGrupo(data),
                extractGastoMedioMotivo(data),
                extractDestinosMaisVisitados(data),
                extractFontesInformacao(data),
                extractUsoAgenciaViagem(data)
        );
    }

    private String extractNomePais(List<List<List<Object>>> data) {
        return data.get(0).get(0).get(0).toString();
    }

    private int extractAnoPesquisa(List<List<List<Object>>> data) {
        return service.parseToInteger(data.get(1).get(0).get(1).toString());
    }

    private List<Motivo_Viagem> extractMotivosViagem(List<List<List<Object>>> data) {
        List<Motivo_Viagem> motivos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            motivos.add(new Motivo_Viagem(
                    data.get(2).get(i).get(0).toString(),
                    Double.parseDouble(data.get(2).get(i).get(1).toString())
            ));
        }
        for (int i = 0; i < 5; i++) {
            motivos.add(new Motivo_Viagem(
                    data.get(3).get(i).get(0).toString(),
                    Double.parseDouble(data.get(3).get(i).get(1).toString())
            ));
        }
        motivos.add(new Motivo_Viagem(
                "Diversão Noturna",
                Double.parseDouble(data.get(3).get(5).get(1).toString())
        ));
        return motivos;
    }

    private List<Composicao_Grupo_Viagem> extractComposicaoGrupo(List<List<List<Object>>> data) {
        return List.of(
                createComposicaoGrupo(data.get(4).get(0)),
                createComposicaoGrupo(data.get(4).get(1)),
                createComposicaoGrupo(data.get(4).get(2)),
                createComposicaoGrupo(data.get(4).get(3)),
                createComposicaoGrupo(data.get(4).get(4))
        );
    }

    private Composicao_Grupo_Viagem createComposicaoGrupo(List<Object> values) {
        return new Composicao_Grupo_Viagem(
                values.get(0).toString(),
                Double.parseDouble(values.get(1).toString())
        );
    }

    private List<Gasto_Medio_Per_Capita_Brasil_Motivo> extractGastoMedioMotivo(List<List<List<Object>>> data) {
        return data.get(5).stream()
                .map(entry -> new Gasto_Medio_Per_Capita_Brasil_Motivo(
                        entry.get(0).toString(),
                        Double.parseDouble(entry.get(1).toString())))
                .collect(Collectors.toList());
    }

    private List<Destinos_Mais_Visistados_Por_Motivo> extractDestinosMaisVisitados(List<List<List<Object>>> data) {
        return List.of(
                createDestinosPorMotivo("Lazer", data.get(6)),
                createDestinosPorMotivo("Negócios, eventos e convenções", data.get(7)),
                createDestinosPorMotivo("Outros motivos", data.get(8))
        );
    }

    private Destinos_Mais_Visistados_Por_Motivo createDestinosPorMotivo(String motivo, List<List<Object>> destinoData) {
        List<Destino_Mais_Visistado> destinos = destinoData.stream()
                .map(entry -> new Destino_Mais_Visistado(
                        entry.get(0).toString().split(" - ")[1],
                        Double.parseDouble(entry.get(1).toString())
                ))
                .collect(Collectors.toList());
        return new Destinos_Mais_Visistados_Por_Motivo(motivo, destinos);
    }

    private List<Fonte_Informacao> extractFontesInformacao(List<List<List<Object>>> data) {
        return data.get(9).stream()
                .map(entry -> new Fonte_Informacao(
                        entry.get(0).toString(),
                        Double.parseDouble(entry.get(1).toString())))
                .collect(Collectors.toList());
    }

    private List<Utilizacao_Agencia_Viagem> extractUsoAgenciaViagem(List<List<List<Object>>> data) {
        return data.get(10).stream()
                .map(entry -> new Utilizacao_Agencia_Viagem(
                        entry.get(0).toString(),
                        Double.parseDouble(entry.get(1).toString())))
                .collect(Collectors.toList());
    }



}
