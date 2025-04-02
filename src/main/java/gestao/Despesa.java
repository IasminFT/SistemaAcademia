package gestao;

/**
 * Representa uma despesa da academia, contendo o nome e o valor associado à despesa.
* 
* @author laviniacharrua e iasmintorres
*/
public class Despesa {

    private String NomeDespesa;
    private double valor;

    /**
     * Construtor padrão para a classe {@code Despesa}.
     */
    public Despesa() {
    }

    /**
     * Retorna o nome da despesa.
     *
     * @return o nome da despesa
     */
    public String getNomeReceita() {
        return NomeDespesa;
    }

    /**
     * Define o nome da despesa.
     *
     * @param NomeReceita o nome a ser definido para a despesa
     */
    public void setNomeReceita(String NomeReceita) {
        this.NomeDespesa = NomeReceita;
    }

    /**
     * Retorna o valor da despesa.
     *
     * @return o valor da despesa
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor da despesa.
     *
     * @param valor o valor a ser definido para a despesa
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Retorna uma representação em string da despesa, contendo o nome e o valor da despesa.
     *
     * @return uma string com as informações da despesa
     */
    @Override
    public String toString() {
        return "Despesa{" +
               "NomeDespesa=" + NomeDespesa +
               ", valor=" + valor +
               '}';
    }
}
