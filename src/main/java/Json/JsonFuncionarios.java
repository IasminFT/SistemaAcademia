package Json;

import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import individuo.Funcionario;

/**
 * Classe responsável por salvar uma lista de objetos {@link Funcionario} em formato JSON em um arquivo.
 * Utiliza a biblioteca Gson para converter objetos em JSON formatado e armazená-los em um arquivo.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class JsonFuncionarios {

    /**
     * Caminho do arquivo JSON onde os dados dos funcionários serão salvos.
     */
    public static final String Funcionario = "C:/Users/Iasmin/Desktop/PROJETO-POO-Iasmin&Lavinia/SistemaAcademia-POO/SistemaAcademia/src/main/java/Json/Funcionario.json";

    /**
     * Construtor padrão para a classe {@code JsonFuncionarios}.
     */
    public JsonFuncionarios() {}

    /**
     * Salva uma lista de objetos {@link Funcionario} em um arquivo JSON no caminho especificado.
     * O JSON é gerado em um formato "pretty-printed" para facilitar a leitura e organização dos dados.
     *
     * @param funcionarios a lista de objetos {@link Funcionario} a serem salvos em formato JSON
     */
    public static void salvarFuncionario(List<Funcionario> funcionarios) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(funcionarios);

        try (FileWriter writer = new FileWriter(Funcionario)) {
            writer.write(json);
            System.out.println("Funcionario salvo com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar Funcionario!");
        }
    }
}
