package processing;

import data.WeatherData;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Converter {
    public static WeatherData fetchDataFromApi(String apiUrl) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configOverride(Double.class)
                .setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.SKIP));
        objectMapper.configOverride(Integer.class)
                .setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.SKIP));

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        WeatherData weatherData = objectMapper.readValue(response.body(), WeatherData.class);
        return weatherData;
    }
}