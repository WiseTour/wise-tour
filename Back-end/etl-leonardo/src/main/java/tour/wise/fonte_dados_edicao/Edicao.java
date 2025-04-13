package tour.wise.fonte_dados_edicao;

import java.time.LocalDate;

public class Edicao{
    private Integer id_edicao;
    private String titulo;
    private String url;
    private String observacoes;
    private LocalDate data_coleta;
    private Fonte_Dados fonte_dados;

    public Edicao() {
    }

    public Edicao(Integer id_edicao, String titulo, String url, String observacoes, LocalDate data_coleta, Fonte_Dados fonte_dados) {
        this.id_edicao = id_edicao;
        this.titulo = titulo;
        this.url = url;
        this.observacoes = observacoes;
        this.data_coleta = data_coleta;
        this.fonte_dados = fonte_dados;
    }

    public Integer getId_edicao() {
        return id_edicao;
    }

    public void setId_edicao(Integer id_edicao) {
        this.id_edicao = id_edicao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public LocalDate getData_coleta() {
        return data_coleta;
    }

    public void setData_coleta(LocalDate data_coleta) {
        this.data_coleta = data_coleta;
    }

    public Fonte_Dados getEdicao() {
        return fonte_dados;
    }

    public void setEdicao(Fonte_Dados fonte_dados) {
        this.fonte_dados = fonte_dados;
    }

    @Override
    public String toString() {
        return "Edicao{" +
                "id_edicao=" + id_edicao +
                ", titulo='" + titulo + '\'' +
                ", url='" + url + '\'' +
                ", observacoes='" + observacoes + '\'' +
                ", data_coleta=" + data_coleta +
                ", fonte_dados=" + fonte_dados +
                '}';
    }
}
