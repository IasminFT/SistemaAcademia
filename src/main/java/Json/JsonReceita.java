package Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gestao.Receita;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Classe responsável por salvar uma lista de valores de receitas em formato JSON.
 * Utiliza a biblioteca Gson para converter os valores em JSON formatado e armazená-los em um arquivo.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class JsonReceita {

    /**
     * Caminho do arquivo JSON onde os dados das receitas serão salvos.
     */
    public static final String Receita = "C:/Users/Iasmin/Desktop/PROJETO-POO-Iasmin&Lavinia/SistemaAcademia-POO/SistemaAcademia/src/main/java/Json/Receitas.json";

    /**
     * Construtor padrão para a classe {@code JsonReceita}.
     */
    public JsonReceita() {}

    /**
     * Salva uma lista de valores de receitas em um arquivo JSON no caminho especificado.
     * O JSON é gerado em um formato "pretty-printed" para facilitar a leitura e organização dos dados.
     *
     * @param receitas a lista de valores {@link Double} representando as receitas a serem salvas em formato JSON
     */
    public static void salvarReceita(List<Double> receitas) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(receitas);

        try (FileWriter writer = new FileWriter(Receita)) {
            writer.write(json);
            System.out.println("Receita salva com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar receita!");
        }
    }
}
