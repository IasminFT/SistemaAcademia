package seguranca.interpreter;

import individuo.Admin;
import individuo.Funcionario;

/**
 * Implementa uma expressão para verificar permissões administrativas,
 * de acordo com o padrão de projeto Interpreter. Avalia se o funcionário
 * fornecido possui permissões de administrador.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class PermissaoAdmin implements Expressao {

    /**
     * Interpreta a expressão para verificar se o funcionário é do tipo {@link Admin}.
     *
     * @param funcionario o funcionário a ser avaliado
     * @return {@code true} se o funcionário é uma instância de {@link Admin}, caso contrário, {@code false}
     */
    @Override
    public boolean interpretar(Funcionario funcionario) {
        // Verifica apenas se o Funcionario é do tipo Admin
        return funcionario instanceof Admin;
    }
}
