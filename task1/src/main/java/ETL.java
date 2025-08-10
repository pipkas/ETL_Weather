import data.Column;
import data.WeatherData;
import processing.Converter;
import processing.Transformer;
import processing.Loader;

import static data.Transformation.*;


public class ETL {
    public static void main(String[] args) {
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=55.0344&longitude=82.9434" +
                "&daily=sunrise,sunset" +
                "&hourly=temperature_2m,relative_humidity_2m,dew_point_2m,apparent_temperature,temperature_80m,temperature_120m," +
                "wind_speed_10m,wind_speed_80m,wind_direction_10m,wind_direction_80m,visibility,evapotranspiration,weather_code,soil_temperature_0cm," +
                "soil_temperature_6cm,rain,showers,snowfall" +
                "&timezone=auto&timeformat=unixtime&wind_speed_unit=kn" +
                "&temperature_unit=fahrenheit&precipitation_unit=inch" +
                "&start_date=2025-07-16&end_date=2025-07-30";

        Column[] columns = fillColumnData();

        String fileName = "weather_data.csv";
        try {
            WeatherData weatherData = Converter.fetchDataFromApi(apiUrl);

            Transformer transformer = new Transformer(columns, weatherData);
            String[][] csvData = transformer.transformData();

            Loader.saveToCsv(csvData, fileName);

            System.out.println("Data was successfully saved in weather_data.csv\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Column[] fillColumnData() {
        final int colCount = 38;
        Column[] columns = new Column[colCount];
        columns[0] = new Column("avg_temperature_2m_24h",
                DEGREES, weatherData -> weatherData.getHourly().getTemperature2m());
        columns[1] = new Column("avg_relative_humidity_2m_24h",
                null, weatherData -> weatherData.getHourly().getRelativeHumidity2m());
        columns[2] = new Column("avg_dew_point_2m_24h",
                DEGREES, weatherData -> weatherData.getHourly().getDewPoint2m());
        columns[3] = new Column("avg_apparent_temperature_24h",
                DEGREES, weatherData -> weatherData.getHourly().getApparentTemperature());
        columns[4] = new Column("avg_temperature_80m_24h",
                DEGREES, weatherData -> weatherData.getHourly().getTemperature80m());
        columns[5] = new Column("avg_temperature_120m_24h",
                DEGREES, weatherData -> weatherData.getHourly().getTemperature120m());
        columns[6] = new Column("avg_wind_speed_10m_24h",
                WINDSPEED, weatherData -> weatherData.getHourly().getWindSpeed10m());
        columns[7] = new Column("avg_wind_speed_80m_24h",
                WINDSPEED, weatherData -> weatherData.getHourly().getWindSpeed80m());
        columns[8] = new Column("avg_visibility_24h",
                FT_TO_M, weatherData -> weatherData.getHourly().getVisibility());
        columns[9] = new Column("total_rain_24h",
                INCH_TO_MM, weatherData -> weatherData.getHourly().getRain());
        columns[10] = new Column("total_showers_24h",
                INCH_TO_MM, weatherData -> weatherData.getHourly().getShowers());
        columns[11] = new Column("total_snowfall_24h",
                INCH_TO_MM, weatherData -> weatherData.getHourly().getSnowfall());
        columns[12] = new Column("avg_temperature_2m_daylight",
                DEGREES, weatherData -> weatherData.getHourly().getTemperature2m());
        columns[13] = new Column("avg_relative_humidity_2m_daylight",
                null, weatherData -> weatherData.getHourly().getRelativeHumidity2m());
        columns[14] = new Column("avg_dew_point_2m_daylight",
                DEGREES, weatherData -> weatherData.getHourly().getDewPoint2m());
        columns[15] = new Column("avg_apparent_temperature_daylight",
                DEGREES, weatherData -> weatherData.getHourly().getApparentTemperature());
        columns[16] = new Column("avg_temperature_80m_daylight",
                DEGREES, weatherData -> weatherData.getHourly().getTemperature80m());
        columns[17] = new Column("avg_temperature_120m_daylight",
                DEGREES, weatherData -> weatherData.getHourly().getTemperature120m());
        columns[18] = new Column("avg_wind_speed_10m_daylight",
                WINDSPEED, weatherData -> weatherData.getHourly().getWindSpeed10m());
        columns[19] = new Column("avg_wind_speed_80m_daylight",
                WINDSPEED, weatherData -> weatherData.getHourly().getWindSpeed80m());
        columns[20] = new Column("avg_visibility_daylight",
                FT_TO_M, weatherData -> weatherData.getHourly().getVisibility());
        columns[21] = new Column("total_rain_daylight",
                INCH_TO_MM, weatherData -> weatherData.getHourly().getRain());
        columns[22] = new Column("total_showers_daylight",
                INCH_TO_MM, weatherData -> weatherData.getHourly().getShowers());
        columns[23] = new Column("total_snowfall_daylight",
                INCH_TO_MM, weatherData -> weatherData.getHourly().getSnowfall());
        columns[24] = new Column("wind_speed_10m_m_per_s",
                WINDSPEED, weatherData -> weatherData.getHourly().getWindSpeed10m());
        columns[25] = new Column("wind_speed_80m_m_per_s",
                WINDSPEED, weatherData -> weatherData.getHourly().getWindSpeed80m());
        columns[26] = new Column("temperature_2m_celsius",
                DEGREES, weatherData -> weatherData.getHourly().getTemperature2m());
        columns[27] = new Column("apparent_temperature_celsius",
                DEGREES, weatherData -> weatherData.getHourly().getApparentTemperature());
        columns[28] = new Column("temperature_80m_celsius",
                DEGREES, weatherData -> weatherData.getHourly().getTemperature80m());
        columns[29] = new Column("temperature_120m_celsius",
                DEGREES, weatherData -> weatherData.getHourly().getTemperature120m());
        columns[30] = new Column("soil_temperature_0cm_celsius",
                DEGREES,weatherData -> weatherData.getHourly().getSoilTemperature0cm());
        columns[31] = new Column("soil_temperature_6cm_celsius",
                DEGREES, weatherData -> weatherData.getHourly().getSoilTemperature6cm());
        columns[32] = new Column("rain_mm",
                INCH_TO_MM, weatherData -> weatherData.getHourly().getRain());
        columns[33] = new Column("showers_mm",
                INCH_TO_MM, weatherData -> weatherData.getHourly().getShowers());
        columns[34] = new Column("snowfall_mm",
                INCH_TO_MM, weatherData -> weatherData.getHourly().getSnowfall());
        columns[35] = new Column("daylight_hours",
                null, weatherData -> weatherData.getDaily().getDaylightDuration());
        columns[36] = new Column("sunrise_iso",
                UNIX_TO_ISO8601, weatherData -> weatherData.getDaily().getSunrise());
        columns[37] = new Column("sunset_iso",
                UNIX_TO_ISO8601, weatherData -> weatherData.getDaily().getSunset());

        return columns;
    }
}