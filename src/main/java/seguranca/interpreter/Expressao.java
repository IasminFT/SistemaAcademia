package seguranca.interpreter;

import individuo.Funcionario;

/**
 * Interface para o padrão de projeto Interpreter, que define uma expressão
 * lógica a ser interpretada para um funcionário. Implementações dessa interface
 * devem avaliar uma condição específica e retornar um valor booleano.
 * 
 * @author laviniacharrua e iasmintorres
 */
public interface Expressao {

    /**
     * Interpreta a expressão para um determinado funcionário, avaliando se ele
     * atende à condição definida pela implementação.
     *
     * @param funcionario o funcionário para o qual a expressão será avaliada
     * @return {@code true} se a condição da expressão for satisfeita, caso contrário, {@code false}
     */
    boolean interpretar(Funcionario funcionario);

}
