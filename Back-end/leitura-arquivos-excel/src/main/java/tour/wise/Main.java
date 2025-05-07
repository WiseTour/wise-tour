package tour.wise;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String fileName = "../../database/dados-originais/uf-regioes-brasil/uf-regioes-brasil-siglas.xlsx";

        // Carregando o arquivo excel
        Path caminho = Path.of(fileName);
        InputStream file = Files.newInputStream(caminho);

        // Extraindo os livros do arquivo
        ETL etl = new ETL();
        List<Object> data = etl.extract(fileName, file, 1, 3, List.of("String", "String", "String") );

        // Fechando o arquivo após a extração
        file.close();

        System.out.println("Dados extraídos:");
        for (Object datum : data) {
            System.out.println(datum);
        }
    }
}