package tour.wise.etl;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tour.wise.model.perfil_estimado_turista.relatorio_turismo_brasil.ficha_sintese_brasil.*;
import tour.wise.model.perfil_estimado_turista.relatorio_turismo_brasil.ficha_sintese_estado.Ficha_Sintese_Estado;
import tour.wise.service.Service;
import tour.wise.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static tour.wise.service.Service.loadWorkbook;

public class Relatorio_Turismo_Brasil_ETL {

    Util util = new Util();
    Service service = new Service();

    String fileNameFichaSintesePais = "../../database/dados-originais/demanda-turistica-internacional/demanda_turistica_internacional_-_fichas_sinteses_2015-2019/05 - Ficha Síntese Países 2015-2019_DIVULGAÇÃO.xlsx";
    String fileNameFichaSinteseBrasil = "../../database/dados-originais/demanda-turistica-internacional/demanda_turistica_internacional_-_fichas_sinteses_2015-2019/01 - Ficha Síntese Brasil - 2015-2019_DIVULGAÇÃO.xlsx";
    String fileNameFichaSinteseEstado = "../../database/dados-originais/demanda-turistica-internacional/demanda_turistica_internacional_-_fichas_sinteses_2015-2019/06 - Ficha Síntese UF 2015-2019_DIVULGAÇÃO.xlsx";



    Workbook workbook;

    public void exe() throws IOException {

        Ficha_Sintese_Brasil_ETL ficha_sintese_brasil_etl = new Ficha_Sintese_Brasil_ETL();

        ficha_sintese_brasil_etl.exe(fileNameFichaSinteseBrasil);

        Ficha_Sintese_Pais_ETL ficha_sintese_pais_etl = new Ficha_Sintese_Pais_ETL();

        ficha_sintese_pais_etl.exe(fileNameFichaSintesePais);

        Ficha_Sintese_Estado_ETL ficha_sintese_estado_etl = new Ficha_Sintese_Estado_ETL();

        ficha_sintese_estado_etl.exe(fileNameFichaSinteseEstado);


    }





}
