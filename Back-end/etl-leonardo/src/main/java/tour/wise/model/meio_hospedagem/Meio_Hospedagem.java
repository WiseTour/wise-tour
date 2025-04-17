package tour.wise.model.meio_hospedagem;

import java.time.LocalDate;

public class Meio_Hospedagem {

    private String cnpj;
    private String situacao;
    private LocalDate data_abertura;
    private LocalDate data_fechamento;
    private Integer unidades_habitacionais;
    private Integer leitos;
    private Integer unidades_habitacionais_acessiveis;
    private Integer leitos_acessiveis;
    private String tipo_hospedagem;
    private String sigla_uf;
    private String fonte;

    public Meio_Hospedagem() {
    }

    public Meio_Hospedagem(String cnpj, String situacao, LocalDate data_abertura, LocalDate data_fechamento, Integer unidades_habitacionais, Integer leitos, Integer unidades_habitacionais_acessiveis, Integer leitos_acessiveis, String tipo_hospedagem, String sigla_uf, String fonte) {
        this.cnpj = cnpj;
        this.situacao = situacao;
        this.data_abertura = data_abertura;
        this.data_fechamento = data_fechamento;
        this.unidades_habitacionais = unidades_habitacionais;
        this.leitos = leitos;
        this.unidades_habitacionais_acessiveis = unidades_habitacionais_acessiveis;
        this.leitos_acessiveis = leitos_acessiveis;
        this.tipo_hospedagem = tipo_hospedagem;
        this.sigla_uf = sigla_uf;
        this.fonte = fonte;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public LocalDate getData_abertura() {
        return data_abertura;
    }

    public void setData_abertura(LocalDate data_abertura) {
        this.data_abertura = data_abertura;
    }

    public LocalDate getData_fechamento() {
        return data_fechamento;
    }

    public void setData_fechamento(LocalDate data_fechamento) {
        this.data_fechamento = data_fechamento;
    }

    public Integer getUnidades_habitacionais() {
        return unidades_habitacionais;
    }

    public void setUnidades_habitacionais(Integer unidades_habitacionais) {
        this.unidades_habitacionais = unidades_habitacionais;
    }

    public Integer getLeitos() {
        return leitos;
    }

    public void setLeitos(Integer leitos) {
        this.leitos = leitos;
    }

    public Integer getUnidades_habitacionais_acessiveis() {
        return unidades_habitacionais_acessiveis;
    }

    public void setUnidades_habitacionais_acessiveis(Integer unidades_habitacionais_acessiveis) {
        this.unidades_habitacionais_acessiveis = unidades_habitacionais_acessiveis;
    }

    public Integer getLeitos_acessiveis() {
        return leitos_acessiveis;
    }

    public void setLeitos_acessiveis(Integer leitos_acessiveis) {
        this.leitos_acessiveis = leitos_acessiveis;
    }

    public String getTipo_hospedagem() {
        return tipo_hospedagem;
    }

    public void setTipo_hospedagem(String tipo_hospedagem) {
        this.tipo_hospedagem = tipo_hospedagem;
    }

    public String getSigla_uf() {
        return sigla_uf;
    }

    public void setSigla_uf(String sigla_uf) {
        this.sigla_uf = sigla_uf;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    @Override
    public String toString() {
        return "Meio_Hospedagem{" +
                "cnpj='" + cnpj + '\'' +
                ", situacao='" + situacao + '\'' +
                ", data_abertura=" + data_abertura +
                ", data_fechamento=" + data_fechamento +
                ", unidades_habitacionais=" + unidades_habitacionais +
                ", leitos=" + leitos +
                ", unidades_habitacionais_acessiveis=" + unidades_habitacionais_acessiveis +
                ", leitos_acessiveis=" + leitos_acessiveis +
                ", tipo_hospedagem='" + tipo_hospedagem + '\'' +
                ", sigla_uf='" + sigla_uf + '\'' +
                ", fonte='" + fonte + '\'' +
                '}';
    }
}
