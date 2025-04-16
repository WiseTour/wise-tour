package tour.wise.entity.base.data.relatorio_turismo_brasil;

public class Motivo_Viagem {
    private Integer id_motivo_viagem;
    private String motivo;
    private Motivo_Viagem motivo_viagem;

    public Motivo_Viagem() {
    }

    public Motivo_Viagem(Integer id_motivo_viagem, String motivo, Motivo_Viagem motivo_viagem) {
        this.id_motivo_viagem = id_motivo_viagem;
        this.motivo = motivo;
        this.motivo_viagem = motivo_viagem;
    }

    public Integer getId_motivo_viagem() {
        return id_motivo_viagem;
    }

    public void setId_motivo_viagem(Integer id_motivo_viagem) {
        this.id_motivo_viagem = id_motivo_viagem;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Motivo_Viagem getMotivo_viagem() {
        return motivo_viagem;
    }

    public void setMotivo_viagem(Motivo_Viagem motivo_viagem) {
        this.motivo_viagem = motivo_viagem;
    }

    @Override
    public String toString() {
        return "Motivo_Viagem{" +
                "id_motivo_viagem=" + id_motivo_viagem +
                ", motivo='" + motivo + '\'' +
                ", motivo_viagem=" + motivo_viagem +
                '}';
    }
}
