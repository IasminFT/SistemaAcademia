package gestao;

/**
 * Representa um produto da academia, com informações sobre seu identificador, nome, descrição,
 * preço, quantidade em estoque, e categoria. Esta classe fornece métodos para manipulação e
 * verificação do estoque, além de controles básicos de igualdade e hash.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class Produto {

    public static int getTotalProdutos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidadeEmEstoque;
    private String categoria;
    private static int totalProdutos = 0;

    /**
     * Construtor para a classe {@code Produto}.
     *
     * @param id o identificador único do produto
     * @param nome o nome do produto
     * @param descricao a descrição do produto
     * @param preco o preço do produto
     * @param quantidadeEmEstoque a quantidade disponível em estoque
     * @param categoria a categoria do produto
     */
    public Produto(int id, String nome, String descricao, double preco, int quantidadeEmEstoque, String categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.categoria = categoria;
        totalProdutos++;
    }

    // Getters e Setters

     /**
     * Obtém o ID do produto.
     *
     * @return ID do produto.
     */
    public int getId() {
        return id;
    }

     /**
     * Define o ID do produto.
     *
     * @param id ID do produto.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome do produto.
     *
     * @return nome do produto.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     *
     * @param nome nome do produto.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a descrição do produto.
     *
     * @return descrição do produto.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do produto.
     *
     * @param descricao descrição do produto.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

     /**
     * Obtém o preço do produto.
     *
     * @return preço do produto.
     */
    public double getPreco() {
        return preco;
    }

   /**
     * Define o preço do produto.
     *
     * @param preco preço do produto.
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

     /**
     * Obtém a quantidade em estoque do produto.
     *
     * @return quantidade em estoque.
     */
    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    /**
     * Define a quantidade em estoque do produto.
     *
     * @param quantidadeEmEstoque quantidade em estoque.
     */
    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }
    
    /**
     * Obtém a categoria do produto.
     *
     * @return categoria do produto.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do produto.
     *
     * @param categoria categoria do produto.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    @Override
    public String toString() {
        return "Produto{" +
            "Id='" + getId() + '\'' +
            "Nome='" + getNome() + '\'' +
            ", Descrição =" + (getDescricao()) +
            ", Preco=" + (getPreco()) +
            ", Quantidade em Estoque=" + (getQuantidadeEmEstoque()) +
            ", Categoria=" + (getCategoria()) +
            '}';
    }
}
