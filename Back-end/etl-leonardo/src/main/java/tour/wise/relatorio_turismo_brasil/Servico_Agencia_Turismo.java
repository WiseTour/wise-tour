package tour.wise.relatorio_turismo_brasil;

public class Servico_Agencia_Turismo {
    Integer id_servico_agencia_turismo;
    private String servico;

    public Servico_Agencia_Turismo() {
    }

    public Servico_Agencia_Turismo(Integer id_servico_agencia_turismo, String servico) {
        this.id_servico_agencia_turismo = id_servico_agencia_turismo;
        this.servico = servico;
    }

    public Integer getId_servico_agencia_turismo() {
        return id_servico_agencia_turismo;
    }

    public void setId_servico_agencia_turismo(Integer id_servico_agencia_turismo) {
        this.id_servico_agencia_turismo = id_servico_agencia_turismo;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    @Override
    public String toString() {
        return "Servico_Agencia_Turismo{" +
                "id_servico_agencia_turismo=" + id_servico_agencia_turismo +
                ", servico='" + servico + '\'' +
                '}';
    }
}

