package seguranca.interpreter;

import individuo.Funcionario;

/**
 * Implementa a expressão lógica "OU" para o padrão de projeto Interpreter.
 * Avalia duas expressões booleanas e retorna verdadeiro se pelo menos uma
 * das expressões for verdadeira para um determinado funcionário.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class OuExpressao implements Expressao {

    private Expressao expr1;
    private Expressao expr2;

    /**
     * Construtor para a classe {@code OuExpressao}.
     *
     * @param expr1 a primeira expressão a ser avaliada
     * @param expr2 a segunda expressão a ser avaliada
     */
    public OuExpressao(Expressao expr1, Expressao expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    /**
     * Interpreta a expressão lógica "OU", retornando verdadeiro se pelo menos
     * uma das expressões for verdadeira para o funcionário fornecido.
     *
     * @param funcionario o funcionário para o qual a expressão será avaliada
     * @return {@code true} se pelo menos uma das expressões for verdadeira, caso contrário, {@code false}
     */
    @Override
    public boolean interpretar(Funcionario funcionario) {
        return expr1.interpretar(funcionario) || expr2.interpretar(funcionario);
    }
}
