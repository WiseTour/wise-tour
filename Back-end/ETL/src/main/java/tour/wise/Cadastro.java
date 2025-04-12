package tour.wise;

    public class Cadastro {

        private Integer id;
        private String nome;
        private String dataNascimento;
        private String cpf;
        private Integer idade;

        public Cadastro() {
        }

        public Cadastro(Integer id, String nome, String dataNascimento, String cpf, Integer idade) {
            this.id = id;
            this.nome = nome;
            this.dataNascimento = dataNascimento;
            this.cpf = cpf;
            this.idade = idade;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getDataNascimento() {
            return dataNascimento;
        }

        public void setDataNascimento(String dataNascimento) {
            this.dataNascimento = dataNascimento;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String genero) {
            this.cpf = cpf;
        }

        public Integer getIdade() {return idade;}

        public void setIdade(Integer idade) {
            this.idade = idade;
        }

        @Override
        public String toString() {
            return this.nome + " (" + this.dataNascimento + ") - " + this.cpf + " - " + this.idade;
        }
    }
