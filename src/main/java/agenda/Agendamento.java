package agenda;

import funcionalidades.Aula;
import individuo.Aluno;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representa um agendamento de uma aula para um aluno específico em uma data e horário determinados.
 * Cada agendamento possui um identificador único, status e tipo de assinatura.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class Agendamento {
    
    private int id = 0; // Identificador único
    private Aula aula;
    private LocalDate data;
    private LocalTime horario;
    private Aluno aluno;
    private String status; // "Preliminar", "Confirmado", "Cancelado"
    private String tipoAssinatura; // "Diária", "Mensalidade"
    private static int contador = 0;

    /**
     * Construtor da classe {@code Agendamento}.
     *
     * @param aula a aula a ser agendada
     * @param aluno o aluno para o qual o agendamento é feito
     * @param data a data do agendamento
     * @param horario o horário do agendamento
     * @param status o status do agendamento ("Preliminar", "Confirmado" ou "Cancelado")
     * @param tipoAssinatura o tipo de assinatura do aluno ("Diária" ou "Mensalidade")
     */
    public Agendamento(Aula aula, Aluno aluno, LocalDate data, LocalTime horario, String status, String tipoAssinatura) {
        this.aula = aula;
        this.aluno = aluno;
        this.data = data;
        this.horario = horario;
        this.status = status;
        this.tipoAssinatura = tipoAssinatura;
        this.id = contador;
        contador++;
    }

    // Getters e Setters

    /**
     * Retorna o identificador único do agendamento.
     *
     * @return o identificador do agendamento
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna a aula associada ao agendamento.
     *
     * @return a aula do agendamento
     */
    public Aula getAula() {
        return aula;
    }

    /**
     * Define a aula para o agendamento.
     *
     * @param aula a aula a ser definida para o agendamento
     */
    public void setAula(Aula aula) {
        this.aula = aula;
    }

    /**
     * Retorna a data do agendamento.
     *
     * @return a data do agendamento
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Define a data para o agendamento.
     *
     * @param data a data a ser definida para o agendamento
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Retorna o horário do agendamento.
     *
     * @return o horário do agendamento
     */
    public LocalTime getHorario() {
        return horario;
    }

    /**
     * Define o horário para o agendamento.
     *
     * @param horario o horário a ser definido para o agendamento
     */
    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    /**
     * Retorna o aluno associado ao agendamento.
     *
     * @return o aluno do agendamento
     */
    public Aluno getAluno() {
        return aluno;
    }

    /**
     * Define o aluno para o agendamento.
     *
     * @param aluno o aluno a ser definido para o agendamento
     */
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    /**
     * Retorna o status do agendamento.
     *
     * @return o status do agendamento ("Preliminar", "Confirmado" ou "Cancelado")
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define o status para o agendamento.
     *
     * @param status o status a ser definido para o agendamento
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Retorna o tipo de assinatura associado ao agendamento.
     *
     * @return o tipo de assinatura do agendamento ("Diária" ou "Mensalidade")
     */
    public String getTipoAssinatura() {
        return tipoAssinatura;
    }

    /**
     * Define o tipo de assinatura para o agendamento.
     *
     * @param tipoAssinatura o tipo de assinatura a ser definido para o agendamento
     */
    public void setTipoAssinatura(String tipoAssinatura) {
        this.tipoAssinatura = tipoAssinatura;
    }
}
