package Json;

import Json.Adapters.LocalDateAdapter;
import Json.Adapters.LocalDateTimeAdapter;
import Json.Adapters.LocalTimeAdapter;
import agenda.Agendamento;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por salvar e carregar listas de agendamentos de reservas em formato JSON.
 * Utiliza a biblioteca Gson para converter objetos em JSON formatado, com adaptadores para tipos de data e hora.
 * 
 * @author laviniacharrua e iasmintorres
 */
public class JsonReservas {

    /**
     * Caminho do arquivo JSON onde as reservas preliminares serão salvas.
     */
    public static final String ReservaPreliminar = "C:/Users/Iasmin/Desktop/PROJETO-POO-Iasmin&Lavinia/SistemaAcademia-POO/SistemaAcademia/src/main/java/Json/ReservasPreliminares.json";

    /**
     * Caminho do arquivo JSON onde as reservas confirmadas serão salvas.
     */
    public static final String ReservaConfirmada = "C:/Users/Iasmin/Desktop/PROJETO-POO-Iasmin&Lavinia/SistemaAcademia-POO/SistemaAcademia/src/main/java/Json/ReservasConfirmadas.json";

    private static final Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
        .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
        .setPrettyPrinting()
        .create();

    /**
     * Salva uma lista de agendamentos preliminares em um arquivo JSON.
     *
     * @param agendamentos a lista de {@link Agendamento} preliminares a serem salvos em formato JSON
     */
    public void salvarAgendamentosPreliminares(List<Agendamento> agendamentos) {
        try (FileWriter writer = new FileWriter(ReservaPreliminar)) {
            gson.toJson(agendamentos, writer);
            System.out.println("Reservas preliminares salvas com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar Reservas Preliminares!");
        }
    }

    /**
     * Salva uma lista de agendamentos confirmados em um arquivo JSON.
     *
     * @param agendamentos a lista de {@link Agendamento} confirmados a serem salvos em formato JSON
     */
    public void salvarAgendamentosConfirmados(List<Agendamento> agendamentos) {
        try (FileWriter writer = new FileWriter(ReservaConfirmada)) {
            gson.toJson(agendamentos, writer);
            System.out.println("Reservas confirmadas salvas com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar Reservas Confirmadas!");
        }
    }

    /**
     * Carrega a lista de reservas preliminares de um arquivo JSON.
     *
     * @return uma lista de {@link Agendamento} representando as reservas preliminares, ou uma lista vazia se ocorrer um erro
     */
    public static List<Agendamento> carregarReservasPreliminares() {
        try (Reader reader = new FileReader(ReservaPreliminar)) {
            Type listType = new TypeToken<List<Agendamento>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>(); 
        }
    }

    /**
     * Carrega a lista de reservas confirmadas de um arquivo JSON.
     *
     * @return uma lista de {@link Agendamento} representando as reservas confirmadas, ou uma lista vazia se ocorrer um erro
     */
    public static List<Agendamento> carregarReservasConfirmadas() {
        try (Reader reader = new FileReader(ReservaConfirmada)) {
            Type listType = new TypeToken<List<Agendamento>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
