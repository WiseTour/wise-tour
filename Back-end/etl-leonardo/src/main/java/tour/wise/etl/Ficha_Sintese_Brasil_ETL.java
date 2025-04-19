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

import static tour.wise.service.Service.loadWorkbook;

public class Ficha_Sintese_Brasil_ETL {

    Util util = new Util();
    Service service = new Service();

    String fileNameFichaSinteseBrasil = "../../database/dados-originais/demanda-turistica-internacional/demanda_turistica_internacional_-_fichas_sinteses_2015-2019/01 - Ficha Síntese Brasil - 2015-2019_DIVULGAÇÃO.xlsx";

    Workbook workbook;

    public void exe() throws IOException {
        ZipSecureFile.setMinInflateRatio(0.0001);

        workbook = loadWorkbook(fileNameFichaSinteseBrasil);

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

    private Ficha_Sintese_Brasil transform(List<List<List<Object>>> data){



        Ficha_Sintese_Brasil ficha_sintese_brasil = new Ficha_Sintese_Brasil(
                service.parseToInteger(data.get(1).get(0).get(1).toString()),
                List.of(
                        new Motivo_Viagem(
                                data.get(2).get(0).get(0).toString(),
                                Double.parseDouble(data.get(2).get(0).get(1).toString())
                        ),
                        new Motivo_Viagem(
                                data.get(2).get(1).get(0).toString(),
                                Double.parseDouble(data.get(2).get(1).get(1).toString())
                        ),
                        new Motivo_Viagem(
                                data.get(2).get(2).get(0).toString(),
                                Double.parseDouble(data.get(2).get(2).get(1).toString())
                        ),
                        new Motivo_Viagem(
                                data.get(3).get(0).get(0).toString(),
                                Double.parseDouble(data.get(3).get(0).get(1).toString())
                        ),
                        new Motivo_Viagem(
                                data.get(3).get(1).get(0).toString(),
                                Double.parseDouble(data.get(3).get(1).get(1).toString())
                        ),
                        new Motivo_Viagem(
                                data.get(3).get(2).get(0).toString(),
                                Double.parseDouble(data.get(3).get(2).get(1).toString())
                        ),
                        new Motivo_Viagem(
                                data.get(3).get(3).get(0).toString(),
                                Double.parseDouble(data.get(3).get(3).get(1).toString())
                        ),
                        new Motivo_Viagem(
                                data.get(3).get(4).get(0).toString(),
                                Double.parseDouble(data.get(3).get(4).get(1).toString())
                        ),
                        new Motivo_Viagem(
                                "Diversão Noturna",
                                Double.parseDouble(data.get(3).get(5).get(1).toString())
                        )
                ),
                List.of(
                        new Composicao_Grupo_Viagem(
                                data.get(4).get(0).get(0).toString(),
                                Double.parseDouble(data.get(4).get(0).get(1).toString())
                        ),
                        new Composicao_Grupo_Viagem(
                                data.get(4).get(1).get(0).toString(),
                                Double.parseDouble(data.get(4).get(1).get(1).toString())
                        ),
                        new Composicao_Grupo_Viagem(
                                data.get(4).get(2).get(0).toString(),
                                Double.parseDouble(data.get(4).get(2).get(1).toString())
                        ),new Composicao_Grupo_Viagem(
                                data.get(4).get(4).get(0).toString(),
                                Double.parseDouble(data.get(4).get(1).get(1).toString())
                        ),
                        new Composicao_Grupo_Viagem(
                                data.get(4).get(4).get(0).toString(),
                                Double.parseDouble(data.get(4).get(4).get(1).toString())
                        )
                ),

                List.of(
                        new Gasto_Medio_Per_Capita_Brasil_Motivo(
                                data.get(5).get(0).get(0).toString(),
                                Double.parseDouble(data.get(5).get(0).get(1).toString())
                        ),
                        new Gasto_Medio_Per_Capita_Brasil_Motivo(
                                data.get(5).get(1).get(0).toString(),
                                Double.parseDouble(data.get(5).get(1).get(1).toString())
                        ),
                        new Gasto_Medio_Per_Capita_Brasil_Motivo(
                                data.get(5).get(2).get(0).toString(),
                                Double.parseDouble(data.get(5).get(2).get(1).toString())
                        )
                ),

                List.of(
                        new Destinos_Mais_Visistados_Por_Motivo(
                                "Lazer",
                                List.of(
                                        new Destino_Mais_Visistado(
                                                data.get(6).get(0).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(6).get(0).get(1).toString())
                                        ),
                                        new Destino_Mais_Visistado(
                                                data.get(6).get(1).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(6).get(1).get(1).toString())
                                        ),
                                        new Destino_Mais_Visistado(
                                                data.get(6).get(2).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(6).get(2).get(1).toString())
                                        ),
                                        new Destino_Mais_Visistado(
                                                data.get(6).get(3).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(6).get(3).get(1).toString())
                                        ),
                                        new Destino_Mais_Visistado(
                                                data.get(6).get(4).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(6).get(4).get(1).toString())
                                        )
                                )

                        ),
                        new Destinos_Mais_Visistados_Por_Motivo(
                                "Negócios, eventos e convenções",
                                List.of(
                                        new Destino_Mais_Visistado(
                                                data.get(7).get(0).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(7).get(0).get(1).toString())
                                        ),
                                        new Destino_Mais_Visistado(
                                                data.get(7).get(1).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(7).get(1).get(1).toString())
                                        ),
                                        new Destino_Mais_Visistado(
                                                data.get(7).get(2).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(7).get(2).get(1).toString())
                                        ),
                                        new Destino_Mais_Visistado(
                                                data.get(7).get(3).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(7).get(3).get(1).toString())
                                        ),
                                        new Destino_Mais_Visistado(
                                                data.get(7).get(4).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(7).get(4).get(1).toString())
                                        )
                                )

                        ),
                        new Destinos_Mais_Visistados_Por_Motivo(
                                "Outros motivos",
                                List.of(
                                        new Destino_Mais_Visistado(
                                                data.get(8).get(0).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(7).get(0).get(1).toString())
                                        ),
                                        new Destino_Mais_Visistado(
                                                data.get(8).get(1).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(7).get(1).get(1).toString())
                                        ),
                                        new Destino_Mais_Visistado(
                                                data.get(8).get(2).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(7).get(2).get(1).toString())
                                        ),
                                        new Destino_Mais_Visistado(
                                                data.get(8).get(3).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(7).get(3).get(1).toString())
                                        ),
                                        new Destino_Mais_Visistado(
                                                data.get(8).get(4).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(7).get(4).get(1).toString())
                                        )
                                )

                        )

                ),
                List.of(
                        new Fonte_Informacao(
                                data.get(9).get(0).get(0).toString(),
                                Double.parseDouble(data.get(8).get(0).get(1).toString())
                        ),
                        new Fonte_Informacao(
                                data.get(9).get(1).get(0).toString(),
                                Double.parseDouble(data.get(9).get(1).get(1).toString())
                        ),
                        new Fonte_Informacao(
                                data.get(9).get(2).get(0).toString(),
                                Double.parseDouble(data.get(9).get(2).get(1).toString())
                        ),
                        new Fonte_Informacao(
                                data.get(9).get(3).get(0).toString(),
                                Double.parseDouble(data.get(9).get(3).get(1).toString())
                        ),
                        new Fonte_Informacao(
                                data.get(9).get(4).get(0).toString(),
                                Double.parseDouble(data.get(9).get(4).get(1).toString())
                        ),
                        new Fonte_Informacao(
                                data.get(9).get(5).get(0).toString(),
                                Double.parseDouble(data.get(9).get(5).get(1).toString())
                        ),
                        new Fonte_Informacao(
                                data.get(9).get(6).get(0).toString(),
                                Double.parseDouble(data.get(9).get(6).get(1).toString())
                        ),
                        new Fonte_Informacao(
                                data.get(9).get(7).get(0).toString(),
                                Double.parseDouble(data.get(9).get(7).get(1).toString())
                        )

                ),
                List.of(
                        new Utilizacao_Agencia_Viagem(
                                data.get(10).get(0).get(0).toString(),
                                Double.parseDouble(data.get(9).get(0).get(1).toString())
                        ),
                        new Utilizacao_Agencia_Viagem(
                                data.get(10).get(1).get(0).toString(),
                                Double.parseDouble(data.get(9).get(1).get(1).toString())
                        ),
                        new Utilizacao_Agencia_Viagem(
                                data.get(10).get(2).get(0).toString(),
                                Double.parseDouble(data.get(9).get(2).get(1).toString())
                        )


                )

        );
        
        return ficha_sintese_brasil;
    };


}
