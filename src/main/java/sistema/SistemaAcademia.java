package sistema;

import Comparator.CompararAlunoCpf;
import Json.JsonAluno;
import Json.JsonCatraca;
import Json.JsonDespesas;
import Json.JsonEstoque;
import Json.JsonFuncionarios;
import Json.JsonReceita;
import Json.JsonReservas;
import agenda.Agendamento;
import agenda.Reservas;
import funcionalidades.Aula;
import funcionalidades.GerenciarSalas;
import gestao.Produto;
import gestao.Venda;
import gestao.Estoque;
import gestao.Financeiro;
import gestao.Receita;
import seguranca.Autenticacao;
import java.util.ArrayList;
import java.util.List;
import individuo.Admin;
import individuo.Aluno;
import individuo.Catraca;
import individuo.Funcionario;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Classe que representa o sistema principal da academia, responsável pelo gerenciamento de alunos,
 * funcionários, agenda de aulas, vendas, estoque e financeiro. Inclui métodos para cadastro, busca,
 * atualização e remoção de registros, bem como para geração de relatórios e controle de permissões.
 * 
 * @author laviniacharrua e iasmintorres
 */

public class SistemaAcademia {
    
    private Autenticacao autenticacao;
    private List<Aluno> entradas;
    private List<Aluno> alunos;
    private List<Funcionario> funcionarios;
    /** Usuário logado no sistema atualmente. */
    public Funcionario usuarioLogado;
    private Reservas reservas;
    private Estoque estoque;
    private List<Receita> receitas;
    private Financeiro financeiro;
    
    
    private Catraca catraca = new Catraca();
    private Scanner scanner = new Scanner(System.in);
    private JsonReservas jsonReservas = new JsonReservas();

     /**
     * Construtor para a classe {@code SistemaAcademia}. Inicializa listas de alunos,
     * funcionários, entradas e estoque, e carrega dados persistidos em JSON.
     *
     * @param autenticacao sistema de autenticação para controle de acesso
     * @param estoque o estoque da academia para produtos
     * @param financeiro o sistema financeiro para gerenciamento de receitas e despesas
     */
    
    public SistemaAcademia(Autenticacao autenticacao, Estoque estoque, Financeiro financeiro) {
        this.autenticacao = autenticacao;
        this.alunos = Json.JsonAluno.carregarAluno();
        this.entradas = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
        this.receitas = new ArrayList<>();
        this.estoque = JsonEstoque.carregarEstoque();
        this.financeiro = financeiro;
        this.reservas = new Reservas(financeiro);
        
        this.reservas.setAgendamentosPreliminares(JsonReservas.carregarReservasPreliminares());
        this.reservas.setAgendamentosConfirmados(JsonReservas.carregarReservasConfirmadas());
    }
    
    
//-------------------- Gerenciamento de Cadastro, Login e Exibição --------------------
    
    //-------------------- USUÁRIOS DO SISTEMA --------------------
    
     /**
     * Cadastra um novo funcionário no sistema, que pode ser do tipo comum ou administrador.
     * Inclui persistência dos dados em JSON.
     */
    
    public void cadastrarFuncionario() {
        System.out.println("\n=== Cadastro de Funcionário ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Salário: ");
        float salario = scanner.nextFloat();
        scanner.nextLine(); // Consome o '\n' restante
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Tipo de Funcionário (1 para Funcionário, 2 para Admin): ");
        int tipoFuncionario = scanner.nextInt();
        scanner.nextLine(); // Consome o '\n' restante
        
        Funcionario novoFuncionario;
        if (tipoFuncionario == 2) {
            novoFuncionario = new Admin(nome, cpf, telefone, email, endereco, salario, senha);
            System.out.println("Admin cadastrado com sucesso!");
        } else {
            String cargo = null;
            novoFuncionario = new Funcionario(nome, cpf, telefone, email, endereco, salario, senha, cargo);
            System.out.println("Funcionário cadastrado com sucesso!");
        }

        adicionarFuncionario(novoFuncionario);
    }
    
    /**
     * Adiciona um funcionário à lista de funcionários e salva a lista atualizada em JSON.
     *
     * @param funcionario o funcionário a ser adicionado
     */
    
