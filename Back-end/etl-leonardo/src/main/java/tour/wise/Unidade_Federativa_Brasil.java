package tour.wise;

public class Unidade_Federativa_Brasil extends  Regiao_Brasil{
   private String sigla;
   private String unidade_federativa;

    public Unidade_Federativa_Brasil(String sigla_regiao, String regiao, String sigla_uf, String unidade_federativa) {
        super(sigla_regiao, regiao);
        this.sigla = sigla_uf;
        this.unidade_federativa = unidade_federativa;
    }

    public Unidade_Federativa_Brasil() {

    }

    public String getSigla_uf() {
        return sigla;
    }

    public void setSigla_uf(String sigla_uf) {
        this.sigla = sigla_uf;
    }

    public String getUnidade_federativa() {
        return unidade_federativa;
    }

    public void setUnidade_federativa(String unidade_federativa) {
        this.unidade_federativa = unidade_federativa;
    }

    public String toString() {
        return super.toString() + " Unidade_Federativa_Brasil{" +
                "sigla_uf='" + sigla + '\'' +
                ", unidade_federativa='" + unidade_federativa + '\'' +
                '}';
    }
}


