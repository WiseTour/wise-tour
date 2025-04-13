package tour.wise.unidades_federativa_regioes;

public class Regiao_Brasil {
    private String sigla;
    private String regiao;

    public Regiao_Brasil(String sigla, String regiao) {
        this.sigla = sigla;
        this.regiao = regiao;
    }
    public Regiao_Brasil() {

    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    @Override
    public String toString() {
        return "Regiao_Brasil{" +
                "sigla='" + sigla + '\'' +
                ", regiao='" + regiao + '\'' +
                '}';
    }
}
