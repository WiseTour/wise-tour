package tour.wise.entity.base.data.relatorio_turismo_brasil;

public class Servico_Adquirido_Fora_Brasil {
    private Integer id_servico_adquirido_fora_brasil;
    private String servico;

    public Servico_Adquirido_Fora_Brasil() {
    }

    public Servico_Adquirido_Fora_Brasil(Integer id_servico_adquirido_fora_brasil, String servico) {
        this.id_servico_adquirido_fora_brasil = id_servico_adquirido_fora_brasil;
        this.servico = servico;
    }

    public Integer getId_servico_adquirido_fora_brasil() {
        return id_servico_adquirido_fora_brasil;
    }

    public void setId_servico_adquirido_fora_brasil(Integer id_servico_adquirido_fora_brasil) {
        this.id_servico_adquirido_fora_brasil = id_servico_adquirido_fora_brasil;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    @Override
    public String toString() {
        return "Servico_Adquirido_Fora_Brasil{" +
                "id_servico_adquirido_fora_brasil=" + id_servico_adquirido_fora_brasil +
                ", servico='" + servico + '\'' +
                '}';
    }
}
