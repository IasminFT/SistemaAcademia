package individuo;

import java.util.List;

/**
 * Representa uma catraca na academia, que registra o estado atual (aberta ou fechada)
 * e mantém um histórico das interações.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class Catraca {

    private boolean estado; // Estado atual da catraca (aberta ou fechada)
    private List<String> historico; // Histórico de interações da catraca

    /**
     * Retorna o estado atual da catraca.
     *
     * @return {@code true} se a catraca estiver aberta, {@code false} se estiver fechada
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Define o estado atual da catraca.
     *
     * @param estado o novo estado da catraca ({@code true} para aberta, {@code false} para fechada)
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Retorna o histórico de interações da catraca.
     *
     * @return uma lista de strings representando o histórico de interações
     */
    public List<String> getHistorico() {
        return historico;
    }

    /**
     * Define o histórico de interações da catraca.
     *
     * @param historico uma lista de strings representando o novo histórico de interações
     */
    public void setHistorico(List<String> historico) {
        this.historico = historico;
    }

    /**
     * Retorna uma representação em string da catraca, incluindo seu estado atual e o histórico.
     *
     * @return uma string com as informações da catraca
     */
    @Override
    public String toString() {
        return "Catraca{" +
               "estado=" + estado +
               ", historico=" + historico +
               '}';
    }
}
