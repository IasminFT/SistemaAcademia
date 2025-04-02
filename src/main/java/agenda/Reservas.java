package agenda;

import Json.JsonReservas;
import funcionalidades.Aula;
import gestao.Financeiro;
import individuo.Aluno;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar os agendamentos de aulas para alunos, incluindo operações
 * de criação, confirmação, cancelamento e edição de agendamentos.
 * A classe interage com o sistema financeiro para registrar receitas e despesas
 * associadas a esses agendamentos.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class Reservas {

    private List<Agendamento> agendamentosPreliminares = new ArrayList<>();
    private List<Agendamento> agendamentosConfirmados = new ArrayList<>();
    private Financeiro financeiro;
    private JsonReservas jsonReservas = new JsonReservas();

    /**
     * Construtor que inicializa a classe {@code Reservas} com um objeto financeiro
     * e uma instância de {@code JsonReservas} para armazenamento dinâmico.
     *
     * @param financeiro a instância de {@link Financeiro} para registrar receitas e despesas
     */
    public Reservas(Financeiro financeiro) {
        this.financeiro = financeiro;
        this.jsonReservas = new JsonReservas();
    }

    /**
     * Cria um novo agendamento preliminar para um aluno em uma aula específica.
     *
     * @param aula a aula a ser agendada
     * @param aluno o aluno para o qual o agendamento é feito
     * @param data a data do agendamento
     * @param horario o horário do agendamento
     * @param tipoAssinatura o tipo de assinatura do aluno (ex.: "Diária" ou "Mensalidade")
     * @return o objeto {@link Agendamento} criado
     * @throws IllegalStateException se a aula não tiver vagas disponíveis
     */
    public Agendamento criarAgendamento(Aula aula, Aluno aluno, LocalDate data, LocalTime horario, String tipoAssinatura) {
        if (aula.temVagasDisponiveis()) {
            Agendamento agendamento = new Agendamento(aula, aluno, data, horario, "Preliminar", tipoAssinatura);
            agendamentosPreliminares.add(agendamento);            
            return agendamento;
        } else {
            throw new IllegalStateException("A aula não tem vagas disponíveis.");
        }
    }

    /**
     * Busca um agendamento pelo seu identificador único (ID).
     *
     * @param id o identificador único do agendamento
     * @return o objeto {@link Agendamento} correspondente, ou {@code null} se não for encontrado
     */
    public Agendamento buscarAgendamentoPorId(int id) {
        for (Agendamento agendamento : agendamentosPreliminares) {
            if (agendamento.getId() == id) {
                return agendamento;
            }
        }
        
        for (Agendamento agendamento : agendamentosConfirmados) {
            if (agendamento.getId() == id) {
                return agendamento;
            }
        }
        return null;
    }

    /**
     * Confirma um agendamento preliminar, registrando a receita e movendo-o para a lista de confirmados.
     *
     * @param agendamento o agendamento a ser confirmado
     * @throws IllegalStateException se o agendamento já estiver confirmado ou não puder ser confirmado
     */
    public void confirmarAgendamento(Agendamento agendamento) {
        if ("Preliminar".equals(agendamento.getStatus())) {
            agendamento.setStatus("Confirmado");
            double valor = agendamento.getTipoAssinatura().equalsIgnoreCase("Diária")
                ? agendamento.getAula().getPrecoDiaria()
                : agendamento.getAula().getPrecoMensalidade();
            financeiro.registrarReceita(valor, "Receita de agendamento: " + agendamento.getAula().getTipoAula());
            
            agendamentosPreliminares.remove(agendamento);
            agendamentosConfirmados.add(agendamento);

        } else {
            throw new IllegalStateException("Agendamento já está confirmado ou não pode ser confirmado.");
        }
    }

    /**
     * Cancela um agendamento confirmado, podendo gerar um reembolso dependendo do tempo restante para a aula.
     *
     * @param agendamento o agendamento a ser cancelado
     * @throws IllegalStateException se o agendamento não estiver confirmado
     */
    public void cancelarAgendamento(Agendamento agendamento) {
        if ("Confirmado".equals(agendamento.getStatus())) {
            LocalDate dataAtual = LocalDate.now();
            long diasRestantes = ChronoUnit.DAYS.between(dataAtual, agendamento.getData());

            agendamento.setStatus("Cancelado");
            agendamentosConfirmados.remove(agendamento);

            if (diasRestantes > 3) {
                double valorReembolso = agendamento.getTipoAssinatura().equalsIgnoreCase("Diária")
                    ? agendamento.getAula().getPrecoDiaria() * 0.5
                    : agendamento.getAula().getPrecoMensalidade() * 0.5;
                financeiro.registrarDespesa(valorReembolso, "Reembolso de cancelamento: " + agendamento.getAula().getTipoAula());
                System.out.println("Reembolso de 50% registrado.");
            } else {
                System.out.println("Cancelamento sem reembolso devido ao curto prazo.");
            }

        } else {
            throw new IllegalStateException("Apenas agendamentos confirmados podem ser cancelados.");
        }
    }

    /**
     * Edita um agendamento preliminar, permitindo alterar a aula, a data e o horário.
     *
     * @param agendamento o agendamento a ser editado
     * @param novaAula a nova aula para o agendamento
     * @param novaData a nova data para o agendamento
     * @param novoHorario o novo horário para o agendamento
     * @throws IllegalStateException se o agendamento não for preliminar
     */
    public void editarAgendamento(Agendamento agendamento, Aula novaAula, LocalDate novaData, LocalTime novoHorario) {
        if ("Preliminar".equals(agendamento.getStatus())) {
            agendamento.setAula(novaAula);
            agendamento.setData(novaData);
            agendamento.setHorario(novoHorario);
            
        } else {
            throw new IllegalStateException("Apenas agendamentos preliminares podem ser editados.");
        }
    }

    /**
     * Exibe a agenda completa de agendamentos, incluindo preliminares e confirmados.
     *
     * @return uma lista contendo todos os agendamentos
     */
    public List<Agendamento> exibirAgenda() {
        List<Agendamento> agendaCompleta = new ArrayList<>();
        agendaCompleta.addAll(agendamentosPreliminares);
        agendaCompleta.addAll(agendamentosConfirmados);
        return agendaCompleta;
    }
    
    /**
    * Remove todos os agendamentos do sistema, tanto preliminares quanto confirmados..
    * Este método chama o método {@code limparAgendamentos} na instância {@link Reservas},
    * limpando todas as listas de agendamentos e sincronizando com o arquivo JSON para garantir que
    * os dados sejam removidos permanentemente.
    *
    */
    
    public void limparAgendamentos() {
        agendamentosPreliminares.clear();
        agendamentosConfirmados.clear();

        // Salva listas vazias nos arquivos JSON para garantir que estejam sincronizadas
        jsonReservas.salvarAgendamentosPreliminares(agendamentosPreliminares);
        jsonReservas.salvarAgendamentosConfirmados(agendamentosConfirmados);

        System.out.println("Todos os agendamentos foram limpos.");
    }

    // Getters e Setters

    /**
     * Retorna a lista de agendamentos preliminares.
     *
     * @return a lista de agendamentos preliminares
     */
    public List<Agendamento> getAgendamentosPreliminares() {
        return agendamentosPreliminares;
    }

    /**
     * Define a lista de agendamentos preliminares.
     *
     * @param agendamentosPreliminares a nova lista de agendamentos preliminares
     */
    public void setAgendamentosPreliminares(List<Agendamento> agendamentosPreliminares) {
        this.agendamentosPreliminares = agendamentosPreliminares;
    }

    /**
     * Retorna a lista de agendamentos confirmados.
     *
     * @return a lista de agendamentos confirmados
     */
    public List<Agendamento> getAgendamentosConfirmados() {
        return agendamentosConfirmados;
    }

    /**
     * Define a lista de agendamentos confirmados.
     *
     * @param agendamentosConfirmados a nova lista de agendamentos confirmados
     */
    public void setAgendamentosConfirmados(List<Agendamento> agendamentosConfirmados) {
        this.agendamentosConfirmados = agendamentosConfirmados;
    }
}
