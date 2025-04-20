package tour.wise;


import tour.wise.etl.Chegada_Turistas_Internacionais_Brasil_ETL;
import tour.wise.etl.perfil_estimado_turista_etl.Perfil_Estimado_Turistas_ETL;
import tour.wise.model.perfil_estimado_turistas.Perfil_Estimado_Turistas;

import java.io.IOException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {


        Perfil_Estimado_Turistas_ETL relatorioTurismoBrasilEtl = new Perfil_Estimado_Turistas_ETL();

        List<Perfil_Estimado_Turistas> perfies = relatorioTurismoBrasilEtl.extractTransform("Ministerio do Turismo","Ministerio do Turismo");

        Integer totalChegadas = 100000000;

        Integer total_chegadas = 0;

        for (Perfil_Estimado_Turistas perfil : perfies) {

            total_chegadas += perfil.getTotal_chegadas();
            System.out.println();
            System.out.println(perfil);
        }

        System.out.println();
        System.out.println("Total de perfies = " + perfies.size());
        System.out.println();
        System.out.println("Total de chegadas = " + totalChegadas);
        System.out.println();
        System.out.println("Total de chegadas para os estados da lista = " + total_chegadas);
        System.out.println();
        System.out.println("Total de chegadas para de outros estados = " + (totalChegadas - total_chegadas));




//        chegadaTuristasInternacionaisBrasilEtl.extractTransform(
//                "../../database/dados-originais/chegada_turistas_ministerio_turismo/chegadas_2024.xlsx",
//                0,
//                0,
//                12,
//                List.of("String", "Numeric", "String", "Numeric", "String", "Numeric", "String", "Numeric", "Numeric", "String", "Numeric", "Numeric"),
//                "Ministério do Turismo",
//                2024
//
//        );
//



    }
}
