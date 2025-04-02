package individuo;

/**
 * Classe abstrata que representa uma pessoa na academia, contendo informações básicas
 * como nome, CPF, telefone, email e endereço. Esta classe é destinada a ser estendida
 * por outras classes que representem tipos específicos de pessoas, como Alunos ou Funcionários.
 * 
 * @author laviniacharrua e iasmintorres
 */
public abstract class Pessoa {

    /** Nome da pessoa. */
    protected String nome;

    /** CPF da pessoa. */
    protected String cpf;

    /** Telefone da pessoa. */
    protected String telefone;

    /** Email da pessoa. */
    protected String email;

    /** Endereço da pessoa. */
    protected String endereco;

    /**
     * Construtor para a classe {@code Pessoa}.
     *
     * @param nome o nome da pessoa
     * @param cpf o CPF da pessoa
     * @param telefone o telefone da pessoa
     * @param email o email da pessoa
     * @param endereco o endereço da pessoa
     */
    public Pessoa(String nome, String cpf, String telefone, String email, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    /**
     * Método abstrato para exibir dados específicos da pessoa.
     * Este método deve ser implementado pelas subclasses.
     *
     * @return uma string com os dados específicos da pessoa
     */
    public abstract String exibirDados();

    // Getters e Setters

    /**
     * Retorna o nome da pessoa.
     *
     * @return o nome da pessoa
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da pessoa.
     *
     * @param nome o novo nome a ser atribuído
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o CPF da pessoa.
     *
     * @return o CPF da pessoa
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF da pessoa.
     *
     * @param cpf o novo CPF a ser atribuído
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Retorna o telefone da pessoa.
     *
     * @return o telefone da pessoa
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone da pessoa.
     *
     * @param telefone o novo telefone a ser atribuído
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Retorna o email da pessoa.
     *
     * @return o email da pessoa
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email da pessoa.
     *
     * @param email o novo email a ser atribuído
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna o endereço da pessoa.
     *
     * @return o endereço da pessoa
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço da pessoa.
     *
     * @param endereco o novo endereço a ser atribuído
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Retorna uma representação em string da pessoa, incluindo nome, CPF, telefone, email e endereço.
     *
     * @return uma string com os dados básicos da pessoa
     */
    @Override
    public String toString() {
        return "Pessoa{" +
               "nome=" + nome +
               ", cpf=" + cpf +
               ", telefone=" + telefone +
               ", email=" + email +
               ", endereco=" + endereco +
               '}';
    }
}
