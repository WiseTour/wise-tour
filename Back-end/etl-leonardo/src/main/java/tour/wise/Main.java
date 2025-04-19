package tour.wise;


import tour.wise.etl.Meio_Hospedagem_ETL;
import tour.wise.etl.Relatorio_Turismo_Brasil_ETL;
import tour.wise.util.Util;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {


        Relatorio_Turismo_Brasil_ETL relatorioTurismoBrasilEtl = new Relatorio_Turismo_Brasil_ETL();

        relatorioTurismoBrasilEtl.exe();

    }
}
