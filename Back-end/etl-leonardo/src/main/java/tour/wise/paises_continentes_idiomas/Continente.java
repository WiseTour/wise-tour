package tour.wise.paises_continentes_idiomas;

public class Continente {
    private Integer id_continente;
    private String nome;

    public Continente() {
    }

    public Continente(Integer id_continente, String nome) {
        this.id_continente = id_continente;
        this.nome = nome;
    }

    public Integer getId_continente() {
        return id_continente;
    }

    public void setId_continente(Integer id_continente) {
        this.id_continente = id_continente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Continente{" +
                "id_continente=" + id_continente +
                ", nome='" + nome + '\'' +
                '}';
    }
}
