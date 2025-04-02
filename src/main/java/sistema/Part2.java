package sistema;

import Comparator.AlunoFind;
import Comparator.CompararAlunoCpf;
import individuo.Aluno;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Classe que realiza operações de busca, ordenação e comparação em uma lista de alunos,
 * incluindo medições de tempo para diferentes métodos de iteração e busca.
 * 
 * @author laviniacharrua e iasmintorres
 */

public class Part2 {

    /**
     * Método principal para executar as operações de iteração, ordenação, busca e
     * comparação na lista de alunos. Inclui medições de tempo para análise de desempenho.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        List<Aluno> alunos = new ArrayList<>(List.of(
                new Aluno("João Silva", "12345678900", "(31) 98765-4321", "joao@exemplo.com", "Rua A, 123"),
                new Aluno("Maria Oliveira", "98765432100", "(31) 91234-5678", "maria@exemplo.com", "Rua B, 456"),
                new Aluno("Carlos Souza", "12312312399", "(31) 99876-5432", "carlos@exemplo.com", "Rua C, 789")
        ));

        // Questão 15: Usando Iterator para percorrer a lista de alunos com cálculo de tempo
        System.out.println("Percorrendo a lista de alunos com Iterator:");
        long startTime = System.nanoTime();
        Iterator<Aluno> iterator = alunos.iterator();
        while (iterator.hasNext()) {
            Aluno aluno = iterator.next();
            System.out.println(aluno);
        }
        long endTime = System.nanoTime();
        long durationIterator = endTime - startTime;
        System.out.println("Tempo de execução do Iterator: " + durationIterator + " nanosegundos\n");

        // Usando foreach para percorrer a lista com cálculo de tempo
        System.out.println("Percorrendo a lista de alunos com foreach:");
        startTime = System.nanoTime();
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
        endTime = System.nanoTime();
        long durationForeach = endTime - startTime;
        System.out.println("Tempo de execução do foreach: " + durationForeach + " nanosegundos\n");

        // Questão 16/17: Ordenando a lista e aplicando Comparator
        CompararAlunoCpf comparator = new CompararAlunoCpf();
        Collections.sort(alunos, comparator); // Ordena a lista usando o Comparator

        // Questão 17: Método find com Iterator e Comparator, com cálculo de tempo
        AlunoFind find = new AlunoFind();
        Aluno alunoToFind = new Aluno("Maria Oliveira", "98765432100", "(31) 91234-5678", "maria@exemplo.com", "Rua B, 456");
        
        startTime = System.nanoTime();
        Aluno foundAluno = find.find(alunos, alunoToFind, comparator);
        endTime = System.nanoTime();
        long durationFind = endTime - startTime;
        
        if (foundAluno != null) {
            System.out.println("Aluno encontrado usando find: " + foundAluno);
        } else {
            System.out.println("Aluno não encontrado usando find.");
        }
        
        System.out.println("Tempo de execução do find: " + durationFind + " nanosegundos\n");

        // Usando binarySearch com cálculo de tempo
        startTime = System.nanoTime();
        int index = Collections.binarySearch(alunos, alunoToFind, comparator);
        endTime = System.nanoTime();
        long durationBinarySearch = endTime - startTime;
        
        if (index >= 0) {
            System.out.println("Aluno encontrado usando binarySearch: " + alunoToFind);
        } else {
            System.out.println("Aluno não encontrado usando binarySearch.");
        }
        
        System.out.println("Tempo de execução do binarySearch: " + durationBinarySearch + " nanosegundos\n");

        // Resultados finais de tempo
        System.out.println("Resumo dos tempos de execução:");
        System.out.println("Iterator: " + durationIterator + " nanosegundos");
        System.out.println("Foreach: " + durationForeach + " nanosegundos");
        System.out.println("Find: " + durationFind + " nanosegundos");
        System.out.println("BinarySearch: " + durationBinarySearch + " nanosegundos");
    }
}
