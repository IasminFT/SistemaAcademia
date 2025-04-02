package funcionalidades;

import individuo.Aluno;

/**
 * Representa uma avaliação física realizada por um aluno na academia.
 * Contém informações sobre o identificador da avaliação, data, horário e o aluno associado.
 * Esta classe estende {@link Servicos}, indicando que uma avaliação física é um dos serviços oferecidos.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class AvaliacaoFisica extends Servicos {

    private String idAvaliaçao; // Identificador único da avaliação física
    private String horario;
    private String data;
    private Aluno aluno;

    /**
     * Construtor para a classe {@code AvaliacaoFisica}.
     *
     * @param idAvaliaçao o identificador único da avaliação
     * @param horario o horário da avaliação
     * @param data a data da avaliação
     * @param aluno o aluno que realizou a avaliação
     */
    public AvaliacaoFisica(String idAvaliaçao, String horario, String data, Aluno aluno) {
        this.idAvaliaçao = idAvaliaçao;
        this.horario = horario;
        this.data = data;
        this.aluno = aluno;
    }

    /**
     * Retorna o identificador da avaliação.
     *
     * @return o identificador da avaliação
     */
    public String getIdAvaliaçao() {
        return idAvaliaçao;
    }

    /**
     * Define o identificador da avaliação.
     *
     * @param idAvaliaçao o identificador a ser definido para a avaliação
     */
    public void setIdAvaliaçao(String idAvaliaçao) {
        this.idAvaliaçao = idAvaliaçao;
    }

    /**
     * Retorna o horário da avaliação.
     *
     * @return o horário da avaliação
     */
    public String getHorario() {
        return horario;
    }

    /**
     * Define o horário da avaliação.
     *
     * @param horario o horário a ser definido para a avaliação
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }

    /**
     * Retorna a data da avaliação.
     *
     * @return a data da avaliação
     */
    public String getData() {
        return data;
    }

    /**
     * Define a data da avaliação.
     *
     * @param data a data a ser definida para a avaliação
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Retorna o aluno associado à avaliação.
     *
     * @return o aluno que realizou a avaliação
     */
    public Aluno getAluno() {
        return aluno;
    }

    /**
     * Define o aluno associado à avaliação.
     *
     * @param aluno o aluno a ser associado à avaliação
     */
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    /**
     * Retorna uma representação em string da avaliação física, contendo informações do ID,
     * horário, data e aluno.
     *
     * @return uma string com os detalhes da avaliação física
     */
    @Override
    public String toString() {
        return "AvaliacaoFisica{" +
               "idAvaliaçao=" + idAvaliaçao +
               ", horario=" + horario +
               ", data=" + data +
               ", aluno=" + aluno +
               '}';
    }
}
