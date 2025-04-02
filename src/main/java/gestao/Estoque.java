package gestao;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável pelo gerenciamento de estoque na academia.
 * Permite adicionar, retirar e consultar produtos no inventário,
 * além de verificar a disponibilidade de produtos em estoque.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class Estoque {

    private Map<Produto, Integer> inventario = new HashMap<>();

    /**
     * Construtor padrão para a classe {@code Estoque}.
     * Inicializa o inventário como um novo {@link HashMap}.
     */
    public Estoque() {
        this.inventario = new HashMap<>();
    }

    /**
     * Adiciona uma quantidade específica de um produto ao estoque.
     *
     * @param produto o produto a ser adicionado
     * @param quantidade a quantidade a ser adicionada ao inventário
     */
    public void adicionarProduto(Produto produto, int quantidade) {
        inventario.put(produto, inventario.getOrDefault(produto, 0) + quantidade);
    }

    /**
     * Retira uma quantidade específica de um produto do estoque, se houver quantidade suficiente.
     *
     * @param produto o produto a ser retirado
     * @param quantidade a quantidade a ser retirada do inventário
     * @return {@code true} se a retirada foi realizada com sucesso, caso contrário {@code false}
     */
    public boolean retirarProduto(Produto produto, int quantidade) {
        if (temEstoqueSuficiente(produto, quantidade)) {
            inventario.put(produto, inventario.get(produto) - quantidade);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Busca um produto no inventário pelo seu identificador único.
     *
     * @param id o identificador do produto
     * @return o produto correspondente ao ID, ou {@code null} se não for encontrado
     */
    public Produto buscarProdutoPorId(int id) {
        for (Produto produto : inventario.keySet()) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    /**
     * Verifica se há estoque suficiente de um produto para atender uma quantidade desejada.
     *
     * @param produto o produto a ser verificado
     * @param quantidadeDesejada a quantidade desejada do produto
     * @return {@code true} se houver quantidade suficiente, caso contrário {@code false}
     */
    public boolean temEstoqueSuficiente(Produto produto, int quantidadeDesejada) {
        Integer quantidadeEmEstoque = inventario.get(produto);
        return quantidadeEmEstoque != null && quantidadeEmEstoque >= quantidadeDesejada;
    }

    /**
     * Consulta a quantidade disponível de um produto específico no estoque.
     *
     * @param produto o produto a ser consultado
     * @return a quantidade disponível no estoque, ou 0 se o produto não estiver no inventário
     */
    public int consultarEstoque(Produto produto) {
        return inventario.getOrDefault(produto, 0);
    }

    /**
     * Exibe o inventário completo, mostrando cada produto e sua quantidade em estoque.
     */
    public void exibirInventario() {
        System.out.println("Inventário Atual:");
        for (Map.Entry<Produto, Integer> entry : inventario.entrySet()) {
            System.out.println("Produto: " + entry.getKey().getNome() + " - Quantidade: " + entry.getValue());
        }
    }

    /**
     * Retorna o total de tipos de produtos no inventário.
     *
     * @return o número total de tipos de produtos
     */
    public int getTotalProdutos() {
        return inventario.size();
    }

    /**
     * Retorna uma cópia do inventário atual, mantendo o encapsulamento da classe.
     *
     * @return uma cópia do mapa de inventário
     */
    public Map<Produto, Integer> getInventario() {
        return new HashMap<>(this.inventario);
    }

    /**
     * Define o inventário com um novo mapa de produtos e quantidades, mantendo o encapsulamento.
     *
     * @param inventario o novo mapa de produtos e quantidades para o inventário
     */
    public void setInventario(Map<Produto, Integer> inventario) {
        this.inventario = new HashMap<>(inventario);
    }
}
