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



    private Ficha_Sintese_Pais transform(List<List<List<Object>>> data){


        Ficha_Sintese_Pais ficha_sintese_pais = new Ficha_Sintese_Pais(
                data.get(0).get(0).get(0).toString(),
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
                                                Double.parseDouble(data.get(8).get(0).get(1).toString())
                                        ),
                                        new Destino_Mais_Visistado(
                                                data.get(8).get(1).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(8).get(1).get(1).toString())
                                        ),
                                        new Destino_Mais_Visistado(
                                                data.get(8).get(2).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(8).get(2).get(1).toString())
                                        ),
                                        new Destino_Mais_Visistado(
                                                data.get(8).get(3).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(8).get(3).get(1).toString())
                                        ),
                                        new Destino_Mais_Visistado(
                                                data.get(8).get(4).get(0).toString().split(" - ")[1],
                                                Double.parseDouble(data.get(8).get(4).get(1).toString())
                                        )
                                )

                        )

                ),
                List.of(
                        new Fonte_Informacao(
                                data.get(9).get(0).get(0).toString(),
                                Double.parseDouble(data.get(9).get(0).get(1).toString())
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
                                Double.parseDouble(data.get(10).get(0).get(1).toString())
                        ),
                        new Utilizacao_Agencia_Viagem(
                                data.get(10).get(1).get(0).toString(),
                                Double.parseDouble(data.get(10).get(1).get(1).toString())
                        ),
                        new Utilizacao_Agencia_Viagem(
                                data.get(10).get(2).get(0).toString(),
                                Double.parseDouble(data.get(10).get(2).get(1).toString())
                        )


                )

        );
        
        return ficha_sintese_pais;
    };


}
