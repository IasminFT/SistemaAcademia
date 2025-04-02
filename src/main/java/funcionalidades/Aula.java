package funcionalidades;

import individuo.Aluno;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma aula oferecida pela academia, com informações sobre o tipo,
 * horários semanais, vagas disponíveis, e preços.
 * Esta classe permite o gerenciamento de alunos registrados na aula.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class Aula {

    private String tipoAula;
    private List<LocalDateTime> horariosSemana;
    private int vagasDisponiveis;
    private List<Aluno> alunosRegistrados = new ArrayList<>();
    private double precoDiaria;
    private double precoMensalidade;

    /**
     * Construtor para a classe {@code Aula}.
     *
     * @param tipoAula o tipo de aula (ex.: "Yoga", "Pilates")
     * @param vagasDisponiveis o número máximo de vagas disponíveis para a aula
     * @param horariosSemana a lista de horários semanais em que a aula é oferecida
     */
    public Aula(String tipoAula, int vagasDisponiveis, List<LocalDateTime> horariosSemana) {
        this.tipoAula = tipoAula;
        this.vagasDisponiveis = vagasDisponiveis;
        this.horariosSemana = horariosSemana;
        this.precoDiaria = precoDiaria;
        this.precoMensalidade = precoMensalidade;
    }

    /**
     * Verifica se há vagas disponíveis para a aula.
     *
     * @return {@code true} se houver vagas disponíveis, caso contrário {@code false}
     */
    public boolean temVagasDisponiveis() {
        return vagasDisponiveis > alunosRegistrados.size();
    }

    /**
     * Registra um aluno na aula, adicionando-o à lista de alunos registrados,
     * caso ainda haja vagas disponíveis.
     *
     * @param aluno o aluno a ser registrado na aula
     * @throws IllegalStateException se não houver vagas disponíveis
     */
    public void registrarAluno(Aluno aluno) {
        if (temVagasDisponiveis()) {
            alunosRegistrados.add(aluno);
        } else {
            throw new IllegalStateException("Não há vagas disponíveis para essa aula.");
        }
    }

    /**
     * Remove um aluno da lista de alunos registrados na aula.
     *
     * @param aluno o aluno a ser removido
     */
    public void removerAluno(Aluno aluno) {
        alunosRegistrados.remove(aluno);
    }

    // Getters

    /**
     * Retorna o tipo de aula.
     *
     * @return o tipo de aula
     */
    public String getTipoAula() {
        return tipoAula;
    }

    /**
     * Retorna os horários semanais em que a aula é oferecida.
     *
     * @return uma lista de horários semanais
     */
    public List<LocalDateTime> getHorariosSemana() {
        return horariosSemana;
    }

    /**
     * Retorna o número máximo de vagas disponíveis para a aula.
     *
     * @return o número de vagas disponíveis
     */
    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    /**
     * Retorna o preço da diária para a aula.
     *
     * @return o preço da diária
     */
    public double getPrecoDiaria() {
        return precoDiaria;
    }

    /**
     * Retorna o preço da mensalidade para a aula.
     *
     * @return o preço da mensalidade
     */
    public double getPrecoMensalidade() {
        return precoMensalidade;
    }
}
