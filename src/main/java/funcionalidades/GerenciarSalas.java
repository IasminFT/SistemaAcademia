package funcionalidades;

import Comparator.CompararNumSala;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe responsável por gerenciar as salas da academia, incluindo a inicialização,
 * edição e exclusão de aulas em cada sala.
 * Utiliza um vetor estático para armazenar as salas e permite operações de exibição,
 * edição e exclusão de aulas nas salas.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class GerenciarSalas {

    // Vetor estático para armazenar as salas
    private static Sala[] salas = new Sala[4];

    /**
     * Construtor que inicializa as salas com valores predefinidos e as ordena
     * com base no número da sala usando o comparador {@link CompararNumSala}.
     */
    public GerenciarSalas() {
        salas[0] = new Sala("Sala de Spinning", 0, 90);
        salas[1] = new Sala("Sala de Musculação", 1, 120);
        salas[2] = new Sala("Sala de Dança", 2, 50);
        salas[3] = new Sala("Sala de Pilates", 3, 65);

        // Ordena o vetor de salas usando o CompararNumSala
        Arrays.sort(salas, new CompararNumSala());
    }

    /**
     * Edita uma sala existente, alterando o nome da aula associada à sala.
     *
     * @param numeroSala o número da sala a ser editada
     * @param novoNomeAula o novo nome da aula a ser definido para a sala
     */
    public static void editarSala(int numeroSala, String novoNomeAula) {
        for (Sala sala : salas) {
            if (sala.getNumeroSala() == numeroSala) {
                sala.setNomeAula(novoNomeAula);
                System.out.println("Sala editada: " + sala);
                return;
            }
        }
        System.out.println("Sala com número " + numeroSala + " não encontrada.");
    }

    /**
     * Exclui uma aula associada a uma sala, definindo o nome da aula como "LIVRE".
     *
     * @param numeroSala o número da sala a ser editada para exclusão de aula
     */
    public static void excluirAulaSala(int numeroSala) {
        for (Sala sala : salas) {
            if (sala.getNumeroSala() == numeroSala) {
                sala.setNomeAula("LIVRE");
            }
        }
    }
    
    /**
     * Retorna uma sala específica com base no seu número.
     *
     * @param numero o número da sala desejada
     * @return o objeto {@link Sala} correspondente ao número fornecido
     */
    public Sala sala(int numero) {
        return salas[numero];
    }

    /**
     * Exibe informações sobre todas as salas, incluindo o nome da aula,
     * o número da sala e o valor associado.
     */
    public void exibirSalas() {
        for (Sala sala : salas) {
            System.out.println(sala.getNomeAula() + "/ Sala: " + sala.getNumeroSala() + "/ Valor: " + sala.getValor());
        }
    }
}
