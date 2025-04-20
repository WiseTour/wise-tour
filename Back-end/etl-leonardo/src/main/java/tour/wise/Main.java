package tour.wise;


import tour.wise.etl.perfil_estimado_turista_etl.Perfil_Estimado_Turistas_ETL;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {


        Perfil_Estimado_Turistas_ETL relatorioTurismoBrasilEtl = new Perfil_Estimado_Turistas_ETL();

        relatorioTurismoBrasilEtl.exe();

    }
}