    public void adicionarFuncionario(Funcionario funcionario){
        funcionarios.add(funcionario);
        JsonFuncionarios.salvarFuncionario(funcionarios);
    }
    
    /**
     * Exibe a lista de todos os funcionários cadastrados no sistema.
     */
    
    public void exibirFuncionarios() {
          System.out.println("\n=== Lista de Funcionários ===");
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        }
    }

    
     /**
     * Atualiza os dados de um funcionário existente no sistema com base no CPF.
     * Inclui persistência dos dados atualizados em JSON.
     */
    
    public void atualizarFuncionario() {
        System.out.print("\nDigite o CPF do funcionário a ser atualizado: ");
        String cpf = scanner.nextLine();

        Funcionario funcionarioEncontrado = null;

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf().equals(cpf)) {
                funcionarioEncontrado = funcionario;
                funcionarios.remove(funcionario);
                break;
            }
        }

        if (funcionarioEncontrado != null) {
            System.out.println("\n=== Atualizar Funcionário ===");
            System.out.print("Novo Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Novo Telefone: ");
            String telefone = scanner.nextLine();
            System.out.print("Novo Email: ");
            String email = scanner.nextLine();
            System.out.print("Novo Endereço: ");
            String endereco = scanner.nextLine();
            System.out.print("Novo Salário: ");
            float salario = scanner.nextFloat();
            scanner.nextLine(); // Consome o '\n' restante
            System.out.print("Nova Senha: ");
            String senha = scanner.nextLine();

            funcionarioEncontrado.setNome(nome);
            funcionarioEncontrado.setTelefone(telefone);
            funcionarioEncontrado.setEmail(email);
            funcionarioEncontrado.setEndereco(endereco);
            funcionarioEncontrado.setSalario(salario);
            funcionarioEncontrado.setSenha(senha);

            System.out.println("Funcionário atualizado com sucesso!");
            funcionarios.add(funcionarioEncontrado);
            JsonFuncionarios.salvarFuncionario(funcionarios);
        }   else {
            System.out.println("Funcionário com CPF " + cpf + " não encontrado.");
        }
    }

    /**
     * Exclui um funcionário do sistema com base no CPF. Inclui persistência dos dados atualizados em JSON.
     */
    
    public void excluirFuncionario() {
        System.out.print("\nDigite o CPF do funcionário a ser excluído: ");
        String cpf = scanner.nextLine();

        Funcionario funcionarioParaRemover = null;

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf().equals(cpf)) {
                funcionarioParaRemover = funcionario;
                break;
            }
        }

        if (funcionarioParaRemover != null) {
            funcionarios.remove(funcionarioParaRemover);
            System.out.println("Funcionário removido com sucesso!");
            JsonFuncionarios.salvarFuncionario(funcionarios);
        }   else {
            System.out.println("Funcionário com CPF " + cpf + " não encontrado.");
        }
    }
    
    /**
    * Remove todos os funcionários do sistema, exceto o administrador, e atualiza o arquivo JSON.
    */
    public void limparTodosOsFuncionariosExcetoAdmin() {
       funcionarios.removeIf(func -> !(func instanceof Admin)); // Remove todos que não são Admin
       JsonFuncionarios.salvarFuncionario(funcionarios); // Salva a lista atualizada no JSON
       System.out.println("Todos os funcionários, exceto o Admin, foram removidos.");
    }
    
    /**
     * Realiza o login de um funcionário ou administrador no sistema.
     *
     * @return true se o login for bem-sucedido, false caso contrário
     */
    
    public boolean fazerLogin() {
        System.out.println("=== Sistema de Login ===");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consome o '\n' restante
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Tenta fazer login com Admins e Funcionários cadastrados
        for (Funcionario func : getFuncionarios()) {
            if (func.getID() == id && autenticacao.login(func, senha)) {
                usuarioLogado = func;
                System.out.println("Login bem-sucedido como " + (func instanceof Admin ? "Admin." : "Funcionário."));
                return true;
            }
        }

        System.out.println("Falha no login. Credenciais incorretas.");
        return false;
    }
    
    /**
     * Retorna o funcionário atualmente logado.
     *
     * @return o funcionário logado
     */
     
    public Funcionario getUsuarioLogado() {
        return usuarioLogado;
    }
     
    /**
     * Realiza o logout do usuário logado e encerra a sessão.
     */
    
    public void fazerLogout() {
        autenticacao.fazerLogout();
        usuarioLogado = null;
        System.out.println("Sessão encerrada. Usuário deslogado.");
    }
    
    //-------------------- ALUNOS --------------------
    
    /**
     * Registra a entrada de um aluno na academia, verificando o acesso na catraca.
     *
     * @param cpf CPF do aluno
     */
    
    public void EntradaAcademia(String cpf){
        for (Aluno aluno : alunos){
            if (aluno.getCpf().equals(cpf)){
                catraca.setEstado(true);
                System.out.println("Acesso Liberado: " + aluno.getNome());
                entradas.add(aluno);
                JsonCatraca.salvarCatraca(entradas);
            }else{
                System.out.println("Acesso Negado");
            }
        }
    }
    
     /**
     * Exibe o histórico de entradas de alunos.
     */
    
    public void entradas(){
        System.out.println(entradas);
    }
    
    
   /**
     * Exibe a quantidade total de alunos registrados no sistema.
     */
    
    public void instanciasAlunos(){
        int instancias = Aluno.getTotalAlunosProtected();
        System.out.println(instancias);
    }

    /**
     * Cadastra um novo aluno no sistema, verificando duplicidade pelo CPF.
     * Inclui persistência dos dados em JSON.
     */
    
    public void cadastrarAluno() {
        System.out.println("\n=== Cadastro de Aluno ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        
        
        boolean statusCadastro = true;
        
        if(find(cpf)==null){
            Aluno novoAluno = new Aluno(nome, cpf, telefone, email, endereco);
            alunos.add(novoAluno);
            JsonAluno.salvarAluno(alunos);
            System.out.println("Aluno cadastrado com sucesso!");
        }else{
            System.out.println("Aluno já cadastrado!");
        }
    }

    /**
     * Exibe a lista de todos os alunos cadastrados.
     */
    
    public void exibirAlunos() {
        System.out.println("\n=== Lista de Alunos ===");
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            for (Aluno aluno : alunos) {
                System.out.println(aluno);
            }   
        }
    }
    
    /**
     * Atualiza os dados de um aluno no sistema com base no CPF.
     * Inclui persistência dos dados atualizados em JSON.
     */
    
    public void atualizarAluno() {
        System.out.print("\nDigite o CPF do aluno a ser atualizado: ");
        String cpf = scanner.nextLine();

        Aluno alunoEncontrado = null;

        for (Aluno aluno : alunos) {
            if (aluno.getCpf().equals(cpf)) {
                alunoEncontrado = aluno;
                alunos.remove(aluno);//Remove o aluno da lista de alunos
                break;
            }
        }
  
        if (alunoEncontrado != null) {
            System.out.println("\n=== Atualizar Aluno ===");
            System.out.print("Novo Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Novo Telefone: ");
            String telefone = scanner.nextLine();
            System.out.print("Novo Email: ");
            String email = scanner.nextLine();
            System.out.print("Novo Endereço: ");
            String endereco = scanner.nextLine();

            alunoEncontrado.setNome(nome);
            alunoEncontrado.setTelefone(telefone);
            alunoEncontrado.setEmail(email);
            alunoEncontrado.setEndereco(endereco);

            System.out.println("Aluno atualizado com sucesso!");
            alunos.add(alunoEncontrado);//adiciona o aluno novo na lista de alunos
           
            JsonAluno.salvarAluno(alunos); //salva a lista com o novo aluno
        } else {
            System.out.println("Aluno com CPF " + cpf + " não encontrado.");
        }
    }

    /**
     * Exclui um aluno do sistema com base no CPF. Inclui persistência dos dados atualizados em JSON.
     */
    public void excluirAluno() {
        System.out.print("\nDigite o CPF do aluno a ser excluído: ");
        String cpf = scanner.nextLine();

        Aluno alunoParaRemover = null;

        for (Aluno aluno : alunos) {
            if (aluno.getCpf().equals(cpf)) {
                alunoParaRemover = aluno;
                break;
            }
        }

        if (alunoParaRemover != null) {
            alunos.remove(alunoParaRemover);
            JsonAluno.salvarAluno(alunos);
            System.out.println("Aluno removido com sucesso!");
        } else {
            System.out.println("Aluno com CPF " + cpf + " não encontrado.");
            }
    }
    
    /**
    * Remove todos os alunos cadastrados no sistema e atualiza o arquivo JSON.
    */
    public void limparTodosOsAlunos() {
       alunos.clear(); // Limpa a lista de alunos
       JsonAluno.salvarAluno(alunos); // Salva a lista vazia no JSON
       System.out.println("Todos os alunos foram removidos.");
    }
    
    /**
     * Busca um aluno pelo CPF.
     *
     * @param cpf o CPF do aluno
     * @return o aluno encontrado, ou null se não encontrado
     */
    
    public Aluno buscarAlunoPorCpf(String cpf) {
        CompararAlunoCpf comparador = new CompararAlunoCpf();

        // Criar um aluno temporário apenas com o CPF
        Aluno alunoTemporario = new Aluno(null, cpf, null, null, null);

        for (Aluno aluno : alunos) {
            if (comparador.compare(aluno, alunoTemporario) == 0) {
                return aluno; // Retorna o aluno se o CPF coincidir
            }
        }
        return null;
    }
    
    /**
    * Exibe informações detalhadas de um aluno específico.
    * 
    * Este método imprime todos os dados armazenados para um aluno específico, incluindo
    * nome, CPF, telefone, e-mail, endereço, data de agendamento e histórico de despesas.
    *
    * @param aluno o aluno cujas informações serão exibidas
    */
   
     public void exibirAlunoEspecifico(Aluno aluno) {
        System.out.println("\n=== Dados do Aluno ===");
        System.out.println("Nome: " + aluno.getNome());
        System.out.println("CPF: " + aluno.getCpf());
        System.out.println("Telefone: " + aluno.getTelefone());
        System.out.println("Email: " + aluno.getEmail());
        System.out.println("Endereço: " + aluno.getEndereco());
        System.out.println("Data de Agendamento: " + aluno.getDataDeAgendamento());
        System.out.println("Histórico de Despesas: " + aluno.getHistoricoDeDespesas());
        System.out.println("----------------------------");
    }
     
    /**
    * Exibe os dados básicos de todos os alunos cadastrados no sistema.
    *
    * Percorre a lista de alunos cadastrados e exibe suas informações básicas, incluindo
    * nome, CPF, telefone, e-mail e endereço. Se não houver alunos cadastrados, 
    * uma mensagem informativa é exibida.
    */
    
    public void exibirDadosAlunos() {
        System.out.println("\n=== Dados dos Clientes ===");
        if (!alunos.isEmpty()) {
            for (Aluno aluno : alunos) {
                System.out.println("Nome: " + aluno.getNome());
                System.out.println("CPF: " + aluno.getCpf());
                System.out.println("Telefone: " + aluno.getTelefone());
                System.out.println("Email: " + aluno.getEmail());
                System.out.println("Endereço: " + aluno.getEndereco());
                System.out.println("----------------------------");
            }
        } else {
            System.out.println("Nenhum cliente cadastrado.");
        }
    }
    
    /**
    * Realiza a busca de um aluno específico na lista de alunos cadastrados
    * com base em seu CPF, utilizando um algoritmo de busca binária.
    *
    * Este método utiliza um {@link Comparator} para realizar uma busca binária eficiente,
    * mas também verifica a lista completa de forma sequencial por meio de um {@link Iterator}.
    *
    * @param cpf o CPF do aluno a ser buscado
    * @return o objeto {@link Aluno} correspondente ao CPF fornecido, ou {@code null} se o aluno não for encontrado
    */
    
    public Aluno find(String cpf) {
        Aluno alunoBusca = new Aluno(null, cpf, null, null,null);
        int index = Collections.binarySearch(alunos, alunoBusca, new CompararAlunoCpf());
                
        Iterator<Aluno> iterator = alunos.iterator();
        
        while(iterator.hasNext()){
            Aluno aluno = iterator.next();
            if(aluno.getCpf().equals(cpf)){
                return aluno;
            }
        }
        return null;
    }
    
    
    // -------------------- Getters e Setters -------------------- 
    
    
    /**
     * Obtém o número total de clientes cadastrados.
     *
     * @return número total de clientes.
     */
    public static int getTotalClientes() {
        return Aluno.getTotalAlunosPrivate();
    }
    
    /**
     * Obtém o objeto de autenticação do sistema.
     *
     * @return objeto de autenticação.
     */
    public Autenticacao getAutenticacao() {
        return autenticacao;
    }
    /**
     * Define o objeto de autenticação do sistema.
     *
     * @param autenticacao objeto de autenticação.
     */
    public void setAutenticacao(Autenticacao autenticacao) {
        this.autenticacao = autenticacao;
    }

    /**
     * Obtém a lista de alunos cadastrados no sistema.
     *
     * @return lista de alunos.
     */
    public List<Aluno> getAlunos() {
        return alunos;
    }
    /**
     * Define a lista de alunos do sistema.
     *
     * @param alunos lista de alunos.
     */
    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }


    
    /**
     * Obtém a lista de funcionários cadastrados no sistema.
     *
     * @return lista de funcionários.
     */
    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    /**
     * Define a lista de funcionários do sistema.
     *
     * @param funcionarios lista de funcionários.
     */
    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    /**
     * Obtém o objeto de reservas do sistema.
     *
     * @return objeto de reservas.
     */
    public Reservas getReservas() {
        return reservas;
    }

