package individuo;

import java.util.List;

/**
 * Representa um aluno da academia, que herda características de {@link Pessoa} e contém
 * informações específicas, como data de agendamento e histórico de despesas. 
 * A classe mantém uma contagem total de alunos usando variáveis de classe.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class Aluno extends Pessoa {

    private String dataDeAgendamento;
    private List<String> historicoDeDespesas;
    private final int ID;
    
    private static int totalAlunosPrivate = 0; // Contagem privada de instâncias de alunos
    protected static int totalAlunosProtected = 0; // Contagem protegida de instâncias de alunos
    
    private static int contadorAluno = 0;

    /**
     * Construtor para a classe {@code Aluno}.
     *
     * @param nome o nome do aluno
     * @param cpf o CPF do aluno
     * @param telefone o telefone do aluno
     * @param email o email do aluno
     * @param endereco o endereço do aluno
     */
    public Aluno(String nome, String cpf, String telefone, String email, String endereco) {
        super(nome, cpf, telefone, email, endereco);
        this.dataDeAgendamento = dataDeAgendamento;
        this.historicoDeDespesas = historicoDeDespesas;
        this.ID = contadorAluno;

        contadorAluno++;
        totalAlunosPrivate++;
        totalAlunosProtected++;
    }

    /**
     * Define a data de agendamento do aluno.
     *
     * @param dataDeAgendamento a data de agendamento a ser definida
     */
    public void setDataDeAgendamento(String dataDeAgendamento) {
        this.dataDeAgendamento = dataDeAgendamento;
    }

    /**
     * Retorna a data de agendamento do aluno.
     *
     * @return a data de agendamento
     */
    public String getDataDeAgendamento() {
        return dataDeAgendamento;
    }

    /**
     * Define o histórico de despesas do aluno.
     *
     * @param historicoDeDespesas uma lista de despesas associadas ao aluno
     */
    public void setHistoricoDeDespesas(List<String> historicoDeDespesas) {
        this.historicoDeDespesas = historicoDeDespesas;
    }

    /**
     * Retorna o histórico de despesas do aluno.
     *
     * @return uma lista de despesas do aluno
     */
    public List<String> getHistoricoDeDespesas() {
        return historicoDeDespesas;
    }

    /**
     * Retorna o total privado de alunos criados.
     *
     * @return o total privado de alunos
     */
    public static int getTotalAlunosPrivate() {
        return totalAlunosPrivate;
    }

    /**
     * Retorna o total protegido de alunos criados.
     *
     * @return o total protegido de alunos
     */
    public static int getTotalAlunosProtected() {
        return totalAlunosProtected;
    }

    /**
     * Exibe dados específicos do aluno, incluindo a data de agendamento.
     *
     * @return uma string com os dados do aluno
     */
    @Override
    public String exibirDados() {
        return "Aluno{" + " dataDeAgendamento=" + dataDeAgendamento + '}';
    }

    /**
     * Retorna uma representação em string do aluno, exibindo o nome, CPF, data de agendamento
     * e histórico de despesas.
     *
     * @return uma string com os detalhes do aluno
     */
    @Override
    public String toString() {
        return "Aluno{" +
            "Nome='" + getNome() + '\'' +
            ", CPF='" + getCpf() + '\'' +
            ", DataDeAgendamento=" + (dataDeAgendamento != null ? dataDeAgendamento : "Nenhum") +
            ", HistoricoDeDespesas=" + (historicoDeDespesas != null ? historicoDeDespesas : "Nenhum") +
            '}';
    }
}
