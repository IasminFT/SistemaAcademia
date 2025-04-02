package gestao;

/**
 * Representa uma receita financeira da academia, contendo o nome e o valor da receita.
 * A classe permite a manipulação e consulta dessas informações.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class Receita {

    private String NomeReceita;
    private double valor;

    /**
     * Construtor padrão para a classe {@code Receita}.
     */
    public Receita() {
    }

    /**
     * Retorna o nome da receita.
     *
     * @return o nome da receita
     */
    public String getNomeReceita() {
        return NomeReceita;
    }

    /**
     * Define o nome da receita.
     *
     * @param NomeReceita o nome a ser definido para a receita
     */
    public void setNomeReceita(String NomeReceita) {
        this.NomeReceita = NomeReceita;
    }

    /**
     * Retorna o valor da receita.
     *
     * @return o valor da receita
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor da receita.
     *
     * @param valor o valor a ser definido para a receita
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Retorna uma representação em string da receita, incluindo o nome e o valor da receita.
     *
     * @return uma string com as informações da receita
     */
    @Override
    public String toString() {
        return "Receita{" +
               "NomeReceita=" + NomeReceita +
               ", valor=" + valor +
               '}';
    }
}
