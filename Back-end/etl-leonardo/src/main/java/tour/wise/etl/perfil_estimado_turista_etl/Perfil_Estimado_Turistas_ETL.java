package tour.wise.etl.perfil_estimado_turista_etl;

import org.apache.poi.ss.usermodel.Workbook;
import tour.wise.service.Service;
import tour.wise.util.Util;

import java.io.IOException;

public class Perfil_Estimado_Turistas_ETL {

    Util util = new Util();
    Service service = new Service();

    String fileNameFichaSintesePais = "../../database/dados-originais/demanda-turistica-internacional/demanda_turistica_internacional_-_fichas_sinteses_2015-2019/05 - Ficha Síntese Países 2015-2019_DIVULGAÇÃO.xlsx";
    String fileNameFichaSinteseBrasil = "../../database/dados-originais/demanda-turistica-internacional/demanda_turistica_internacional_-_fichas_sinteses_2015-2019/01 - Ficha Síntese Brasil - 2015-2019_DIVULGAÇÃO.xlsx";
    String fileNameFichaSinteseEstado = "../../database/dados-originais/demanda-turistica-internacional/demanda_turistica_internacional_-_fichas_sinteses_2015-2019/06 - Ficha Síntese UF 2015-2019_DIVULGAÇÃO.xlsx";



    Workbook workbook;

    public void exe() throws IOException {

        Ficha_Sintese_Brasil_ET ficha_sintese_brasil_etl = new Ficha_Sintese_Brasil_ET();

        ficha_sintese_brasil_etl.extractTransform(fileNameFichaSinteseBrasil);

        Ficha_Sintese_Pais_ET ficha_sintese_pais_etl = new Ficha_Sintese_Pais_ET();

        ficha_sintese_pais_etl.extractTransform(fileNameFichaSintesePais);

        Ficha_Sintese_Estado_ET ficha_sintese_estado_etl = new Ficha_Sintese_Estado_ET();

        ficha_sintese_estado_etl.extractTransform(fileNameFichaSinteseEstado);


    }





}
