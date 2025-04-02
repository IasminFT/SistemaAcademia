package Json.Adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Adaptador para serializar e desserializar objetos {@link LocalDateTime} em formato JSON
 * utilizando o padrão de formatação ISO-8601 (AAAA-MM-DDTHH:MM:SS).
 * Esta classe é usada pelo Gson para converter automaticamente {@link LocalDateTime} 
 * para string e vice-versa durante o processo de (de)serialização JSON.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {

    /**
     * Formata a data e hora no padrão ISO-8601 (AAAA-MM-DDTHH:MM:SS).
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Serializa um objeto {@link LocalDateTime} para uma string JSON.
     *
     * @param out o escritor JSON onde a data e hora serão escritas
     * @param value o valor {@link LocalDateTime} a ser convertido para JSON
     * @throws IOException se ocorrer um erro de entrada/saída
     */
    @Override
    public void write(JsonWriter out, LocalDateTime value) throws IOException {
        out.value(value.format(formatter));
    }

    /**
     * Desserializa uma string JSON para um objeto {@link LocalDateTime}.
     *
     * @param in o leitor JSON de onde a string da data e hora será lida
     * @return o objeto {@link LocalDateTime} resultante da string lida
     * @throws IOException se ocorrer um erro de entrada/saída
     */
    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        return LocalDateTime.parse(in.nextString(), formatter);
    }
}
