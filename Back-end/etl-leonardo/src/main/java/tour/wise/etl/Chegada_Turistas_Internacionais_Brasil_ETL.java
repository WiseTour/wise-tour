//package tour.wise.etl;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Chegada_Turistas_Internacionais_Brasil_ETL {
//    String baseDirectory = "../../database/dados-originais/chegada_turistas_ministerio_turismo/";
//
//    List<Chegadas_Turistas_Internacionais_Brasil> todasAsChegadas = new ArrayList<>();
//
//    ETL etl = new ETL();
//
//        for (int ano = 2021; ano <= 2024; ano++) {
//        String fileName = baseDirectory + "chegadas_" + ano + ".xlsx";
//
//        List<Chegadas_Turistas_Internacionais_Brasil> chegadasAno = processaArquivo(fileName, etl, ano);
//        todasAsChegadas.addAll(chegadasAno);
//    }
//
//        System.out.println("Dados extraídos dos arquivos:");
//        todasAsChegadas.forEach(System.out::println);
//
//    public static List<Chegadas_Turistas_Internacionais_Brasil> processaArquivo(String fileName, ETL etl, Integer edicao) {
//        List<Chegadas_Turistas_Internacionais_Brasil> chegadas = new ArrayList<>();
//
//        try {
//            Path caminho = Path.of(fileName);
//            try (InputStream file = Files.newInputStream(caminho)) {
//                List<List<Object>> dataChegadaTuristas = etl.extract(fileName, file, 0, 0, 12,
//                        List.of("String", "Numeric", "String", "Numeric", "String", "Numeric", "String", "Numeric", "Numeric", "String", "Numeric", "Numeric"));
//
//                for (List<Object> line : dataChegadaTuristas) {
//                    String pais = line.get(2).toString();
//                    String uf = line.get(4).toString();
//                    String via = line.get(6).toString();
//                    Integer ano = Double.valueOf(line.get(8).toString()).intValue();
//                    Integer mes = Double.valueOf(line.get(10).toString()).intValue();
//                    Integer chegadasNum = Double.valueOf(line.get(11).toString()).intValue();
//                    String fonte = "Ministério do Turismo";
//
//                    Chegadas_Turistas_Internacionais_Brasil chegada = new Chegadas_Turistas_Internacionais_Brasil(
//                            uf, pais, via, mes, ano, chegadasNum, fonte, edicao);
//
//                    chegadas.add(chegada);
//                }
//            }
//        } catch (IOException e) {
//            System.err.println("Erro ao processar o arquivo: " + fileName + " - " + e.getMessage());
//        }
//
//        return chegadas;
//    }
//}
//
//
