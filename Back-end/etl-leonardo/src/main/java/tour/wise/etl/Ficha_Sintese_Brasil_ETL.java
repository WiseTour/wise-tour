package tour.wise.etl;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Workbook;
import tour.wise.model.perfil_estimado_turista.relatorio_turismo_brasil.ficha_sintese_brasil.*;
import tour.wise.service.Service;
import tour.wise.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static tour.wise.service.Service.loadWorkbook;

public class Ficha_Sintese_Brasil_ETL {

    Util util = new Util();
    Service service = new Service();


    Workbook workbook;

    public void exe( String fileName) throws IOException {
        ZipSecureFile.setMinInflateRatio(0.0001);

        workbook = loadWorkbook(fileName);

        List<List<List<List<Object>>>> data = new ArrayList<>();
        for(Integer j = 0; j <= 4; j++){
            data.add(extract(
                    workbook,
                    1,
                    List.of(1, 3+j),
                    List.of(10, 12+j),
                    List.of("string", "numeric")));

        }

        workbook.close();

        // TRANSFORM

        List<Ficha_Sintese_Brasil> fichas_sintese_brasil = new ArrayList<>();

        for (List<List<List<Object>>> datum : data) {
            fichas_sintese_brasil.add(transform(datum));
            System.out.println(datum);
        }

        System.out.println();
        System.out.println("Ficha Sintese Brasil");

        for (Ficha_Sintese_Brasil ficha_sintese_brasil : fichas_sintese_brasil) {
            System.out.println(ficha_sintese_brasil);
        }

    }


    private List<List<List<Object>>> extract(Workbook workbook, Integer sheetNumber, List<Integer> leftColluns, List<Integer> rightColluns, List<String> collunsType) throws IOException {

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

    private Ficha_Sintese_Brasil transform(List<List<List<Object>>> data) {
        return new Ficha_Sintese_Brasil(
                extractAnoPesquisa(data),
                extractMotivosViagem(data),
                extractComposicaoGrupo(data),
                extractGastoMedioMotivo(data),
                extractDestinosMaisVisitados(data),
                extractFontesInformacao(data),
                extractUsoAgenciaViagem(data)
        );
    }

    private Integer extractAnoPesquisa(List<List<List<Object>>> data) {
        return service.parseToInteger(data.get(1).get(0).get(1).toString());
    }

    private List<Motivo_Viagem> extractMotivosViagem(List<List<List<Object>>> data) {
        List<Motivo_Viagem> motivos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            motivos.add(createMotivoViagem(data.get(2).get(i)));
        }
        for (int i = 0; i < 5; i++) {
            motivos.add(createMotivoViagem(data.get(3).get(i)));
        }
        motivos.add(new Motivo_Viagem(
                "Diversão Noturna",
                Double.parseDouble(data.get(3).get(5).get(1).toString())
        ));
        return motivos;
    }

    private Motivo_Viagem createMotivoViagem(List<Object> values) {
        return new Motivo_Viagem(
                values.get(0).toString(),
                Double.parseDouble(values.get(1).toString())
        );
    }

    private List<Composicao_Grupo_Viagem> extractComposicaoGrupo(List<List<List<Object>>> data) {
        return data.get(4).stream()
                .map(this::createComposicaoGrupo)
                .collect(Collectors.toList());
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
        String[] motivos = {
                "Lazer",
                "Negócios, eventos e convenções",
                "Outros motivos"
        };

        List<Destinos_Mais_Visistados_Por_Motivo> destinosPorMotivo = new ArrayList<>();

        for (int i = 0; i < motivos.length; i++) {
            destinosPorMotivo.add(
                    new Destinos_Mais_Visistados_Por_Motivo(
                            motivos[i],
                            createDestinos(data.get(6 + i))
                    )
            );
        }

        return destinosPorMotivo;
    }

    private List<Destino_Mais_Visistado> createDestinos(List<List<Object>> destinoData) {
        return destinoData.stream()
                .map(entry -> new Destino_Mais_Visistado(
                        entry.get(0).toString().split(" - ")[1],
                        Double.parseDouble(entry.get(1).toString())
                ))
                .collect(Collectors.toList());
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
