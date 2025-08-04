import data.WeatherData;
import processing.Converter;
import processing.Transformer;
import processing.Loader;

import java.util.List;


public class ETL {
    public static void main(String[] args) {
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=55.0344&longitude=82.9434" +
                "&daily=sunrise,sunset,daylight_duration" +
                "&hourly=temperature_2m,relative_humidity_2m,dew_point_2m,apparent_temperature,temperature_80m,temperature_120m," +
                "wind_speed_10m,wind_speed_80m,wind_direction_10m,wind_direction_80m,visibility,evapotranspiration,weather_code,soil_temperature_0cm," +
                "soil_temperature_6cm,rain,showers,snowfall" +
                "&timezone=auto&timeformat=unixtime&wind_speed_unit=kn" +
                "&temperature_unit=fahrenheit&precipitation_unit=inch" +
                "&start_date=2025-05-16&end_date=2025-05-30";

        String csvHeader = "Широта,Долгота,Дата,Время,Температура на высоте 2м(°F),Относительная влажность на высоте 2м(%)," +
                "Точка росы на высоте 2м(°F),Ощущаемая температура (°F),Температура на высоте 80м(°F),Температура на высоте 120м(°F)," +
                "Скорость ветра на высоте 10м(узлы),Скорость ветра на высоте 80м(узлы),Направление ветра на высоте 10м(градусы)," +
                "Направление ветра на высоте 80м(градусы),Видимость (футы),Испаряемость(дюймы),Код погоды,Температура почвы на глубине 0 см (°F)," +
                "Температура почвы на глубине 6 см (°F),Количество осадков (дюймы),Восход,Закат,Продолжительность светового дня (часы)";

        String fileName = "weather_data.csv";
        try {
            WeatherData weatherData = Converter.fetchDataFromApi(apiUrl);

            List<String[]> csvData = Transformer.transformData(weatherData, csvHeader);

            Loader.saveToCsv(csvData, fileName);

            System.out.println("Data was successfully saved in weather_data.csv\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}