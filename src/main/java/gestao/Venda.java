package gestao;

import individuo.Aluno;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Representa uma venda de produtos para um cliente na academia. Contém informações sobre
 * o identificador da venda, produtos vendidos, valor total, data da venda e o cliente associado.
 * A classe implementa {@link Serializable} para permitir a persistência de dados.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class Venda implements Serializable {

    private final int idVenda;
    private List<Produto> produtos;
    private double valorTotal; // Calculado automaticamente com base nos produtos
    private Date dataVenda;
    private Aluno cliente; // Cliente associado à venda
    private static int contadorVenda = 0;

    /**
     * Construtor para a classe {@code Venda}. Calcula automaticamente o valor total com base nos produtos.
     *
     * @param idVenda o identificador único da venda
     * @param produtos a lista de produtos vendidos
     * @param dataVenda a data em que a venda foi realizada
     * @param cliente o cliente que realizou a compra
     */
    public Venda(int idVenda, List<Produto> produtos, Date dataVenda, Aluno cliente) {
        this.idVenda = contadorVenda;
        this.produtos = produtos;
        this.valorTotal = produtos.stream().mapToDouble(p -> p.getPreco() * p.getQuantidadeEmEstoque()).sum();
        this.dataVenda = dataVenda;
        this.cliente = cliente;
        
        contadorVenda ++;
    }

    /**
     * Calcula o valor total da venda com base nos produtos e suas quantidades.
     *
     * @return o valor total da venda
     */
    private double calcularValorTotal() {
        double total = 0.0;
        for (Produto produto : produtos) {
            total += produto.getPreco() * produto.getQuantidadeEmEstoque();
        }
        return total;
    }

    // Getters e Setters

        /**
      * Obtém o ID da venda.
      *
      * @return ID da venda.
      */
    public int getIdVenda() {
        return idVenda;
    }

    
    /**
     * Obtém a lista de produtos incluídos na venda.
     *
     * @return lista de produtos.
     */
    public List<Produto> getProdutos() {
        return produtos;
    }

    /**
     * Define a lista de produtos vendidos e recalcula o valor total.
     *
     * @param produtos a nova lista de produtos vendidos
     */
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
        this.valorTotal = calcularValorTotal();
    }

   
    /**
     * Obtém o valor total da venda.
     *
     * @return valor total.
     */
    public double getValorTotal() {
        return valorTotal;
    }

   /**
    * Obtém a data da venda.
    *
    * @return data da venda.
    */
    public Date getDataVenda() {
        return dataVenda;
    }

        /**
     * Define a data da venda.
     *
     * @param dataVenda data da venda.
     */
    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    /**
     * Obtém o cliente associado à venda.
     *
     * @return cliente da venda.
     */
    public Aluno getCliente() {
        return cliente;
    }

    /**
     * Retorna uma representação em string da venda, incluindo o ID, cliente, data da venda,
     * produtos vendidos e o valor total da venda.
     *
     * @return uma string com os detalhes da venda
     */
    @Override
    public String toString() {
        StringBuilder detalhes = new StringBuilder();
        detalhes.append("ID da Venda: ").append(idVenda).append("\n")
                .append("Cliente: ").append(cliente.getNome()).append("\n")
                .append("Data da Venda: ").append(dataVenda).append("\n")
                .append("Produtos Vendidos:\n");

        for (Produto produto : produtos) {
            detalhes.append(" - ").append(produto.getNome()).append(", Quantidade: ")
                    .append(produto.getQuantidadeEmEstoque()).append(", Preço Unitário: R$ ")
                    .append(produto.getPreco()).append("\n");
        }

        detalhes.append("Valor Total: R$ ").append(valorTotal).append("\n");
        return detalhes.toString();
    }

    /**
     * Verifica se duas vendas são iguais com base no seu identificador único.
     *
     * @param obj o objeto a ser comparado
     * @return {@code true} se as vendas tiverem o mesmo ID, caso contrário {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Venda venda = (Venda) obj;
        return idVenda == venda.idVenda;
    }

    /**
     * Retorna o hash code da venda com base no identificador único.
     *
     * @return o hash code da venda
     */
    @Override
    public int hashCode() {
        return idVenda;
    }
}
