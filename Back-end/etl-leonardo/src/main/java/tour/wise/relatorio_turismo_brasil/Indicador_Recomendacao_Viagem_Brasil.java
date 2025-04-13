package tour.wise.relatorio_turismo_brasil;

public class Indicador_Recomendacao_Viagem_Brasil extends Relatorio_Turismo_Brasil{
    private Integer id_indicador_recomendacao_viagem_brasil;
    private Integer recomendacoes_positivas;
    private Integer recomendacoes_negativas;
    private Integer recomendacoes_indecisas;
    private Relatorio_Turismo_Brasil relatorio_turismo_brasil;

    public Indicador_Recomendacao_Viagem_Brasil() {
    }

    public Indicador_Recomendacao_Viagem_Brasil(Integer id_indicador_recomendacao_viagem_brasil, Integer recomendacoes_positivas, Integer recomendacoes_negativas, Integer recomendacoes_indecisas, Relatorio_Turismo_Brasil relatorio_turismo_brasil) {
        this.id_indicador_recomendacao_viagem_brasil = id_indicador_recomendacao_viagem_brasil;
        this.recomendacoes_positivas = recomendacoes_positivas;
        this.recomendacoes_negativas = recomendacoes_negativas;
        this.recomendacoes_indecisas = recomendacoes_indecisas;
        this.relatorio_turismo_brasil = relatorio_turismo_brasil;
    }

    public Integer getId_indicador_recomendacao_viagem_brasil() {
        return id_indicador_recomendacao_viagem_brasil;
    }

    public void setId_indicador_recomendacao_viagem_brasil(Integer id_indicador_recomendacao_viagem_brasil) {
        this.id_indicador_recomendacao_viagem_brasil = id_indicador_recomendacao_viagem_brasil;
    }

    public Integer getRecomendacoes_positivas() {
        return recomendacoes_positivas;
    }

    public void setRecomendacoes_positivas(Integer recomendacoes_positivas) {
        this.recomendacoes_positivas = recomendacoes_positivas;
    }

    public Integer getRecomendacoes_negativas() {
        return recomendacoes_negativas;
    }

    public void setRecomendacoes_negativas(Integer recomendacoes_negativas) {
        this.recomendacoes_negativas = recomendacoes_negativas;
    }

    public Integer getRecomendacoes_indecisas() {
        return recomendacoes_indecisas;
    }

    public void setRecomendacoes_indecisas(Integer recomendacoes_indecisas) {
        this.recomendacoes_indecisas = recomendacoes_indecisas;
    }

    public Relatorio_Turismo_Brasil getRelatorio_turismo_brasil() {
        return relatorio_turismo_brasil;
    }

    public void setRelatorio_turismo_brasil(Relatorio_Turismo_Brasil relatorio_turismo_brasil) {
        this.relatorio_turismo_brasil = relatorio_turismo_brasil;
    }

    @Override
    public String toString() {
        return "Indicador_Recomendacao_Viagem_Brasil{" +
                "id_indicador_recomendacao_viagem_brasil=" + id_indicador_recomendacao_viagem_brasil +
                ", recomendacoes_positivas=" + recomendacoes_positivas +
                ", recomendacoes_negativas=" + recomendacoes_negativas +
                ", recomendacoes_indecisas=" + recomendacoes_indecisas +
                ", relatorio_turismo_brasil=" + relatorio_turismo_brasil +
                '}';
    }
}
