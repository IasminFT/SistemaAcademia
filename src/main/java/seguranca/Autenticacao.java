package seguranca;

import individuo.Funcionario;

/**
 * Classe responsável pelo sistema de autenticação de funcionários na academia.
 * Permite o login e logout de funcionários e verifica se o usuário logado é um administrador.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class Autenticacao {

    private Funcionario usuarioLogado;
    private boolean isAdmin;

    /**
     * Método para autenticar o usuário com base na senha fornecida.
     * Se a autenticação for bem-sucedida, define o usuário logado e verifica
     * se ele possui o cargo de administrador.
     *
     * @param funcionario o funcionário que está tentando fazer login
     * @param senha a senha fornecida para autenticação
     * @return {@code true} se a autenticação for bem-sucedida, caso contrário, {@code false}
     */
    public boolean login(Funcionario funcionario, String senha) {
        if (funcionario.autenticar(senha)) {
            usuarioLogado = funcionario;
            isAdmin = funcionario.getCargo().equals("Admin");
            return true;
        }
        return false;
    }

    /**
     * Verifica se o usuário logado possui o cargo de administrador.
     *
     * @return {@code true} se o usuário logado é um administrador, caso contrário, {@code false}
     */
    public boolean isAdminLogado() {
        return isAdmin;
    }

    /**
     * Encerra a sessão do usuário atual, realizando o logout.
     * Define o usuário logado e a flag de administrador como {@code null} e {@code false}.
     */
    public void fazerLogout() {
        usuarioLogado = null;
        isAdmin = false;
    }
}
