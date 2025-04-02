package Json;

import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gestao.Estoque;
import gestao.Produto;
import java.io.FileReader;
import java.util.Map;

/**
 * Classe responsável por salvar e carregar dados do inventário em formato JSON.
 * Utiliza a biblioteca Gson para converter dados de inventário em JSON formatado
 * e para carregar informações do inventário a partir de um arquivo JSON.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class JsonEstoque {

    /**
     * Caminho do arquivo JSON onde os dados do estoque serão salvos.
     */
    public static final String Estoque = "C:/Users/Iasmin/Desktop/PROJETO-POO-Iasmin&Lavinia/SistemaAcademia-POO/SistemaAcademia/src/main/java/Json/Estoque.json";

    /**
     * Construtor padrão para a classe {@code JsonEstoque}.
     */
    public JsonEstoque() {}

    /**
     * Salva o inventário de produtos em um arquivo JSON no caminho especificado.
     * O JSON é gerado em um formato "pretty-printed" para facilitar a leitura e organização dos dados.
     *
     * @param inventario o mapa contendo objetos {@link Produto} e suas quantidades correspondentes no estoque
     */
    public static void salvarEstoque(Map<Produto, Integer> inventario) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(inventario);

        try (FileWriter writer = new FileWriter(Estoque)) {
            writer.write(json);
            System.out.println("Estoque salvo com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar o estoque!");
        }
    }

    /**
     * Carrega o inventário de produtos a partir de um arquivo JSON.
     *
     * @return um objeto {@link Estoque} contendo os dados do inventário ou {@code null} se ocorrer um erro de leitura
     */
    public static Estoque carregarEstoque() {
        try (FileReader reader = new FileReader(Estoque)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Estoque.class);  // Sem TypeToken
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
