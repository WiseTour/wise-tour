package tour.wise.meios_hospedagem;

public class Tipo_Hospedagem {
    private Integer id_tipo_hospedagem;
    private String tipo;

    public Tipo_Hospedagem() {
    }

    public Tipo_Hospedagem(Integer id_tipo_hospedagem, String tipo) {
        this.id_tipo_hospedagem = id_tipo_hospedagem;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getId_tipo_hospedagem() {
        return id_tipo_hospedagem;
    }

    public void setId_tipo_hospedagem(Integer id_tipo_hospedagem) {
        this.id_tipo_hospedagem = id_tipo_hospedagem;
    }

    @Override
    public String toString() {
        return "Tipo_Hospedagem{" +
                "id_tipo_hospedagem=" + id_tipo_hospedagem +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
