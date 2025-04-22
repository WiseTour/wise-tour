package tour.wise.etl.perfil_estimado_turista_etl;

import tour.wise.model.perfil_estimado_turistas.Perfil_Estimado_Turistas;
import tour.wise.model.perfil_estimado_turistas.fichas_sintese.Ficha_Sintese_Pais;
import tour.wise.model.perfil_estimado_turistas.fichas_sintese.ficha_sintese_brasil.*;
import tour.wise.model.perfil_estimado_turistas.fichas_sintese.ficha_sintese_estado.Ficha_Sintese_Estado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Perfil_Estimado_Turistas_ETL {


    String fileNameFichaSintesePais = "../../database/dados-originais/demanda-turistica-internacional/demanda_turistica_internacional_-_fichas_sinteses_2015-2019/05 - Ficha Síntese Países 2015-2019_DIVULGAÇÃO.xlsx";
    String fileNameFichaSinteseBrasil = "../../database/dados-originais/demanda-turistica-internacional/demanda_turistica_internacional_-_fichas_sinteses_2015-2019/01 - Ficha Síntese Brasil - 2015-2019_DIVULGAÇÃO.xlsx";
    String fileNameFichaSinteseEstado = "../../database/dados-originais/demanda-turistica-internacional/demanda_turistica_internacional_-_fichas_sinteses_2015-2019/06 - Ficha Síntese UF 2015-2019_DIVULGAÇÃO.xlsx";


    public List<Perfil_Estimado_Turistas> extractTransform(String fonte_perfil, String fonte_chegadas) throws IOException {

        Ficha_Sintese_Brasil_ET ficha_sintese_brasil_etl = new Ficha_Sintese_Brasil_ET();

        List<Ficha_Sintese_Brasil> fichas_sintese_brasil = ficha_sintese_brasil_etl.extractTransform(fileNameFichaSinteseBrasil, 0, 0);

        Ficha_Sintese_Pais_ET ficha_sintese_pais_etl = new Ficha_Sintese_Pais_ET();

        List<Ficha_Sintese_Pais> fichas_sintese_por_pais = ficha_sintese_pais_etl.extractTransformFicha_Sintese_Pais(fileNameFichaSintesePais,0, 0);

        Ficha_Sintese_Estado_ET ficha_sintese_estado_etl = new Ficha_Sintese_Estado_ET();

        List<Ficha_Sintese_Estado> fichas_sintese_por_estado =  ficha_sintese_estado_etl.extractTransformFicha_Sintese_Estado(fileNameFichaSinteseEstado, 0, 0);


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

        Integer totalChegadas = 100000000;

        List<Perfil_Estimado_Turistas>  perfis_estimado_turistas = new ArrayList<>();

        String pais = "Alemanha";
        String estado = "Alagoas";
        Integer ano = 2015;

        for (Ficha_Sintese_Brasil ficha_sintese_pais : fichas_sintese_por_pais) {

            if(((Ficha_Sintese_Pais) ficha_sintese_pais).getPais().equalsIgnoreCase(pais) && ficha_sintese_pais.getAno().equals(ano)){

                for (Fonte_Informacao fonte_informacao_pais : ficha_sintese_pais.getFontes_informacao()) {

                    for (Composicao_Grupo_Viagem composicao_grupo_viagem_pais : ficha_sintese_pais.getComposicao_grupos_viagem()) {

                        for (Utilizacao_Agencia_Viagem utilizacao_agencia_viagem_pais : ficha_sintese_pais.getUtilizacao_agencia_viagem()) {

                            for (Motivo_Viagem motivo_viagem_pais : ficha_sintese_pais.getMotivos()) {

                                Double totalChegadasFiltrada = (
                                        totalChegadas // totalChegadas = TOTAL DE CHEGADAS DAQUELE ESTADO DAQUELA NACIONALIDADE (PESQUISAR NO BD)
                                                * fonte_informacao_pais.getPorcentagem()/100
                                                * composicao_grupo_viagem_pais.getPorcentagem()/100
                                                * utilizacao_agencia_viagem_pais.getPorcentagem()/100
                                                * motivo_viagem_pais.getPorcentagem()
                                );

                                for (Destinos_Mais_Visistados_Por_Motivo destinosMaisVisistadosPorMotivo : ficha_sintese_pais.getDestinos_mais_visistados_por_motivo()) {
                                    if(destinosMaisVisistadosPorMotivo.getMotivo().equalsIgnoreCase(motivo_viagem_pais.getMotivo())){
                                        for (Destino_Mais_Visistado destino : destinosMaisVisistadosPorMotivo.getDestinos_mais_visistado()) {
                                            if(destino.getDestino().equalsIgnoreCase(estado)){
                                                totalChegadasFiltrada *= destino.getPorcentagem()/100;
                                            }
                                        }
                                    }
                                }

                                if(motivo_viagem_pais.getMotivo().equalsIgnoreCase("Lazer")){

                                    for (Motivacao_Viagem_Lazer motivacao_viagem_lazer_pais : ficha_sintese_pais.getMotivacoes_viagem_lazer()) {

                                        totalChegadasFiltrada *= (motivacao_viagem_lazer_pais.getPorcentagem()/100);

                                        for (Ficha_Sintese_Estado fichaSinteseEstado : fichas_sintese_por_estado) {

                                            if(fichaSinteseEstado.getUf_destino().equalsIgnoreCase(estado) && fichaSinteseEstado.getAno().equals(ficha_sintese_pais.getAno())){

                                                Fonte_Informacao fonte_informacao_estado = fichaSinteseEstado.getFontes_informacao().stream()
                                                        .filter(item -> item.getFonte().equalsIgnoreCase(fonte_informacao_pais.getFonte()))
                                                        .findFirst()
                                                        .orElse(null);

                                                Composicao_Grupo_Viagem composicao_grupo_viagem_estado = fichaSinteseEstado.getComposicao_grupos_viagem().stream()
                                                        .filter(item -> item.getComposicao().equalsIgnoreCase(composicao_grupo_viagem_pais.getComposicao()))
                                                        .findFirst()
                                                        .orElse(null);

                                                Utilizacao_Agencia_Viagem utilizacao_agencia_viagem_estado = fichaSinteseEstado.getUtilizacao_agencia_viagem().stream()
                                                        .filter(item -> item.getTipo().equalsIgnoreCase(utilizacao_agencia_viagem_pais.getTipo()))
                                                        .findFirst()
                                                        .orElse(null);

                                                Motivo_Viagem motivo_viagem_estado = fichaSinteseEstado.getMotivos().stream()
                                                        .filter(item -> item.getMotivo().equalsIgnoreCase(motivo_viagem_pais.getMotivo()))
                                                        .findFirst()
                                                        .orElse(null);

                                                Motivacao_Viagem_Lazer motivacao_viagem_lazer_estado = fichaSinteseEstado.getMotivacoes_viagem_lazer().stream()
                                                        .filter(item -> item.getMotivacao().equalsIgnoreCase(motivacao_viagem_lazer_pais.getMotivacao()))
                                                        .findFirst()
                                                        .orElse(null);

                                                if(fonte_informacao_estado != null) {

                                                    totalChegadasFiltrada *= (fonte_informacao_estado.getPorcentagem()/100);

                                                }

                                                if(composicao_grupo_viagem_estado != null) {

                                                    totalChegadasFiltrada *= (composicao_grupo_viagem_estado.getPorcentagem()/100);

                                                }

                                                if(utilizacao_agencia_viagem_estado != null) {

                                                    totalChegadasFiltrada *= (utilizacao_agencia_viagem_estado.getPorcentagem()/100);

                                                }

                                                if(motivo_viagem_estado != null) {

                                                    totalChegadasFiltrada *= (motivo_viagem_estado.getPorcentagem()/100);

                                                }


                                                if(motivacao_viagem_lazer_estado != null) {

                                                    totalChegadasFiltrada *= (
                                                            motivacao_viagem_lazer_pais.getPorcentagem()/100
                                                                    * motivacao_viagem_lazer_estado.getPorcentagem()/100

                                                    );

                                                }

                                                if((int) Math.round(totalChegadasFiltrada) > 0){
                                                    perfis_estimado_turistas.add(
                                                            new Perfil_Estimado_Turistas(
                                                                    fonte_perfil,
                                                                    fonte_chegadas,
                                                                    (int) Math.round(totalChegadasFiltrada),
                                                                    ficha_sintese_pais.getAno(),
                                                                    fichaSinteseEstado.getUf_destino(),
                                                                    ((Ficha_Sintese_Pais) ficha_sintese_pais).getPais(),
                                                                    null,
                                                                    null,
                                                                    composicao_grupo_viagem_pais.getComposicao(),
                                                                    fonte_informacao_pais.getFonte(),
                                                                    utilizacao_agencia_viagem_pais.getTipo(),
                                                                    motivo_viagem_pais.getMotivo(),
                                                                    motivacao_viagem_lazer_pais.getMotivacao()
                                                            )
                                                    );
                                                }
                                            }

                                        }

                                    }

                                }else {

                                    for (Ficha_Sintese_Estado fichaSinteseEstado : fichas_sintese_por_estado) {

                                        if(fichaSinteseEstado.getUf_destino().equalsIgnoreCase(estado) && fichaSinteseEstado.getAno().equals(ficha_sintese_pais.getAno())){

                                            Fonte_Informacao fonte_informacao_estado = fichaSinteseEstado.getFontes_informacao().stream()
                                                    .filter(item -> item.getFonte().equalsIgnoreCase(fonte_informacao_pais.getFonte()))
                                                    .findFirst()
                                                    .orElse(null);

                                            Composicao_Grupo_Viagem composicao_grupo_viagem_estado = fichaSinteseEstado.getComposicao_grupos_viagem().stream()
                                                    .filter(item -> item.getComposicao().equalsIgnoreCase(composicao_grupo_viagem_pais.getComposicao()))
                                                    .findFirst()
                                                    .orElse(null);

                                            Utilizacao_Agencia_Viagem utilizacao_agencia_viagem_estado = fichaSinteseEstado.getUtilizacao_agencia_viagem().stream()
                                                    .filter(item -> item.getTipo().equalsIgnoreCase(utilizacao_agencia_viagem_pais.getTipo()))
                                                    .findFirst()
                                                    .orElse(null);

                                            Motivo_Viagem motivo_viagem_estado = fichaSinteseEstado.getMotivos().stream()
                                                    .filter(item -> item.getMotivo().equalsIgnoreCase(motivo_viagem_pais.getMotivo()))
                                                    .findFirst()
                                                    .orElse(null);


                                            if(fonte_informacao_estado != null) {

                                                totalChegadasFiltrada *= (fonte_informacao_estado.getPorcentagem()/100);

                                            }

                                            if(composicao_grupo_viagem_estado != null) {

                                                totalChegadasFiltrada *= (composicao_grupo_viagem_estado.getPorcentagem()/100);

                                            }

                                            if(utilizacao_agencia_viagem_estado != null) {

                                                totalChegadasFiltrada *= (utilizacao_agencia_viagem_estado.getPorcentagem()/100);

                                            }

                                            if(motivo_viagem_estado != null) {

                                                totalChegadasFiltrada *= (motivo_viagem_estado.getPorcentagem()/100);

                                            }

                                            if((int) Math.round(totalChegadasFiltrada) > 0){
                                                perfis_estimado_turistas.add(
                                                        new Perfil_Estimado_Turistas(
                                                                fonte_perfil,
                                                                fonte_chegadas,
                                                                (int) Math.round(totalChegadasFiltrada),
                                                                ficha_sintese_pais.getAno(),
                                                                fichaSinteseEstado.getUf_destino(),
                                                                ((Ficha_Sintese_Pais) ficha_sintese_pais).getPais(),
                                                                null,
                                                                null,
                                                                composicao_grupo_viagem_pais.getComposicao(),
                                                                fonte_informacao_pais.getFonte(),
                                                                utilizacao_agencia_viagem_pais.getTipo(),
                                                                motivo_viagem_pais.getMotivo(),
                                                                null
                                                        )
                                                );
                                            }
                                        }

                                    }




                                }
                            }


                        }

                    }

                }

            }

        }

        return perfis_estimado_turistas;
    }

}
