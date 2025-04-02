package funcionalidades;

import java.util.List;

/**
 * Classe abstrata que representa um serviço oferecido pela academia, contendo informações
 * sobre o tipo de serviço, valor e instrutor responsável. Fornece métodos para visualizar,
 * cancelar e editar serviços, que podem ser implementados por subclasses.
 * 
 * @author laviniacharrua e iasmintorres
 */
public abstract class Servicos {

    /** Tipo de serviço oferecido. */
    protected String tipoDeServico;

    /** Valor do serviço. */
    protected float valor;

    /** Instrutor responsável pelo serviço. */
    protected String instrutor;

    /**
     * Visualiza informações sobre uma aula específica.
     * Este método deve ser implementado pelas subclasses para retornar os detalhes da aula.
     *
     * @param aula a aula a ser visualizada
     * @return uma lista de strings com as informações da aula
     */
    public List<String> verAula(Aula aula) {
        // Implementar lógica
        return null;
    }

    /**
     * Cancela um serviço específico.
     * Este método deve ser implementado pelas subclasses para cancelar o serviço fornecido.
     *
     * @param servico o serviço a ser cancelado
     */
    public void cancelarServico(Servicos servico) {
        // Implementar lógica
    }

    /**
     * Edita um serviço específico.
     * Este método deve ser implementado pelas subclasses para editar o serviço fornecido.
     *
     * @param servico o serviço a ser editado
     */
    public void editarServico(Servicos servico) {
        // Implementar lógica
    }

    /**
     * Retorna o tipo de serviço.
     *
     * @return o tipo de serviço
     */
    public String getTipoDeServico() {
        return tipoDeServico;
    }

    /**
     * Define o tipo de serviço.
     *
     * @param tipoDeServico o tipo de serviço a ser definido
     */
    public void setTipoDeServico(String tipoDeServico) {
        this.tipoDeServico = tipoDeServico;
    }

    /**
     * Retorna o valor do serviço.
     *
     * @return o valor do serviço
     */
    public float getValor() {
        return valor;
    }

    /**
     * Define o valor do serviço.
     *
     * @param valor o valor a ser definido para o serviço
     */
    public void setValor(float valor) {
        this.valor = valor;
    }

    /**
     * Retorna o nome do instrutor responsável pelo serviço.
     *
     * @return o nome do instrutor
     */
    public String getInstrutor() {
        return instrutor;
    }

    /**
     * Define o instrutor responsável pelo serviço.
     *
     * @param instrutor o nome do instrutor a ser definido
     */
    public void setInstrutor(String instrutor) {
        this.instrutor = instrutor;
    }

    /**
     * Retorna uma representação em string do serviço, contendo o tipo de serviço, valor e instrutor.
     *
     * @return uma string com as informações do serviço
     */
    @Override
    public String toString() {
        return "Servicos{" +
               "tipoDeServico=" + tipoDeServico +
               ", valor=" + valor +
               ", instrutor=" + instrutor +
               '}';
    }
}
