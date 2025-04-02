package Json.Adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Adaptador para serializar e desserializar objetos {@link LocalDate} em formato JSON
 * utilizando o padrão de formatação ISO-8601 (AAAA-MM-DD).
 * Esta classe é usada pelo Gson para converter automaticamente {@link LocalDate} 
 * para string e vice-versa durante o processo de (de)serialização JSON.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    /**
     * Formata a data no padrão ISO-8601 (AAAA-MM-DD).
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Serializa um objeto {@link LocalDate} para uma string JSON.
     *
     * @param out o escritor JSON onde a data será escrita
     * @param value o valor {@link LocalDate} a ser convertido para JSON
     * @throws IOException se ocorrer um erro de entrada/saída
     */
    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        out.value(value.format(formatter));
    }

    /**
     * Desserializa uma string JSON para um objeto {@link LocalDate}.
     *
     * @param in o leitor JSON de onde a string da data será lida
     * @return o objeto {@link LocalDate} resultante da string lida
     * @throws IOException se ocorrer um erro de entrada/saída
     */
    @Override
    public LocalDate read(JsonReader in) throws IOException {
        return LocalDate.parse(in.nextString(), formatter);
    }
}
