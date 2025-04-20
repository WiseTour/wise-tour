package tour.wise.model.perfil_estimado_turistas.fichas_sintese.ficha_sintese_brasil;

public class Destino_Mais_Visistado {
    private String destino;
    private Double porcentagem;

    public Destino_Mais_Visistado(String destino, Double porcentagem) {
        this.destino = destino;
        this.porcentagem = porcentagem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(Double porcentagem) {
        this.porcentagem = porcentagem;
    }

    @Override
    public String toString() {
        return "Destino_Mais_Visistado{" +
                "destino='" + destino + '\'' +
                ", porcentagem=" + porcentagem +
                '}';
    }
}
