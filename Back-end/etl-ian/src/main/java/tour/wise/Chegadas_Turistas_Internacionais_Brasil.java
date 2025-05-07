package tour.wise;

public class Chegadas_Turistas_Internacionais_Brasil {
    private String unidade_federativa;
    private String pais_origem;
    private String via_acesso;
    private Integer mes;
    private Integer ano;
    private Integer chegadas;
    private String fonte;
    private Integer edicao;


    public Chegadas_Turistas_Internacionais_Brasil(String unidade_federativa, String pais_origem, String via_acesso, Integer mes, Integer ano, Integer chegadas, String fonte, Integer edicao) {
        this.unidade_federativa = unidade_federativa;
        this.pais_origem = pais_origem;
        this.via_acesso = via_acesso;
        this.mes = mes;
        this.ano = ano;
        this.chegadas = chegadas;
        this.fonte = fonte;
        this.edicao = edicao;
    }

    public Chegadas_Turistas_Internacionais_Brasil() {
    }

    public String getUnidade_federativa() {
        return unidade_federativa;
    }

    public void setUnidade_federativa(String unidade_federativa) {
        this.unidade_federativa = unidade_federativa;
    }

    public String getPais_origem() {
        return pais_origem;
    }

    public void setPais_origem(String pais_origem) {
        this.pais_origem = pais_origem;
    }

    public String getVia_acesso() {
        return via_acesso;
    }

    public void setVia_acesso(String via_acesso) {
        this.via_acesso = via_acesso;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getChegadas() {
        return chegadas;
    }

    public void setChegadas(Integer chegadas) {
        this.chegadas = chegadas;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    @Override
    public String toString() {
        return "Chegadas_Turistas_Internacionais_Brasil{" +
                "unidade_federativa='" + unidade_federativa + '\'' +
                ", pais_origem='" + pais_origem + '\'' +
                ", via_acesso='" + via_acesso + '\'' +
                ", mes=" + mes +
                ", ano=" + ano +
                ", chegadas=" + chegadas +
                ", fonte='" + fonte + '\'' +
                ", edicao='" + edicao + '\'' +
                '}';
    }
}
