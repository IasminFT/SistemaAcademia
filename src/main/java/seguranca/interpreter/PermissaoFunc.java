package seguranca.interpreter;

import individuo.Funcionario;

/**
 * Implementa uma expressão para verificar permissões de acesso para funcionários comuns,
 * de acordo com o padrão de projeto Interpreter. Permite acesso para funcionários com
 * cargo de "Funcionario" ou "Admin".
 * 
 * @author laviniacharrua e iasmintorres
 */
public class PermissaoFunc implements Expressao {

    /**
     * Interpreta a expressão para verificar se o funcionário possui permissão de acesso limitado.
     * Permite acesso se o cargo do funcionário for "Funcionario" ou "Admin".
     *
     * @param funcionario o funcionário a ser avaliado
     * @return {@code true} se o funcionário possui um cargo com permissão de acesso limitado, caso contrário, {@code false}
     */
    @Override
    public boolean interpretar(Funcionario funcionario) {
        // Permite acesso limitado a Funcionário comum e Admin
        return funcionario.getCargo().equals("Funcionario") || funcionario.getCargo().equals("Admin");
    }
}
