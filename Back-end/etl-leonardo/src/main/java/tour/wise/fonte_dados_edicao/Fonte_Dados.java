package tour.wise.fonte_dados_edicao;

public class Fonte_Dados {
    private Integer id_fonte_dados;
    private String nome;
    private String descricao;
    private String orgao_emissor;

    public Fonte_Dados(Integer id_fonte_dados, String nome, String descricao, String orgao_emissor) {
        this.id_fonte_dados = id_fonte_dados;
        this.nome = nome;
        this.descricao = descricao;
        this.orgao_emissor = orgao_emissor;
    }

    public Fonte_Dados() {
    }

    public int getId() {
        return id_fonte_dados;
    }

    public void setId(Integer id) {
        this.id_fonte_dados = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOrgao_emissor() {
        return orgao_emissor;
    }

    public void setOrgao_emissor(String orgao_emissor) {
        this.orgao_emissor = orgao_emissor;
    }

    @Override
    public String toString() {
        return "Fonte_Dados{" +
                "id_fonte_dados=" + id_fonte_dados +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", orgao_emissor='" + orgao_emissor + '\'' +
                '}';
    }
}
