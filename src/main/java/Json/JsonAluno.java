package Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import individuo.Aluno;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Classe responsável por salvar uma lista de objetos {@link Aluno} em formato JSON em um arquivo.
 * Utiliza a biblioteca Gson para converter objetos em JSON formatado.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class JsonAluno {

    /**
     * Caminho do arquivo JSON onde os dados dos alunos serão salvos.
     */
    public static final String Aluno = "C:/Users/Iasmin/Desktop/PROJETO-POO-Iasmin&Lavinia/SistemaAcademia-POO/SistemaAcademia/src/main/java/Json/Aluno.json";

    /**
     * Construtor padrão para a classe {@code JsonAluno}.
     */
    public JsonAluno() {}

    /**
     * Salva uma lista de objetos {@link Aluno} em um arquivo JSON no caminho especificado.
     * O JSON é gerado em um formato "pretty-printed" para melhor legibilidade.
     *
     * @param alunos a lista de objetos {@link Aluno} a ser salva em formato JSON
     */
    public static void salvarAluno(List<Aluno> alunos) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(alunos);

        try (FileWriter writer = new FileWriter(Aluno)) {
            writer.write(json);
            System.out.println("Aluno salvo com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar Aluno!");
        }
    }
    
     /**
     * Carrega o Aluno a partir de um arquivo JSON.
     *
     * @return um objeto {@link Aluno} contendo os dados do inventário ou {@code null} se ocorrer um erro de leitura
     */
    public static List<Aluno> carregarAluno() {
        try (FileReader reader = new FileReader(Aluno)) {
            Gson gson = new Gson();
            Type alunoListType = new TypeToken<List<Aluno>>(){}.getType(); // Define o tipo como List<Aluno>
            return gson.fromJson(reader, alunoListType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
