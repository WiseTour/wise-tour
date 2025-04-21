package tour.wise.etl;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Workbook;
import tour.wise.model.Pais;
import tour.wise.model.Unidade_Federativa_Brasil;
import tour.wise.model.chegada_turistas_internacionais_brasil.Chegada_Turistas_Internacionais_Brasil;
import tour.wise.model.perfil_estimado_turistas.fichas_sintese.ficha_sintese_brasil.Ficha_Sintese_Brasil;
import tour.wise.service.Service;
import tour.wise.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static tour.wise.service.Service.loadWorkbook;

public class Chegada_Turistas_Internacionais_Brasil_ETL {

    Service service = new Service();

    public List<Chegada_Turistas_Internacionais_Brasil> extractTransform(String fileName, Integer sheetNumber, Integer header, Integer colluns, List<String> types, String fonte, Integer edicao) throws IOException {

        // EXTRACT
        List<List<Object>> data = extract(fileName, sheetNumber, header, colluns, types);

        // TRANSFORM

        List<Chegada_Turistas_Internacionais_Brasil> chegadas_turistas_internacionais_brasil = transform(data, fonte, edicao);

        System.out.println();
        System.out.println("Chegadas");

        for (Chegada_Turistas_Internacionais_Brasil chegada_turistas_internacionais_brasil : chegadas_turistas_internacionais_brasil) {
            System.out.println(chegada_turistas_internacionais_brasil);
        }

        return chegadas_turistas_internacionais_brasil;

    }

    public List<List<Object>> extract(String fileName, Integer sheetNumber, Integer header, Integer colluns, List<String> types) {

        List<List<Object>> data = service.extract(fileName, sheetNumber, header, colluns, types);

        System.out.println("\nETL finalizado com sucesso.");
        return data;

    }

    public List<Chegada_Turistas_Internacionais_Brasil> transform( List<List<Object>> data, String fonte, Integer edicao) {

        List<Chegada_Turistas_Internacionais_Brasil> chegadas_turistas_internacionais_brasil = new ArrayList<>();

        for (List<Object> datum : data) {
            Pais pais_origem = new Pais(datum.get(2).toString());
            Unidade_Federativa_Brasil destino = new Unidade_Federativa_Brasil(datum.get(4).toString());
            String via = datum.get(6).toString();
            Integer ano = Double.valueOf(datum.get(10).toString()).intValue();
            Integer mes = Double.valueOf(datum.get(8).toString()).intValue();
            Integer chegadasNum = Double.valueOf(datum.get(11).toString()).intValue();
            chegadas_turistas_internacionais_brasil.add(new Chegada_Turistas_Internacionais_Brasil(destino, pais_origem, via, ano, mes, chegadasNum, fonte, edicao));
        }


        return chegadas_turistas_internacionais_brasil;
    }




}


