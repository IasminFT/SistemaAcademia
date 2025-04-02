package individuo;

/**
 * Representa um funcionário da academia, com informações sobre salário, senha, cargo e ID.
 * A classe fornece métodos para autenticação e exibição de dados específicos do funcionário.
 * Herda características da classe {@link Pessoa}.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class Funcionario extends Pessoa {

    private float salario;
    private String senha;
    private final int ID;
    private String cargo; // Exemplo: "Funcionario" ou "Admin"
    
    private static int contadorFuncionario = 1; // Inicia o contador de ID em 1

    /**
     * Construtor para a classe {@code Funcionario}.
     * Atribui um ID exclusivo para cada instância de funcionário.
     *
     * @param nome o nome do funcionário
     * @param cpf o CPF do funcionário
     * @param telefone o telefone do funcionário
     * @param email o email do funcionário
     * @param endereco o endereço do funcionário
     * @param salario o salário do funcionário
     * @param senha a senha do funcionário para fins de autenticação
     * @param cargo o cargo do funcionário (ex.: "Funcionario" ou "Admin")
     */
    public Funcionario(String nome, String cpf, String telefone, String email, String endereco, float salario, String senha, String cargo) {
        super(nome, cpf, telefone, email, endereco);
        this.salario = salario;
        this.senha = senha;
        this.cargo = (cargo != null && !cargo.isEmpty()) ? cargo : "Funcionario";
        this.ID = contadorFuncionario;
        contadorFuncionario++;
    }

    /**
     * Retorna o salário do funcionário.
     *
     * @return o salário do funcionário
     */
    public float getSalario() {
        return salario;
    }

    /**
     * Define o salário do funcionário.
     *
     * @param salario o novo salário a ser atribuído
     */
    public void setSalario(float salario) {
        this.salario = salario;
    }

    /**
     * Retorna a senha do funcionário.
     *
     * @return a senha do funcionário
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do funcionário.
     *
     * @param senha a nova senha a ser atribuída
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Retorna o identificador único (ID) do funcionário.
     *
     * @return o ID do funcionário
     */
    public int getID() {
        return ID;
    }

    /**
     * Retorna o cargo do funcionário.
     *
     * @return o cargo do funcionário
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Define o cargo do funcionário.
     *
     * @param cargo o novo cargo a ser atribuído
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * Retorna uma representação em string do funcionário, incluindo ID, nome, cargo e salário.
     *
     * @return uma string com as informações do funcionário
     */
    @Override
    public String toString() {
        return "Funcionario{" + "ID=" + ID + ", Nome=" + nome + ", Cargo=" + cargo + ", Salario=" + salario + '}';
    }

    /**
     * Exibe dados específicos do funcionário, incluindo nome, cargo e salário.
     *
     * @return uma string com os dados do funcionário
     */
    @Override
    public String exibirDados() {
        return "Funcionario - Nome: " + nome + ", Cargo: " + cargo + ", Salário: " + salario;
    }

    /**
     * Método para autenticar o funcionário com base na senha.
     *
     * @param senha a senha fornecida para autenticação
     * @return {@code true} se a senha estiver correta, caso contrário, {@code false}
     */
    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }
}
