package tour.wise.relatorio_turismo_brasil;

public class Indicador_Renda__Media_Mensal_Turistas {
    private Integer id_indicador_renda_media_mensal;
    private Relatorio_Turismo_Brasil relatorio_turismo_brasil;
    private Double renda_media_familiar_em_reais;
    private Double renda_media_individual_em_reais;

    public Indicador_Renda__Media_Mensal_Turistas() {
    }

    public Indicador_Renda__Media_Mensal_Turistas(Integer id_indicador_renda_media_mensal, Relatorio_Turismo_Brasil relatorio_turismo_brasil, Double renda_media_familiar_em_reais, Double renda_media_individual_em_reais) {
        this.id_indicador_renda_media_mensal = id_indicador_renda_media_mensal;
        this.relatorio_turismo_brasil = relatorio_turismo_brasil;
        this.renda_media_familiar_em_reais = renda_media_familiar_em_reais;
        this.renda_media_individual_em_reais = renda_media_individual_em_reais;
    }

    public Integer getId_indicador_renda_media_mensal() {
        return id_indicador_renda_media_mensal;
    }

    public void setId_indicador_renda_media_mensal(Integer id_indicador_renda_media_mensal) {
        this.id_indicador_renda_media_mensal = id_indicador_renda_media_mensal;
    }

    public Relatorio_Turismo_Brasil getRelatorio_turismo_brasil() {
        return relatorio_turismo_brasil;
    }

    public void setRelatorio_turismo_brasil(Relatorio_Turismo_Brasil relatorio_turismo_brasil) {
        this.relatorio_turismo_brasil = relatorio_turismo_brasil;
    }

    public Double getRenda_media_familiar_em_reais() {
        return renda_media_familiar_em_reais;
    }

    public void setRenda_media_familiar_em_reais(Double renda_media_familiar_em_reais) {
        this.renda_media_familiar_em_reais = renda_media_familiar_em_reais;
    }

    public Double getRenda_media_individual_em_reais() {
        return renda_media_individual_em_reais;
    }

    public void setRenda_media_individual_em_reais(Double renda_media_individual_em_reais) {
        this.renda_media_individual_em_reais = renda_media_individual_em_reais;
    }

    @Override
    public String toString() {
        return "Indicador_Renda__Media_Mensal_Turistas{" +
                "id_indicador_renda_media_mensal=" + id_indicador_renda_media_mensal +
                ", relatorio_turismo_brasil=" + relatorio_turismo_brasil +
                ", renda_media_familiar_em_reais=" + renda_media_familiar_em_reais +
                ", renda_media_individual_em_reais=" + renda_media_individual_em_reais +
                '}';
    }
}
