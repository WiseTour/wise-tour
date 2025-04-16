package tour.wise.model.relatorio_turismo_brasil;
import tour.wise.model.Unidade_Federativa_Brasil;
import tour.wise.model.meio_hospedagem.Tipo_Hospedagem;

import java.util.List;


public class Perfil_Estimado_Visitantes {
    private Integer id_perfil_estimado_visistantes;
    private Unidade_Federativa_Brasil unidade_federativa_brasil;
    private String genero;
    private Integer total_turistas;
    private String faixa_etaria;
    private String escolaridade;
    private String composicao_grupo_familiar;
    private String fonte_informacao_viagem;
    private String frequencia_visitas;
    private String intencao_retorno;
    private String recomendaria_viagem_brasil;
    private Double nivel_satisfacao;
    private String servico_agencia_turismo;
    private String motivo_viagem;
    private Tipo_Hospedagem tipo_hospedagem;
    private Relatorio_Turismo_Brasil relatorio_turismo_brasil;
    private List<Avaliacao_Viagem_Setor> avaliacoes_viagem;
    private List<Servico_Adquirido_Fora_Brasil> lista_servicos_adquiridos_fora_brasil;

    public Perfil_Estimado_Visitantes() {
    }

    public Perfil_Estimado_Visitantes(Integer id_perfil_estimado_visistantes, Unidade_Federativa_Brasil unidade_federativa_brasil, String genero, Integer total_turistas, String faixa_etaria, String escolaridade, String composicao_grupo_familiar, String fonte_informacao_viagem, String frequencia_visitas, String intencao_retorno, String recomendaria_viagem_brasil, Double nivel_satisfacao, String servico_agencia_turismo, String motivo_viagem, Tipo_Hospedagem tipo_hospedagem, Relatorio_Turismo_Brasil relatorio_turismo_brasil, List<Avaliacao_Viagem_Setor> avaliacoes_viagem, List<Servico_Adquirido_Fora_Brasil> lista_servicos_adquiridos_fora_brasil) {
        this.id_perfil_estimado_visistantes = id_perfil_estimado_visistantes;
        this.unidade_federativa_brasil = unidade_federativa_brasil;
        this.genero = genero;
        this.total_turistas = total_turistas;
        this.faixa_etaria = faixa_etaria;
        this.escolaridade = escolaridade;
        this.composicao_grupo_familiar = composicao_grupo_familiar;
        this.fonte_informacao_viagem = fonte_informacao_viagem;
        this.frequencia_visitas = frequencia_visitas;
        this.intencao_retorno = intencao_retorno;
        this.recomendaria_viagem_brasil = recomendaria_viagem_brasil;
        this.nivel_satisfacao = nivel_satisfacao;
        this.servico_agencia_turismo = servico_agencia_turismo;
        this.motivo_viagem = motivo_viagem;
        this.tipo_hospedagem = tipo_hospedagem;
        this.relatorio_turismo_brasil = relatorio_turismo_brasil;
        this.avaliacoes_viagem = avaliacoes_viagem;
        this.lista_servicos_adquiridos_fora_brasil = lista_servicos_adquiridos_fora_brasil;
    }

    public Integer getId_perfil_estimado_visistantes() {
        return id_perfil_estimado_visistantes;
    }

    public void setId_perfil_estimado_visistantes(Integer id_perfil_estimado_visistantes) {
        this.id_perfil_estimado_visistantes = id_perfil_estimado_visistantes;
    }

    public Unidade_Federativa_Brasil getUnidade_federativa_brasil() {
        return unidade_federativa_brasil;
    }

