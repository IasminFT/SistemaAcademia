package funcionalidades;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma sala da academia onde ocorrem diferentes aulas. 
 * Cada sala possui um nome de aula, um número identificador, uma lista de horários 
 * e um valor associado para a utilização.
* 
 * @author laviniacharrua e iasmintorres
 */
public class Sala {

    private String nomeAula;
    private final int numeroSala; // ID da sala
    private List<String> horarios; // Lista de horários em que as aulas ocorrem
    private double valor = 0;

    /**
     * Construtor para a classe {@code Sala}.
     *
     * @param nomeAula o nome da aula associada à sala
     * @param numeroSala o identificador único da sala
     * @param valor o valor associado à utilização da sala
     */
    public Sala(String nomeAula, int numeroSala, double valor) {
        this.nomeAula = nomeAula;
        this.numeroSala = numeroSala;
        this.horarios = new ArrayList<>();
        this.valor = valor;
    }

    /**
     * Retorna o nome da aula associada à sala.
     *
     * @return o nome da aula
     */
    public String getNomeAula() {
        return nomeAula;
    }

    /**
     * Define o nome da aula associada à sala.
     *
     * @param nomeAula o novo nome da aula
     */
    public void setNomeAula(String nomeAula) {
        this.nomeAula = nomeAula;
    }

    /**
     * Retorna o valor associado à utilização da sala.
     *
     * @return o valor da sala
     */
    public double getValor() {
        return valor;
    }

    /**
     * Retorna o número identificador da sala.
     *
     * @return o número da sala
     */
    public int getNumeroSala() {
        return numeroSala;
    }

    /**
     * Retorna a lista de horários em que as aulas ocorrem na sala.
     *
     * @return a lista de horários das aulas
     */
    public List<String> getHorarios() {
        return horarios;
    }

    /**
     * Adiciona um horário à lista de horários da sala.
     *
     * @param horario o horário a ser adicionado
     */
    public void adicionarHorario(String horario) {
        horarios.add(horario);
    }

    /**
     * Remove um horário da lista de horários da sala.
     *
     * @param horario o horário a ser removido
     */
    public void removerHorario(String horario) {
        horarios.remove(horario);
    }

    /**
     * Retorna uma representação em string da sala, incluindo o nome da aula,
     * número da sala e lista de horários.
     *
     * @return uma string com as informações da sala
     */
    @Override
    public String toString() {
        return "Sala{" +
               "nomeAula='" + nomeAula + '\'' +
               ", numeroSala=" + numeroSala +
               ", horarios=" + horarios +
               '}';
    }
}
