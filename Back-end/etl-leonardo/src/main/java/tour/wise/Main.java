package tour.wise;


import tour.wise.etl.Chegada_Turistas_Internacionais_Brasil_ETL;
import tour.wise.etl.perfil_estimado_turista_etl.Perfil_Estimado_Turistas_ETL;

import java.io.IOException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {


        Perfil_Estimado_Turistas_ETL relatorioTurismoBrasilEtl = new Perfil_Estimado_Turistas_ETL();

        relatorioTurismoBrasilEtl.extractTransform("Ministerio do Turismo","Ministerio do Turismo");

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
