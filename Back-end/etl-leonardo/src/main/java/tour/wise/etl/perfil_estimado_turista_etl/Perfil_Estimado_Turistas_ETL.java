package tour.wise.etl.perfil_estimado_turista_etl;

import org.apache.poi.ss.usermodel.Workbook;
import tour.wise.etl.Chegada_Turistas_Internacionais_Brasil_ETL;
import tour.wise.model.perfil_estimado_turistas.Perfil_Estimado_Turistas;
import tour.wise.model.perfil_estimado_turistas.fichas_sintese.Ficha_Sintese_Pais;
import tour.wise.model.perfil_estimado_turistas.fichas_sintese.ficha_sintese_brasil.Ficha_Sintese_Brasil;
import tour.wise.model.perfil_estimado_turistas.fichas_sintese.ficha_sintese_estado.Ficha_Sintese_Estado;
import tour.wise.service.Service;
import tour.wise.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Perfil_Estimado_Turistas_ETL {

    Util util = new Util();
    Service service = new Service();

    String fileNameFichaSintesePais = "../../database/dados-originais/demanda-turistica-internacional/demanda_turistica_internacional_-_fichas_sinteses_2015-2019/05 - Ficha Síntese Países 2015-2019_DIVULGAÇÃO.xlsx";
    String fileNameFichaSinteseBrasil = "../../database/dados-originais/demanda-turistica-internacional/demanda_turistica_internacional_-_fichas_sinteses_2015-2019/01 - Ficha Síntese Brasil - 2015-2019_DIVULGAÇÃO.xlsx";
    String fileNameFichaSinteseEstado = "../../database/dados-originais/demanda-turistica-internacional/demanda_turistica_internacional_-_fichas_sinteses_2015-2019/06 - Ficha Síntese UF 2015-2019_DIVULGAÇÃO.xlsx";



    Workbook workbook;

    public void extractTransform(String fonte_perfil, String fonte_chegadas) throws IOException {

        Ficha_Sintese_Brasil_ET ficha_sintese_brasil_etl = new Ficha_Sintese_Brasil_ET();

        List<Ficha_Sintese_Brasil> fichas_sintese_brasil = ficha_sintese_brasil_etl.extractTransform(fileNameFichaSinteseBrasil, 0, 0);

        Ficha_Sintese_Pais_ET ficha_sintese_pais_etl = new Ficha_Sintese_Pais_ET();

        List<Ficha_Sintese_Pais> fichas_sintese_por_pais = ficha_sintese_pais_etl.extractTransformFicha_Sintese_Pais(fileNameFichaSintesePais,0, 0);

        Ficha_Sintese_Estado_ET ficha_sintese_estado_etl = new Ficha_Sintese_Estado_ET();

        List<Ficha_Sintese_Estado> fichas_sintese_por_estado =  ficha_sintese_estado_etl.extractTransformFicha_Sintese_Estado(fileNameFichaSinteseEstado, 0, 0);

        Chegada_Turistas_Internacionais_Brasil_ETL chegadaTuristasInternacionaisBrasilEtl = new Chegada_Turistas_Internacionais_Brasil_ETL();

        System.out.println();
        System.out.println("Ficha Sintese Brasil");

        for (Ficha_Sintese_Brasil ficha_sintese_brasil : fichas_sintese_brasil) {
            System.out.println(ficha_sintese_brasil);
        }

        System.out.println();
        System.out.println("Ficha Sintese por Estado");

        for (Ficha_Sintese_Brasil ficha_sintese_estado : fichas_sintese_por_estado) {
            System.out.println(ficha_sintese_estado);
        }

        System.out.println();
        System.out.println("Ficha Sintese por Pais");

        for (Ficha_Sintese_Brasil ficha_sintese_pais : fichas_sintese_por_pais) {
            System.out.println(ficha_sintese_pais);
        }






        // TRANSFORM

        Integer totalChegadas = 1000;

        List<Perfil_Estimado_Turistas>  perfis_estimado_turistas = new ArrayList<>();

        for (Ficha_Sintese_Brasil ficha_sintese_pais : fichas_sintese_por_pais) {
            perfis_estimado_turistas.add(
                    new Perfil_Estimado_Turistas(
                            fonte_perfil,
                            fonte_chegadas,
                            (totalChegadas),
                            

                    )
            );
        };




    }





}
