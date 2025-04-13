package tour.wise.relatorio_turismo_brasil;

public class Indicador_Motivo_Viagem {
    private Integer id_indicador_motivo_viagem;
    private Double gasto_medio_per_capita_em_reais;
    private Double permanencia_media_dias;
    private Double quantidade_media_destinos_visistados;
    private Relatorio_Turismo_Brasil relatorio_turismo_brasil;
    private Motivo_Viagem motivo_viagem;

    public Indicador_Motivo_Viagem() {
    }

    public Indicador_Motivo_Viagem(Integer id_indicador_motivo_viagem, Double gasto_medio_per_capita_em_reais, Double permanencia_media_dias, Double quantidade_media_destinos_visistados, Relatorio_Turismo_Brasil relatorio_turismo_brasil, Motivo_Viagem motivo_viagem) {
        this.id_indicador_motivo_viagem = id_indicador_motivo_viagem;
        this.gasto_medio_per_capita_em_reais = gasto_medio_per_capita_em_reais;
        this.permanencia_media_dias = permanencia_media_dias;
        this.quantidade_media_destinos_visistados = quantidade_media_destinos_visistados;
        this.relatorio_turismo_brasil = relatorio_turismo_brasil;
        this.motivo_viagem = motivo_viagem;
    }

    public Integer getId_indicador_motivo_viagem() {
        return id_indicador_motivo_viagem;
    }

    public void setId_indicador_motivo_viagem(Integer id_indicador_motivo_viagem) {
        this.id_indicador_motivo_viagem = id_indicador_motivo_viagem;
    }

    public Double getGasto_medio_per_capita_em_reais() {
        return gasto_medio_per_capita_em_reais;
    }

    public void setGasto_medio_per_capita_em_reais(Double gasto_medio_per_capita_em_reais) {
        this.gasto_medio_per_capita_em_reais = gasto_medio_per_capita_em_reais;
    }

    public Double getPermanencia_media_dias() {
        return permanencia_media_dias;
    }

    public void setPermanencia_media_dias(Double permanencia_media_dias) {
        this.permanencia_media_dias = permanencia_media_dias;
    }

    public Double getQuantidade_media_destinos_visistados() {
        return quantidade_media_destinos_visistados;
    }

    public void setQuantidade_media_destinos_visistados(Double quantidade_media_destinos_visistados) {
        this.quantidade_media_destinos_visistados = quantidade_media_destinos_visistados;
    }

    public Relatorio_Turismo_Brasil getRelatorio_turismo_brasil() {
        return relatorio_turismo_brasil;
    }

    public void setRelatorio_turismo_brasil(Relatorio_Turismo_Brasil relatorio_turismo_brasil) {
        this.relatorio_turismo_brasil = relatorio_turismo_brasil;
    }

    public Motivo_Viagem getMotivo_viagem() {
        return motivo_viagem;
    }

    public void setMotivo_viagem(Motivo_Viagem motivo_viagem) {
        this.motivo_viagem = motivo_viagem;
    }

    @Override
    public String toString() {
        return "Indicador_Motivo_Viagem{" +
                "id_indicador_motivo_viagem=" + id_indicador_motivo_viagem +
                ", gasto_medio_per_capita_em_reais=" + gasto_medio_per_capita_em_reais +
                ", permanencia_media_dias=" + permanencia_media_dias +
                ", quantidade_media_destinos_visistados=" + quantidade_media_destinos_visistados +
                ", relatorio_turismo_brasil=" + relatorio_turismo_brasil +
                ", motivo_viagem=" + motivo_viagem +
                '}';
    }
}
