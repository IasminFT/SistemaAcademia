package Comparator;

import individuo.Aluno;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Classe utilitária para buscar um objeto {@link Aluno} específico em uma lista, com base em um comparador personalizado.
 * Esta classe fornece um método para localizar uma instância de {@link Aluno}
 * em uma coleção utilizando uma estratégia de comparação especificada.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class AlunoFind {

    /**
     * Busca um {@link Aluno} em uma lista fornecida, usando um {@link Comparator} especificado para a correspondência.
     *
     * @param alunos a lista de objetos {@link Aluno} onde a busca será realizada
     * @param alunoToFind o objeto {@link Aluno} que se deseja encontrar na lista
     * @param comparator o {@link Comparator} utilizado para definir os critérios de correspondência entre objetos {@link Aluno}
     * @return o objeto {@link Aluno} correspondente, caso seja encontrado; {@code null} se não houver correspondência
     */
    public Aluno find(List<Aluno> alunos, Aluno alunoToFind, Comparator<Aluno> comparator) {
        Iterator<Aluno> iterator = alunos.iterator();

        while (iterator.hasNext()) {
            Aluno currentAluno = iterator.next();
            if (comparator.compare(currentAluno, alunoToFind) == 0) {
                return currentAluno;
            }
        }

        return null; // Aluno não encontrado
    }
}