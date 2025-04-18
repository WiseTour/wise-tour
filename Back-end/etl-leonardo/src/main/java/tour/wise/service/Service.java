package tour.wise.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.regex.ParseException;
import tour.wise.model.meio_hospedagem.Meio_Hospedagem;


public class Service {

    public List<List<Object>> extract(String fileName, InputStream file, Integer sheetNumber, Integer header, Integer colluns, List<String> types) {
        try {
            System.out.printf("\nIniciando leitura do arquivo %s\n%n", fileName);

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

    public  <T> List<T> extractRange(
            String fileName,
            InputStream file,
            Integer sheetNumber,
            int startRow,
            int endRow,
            List<Integer> columns, // Índices das colunas a serem lidas
            List<String> types, // Tipos das colunas (na mesma ordem de columns)
            Function<List<Object>, T> mapper
    ) {
        try {
            System.out.println("\nIniciando leitura do arquivo %s\n".formatted(fileName));

            Workbook workbook = fileName.endsWith(".xlsx") ?
                    new XSSFWorkbook(file) :
                    new HSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(sheetNumber);
            List<T> data = new ArrayList<>();

            for (Row row : sheet) {
                int rowNum = row.getRowNum();

                if (rowNum < startRow || rowNum > endRow) {
                    continue;
                }

                System.out.println("Lendo linha " + rowNum);
                List<Object> linha = new ArrayList<>();

                for (int i = 0; i < columns.size(); i++) {
                    int colIndex = columns.get(i);
                    String type = types.get(i);

                    linha.add(transformTypeCell(row.getCell(colIndex), type));
                }

                data.add(mapper.apply(linha));
            }

            workbook.close();
            System.out.println("\nLeitura do arquivo finalizada\n");

            return data;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static Object transformTypeCell(Cell cell, String tipo) {
        if (cell == null) {
            return ""; // ou algum valor padrão, ou até lançar uma exceção personalizada
        }
        switch (tipo.toLowerCase()) {
            case "string":
                return cell.getStringCellValue();

            case "numeric":
                switch (cell.getCellType()) {
                    case NUMERIC:
                        return cell.getNumericCellValue();
                    case STRING:
                        String texto = cell.getStringCellValue().trim();
                        if (texto.equals("-") || texto.isEmpty()) {
                            return 0;
                        }
                        try {
                            return Double.parseDouble(texto);
                        } catch (NumberFormatException e) {
                            return 0; // ou lançar erro, depende da lógica
                        }
                    case BLANK:
                        return 0;
                    default:
                        return 0;
                }

            case "boolean":
                return cell.getBooleanCellValue();

            case "date":
                return cell.getDateCellValue();

            default:
                throw new IllegalArgumentException("Tipo de leitura não suportado: " + tipo);
        }
    }

    public List<Meio_Hospedagem> transformMeioHospedagem(List<List<Object>> data_hospedagem){

        List<Meio_Hospedagem> meios_hospedagem = new ArrayList<>();

        for (List<Object> line : data_hospedagem) {
            meios_hospedagem.add(new Meio_Hospedagem(line.get(1).toString(), null, transformDate(line.get(8).toString()) , null,parseToInteger(line.get(16)), parseToInteger(line.get(17)), parseToInteger(line.get(18)), parseToInteger(line.get(19)), line.get(15).toString(), line.get(6).toString(), "Ministério do Turismo"));
        }

        return meios_hospedagem;
    }

    private static LocalDate transformDate(Object dateObject) {
        if (dateObject == null) return null;

        Date date = null;

        if (dateObject instanceof Date) {
            date = (Date) dateObject;
        } else if (dateObject instanceof String) {
            String str = ((String) dateObject).trim();

            // Tenta primeiro no formato padrão "dd/MM/yyyy"
            try {
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                date = sdf1.parse(str);
            } catch (ParseException | java.text.ParseException e1) {
                // Tenta o formato do toString(): "Tue Mar 18 00:00:00 BRT 1958"
                try {
                    SimpleDateFormat sdf2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                    date = sdf2.parse(str);
                } catch (ParseException | java.text.ParseException e2) {
                    throw new RuntimeException(e2);
                }
            }
        }

        if (date != null) {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        return null;
    }

    private static Integer parseToInteger(Object obj) {
        if (obj == null) return 0;
        try {
            return (int) Double.parseDouble(obj.toString());
        } catch (NumberFormatException e) {
            return 0; // ou lance uma exceção se quiser validar
        }
    }


}