// -------------------- MÉTODOS DO SISTEMA DE GESTÃO DE AGENDA --------------------
        
    /**
    * Cria um novo agendamento para uma aula em uma sala específica, registrando informações
    * como aluno, data, horário e tipo de assinatura.
    *
    * Este método solicita informações sobre o aluno, a data e horário desejados, a sala,
    * e o tipo de assinatura. Em seguida, o agendamento é criado e salvo em uma lista preliminar
    * de agendamentos e persistido em um arquivo JSON. Uma nova receita é também registrada.
    */ 
     
    public void criarAgendamento() {
        System.out.println("\n=== Salas disponíveis ===");
        GerenciarSalas salas = new GerenciarSalas();
        salas.exibirSalas();
        System.out.println("\n=== Criar Novo Agendamento ===");
        System.out.print("Nome do Aluno: ");
        String nomeAluno = scanner.nextLine();
        System.out.print("Data (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());
        System.out.print("Horário (HH:MM): ");
        LocalTime horario = LocalTime.parse(scanner.nextLine());
        System.out.print("Numero da sala: ");
        int numSala = scanner.nextInt();
        System.out.print("Tipo de Assinatura (Diária ou Mensalidade): ");
        String tipoAssinatura = scanner.nextLine();
        scanner.nextLine();
        
        Receita receita = new Receita();
        receita.setNomeReceita(salas.sala(numSala).getNomeAula());
        receita.setValor(salas.sala(numSala).getValor());
        receitas.add(receita);
        JsonReceita.salvarReceita(financeiro.getReceitas());
        
        Aluno aluno = new Aluno(nomeAluno, "CPF Exemplo", "Telefone", "Email", "Endereço");
        Aula aula = new Aula(salas.sala(numSala).getNomeAula(), 10, null); // Exemplo de criação de aula
        Agendamento novoAgendamento = reservas.criarAgendamento(aula, aluno, data, horario, tipoAssinatura);
        
        jsonReservas.salvarAgendamentosPreliminares(reservas.getAgendamentosPreliminares());
        System.out.println("Agendamento criado com ID: " + novoAgendamento.getId());
    }

    /**
    * Confirma um agendamento existente, alterando seu status para "Confirmado"
    * e registrando uma nova receita com base no agendamento.
    *
    * O método busca o agendamento pelo ID fornecido e, se encontrado e em status "Preliminar",
    * atualiza o status para "Confirmado" e salva a atualização no arquivo JSON.
    */
    
    public void confirmarAgendamento() {
        System.out.print("Digite o ID do agendamento: ");
        int id = scanner.nextInt();

        Agendamento agendamento = reservas.buscarAgendamentoPorId(id);
        if (agendamento != null) {
            reservas.confirmarAgendamento(agendamento);
            System.out.println("Agendamento confirmado com receita registrada.");
            jsonReservas.salvarAgendamentosConfirmados(reservas.getAgendamentosConfirmados());
        } else {
            System.out.println("Agendamento não encontrado.");
        }
    }
    
    /**
    * Cancela um agendamento confirmado, alterando seu status para "Cancelado"
    * e registrando o cancelamento no sistema.
    *
    * Este método busca o agendamento pelo ID, e se o agendamento estiver confirmado,
    * ele é alterado para "Cancelado". A atualização é persistida em um arquivo JSON.
    */

    public void cancelarAgendamento() {
        System.out.print("Digite o ID do agendamento: ");
        int id = scanner.nextInt();

        Agendamento agendamento = reservas.buscarAgendamentoPorId(id);
        if (agendamento != null) {
            reservas.cancelarAgendamento(agendamento);
            System.out.println("Agendamento cancelado.");
            jsonReservas.salvarAgendamentosConfirmados(reservas.getAgendamentosConfirmados());
        } else {
            System.out.println("Agendamento não encontrado.");
        }
    }

    /**
    * Edita um agendamento preliminar existente, permitindo alterar a data, horário
    * e nome da aula. Apenas agendamentos em status "Preliminar" podem ser editados.
    *
    * Este método busca o agendamento pelo ID e, se estiver em status "Preliminar",
    * permite alterar os detalhes do agendamento, salvando as alterações no arquivo JSON.
    */
    
    public void editarAgendamento() {            
        System.out.println("\n=== Editar Agendamento ===");
        System.out.print("Digite o ID do agendamento: ");
        int id = scanner.nextInt();

        Agendamento agendamento = reservas.buscarAgendamentoPorId(id);
        if (agendamento != null && "Preliminar".equals(agendamento.getStatus())) {
            System.out.print("Nova data (AAAA-MM-DD): ");
            LocalDate novaData = LocalDate.parse(scanner.nextLine());
            System.out.print("Novo horário (HH:MM): ");
            LocalTime novoHorario = LocalTime.parse(scanner.nextLine());
            System.out.print("Novo nome da aula: ");
            String novoNomeAula = scanner.nextLine();

            Aula novaAula = new Aula(novoNomeAula, 10, null); // Exemplo de criação de nova aula
            reservas.editarAgendamento(agendamento, novaAula, novaData, novoHorario);
            System.out.println("Agendamento editado com sucesso!");
            
           jsonReservas.salvarAgendamentosPreliminares(reservas.getAgendamentosPreliminares());
        } else {
            System.out.println("Agendamento não encontrado ou não pode ser editado.");
        }
    }
    
    /**
    * Exibe a agenda completa, listando todos os agendamentos preliminares e confirmados.
    * 
    * Para cada agendamento, exibe informações como ID, tipo de aula, data, horário e status.
    */

    public void verificarAgenda() {
        System.out.println("\n=== Agenda Completa ===");

        System.out.println("\n--- Agendamentos Preliminares ---");
        for (Agendamento ag : reservas.getAgendamentosPreliminares()) {
            System.out.println("ID: " + ag.getId() + ", Aula: " + ag.getAula().getTipoAula() +
               ", Data: " + ag.getData() + ", Horário: " + ag.getHorario() + ", Status: " + ag.getStatus());
        }

        System.out.println("\n--- Agendamentos Confirmados ---");
        for (Agendamento ag : reservas.getAgendamentosConfirmados()) {
        System.out.println("ID: " + ag.getId() + ", Aula: " + ag.getAula().getTipoAula() +
                ", Data: " + ag.getData() + ", Horário: " + ag.getHorario() + ", Status: " + ag.getStatus());
        }
    }
    
    /**
    * Remove todos os agendamentos das listas de agendamentos preliminares e confirmados.
    * Este método esvazia as listas {@code agendamentosPreliminares} e {@code agendamentosConfirmados},
    * e salva as listas vazias nos arquivos JSON correspondentes.
    */
    
    public void limparTodosOsAgendamentos() {
        reservas.limparAgendamentos();
    }

    
// -------------------- MÉTODOS DO SISTEMA COMERCIAL/FINANCEIRO -------------------- 

   /**
    * Exibe a quantidade total de instâncias da classe {@link Produto} criadas.
    * Utiliza o método estático de {@link Produto} para obter o total.
    */
    public void instanciasProdutos(){
        int instancias = Produto.getTotalProdutos();
        System.out.println(instancias);
    }

    /**
    * Retorna a contagem total de produtos no estoque.
    *
    * @return o número total de produtos se o estoque estiver inicializado;
    * caso contrário, exibe uma mensagem de erro e retorna 0.
    */
    public int getTotalProdutos() {
        if (estoque != null) {
            return estoque.getTotalProdutos();
        } else {
            System.out.println("Erro: Estoque não inicializado.");
            return 0;
        }
    }
    
    /**
    * Retorna o objeto {@link Estoque} associado ao sistema.
    *
    * @return o estoque da academia
    */
    public Estoque getEstoque() {
        return estoque;
    }

    /**
    * Retorna o objeto {@link Financeiro} associado ao sistema.
    *
    * @return o sistema financeiro da academia
    */
    public Financeiro getFinanceiro() {
        return financeiro;
    }
    
    /**
    * Adiciona um novo produto ao estoque da academia, solicitando informações
    * como ID, nome, descrição, preço, quantidade e categoria do produto.
    *
    * O produto é adicionado ao inventário e o estado atualizado é salvo no arquivo JSON.
    */
    public void adicionarProdutoAoEstoque() {
        System.out.println("\n=== Adicionar Produto ao Estoque ===");
        System.out.print("ID do Produto: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consome a quebra de linha
        System.out.print("Nome do Produto: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição do Produto: ");
        String descricao = scanner.nextLine();
        System.out.print("Preço do Produto: ");
        double preco = scanner.nextDouble();
        System.out.print("Quantidade em Estoque: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Consome a quebra de linha
        System.out.print("Categoria do Produto: ");
        String categoria = scanner.nextLine();

        Produto produto = new Produto(id, nome, descricao, preco, quantidade, categoria);
        estoque.adicionarProduto(produto, quantidade); // Adiciona o produto ao inventário interno
        System.out.println("Produto adicionado ao estoque com sucesso!");

        // Salva o inventário atualizado no JSON
        JsonEstoque.salvarEstoque(estoque.getInventario());
    }
    
    /**
    * Exclui um produto específico do estoque com base no ID fornecido e atualiza o arquivo JSON.
    *
    * @param id o identificador do produto a ser removido
    */
    public void excluirProdutoDoEstoque(int id) {
       Produto produto = buscarProdutoPorId(id); // Localiza o produto pelo ID

       if (produto != null) {
           estoque.getInventario().remove(produto); // Remove o produto do inventário
           JsonEstoque.salvarEstoque(estoque.getInventario()); // Atualiza o JSON com o inventário atualizado
           System.out.println("Produto removido com sucesso!");
       } else {
           System.out.println("Produto com ID " + id + " não encontrado no estoque.");
       }
    }
    
    /**
    * Remove todos os itens do estoque e atualiza o arquivo JSON.
    */
    public void limparTodoEstoque() {
       estoque.getInventario().clear(); // Limpa o inventário do estoque
       JsonEstoque.salvarEstoque(estoque.getInventario()); // Salva o inventário vazio no JSON
       System.out.println("Todo o estoque foi removido.");
    }

    
    /**
    * Busca um produto no estoque pelo seu ID.
    *
    * @param id o identificador do produto
    * @return o objeto {@link Produto} encontrado ou {@code null} se o produto não existir no estoque
    */
    private Produto buscarProdutoPorId(int id) {
        System.out.println("Inventário Atual: " + estoque.getInventario());

        for (Produto p : estoque.getInventario().keySet()) {
            if (p.getId() == id) {
                return p;
            }
        }
        System.out.println("Produto não encontrado no estoque.");
        return null;
    }

    /**
    * Lança uma nova despesa no sistema financeiro, disponível apenas para o usuário Admin.
    * Solicita o valor da despesa e persiste a atualização no arquivo JSON.
    */
    public void lancarDespesa() {
        System.out.println("\n=== Lançar Despesa ===");
        System.out.print("Valor da Despesa: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consome a quebra de linha

        if (autenticacao.isAdminLogado()) {
            financeiro.registrarDespesa(valor, "Descrição da despesa");
            System.out.println("Despesa de " + valor + " lançada com sucesso.");
            
            JsonDespesas.salvarDespesas(financeiro.getDespesas());
            
        } else {
            System.out.println("Acesso negado. Apenas Admin pode lançar despesas.");
        }
    }
    
    /**
    * Registra uma nova venda de produtos no sistema, verificando se há estoque suficiente.
    * Atualiza o inventário e registra a receita da venda no sistema financeiro.
    */
    public void registrarVenda() {
        System.out.println("\n=== Registrar Venda ===");
        System.out.print("ID do Produto: ");
        int id = scanner.nextInt();
        System.out.print("Quantidade Vendida: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        // Recupera o produto pelo ID
        Produto produto = estoque.buscarProdutoPorId(id);

        if (produto != null) { // Verifica se o produto foi encontrado
            // Verifica se há estoque suficiente para a quantidade desejada
            if (estoque.temEstoqueSuficiente(produto, quantidade)) {
                double valorTotal = quantidade * produto.getPreco(); // Calcula o valor total da venda
                financeiro.registrarReceita(valorTotal, "Venda de produto: " + produto.getNome());
                estoque.retirarProduto(produto, quantidade); // Atualiza o inventário
                System.out.println("Venda realizada com sucesso!");
            } else {
                System.out.println("Venda não realizada: estoque insuficiente para o produto.");
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    /**
    * Exibe uma lista de todas as vendas de diárias e mensalidades realizadas,
    * mostrando o valor total de cada venda registrada.
    */
    public void exibirVendasDiariasEMensalidades() {
        System.out.println("\n=== Vendas de Diárias e Mensalidades ===");
        if (financeiro != null && !financeiro.getReceitas().isEmpty()) {
            int idVenda = 1;
            for (Double valor : financeiro.getReceitas()) {
                System.out.println("ID da Venda: " + idVenda++);
                System.out.println("Valor Total da Venda: R$ " + valor);
                System.out.println("----------------------------");
            }
        } else {
            System.out.println("Nenhuma venda de diária ou mensalidade registrada.");
        }
    }
    
    /**
    * Exibe o inventário atual da academia, listando os produtos disponíveis.
    * Se o estoque não estiver inicializado, exibe uma mensagem de erro.
    */
    public void exibirInventario() {
        System.out.println("\n=== Inventário da Academia ===");
        if (estoque != null) {
            estoque.exibirInventario();
        } else {
            System.out.println("Erro: Estoque não inicializado.");
        }
    }

    /**
    * Emite o balanço financeiro mensal, disponível apenas para o usuário Admin.
    * Calcula e exibe o balanço mensal com base nas receitas e despesas registradas.
    */
    public void emitirBalancoMensal() {
        if (autenticacao.isAdminLogado()) {
            double balanco = financeiro.calcularBalancoMensal();
            System.out.println("Balanço Mensal: R$ " + balanco);
        } else {
            System.out.println("Acesso negado. Apenas Admin pode emitir balanço mensal.");
        }
    }
    
    /**
    * Gera um extrato de venda detalhado e salva o conteúdo em um arquivo de texto.
    *
    * @param venda o objeto {@link Venda} que representa a venda a ser registrada no extrato
    */
    public void gerarExtratoVenda(Venda venda) {
        System.out.println("\n=== Extrato da Venda ===");
        System.out.println(venda.toString());

        // Salva o extrato em um arquivo
        try (PrintWriter writer = new PrintWriter(new File("extrato_venda_" + venda.getIdVenda() + ".txt"))) {
            writer.println(venda.toString());
            System.out.println("Extrato da venda salvo em: extrato_venda_" + venda.getIdVenda() + ".txt");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o extrato da venda: " + e.getMessage());
        }
    }    
}
   