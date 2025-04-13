package tour.wise.via_acesso;

public class Via_Acesso {
    private Integer id_via_acesso;
    private String nome;

    public Via_Acesso() {
    }

    public Via_Acesso(Integer id_via_acesso, String nome) {
        this.id_via_acesso = id_via_acesso;
        this.nome = nome;
    }

    public Integer getId_via_acesso() {
        return id_via_acesso;
    }

    public void setId_via_acesso(Integer id_via_acesso) {
        this.id_via_acesso = id_via_acesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Via_Acesso{" +
                "id_via_acesso=" + id_via_acesso +
                ", nome='" + nome + '\'' +
                '}';
    }
}
