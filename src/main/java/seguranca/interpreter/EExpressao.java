package seguranca.interpreter;

import individuo.Funcionario;

/**
 * Implementa a expressão lógica "E" para o padrão de projeto Interpreter.
 * Avalia duas expressões booleanas e retorna verdadeiro apenas se ambas
 * as expressões forem verdadeiras para um determinado funcionário.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class EExpressao implements Expressao {

    private Expressao expr1;
    private Expressao expr2;

    /**
     * Construtor para a classe {@code EExpressao}.
     *
     * @param expr1 a primeira expressão a ser avaliada
     * @param expr2 a segunda expressão a ser avaliada
     */
    public EExpressao(Expressao expr1, Expressao expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    /**
     * Interpreta a expressão lógica "E", retornando verdadeiro apenas se ambas
     * as expressões forem verdadeiras para o funcionário fornecido.
     *
     * @param funcionario o funcionário para o qual a expressão será avaliada
     * @return {@code true} se ambas as expressões forem verdadeiras, caso contrário, {@code false}
     */
    @Override
    public boolean interpretar(Funcionario funcionario) {
        return expr1.interpretar(funcionario) && expr2.interpretar(funcionario);
    }
}
