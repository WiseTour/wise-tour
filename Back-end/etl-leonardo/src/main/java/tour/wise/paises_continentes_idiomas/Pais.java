package tour.wise.paises_continentes_idiomas;

import java.util.List;

public class Pais {
    private Integer id_pais;
    private String nome;
    private Continente continente;
    private List<Idioma> idiomas;

    public Pais() {
    }

    public Pais(Integer id_pais, String nome, Continente continente, List<Idioma> idiomas) {
        this.id_pais = id_pais;
        this.nome = nome;
        this.continente = continente;
        this.idiomas = idiomas;
    }

    public Integer getId_pais() {
        return id_pais;
    }

    public void setId_pais(Integer id_pais) {
        this.id_pais = id_pais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Continente getContinente() {
        return continente;
    }

    public void setContinente(Continente continente) {
        this.continente = continente;
    }

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "id_pais=" + id_pais +
                ", nome='" + nome + '\'' +
                ", continente=" + continente +
                ", idiomas=" + idiomas +
                '}';
    }
}
