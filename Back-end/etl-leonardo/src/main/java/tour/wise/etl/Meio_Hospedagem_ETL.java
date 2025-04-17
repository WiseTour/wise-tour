package tour.wise.etl;

import tour.wise.model.meio_hospedagem.Meio_Hospedagem;
import tour.wise.service.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Meio_Hospedagem_ETL {

    Service service = new Service();

    List<Meio_Hospedagem> meios_hospedagem;

    public void exe() throws IOException {

        String fileName = "../../database/dados-originais/meios_hospedagem/meio-de-hospedagem (1).xls";

        Path path = Path.of(fileName);

        InputStream excelFile = Files.newInputStream(path);

        List<List<Object>> data_hospedagem = service.extract(fileName, excelFile,0, 0, 20, List.of("string", "string", "string", "string", "string", "string", "string", "string", "date", "string", "string", "string", "string", "date", "string", "string", "numeric", "numeric", "numeric", "numeric"));

        excelFile.close();

        meios_hospedagem = service.transformMeioHospedagem(data_hospedagem);

        for (Meio_Hospedagem meio_hospedagem : meios_hospedagem) {
            System.out.println(meio_hospedagem.toString());
        }
    }

}
