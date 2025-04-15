package tour.wise.entity.base.data.relatorio_turismo_brasil;

import tour.wise.entity.base.data.Fonte;
import tour.wise.entity.base.data.Pais;

public class Relatorio_Turismo_Brasil{
    private Integer id_relatorio_turismo_brasil;
    private Fonte edicao;
    private Pais pais;
    private Integer ano;

    public Relatorio_Turismo_Brasil() {
    }

    public Relatorio_Turismo_Brasil(Integer id_relatorio_turismo_brasil, Fonte edicao, Pais pais, Integer ano) {
        this.id_relatorio_turismo_brasil = id_relatorio_turismo_brasil;
        this.edicao = edicao;
        this.pais = pais;
        this.ano = ano;
    }

    public Integer getId_relatorio_turismo_brasil() {
        return id_relatorio_turismo_brasil;
    }

    public void setId_relatorio_turismo_brasil(Integer id_relatorio_turismo_brasil) {
        this.id_relatorio_turismo_brasil = id_relatorio_turismo_brasil;
    }

    public Fonte getEdicao() {
        return edicao;
    }

    public void setEdicao(Fonte edicao) {
        this.edicao = edicao;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Relatorio_Turismo_Brasil{" +
                "id_relatorio_turismo_brasil=" + id_relatorio_turismo_brasil +
                ", edicao=" + edicao +
                ", pais=" + pais +
                ", ano=" + ano +
                '}';
    }
}

