package Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import individuo.Aluno;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Classe responsável por salvar dados de entrada de {@link Aluno} na catraca em formato JSON.
 * Utiliza a biblioteca Gson para converter objetos em JSON formatado e salvar em um arquivo.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class JsonCatraca {

    /**
     * Caminho do arquivo JSON onde os dados de entrada na catraca serão salvos.
     */
    public static final String Catraca = "C:/Users/Iasmin/Desktop/PROJETO-POO-Iasmin&Lavinia/SistemaAcademia-POO/SistemaAcademia/src/main/java/Json/Catraca.json";

    /**
     * Construtor padrão para a classe {@code JsonCatraca}.
     */
    public JsonCatraca() {}

    /**
     * Salva uma lista de entradas de {@link Aluno} na catraca em um arquivo JSON no caminho especificado.
     * O JSON é gerado em um formato "pretty-printed" para facilitar a leitura e a organização dos dados.
     *
     * @param alunos a lista de objetos {@link Aluno} representando as entradas na catraca a serem salvas em formato JSON
     */
    public static void salvarCatraca(List<Aluno> alunos) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(alunos);

        try (FileWriter writer = new FileWriter(Catraca)) {
            writer.write(json);
            System.out.println("Entrada na catraca salva com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar entrada na catraca!");
        }
    }
}
