package Json;

import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gestao.Despesa;

/**
 * Classe responsável por salvar uma lista de valores de despesas em formato JSON.
 * Utiliza a biblioteca Gson para converter os valores em JSON formatado e armazená-los em um arquivo.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class JsonDespesas {

    /**
     * Caminho do arquivo JSON onde os dados das despesas serão salvos.
     */
    public static final String Despesa = "C:/Users/Iasmin/Desktop/PROJETO-POO-Iasmin&Lavinia/SistemaAcademia-POO/SistemaAcademia/src/main/java/Json/Despesas.json";

    /**
     * Construtor padrão para a classe {@code JsonDespesas}.
     */
    public JsonDespesas() {}

    /**
     * Salva uma lista de valores de despesas em um arquivo JSON no caminho especificado.
     * O JSON é gerado em um formato "pretty-printed" para facilitar a leitura e organização dos dados.
     *
     * @param despesas a lista de valores {@link Double} representando as despesas a serem salvas em formato JSON
     */
    public static void salvarDespesas(List<Double> despesas) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(despesas);

        try (FileWriter writer = new FileWriter(Despesa)) {
            writer.write(json);
            System.out.println("Despesa salva com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar despesa!");
        }
    }
}