    public void setUnidade_federativa_brasil(Unidade_Federativa_Brasil unidade_federativa_brasil) {
        this.unidade_federativa_brasil = unidade_federativa_brasil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getTotal_turistas() {
        return total_turistas;
    }

    public void setTotal_turistas(Integer total_turistas) {
        this.total_turistas = total_turistas;
    }

    public String getFaixa_etaria() {
        return faixa_etaria;
    }

    public void setFaixa_etaria(String faixa_etaria) {
        this.faixa_etaria = faixa_etaria;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getComposicao_grupo_familiar() {
        return composicao_grupo_familiar;
    }

    public void setComposicao_grupo_familiar(String composicao_grupo_familiar) {
        this.composicao_grupo_familiar = composicao_grupo_familiar;
    }

    public String getFonte_informacao_viagem() {
        return fonte_informacao_viagem;
    }

    public void setFonte_informacao_viagem(String fonte_informacao_viagem) {
        this.fonte_informacao_viagem = fonte_informacao_viagem;
    }

    public String getFrequencia_visitas() {
        return frequencia_visitas;
    }

    public void setFrequencia_visitas(String frequencia_visitas) {
        this.frequencia_visitas = frequencia_visitas;
    }

    public String getIntencao_retorno() {
        return intencao_retorno;
    }

    public void setIntencao_retorno(String intencao_retorno) {
        this.intencao_retorno = intencao_retorno;
    }

    public String getRecomendaria_viagem_brasil() {
        return recomendaria_viagem_brasil;
    }

    public void setRecomendaria_viagem_brasil(String recomendaria_viagem_brasil) {
        this.recomendaria_viagem_brasil = recomendaria_viagem_brasil;
    }

    public Double getNivel_satisfacao() {
        return nivel_satisfacao;
    }

    public void setNivel_satisfacao(Double nivel_satisfacao) {
        this.nivel_satisfacao = nivel_satisfacao;
    }

    public String getServico_agencia_turismo() {
        return servico_agencia_turismo;
    }

    public void setServico_agencia_turismo(String servico_agencia_turismo) {
        this.servico_agencia_turismo = servico_agencia_turismo;
    }

    public String getMotivo_viagem() {
        return motivo_viagem;
    }

    public void setMotivo_viagem(String motivo_viagem) {
        this.motivo_viagem = motivo_viagem;
    }

    public Tipo_Hospedagem getTipo_hospedagem() {
        return tipo_hospedagem;
    }

    public void setTipo_hospedagem(Tipo_Hospedagem tipo_hospedagem) {
        this.tipo_hospedagem = tipo_hospedagem;
    }

    public Relatorio_Turismo_Brasil getRelatorio_turismo_brasil() {
        return relatorio_turismo_brasil;
    }

    public void setRelatorio_turismo_brasil(Relatorio_Turismo_Brasil relatorio_turismo_brasil) {
        this.relatorio_turismo_brasil = relatorio_turismo_brasil;
    }

    public List<Avaliacao_Viagem_Setor> getAvaliacoes_viagem() {
        return avaliacoes_viagem;
    }

    public void setAvaliacoes_viagem(List<Avaliacao_Viagem_Setor> avaliacoes_viagem) {
        this.avaliacoes_viagem = avaliacoes_viagem;
    }

    public List<Servico_Adquirido_Fora_Brasil> getLista_servicos_adquiridos_fora_brasil() {
        return lista_servicos_adquiridos_fora_brasil;
    }

    public void setLista_servicos_adquiridos_fora_brasil(List<Servico_Adquirido_Fora_Brasil> lista_servicos_adquiridos_fora_brasil) {
        this.lista_servicos_adquiridos_fora_brasil = lista_servicos_adquiridos_fora_brasil;
    }

    @Override
    public String toString() {
        return "Perfil_Estimado_Visitantes{" +
                "id_perfil_estimado_visistantes=" + id_perfil_estimado_visistantes +
                ", unidade_federativa_brasil=" + unidade_federativa_brasil +
                ", genero='" + genero + '\'' +
                ", total_turistas=" + total_turistas +
                ", faixa_etaria='" + faixa_etaria + '\'' +
                ", escolaridade='" + escolaridade + '\'' +
                ", composicao_grupo_familiar='" + composicao_grupo_familiar + '\'' +
                ", fonte_informacao_viagem='" + fonte_informacao_viagem + '\'' +
                ", frequencia_visitas='" + frequencia_visitas + '\'' +
                ", intencao_retorno='" + intencao_retorno + '\'' +
                ", recomendaria_viagem_brasil='" + recomendaria_viagem_brasil + '\'' +
                ", nivel_satisfacao=" + nivel_satisfacao +
                ", servico_agencia_turismo='" + servico_agencia_turismo + '\'' +
                ", motivo_viagem='" + motivo_viagem + '\'' +
                ", tipo_hospedagem=" + tipo_hospedagem +
                ", relatorio_turismo_brasil=" + relatorio_turismo_brasil +
                ", avaliacoes_viagem=" + avaliacoes_viagem +
                ", lista_servicos_adquiridos_fora_brasil=" + lista_servicos_adquiridos_fora_brasil +
                '}';
    }
}
