package Comparator;

import individuo.Aluno;
import java.util.Comparator;

/**
 * Implementação de {@link Comparator} para comparar objetos {@link Aluno} com base no CPF.
 * Esta classe permite ordenar ou comparar instâncias de {@link Aluno} pela propriedade de CPF,
 * utilizando uma comparação caracter a caracter.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class CompararAlunoCpf implements Comparator<Aluno> {

    /**
     * Compara dois objetos {@link Aluno} com base no CPF.
     *
     * @param a1 o primeiro objeto {@link Aluno} para comparação
     * @param a2 o segundo objeto {@link Aluno} para comparação
     * @return um valor negativo, zero, ou positivo se o CPF de {@code a1} for, respectivamente,
     *         menor, igual ou maior que o CPF de {@code a2}
     */
    @Override
    public int compare(Aluno a1, Aluno a2) {
        return compareStrings(a1.getCpf(), a2.getCpf());
    }

    /**
     * Compara duas strings caracter a caracter até o menor comprimento entre as duas.
     * Se todos os caracteres até o menor comprimento são iguais, retorna a diferença
     * entre os comprimentos das strings.
     *
     * @param s1 a primeira string para comparação
     * @param s2 a segunda string para comparação
     * @return um valor negativo, zero, ou positivo se {@code s1} for, respectivamente,
     *         menor, igual ou maior que {@code s2}
     */
    private static int compareStrings(String s1, String s2) {
        int len1 = s1.length(); // Comprimento da primeira string
        int len2 = s2.length(); // Comprimento da segunda string
        int lim = Math.min(len1, len2); // Menor comprimento entre as duas strings

        for (int i = 0; i < lim; i++) { // Percorre caracteres até o menor comprimento
            char c1 = s1.charAt(i); // Caractere na posição i da primeira string
            char c2 = s2.charAt(i); // Caractere na posição i da segunda string
            if (c1 != c2) { // Se os caracteres forem diferentes, retorna a diferença
                return c1 - c2;
            }
        }
        // Retorna a diferença entre os comprimentos das strings, se os caracteres forem iguais
        return len1 - len2;
    }
}

