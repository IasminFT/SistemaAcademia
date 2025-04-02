package Comparator;

import funcionalidades.Sala;
import java.util.Comparator;

/**
 * Implementação de {@link Comparator} para comparar objetos {@link Sala} com base no número da sala.
 * Esta classe permite ordenar ou comparar instâncias de {@link Sala} pela propriedade de número da sala.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class CompararNumSala implements Comparator<Sala> {

    /**
     * Compara dois objetos {@link Sala} com base no número da sala.
     *
     * @param sala1 a primeira sala para comparação
     * @param sala2 a segunda sala para comparação
     * @return um valor positivo se o número da sala de {@code sala1} for maior que o de {@code sala2},
     *         um valor negativo se for menor, e zero se forem iguais
     */
    public int compare(Sala sala1, Sala sala2) {
        int numeroSalaCompare = sala1.getNumeroSala() - sala2.getNumeroSala();

        if (numeroSalaCompare > 0) {
            return 1; // sala1 tem número da sala maior que sala2
        } else if (numeroSalaCompare < 0) {
            return -1; // sala1 tem número da sala menor que sala2
        } else {
            return 0; // sala1 e sala2 têm o mesmo número da sala
        }
    }
}
