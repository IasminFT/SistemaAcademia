package individuo;

/**
 * Representa um administrador da academia, que herda atributos e métodos da classe {@link Funcionario}.
 * A classe inclui uma senha exclusiva para acesso administrativo e uma função para exibir dados específicos.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class Admin extends Funcionario {
    
    private String senhaAdmin;

    /**
     * Construtor para a classe {@code Admin}.
     *
     * @param nome o nome do administrador
     * @param cpf o CPF do administrador
     * @param telefone o telefone do administrador
     * @param email o email do administrador
     * @param endereco o endereço do administrador
     * @param salario o salário do administrador
     * @param senha a senha administrativa do administrador
     */
    public Admin(String nome, String cpf, String telefone, String email, String endereco, float salario, String senha) {
        super(nome, cpf, telefone, email, endereco, salario, senha, "Admin");
        this.senhaAdmin = senha;
    }

    /**
     * Retorna a senha administrativa do administrador.
     *
     * @return a senha do administrador
     */
    public String getSenhaAdmin() {
        return senhaAdmin;
    }

    /**
     * Retorna uma representação em string do administrador, exibindo apenas o ID e o nome.
     *
     * @return uma string com o ID e o nome do administrador
     */
    @Override
    public String toString() {
        return "Admin{" +
            "ID=" + getID() +
            ", Nome=" + getNome() +
            '}';
    }

    /**
     * Exibe dados específicos do administrador, incluindo a senha administrativa.
     *
     * @return uma string com a senha do administrador
     */
    @Override
    public String exibirDados() {
        return "A senha do administrador é: " + senhaAdmin;
    }
}
