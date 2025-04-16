package tour.wise.etl;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class Extract {

    public List<List<Object>> extract(String fileName, InputStream file, Integer sheetNumber, Integer header, Integer colluns, List<String> types) {
        try {
            System.out.println("\nIniciando leitura do arquivo %s\n".formatted(fileName));

            // Criando um objeto Workbook a partir do arquivo recebido,
            Workbook workbook;
            if (fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(file); //.XLSX
            } else {
                workbook = new HSSFWorkbook(file); //.XLS
            }

            // Pegando a planilha referenciada em "sheetNumber" do arquivo
            Sheet sheet = workbook.getSheetAt(sheetNumber);

            // Criando nova lista
            List<List<Object>> data = new ArrayList<>();

            // Iterando sobre as linhas da planilha
            for (Row row : sheet) {

                if (row.getRowNum() == header) {
                    System.out.println("\nLendo cabeçalho");

                    for (int i = 0; i < colluns; i++) {
                        String coluna = row.getCell(i).getStringCellValue();
                        System.out.println("Coluna " + i + ": " + coluna);
                    }

                    System.out.println("--------------------");
                    continue;
                }

                // Extraindo valor das células e criando objeto Linha
                System.out.println("Lendo linha " + row.getRowNum());

                List<Object> linha = new ArrayList<>();

                for (int i = 0; i < colluns ; i++) {

                    linha.add(transformTypeCell(row.getCell(i), types.get(i)));
                }


                data.add(linha);
            }

            // Fechando o workbook após a leitura
            workbook.close();

            System.out.println("\nLeitura do arquivo finalizada\n");

            return data;


        } catch (IOException e) {
            // Caso ocorra algum erro durante a leitura do arquivo uma exceção será lançada
            throw new RuntimeException(e);
        }
    }

    private static Object transformTypeCell(Cell cell, String tipo) {
        switch (tipo.toLowerCase()) {
            case "string":
                return cell.getStringCellValue();

            case "numeric":
                return cell.getNumericCellValue();

            case "boolean":
                return cell.getBooleanCellValue();

            case "date":
                return transformDate(cell.getDateCellValue());

            default:
                throw new IllegalArgumentException("Tipo de leitura não suportado: " + tipo);
        }
    }

    private static LocalDate transformDate(Date data) {
        return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
