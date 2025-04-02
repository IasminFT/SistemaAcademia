package Json.Adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Adaptador para serializar e desserializar objetos {@link LocalTime} em formato JSON
 * utilizando o padrão de formatação "HH:mm" (hora:minuto).
 * Esta classe é usada pelo Gson para converter automaticamente {@link LocalTime}
 * para string e vice-versa durante o processo de (de)serialização JSON.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class LocalTimeAdapter extends TypeAdapter<LocalTime> {

    /**
     * Formata o horário no padrão "HH:mm" (hora:minuto).
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Serializa um objeto {@link LocalTime} para uma string JSON.
     *
     * @param out o escritor JSON onde o horário será escrito
     * @param value o valor {@link LocalTime} a ser convertido para JSON
     * @throws IOException se ocorrer um erro de entrada/saída
     */
    @Override
    public void write(JsonWriter out, LocalTime value) throws IOException {
        out.value(value != null ? value.format(formatter) : null);
    }

    /**
     * Desserializa uma string JSON para um objeto {@link LocalTime}.
     *
     * @param in o leitor JSON de onde a string do horário será lida
     * @return o objeto {@link LocalTime} resultante da string lida, ou {@code null} se a string estiver vazia
     * @throws IOException se ocorrer um erro de entrada/saída
     */
    @Override
    public LocalTime read(JsonReader in) throws IOException {
        String time = in.nextString();
        return time != null && !time.isEmpty() ? LocalTime.parse(time, formatter) : null;
    }
}
