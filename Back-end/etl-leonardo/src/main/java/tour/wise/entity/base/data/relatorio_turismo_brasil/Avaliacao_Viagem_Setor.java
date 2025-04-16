package tour.wise.entity.base.data.relatorio_turismo_brasil;

public class Avaliacao_Viagem_Setor {
    private Integer id_avaliacao_viagem;
    private String setor;
    private Double avaliacao_media;
    private Relatorio_Turismo_Brasil relatorio_turismo_brasil;

    public Avaliacao_Viagem_Setor() {
    }

    public Avaliacao_Viagem_Setor(Integer id_avaliacao_viagem, String setor, Double avaliacao_media, Relatorio_Turismo_Brasil relatorio_turismo_brasil) {
        this.id_avaliacao_viagem = id_avaliacao_viagem;
        this.setor = setor;
        this.avaliacao_media = avaliacao_media;
        this.relatorio_turismo_brasil = relatorio_turismo_brasil;
    }

    public Integer getId_avaliacao_viagem() {
        return id_avaliacao_viagem;
    }

    public void setId_avaliacao_viagem(Integer id_avaliacao_viagem) {
        this.id_avaliacao_viagem = id_avaliacao_viagem;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Double getAvaliacao_media() {
        return avaliacao_media;
    }

    public void setAvaliacao_media(Double avaliacao_media) {
        this.avaliacao_media = avaliacao_media;
    }

    public Relatorio_Turismo_Brasil getRelatorio_turismo_brasil() {
        return relatorio_turismo_brasil;
    }

    public void setRelatorio_turismo_brasil(Relatorio_Turismo_Brasil relatorio_turismo_brasil) {
        this.relatorio_turismo_brasil = relatorio_turismo_brasil;
    }

    @Override
    public String toString() {
        return "Avaliacao_Viagem_Setor{" +
                "id_avaliacao_viagem=" + id_avaliacao_viagem +
                ", setor='" + setor + '\'' +
                ", avaliacao_media=" + avaliacao_media +
                ", relatorio_turismo_brasil=" + relatorio_turismo_brasil +
                '}';
    }
}
