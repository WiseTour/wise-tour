package tour.wise.model;

public class Idioma {
   private Integer id_idioma;
   private String nome;

    public Idioma() {
    }

    public Idioma(Integer id_idioma, String nome) {
        this.id_idioma = id_idioma;
        this.nome = nome;
    }

    public Integer getId_idioma() {
        return id_idioma;
    }

    public void setId_idioma(Integer id_idioma) {
        this.id_idioma = id_idioma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Idioma{" +
                "id_idioma=" + id_idioma +
                ", nome='" + nome + '\'' +
                '}';
    }
}
