import data.Column;
import data.WeatherData;
import processing.Converter;
import processing.Transformer;
import processing.Loader;

import static data.Transformation.*;

//main class for starting an app
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

        final String[] colNames  = {"local_day", "local_time", "avg_temperature_2m_24h", "avg_relative_humidity_2m_24h", "avg_dew_point_2m_24h",
                "avg_apparent_temperature_24h", "avg_temperature_80m_24h", "avg_temperature_120m_24h", "avg_wind_speed_10m_24h",
                "avg_wind_speed_80m_24h", "avg_visibility_24h", "total_rain_24h", "total_showers_24h", "total_snowfall_24h",
                "avg_temperature_2m_daylight", "avg_relative_humidity_2m_daylight", "avg_new_point_2m_daylight",
                "avg_apparent_temperature_daylight", "avg_temperature_80m_daylight", "avg_temperature_120m_daylight",
                "avg_wind_speed_10m_daylight", "avg_wind_speed_80m_daylight", "avg_visibility_daylight", "total_rain_daylight",
                "total_showers_daylight", "total_snowfall_daylight", "wind_speed_10m_m_per_s", "wind_speed_80m_m_per_s",
                "temperature_2m_celsius", "apparent_temperature_celsius", "temperature_80m_celsius", "temperature_120m_celsius",
                "soil_temperature_0cm_celsius", "soil_temperature_6cm_celsius", "rain_mm", "showers_mm", "snowfall_mm",
                "daylight_hours", "sunrise_iso", "sunset_iso"};

        Column[] columns = fillColumnData();

        String fileName = "weather_data.csv";
        try {
            WeatherData weatherData = Converter.fetchDataFromApi(apiUrl);

            Transformer transformer = new Transformer(columns, weatherData);
            String[][] data = transformer.transformData();

            Loader.saveToDb(data, colNames);

            Loader.saveToCsv(data, colNames, fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Column[] fillColumnData() {
        final int colCount = 40;
        Column[] columns = new Column[colCount];
        columns[0] = new Column(UNIX_TO_LOCAL_DATE, weatherData -> weatherData.getDaily().getTime());
        columns[1] = new Column(UNIX_TO_LOCAL_TIME, weatherData -> weatherData.getHourly().getTime());
        columns[2] = new Column(DEGREES, weatherData -> weatherData.getHourly().getTemperature2m());
        columns[3] = new Column(null, weatherData -> weatherData.getHourly().getRelativeHumidity2m());
        columns[4] = new Column(DEGREES, weatherData -> weatherData.getHourly().getDewPoint2m());
        columns[5] = new Column(DEGREES, weatherData -> weatherData.getHourly().getApparentTemperature());
        columns[6] = new Column(DEGREES, weatherData -> weatherData.getHourly().getTemperature80m());
        columns[7] = new Column(DEGREES, weatherData -> weatherData.getHourly().getTemperature120m());
        columns[8] = new Column(WINDSPEED, weatherData -> weatherData.getHourly().getWindSpeed10m());
        columns[9] = new Column(WINDSPEED, weatherData -> weatherData.getHourly().getWindSpeed80m());
        columns[10] = new Column(FT_TO_M, weatherData -> weatherData.getHourly().getVisibility());
        columns[11] = new Column(INCH_TO_MM, weatherData -> weatherData.getHourly().getRain());
        columns[12] = new Column(INCH_TO_MM, weatherData -> weatherData.getHourly().getShowers());
        columns[13] = new Column(INCH_TO_MM, weatherData -> weatherData.getHourly().getSnowfall());
        columns[14] = new Column(DEGREES, weatherData -> weatherData.getHourly().getTemperature2m());
        columns[15] = new Column(null, weatherData -> weatherData.getHourly().getRelativeHumidity2m());
        columns[16] = new Column(DEGREES, weatherData -> weatherData.getHourly().getDewPoint2m());
        columns[17] = new Column(DEGREES, weatherData -> weatherData.getHourly().getApparentTemperature());
        columns[18] = new Column(DEGREES, weatherData -> weatherData.getHourly().getTemperature80m());
        columns[19] = new Column(DEGREES, weatherData -> weatherData.getHourly().getTemperature120m());
        columns[20] = new Column(WINDSPEED, weatherData -> weatherData.getHourly().getWindSpeed10m());
        columns[21] = new Column(WINDSPEED, weatherData -> weatherData.getHourly().getWindSpeed80m());
        columns[22] = new Column(FT_TO_M, weatherData -> weatherData.getHourly().getVisibility());
        columns[23] = new Column(INCH_TO_MM, weatherData -> weatherData.getHourly().getRain());
        columns[24] = new Column(INCH_TO_MM, weatherData -> weatherData.getHourly().getShowers());
        columns[25] = new Column(INCH_TO_MM, weatherData -> weatherData.getHourly().getSnowfall());
        columns[26] = new Column(WINDSPEED, weatherData -> weatherData.getHourly().getWindSpeed10m());
        columns[27] = new Column(WINDSPEED, weatherData -> weatherData.getHourly().getWindSpeed80m());
        columns[28] = new Column(DEGREES, weatherData -> weatherData.getHourly().getTemperature2m());
        columns[29] = new Column(DEGREES, weatherData -> weatherData.getHourly().getApparentTemperature());
        columns[30] = new Column(DEGREES, weatherData -> weatherData.getHourly().getTemperature80m());
        columns[31] = new Column(DEGREES, weatherData -> weatherData.getHourly().getTemperature120m());
        columns[32] = new Column(DEGREES,weatherData -> weatherData.getHourly().getSoilTemperature0cm());
        columns[33] = new Column(DEGREES, weatherData -> weatherData.getHourly().getSoilTemperature6cm());
        columns[34] = new Column(INCH_TO_MM, weatherData -> weatherData.getHourly().getRain());
        columns[35] = new Column(INCH_TO_MM, weatherData -> weatherData.getHourly().getShowers());
        columns[36] = new Column(INCH_TO_MM, weatherData -> weatherData.getHourly().getSnowfall());
        columns[37] = new Column(null, weatherData -> weatherData.getDaily().getDaylightDuration());
        columns[38] = new Column(UNIX_TO_ISO8601, weatherData -> weatherData.getDaily().getSunrise());
        columns[39] = new Column(UNIX_TO_ISO8601, weatherData -> weatherData.getDaily().getSunset());

        return columns;
    }
}