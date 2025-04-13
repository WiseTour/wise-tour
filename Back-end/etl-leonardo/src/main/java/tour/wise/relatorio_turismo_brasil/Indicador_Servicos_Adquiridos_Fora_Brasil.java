package tour.wise.relatorio_turismo_brasil;

import java.util.List;

public class Indicador_Servicos_Adquiridos_Fora_Brasil {
    private List<Servico_Adquirido_Fora_Brasil> servico_adquirido_fora_brasils;

    public Indicador_Servicos_Adquiridos_Fora_Brasil() {
    }

    public Indicador_Servicos_Adquiridos_Fora_Brasil(List<Servico_Adquirido_Fora_Brasil> servico_adquirido_fora_brasils) {
        this.servico_adquirido_fora_brasils = servico_adquirido_fora_brasils;
    }

    public List<Servico_Adquirido_Fora_Brasil> getServico_adquirido_fora_brasils() {
        return servico_adquirido_fora_brasils;
    }

    public void setServico_adquirido_fora_brasils(List<Servico_Adquirido_Fora_Brasil> servico_adquirido_fora_brasils) {
        this.servico_adquirido_fora_brasils = servico_adquirido_fora_brasils;
    }

    @Override
    public String toString() {
        return "Indicador_Servicos_Adquiridos_Fora_Brasil{" +
                "servico_adquirido_fora_brasils=" + servico_adquirido_fora_brasils +
                '}';
    }
}
