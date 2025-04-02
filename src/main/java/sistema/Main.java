package sistema;

import funcionalidades.Aula;
import gestao.Estoque;
import gestao.Financeiro;
import gestao.Produto;
import individuo.Admin;
import individuo.Aluno;
import individuo.Funcionario;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import seguranca.Autenticacao;

/**
 * Classe principal para iniciar o sistema de academia.
 * Configura e inicializa o sistema com dados de teste, autentica os usuários e
 * apresenta menus para gerenciamento de alunos, agendamentos, vendas, estoque,
 * funcionários e finanças.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class Main {

    /**
     * Método principal para iniciar a aplicação.
     * Configura o sistema com dados de teste e gerencia a interface de login e menus.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        Autenticacao autenticacao = new Autenticacao();
        Estoque estoque = new Estoque();
        Financeiro financeiro = new Financeiro(estoque);
        SistemaAcademia sistema = new SistemaAcademia(autenticacao, estoque, financeiro);

        // Simulações de bases de dados
        inicializarFuncionarios(sistema);
        inicializarAlunos(sistema);
        inicializarAulas(sistema);
        inicializarEstoque(sistema);
        
        // Configuração do Scanner para entrada de dados
        Scanner scanner = new Scanner(System.in);

        // Loop principal de login
        boolean continuar = true;
        while (continuar) {
            if (sistema.fazerLogin()) {
                continuar = exibirMenuPrincipal(scanner, sistema);
            } else {
                System.out.println("Reiniciando a tentativa de login...");
            }
        }
        scanner.close();
    }

    // Métodos de inicialização de dados de teste

    private static void inicializarFuncionarios(SistemaAcademia sistema) {
        Admin admin = new Admin("Lavinia", "123456", "12345-6789", "admin@academia.com", "Rua Admin", 5000, "Admin123");
        Funcionario funcionario = new Funcionario("Iasmin", "789101", "98765-4321", "funcionario@academia.com", "Rua Funcionario", 3000, "Func123", "Funcionario");
        sistema.adicionarFuncionario(admin);
        sistema.adicionarFuncionario(funcionario);
        System.out.println("Funcionários de teste cadastrados com sucesso.");
    }

    private static void inicializarAlunos(SistemaAcademia sistema) {
        Aluno aluno1 = new Aluno("João Silva", "12345678900", "(31) 98765-4321", "joao@exemplo.com", "Rua A, 123");
        Aluno aluno2 = new Aluno("Maria Oliveira", "98765432100", "(31) 91234-5678", "maria@exemplo.com", "Rua B, 456");
        sistema.getAlunos().addAll(Arrays.asList(aluno1, aluno2));
        System.out.println("Alunos de teste cadastrados com sucesso.");
    }

    private static void inicializarAulas(SistemaAcademia sistema) {
        Aula aula1 = new Aula("Spinning", 10, Arrays.asList(LocalDateTime.of(2024, 11, 10, 8, 0)));
        Aula aula2 = new Aula("Pilates", 5, Arrays.asList(LocalDateTime.of(2024, 11, 11, 9, 0)));
        System.out.println("Aulas de teste criadas com sucesso.");
    }

    private static void inicializarEstoque(SistemaAcademia sistema) {
        sistema.getEstoque().adicionarProduto(new Produto(1, "Barra de Proteína", "Barra com alto teor de proteína", 5.50, 100, "Lanchonete"), 100);
        sistema.getEstoque().adicionarProduto(new Produto(2, "Garrafa de Água", "Garrafa de 500ml", 2.00, 200, "Lanchonete"), 200);
        sistema.getEstoque().adicionarProduto(new Produto(3, "Luvas de Academia", "Luvas para treino de musculação", 15.00, 50, "Loja"), 50);
        System.out.println("Estoque inicializado com produtos de teste.");
    }

    // Métodos de exibição de menus

    private static boolean exibirMenuPrincipal(Scanner scanner, SistemaAcademia sistema) {
        boolean continuar = true;
        boolean sair = false;
        Funcionario usuarioLogado = sistema.getUsuarioLogado();
        boolean isAdmin = usuarioLogado instanceof Admin;

        while (!sair) {
            System.out.println("\n==== Menu Principal ====");
            System.out.println("1. Gestão de Alunos");
            System.out.println("2. Gestão de Agendamentos");
            System.out.println("3. Gestão de Vendas");
            System.out.println("4. Estoque");
            if (isAdmin) {
                System.out.println("5. Gestão de Funcionários");
                System.out.println("6. Gestão Financeira");
            }
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome o '\n' restante

            switch (opcao) {
                case 1:
                    exibirMenuAlunos(scanner, sistema, isAdmin);
                    break;
                case 2:
                    exibirMenuAgendamentos(scanner, sistema, isAdmin);
                    break;
                case 3:
                    exibirMenuVendas(scanner, sistema, isAdmin);
                    break;
                case 4:
                    if (isAdmin) {
                        exibirMenuEstoque(scanner, sistema, isAdmin);
                    } else {
                        System.out.println("Opção restrita ao Admin.");
                    }
                    break;
                case 5:
                    if (isAdmin) {
                        exibirMenuFuncionarios(scanner, sistema);
                    } else {
                        System.out.println("Opção restrita ao Admin.");
                    }
                    break;
                case 6:
                    if (isAdmin) {
                        exibirMenuFinanceiro(scanner, sistema);
                    } else {
                        System.out.println("Opção restrita ao Admin.");
                    }
                    break;
                case 0:
                    sistema.fazerLogout();
                    sair = true;
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        return continuar;
    }
    
    // Implementação de outros menus como exibirMenuAlunos, exibirMenuAgendamentos etc., similar ao exibirMenuPrincipal

    private static void exibirMenuAlunos(Scanner scanner, SistemaAcademia sistema, boolean isAdmin) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n==== Gestão de Alunos ====");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Exibir Alunos");
            System.out.println("3. Buscar Aluno por CPF");
            if (isAdmin) {
                System.out.println("4. Atualizar Aluno");
                System.out.println("5. Excluir Aluno");
                System.out.println("6. Limpar Todos os Alunos");
            }
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    sistema.cadastrarAluno();
                    break;
                case 2:
                    sistema.exibirAlunos();
                    break;
                case 3:
                    System.out.print("Digite o CPF do aluno: ");
                    String cpf = scanner.nextLine();
                    Aluno aluno = sistema.buscarAlunoPorCpf(cpf);
                    if (aluno != null) {
                        sistema.exibirAlunoEspecifico(aluno);
                    } else {
                        System.out.println("Aluno com CPF " + cpf + " não encontrado.");
                    }
                    break;
                case 4:
                    if (isAdmin) sistema.atualizarAluno();
                    break;
                case 5:
                    if (isAdmin) sistema.excluirAluno();
                    break;
                case 6:
                    if (isAdmin) sistema.limparTodosOsAlunos();
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
            // Exibe a opção de voltar, fazer logout ou sair
            if (!sair) {
                int escolha = mostrarOpcaoVoltarOuSair(scanner);
                if (escolha == 0) {
                    sair = true;
                } else if (escolha == 1) { // Logout
                    sistema.fazerLogout();
                    sair = true;
                } else if (escolha == 2) { // Sair
                    System.exit(0);
                }
            }
        }
    }


    private static void exibirMenuAgendamentos(Scanner scanner, SistemaAcademia sistema, boolean isAdmin) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n==== Gestão de Agendamentos ====");
            System.out.println("1. Fazer Agendamento");
            System.out.println("2. Confirmar Agendamento");
            System.out.println("3. Cancelar Agendamento");
            System.out.println("4. Editar Agendamento");
            System.out.println("5. Verificar Agenda");
            if (isAdmin) {
                System.out.println("6. Limpar Todos os Agendamentos");
            }
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    sistema.criarAgendamento();
                    break;
                case 2:
                    sistema.confirmarAgendamento();
                    break;
                case 3:
                    sistema.cancelarAgendamento();
                    break;
                case 4:
                    sistema.editarAgendamento();
                    break;
                case 5:
                    sistema.verificarAgenda();
                    break;
                case 6:
                    sistema.limparTodosOsAgendamentos();
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            // Exibe a opção de voltar, fazer logout ou sair
            if (!sair) {
                int escolha = mostrarOpcaoVoltarOuSair(scanner);
                if (escolha == 0) {
                    sair = true;
                } else if (escolha == 1) { // Logout
                    sistema.fazerLogout();
                    sair = true;
                } else if (escolha == 2) { // Sair
                    System.exit(0);
                }
            }
        }
    }

    private static void exibirMenuVendas(Scanner scanner, SistemaAcademia sistema, boolean isAdmin) {
       boolean sair = false;
        while (!sair) {
            System.out.println("\n==== Gestão de Vendas ====");
            System.out.println("1. Registrar Venda");
            if (isAdmin) {
                System.out.println("2. Exibir Vendas de Diárias e Mensalidades");
            }
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Registrar Venda");
                    sistema.registrarVenda();
                    break;
                case 2:
                    if (isAdmin) sistema.exibirVendasDiariasEMensalidades();
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        // Exibe a opção de voltar, fazer logout ou sair
        if (!sair) {
            int escolha = mostrarOpcaoVoltarOuSair(scanner);
            if (escolha == 0) {
                    sair = true;
                } else if (escolha == 1) { // Logout
                    sistema.fazerLogout();
                    sair = true;
                } else if (escolha == 2) { // Sair
                    System.exit(0);
                }
        }
    }
    }

    private static void exibirMenuEstoque(Scanner scanner, SistemaAcademia sistema, boolean isAdmin) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n==== Estoque ====");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Exibir Inventário");
            if (isAdmin) {
                System.out.println("3. Excluir Produto Específico");
                System.out.println("4. Limpar Todo o Estoque");
            }
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    sistema.adicionarProdutoAoEstoque();
                    break;
                case 2:
                    sistema.exibirInventario();
                    break;
                case 3:
                    System.out.print("Digite o ID do produto a ser excluído: ");
                    int idProduto = scanner.nextInt();
                    scanner.nextLine();
                    sistema.excluirProdutoDoEstoque(idProduto); // Chama o método para excluir produto específico
                    break;
                case 4:
                    sistema.limparTodoEstoque(); // Chama o método para limpar todo o estoque
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            // Exibe a opção de voltar, fazer logout ou sair
            if (!sair) {
                int escolha = mostrarOpcaoVoltarOuSair(scanner);
                if (escolha == 0) {
                    sair = true;
                } else if (escolha == 1) { // Logout
                    sistema.fazerLogout();
                    sair = true;
                } else if (escolha == 2) { // Sair
                    System.exit(0);
                }
            }
        }
    }


    private static void exibirMenuFuncionarios(Scanner scanner, SistemaAcademia sistema) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n==== Gestão de Funcionários ====");
            System.out.println("1. Cadastrar Funcionário");
            System.out.println("2. Exibir Funcionários");
            System.out.println("3. Atualizar Funcionário");
            System.out.println("4. Excluir Funcionário");
            System.out.println("5. Limpar Todos os Funcionários (exceto Admin)");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    sistema.cadastrarFuncionario();
                    break;
                case 2:
                    sistema.exibirFuncionarios();
                    break;
                case 3:
                    sistema.atualizarFuncionario();
                    break;
                case 4:
                    sistema.excluirFuncionario();
                    break;
                case 5:
                    sistema.limparTodosOsFuncionariosExcetoAdmin();
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
            // Exibe a opção de voltar, fazer logout ou sair
            if (!sair) {
                int escolha = mostrarOpcaoVoltarOuSair(scanner);
                if (escolha == 0) {
                    sair = true;
                } else if (escolha == 1) { // Logout
                    sistema.fazerLogout();
                    sair = true;
                } else if (escolha == 2) { // Sair
                    System.exit(0);
                }
            }
        }
    }


    private static void exibirMenuFinanceiro(Scanner scanner, SistemaAcademia sistema) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\n==== Gestão Financeira ====");
            System.out.println("1. Lançar Despesa");
            System.out.println("2. Emitir Balanço Mensal");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    sistema.lancarDespesa();
                    break;
                case 2:
                    sistema.emitirBalancoMensal();
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            // Exibe a opção de voltar, fazer logout ou sair
            if (!sair) {
                int escolha = mostrarOpcaoVoltarOuSair(scanner);
                if (escolha == 0) {
                    sair = true;
                } else if (escolha == 1) { // Logout
                    sistema.fazerLogout();
                    sair = true;
                } else if (escolha == 2) { // Sair
                    System.exit(0);
                }
            }
        }
    }
    
     /**
     * Exibe opções para o usuário voltar ao menu, fazer logout ou sair do sistema.
     *
     * @param scanner o objeto Scanner para receber a entrada do usuário
     * @return a escolha do usuário: 0 para voltar, 1 para logout, ou 2 para sair
     */
    
   public static int mostrarOpcaoVoltarOuSair(Scanner scanner) {
        while (true) {
            System.out.println("0. Voltar ao Menu Principal");
            System.out.println("1. Logout");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consome a quebra de linha após o int

            if (escolha ==0 || escolha == 1 || escolha == 2) {
                return escolha; // Retorna a escolha do usuário
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

}
