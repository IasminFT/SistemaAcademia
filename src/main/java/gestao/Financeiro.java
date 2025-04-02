package gestao;

import individuo.Aluno;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe responsável pelo gerenciamento financeiro da academia, incluindo controle de despesas,
 * receitas, e histórico de transações. Também oferece funcionalidades para registrar vendas,
 * atualizar o estoque e obter um resumo financeiro.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class Financeiro {

    private List<Double> despesas;
    private List<Double> receitas;
    private List<String> historicoTransacoes;
    private Estoque estoque;

    /**
     * Construtor da classe {@code Financeiro}.
     *
     * @param estoque o inventário de produtos para controle de vendas e saídas
     */
    public Financeiro(Estoque estoque) {
        this.despesas = new ArrayList<>();
        this.receitas = new ArrayList<>();
        this.historicoTransacoes = new ArrayList<>();
        this.estoque = estoque;
    }

    /**
     * Registra uma venda de produtos, atualizando o estoque e as receitas.
     *
     * @param produtos a lista de produtos vendidos
     * @param dataVenda a data em que a venda foi realizada
     * @param cliente o cliente que realizou a compra
     * @return {@code true} se a venda foi realizada com sucesso, caso contrário {@code false}
     */
    public boolean registrarVenda(List<Produto> produtos, Date dataVenda, Aluno cliente) {
        boolean estoqueSuficiente = true;
        double valorTotalVenda = 0.0;

        for (Produto produto : produtos) {
            if (!estoque.temEstoqueSuficiente(produto, produto.getQuantidadeEmEstoque())) {
                System.out.println("Venda não realizada: estoque insuficiente para o produto " + produto.getNome());
                estoqueSuficiente = false;
                break;
            }
            valorTotalVenda += produto.getPreco() * produto.getQuantidadeEmEstoque();
        }

        if (estoqueSuficiente) {
            for (Produto produto : produtos) {
                estoque.retirarProduto(produto, produto.getQuantidadeEmEstoque());
            }

            Venda venda = new Venda(receitas.size() + 1, produtos, dataVenda, cliente);
            receitas.add(valorTotalVenda);
            historicoTransacoes.add("Venda registrada: Produtos vendidos - Valor Total: R$ " + valorTotalVenda);
            System.out.println("Venda registrada com sucesso: Valor total: R$ " + valorTotalVenda);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Registra uma receita e adiciona uma descrição ao histórico de transações.
     *
     * @param valor o valor da receita
     * @param descricao uma descrição da receita
     */
    public void registrarReceita(double valor, String descricao) {
        receitas.add(valor);
        historicoTransacoes.add("Receita: " + descricao + " - Valor: R$ " + valor);
        System.out.println("Receita registrada: " + descricao + " - Valor: R$ " + valor);
    }

    /**
     * Registra uma despesa e adiciona uma descrição ao histórico de transações.
     *
     * @param valor o valor da despesa
     * @param descricao uma descrição da despesa
     */
    public void registrarDespesa(double valor, String descricao) {
        despesas.add(valor);
        historicoTransacoes.add("Despesa: " + descricao + " - Valor: R$ " + valor);
        System.out.println("Despesa registrada: " + descricao + " - Valor: R$ " + valor);
    }

    /**
     * Obtém um resumo financeiro detalhado, incluindo receitas, despesas, balanço e histórico de transações.
     *
     * @return uma string contendo o resumo financeiro detalhado
     */
    public String obterResumoFinanceiro() {
        double totalDespesas = despesas.stream().mapToDouble(Double::doubleValue).sum();
        double totalReceitas = receitas.stream().mapToDouble(Double::doubleValue).sum();
        double balancoMensal = totalReceitas - totalDespesas;

        StringBuilder resumo = new StringBuilder();
        resumo.append("\n=== Resumo Financeiro ===\n");
        resumo.append("Total de Receitas: R$ ").append(totalReceitas).append("\n");
        resumo.append("Total de Despesas: R$ ").append(totalDespesas).append("\n");
        resumo.append("Balanço Mensal: R$ ").append(balancoMensal).append("\n");
        resumo.append("Histórico de Transações:\n");

        for (String transacao : historicoTransacoes) {
            resumo.append(transacao).append("\n");
        }

        return resumo.toString();
    }

    /**
     * Exibe o histórico de transações no console.
     */
    public void exibirHistoricoTransacoes() {
        System.out.println("\n=== Histórico de Transações ===");
        if (historicoTransacoes.isEmpty()) {
            System.out.println("Nenhuma transação registrada.");
        } else {
            for (String transacao : historicoTransacoes) {
                System.out.println(transacao);
            }
        }
    }

    /**
     * Calcula o balanço mensal, subtraindo as despesas das receitas.
     *
     * @return o valor do balanço mensal
     */
    public double calcularBalancoMensal() {
        double totalDespesas = despesas.stream().mapToDouble(Double::doubleValue).sum();
        double totalReceitas = receitas.stream().mapToDouble(Double::doubleValue).sum();
        return totalReceitas - totalDespesas;
    }

    // Getters e Setters

    public List<Double> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Double> receitas) {
        this.receitas = receitas;
    }

    /**
     * Obtém a lista de despesas registradas no sistema.
     *
     * @return lista de despesas.
     */
    public List<Double> getDespesas() {
        return despesas;
    }

    /**
     * Define a lista de despesas do sistema.
     *
     * @param despesas lista de valores de despesas.
     */
    public void setDespesas(List<Double> despesas) {
        this.despesas = despesas;
    }
    /**
     * Obtém o estoque associado ao sistema financeiro.
     *
     * @return objeto Estoque.
     */
    public Estoque getEstoque() {
        return estoque;
    }

    /**
     * Define o estoque associado ao sistema financeiro.
     *
     * @param estoque objeto Estoque.
     */
    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    /**
     * Obtém o histórico de transações financeiras.
     *
     * @return lista de transações.
     */
    public List<String> getHistoricoTransacoes() {
        return historicoTransacoes;
    }

    /**
     * Define o histórico de transações financeiras.
     *
     * @param historicoTransacoes lista de transações.
     */
    public void setHistoricoTransacoes(List<String> historicoTransacoes) {
        this.historicoTransacoes = historicoTransacoes;
    }}
