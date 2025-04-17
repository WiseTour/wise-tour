package tour.wise;


import tour.wise.etl.Meio_Hospedagem_ETL;
import tour.wise.util.Util;

import java.io.File;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        Meio_Hospedagem_ETL meioHospedagemEtl = new Meio_Hospedagem_ETL();

        meioHospedagemEtl.exe();

    }
}
