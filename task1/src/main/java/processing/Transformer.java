package processing;

import data.WeatherData;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Transformer {

    private static final int colNumber = 23;
    public static List<String[]> transformData(WeatherData weatherData, String csvHeader) {
        List<String[]> csvData = new ArrayList<>();
        csvData.add(csvHeader.split(","));

        for (int i = 0; i < weatherData.getHourly().getTime().length; i++) {
            String[] row = new String[colNumber];
            Arrays.fill(row, "");

            LocalDateTime dateTime = LocalDateTime.ofInstant(
                    Instant.ofEpochSecond(weatherData.getHourly().getTime()[i]),
                    ZoneId.of(weatherData.getTimezone()));
            row[0] = String.valueOf(weatherData.getLatitude());
            row[1] = String.valueOf(weatherData.getLongitude());
            row[2] = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            row[3] = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));

            row[4] = String.valueOf(weatherData.getHourly().getTemperature2m()[i]);
            row[5] = String.valueOf(weatherData.getHourly().getRelativeHumidity2m()[i]);

            row[6] = String.valueOf(weatherData.getHourly().getDewPoint2m()[i]);
            row[7] = String.valueOf(weatherData.getHourly().getApparentTemperature()[i]);
            row[8] = String.valueOf(weatherData.getHourly().getTemperature80m()[i]);
            row[9] = String.valueOf(weatherData.getHourly().getTemperature120m()[i]);

            row[10] = String.valueOf(weatherData.getHourly().getWindSpeed10m()[i]);
            row[11] = String.valueOf(weatherData.getHourly().getWindSpeed80m()[i]);
            row[12] = String.valueOf(weatherData.getHourly().getWindDirection10m()[i]);
            row[13] = String.valueOf(weatherData.getHourly().getWindDirection80m()[i]);

            row[14] = String.valueOf(weatherData.getHourly().getVisibility()[i]);
            row[15] = String.valueOf(weatherData.getHourly().getEvapotranspiration()[i]);
            row[16] = String.valueOf(weatherData.getHourly().getWeatherCode()[i]);

            row[17] = String.valueOf(weatherData.getHourly().getSoilTemperature0cm()[i]);
            row[18] = String.valueOf(weatherData.getHourly().getSoilTemperature6cm()[i]);

            double precipitation = weatherData.getHourly().getRain()[i] +
                    weatherData.getHourly().getShowers()[i] +
                    weatherData.getHourly().getSnowfall()[i];
            row[19] = String.valueOf(precipitation);

            addDailyData(weatherData, row, dateTime.toLocalDate().toString());

            csvData.add(row);
        }

        return csvData;
    }

    private static void addDailyData(WeatherData weatherData, String[] row, String date) {

        for (int i = 0; i < weatherData.getDaily().getTime().length; i++) {
            LocalDateTime dailyDate = LocalDateTime.ofInstant(
                    Instant.ofEpochSecond(weatherData.getDaily().getTime()[i]),
                    ZoneId.of(weatherData.getTimezone()));

            if (dailyDate.toLocalDate().toString().equals(date)) {

                LocalDateTime sunrise = LocalDateTime.ofInstant(
                        Instant.ofEpochSecond(weatherData.getDaily().getSunrise()[i]),
                        ZoneId.of(weatherData.getTimezone()));
                LocalDateTime sunset = LocalDateTime.ofInstant(
                        Instant.ofEpochSecond(weatherData.getDaily().getSunset()[i]),
                        ZoneId.of(weatherData.getTimezone()));

                row[20] = sunrise.format(DateTimeFormatter.ofPattern("HH:mm"));
                row[21] = sunset.format(DateTimeFormatter.ofPattern("HH:mm"));

                double daylightHours = weatherData.getDaily().getDaylightDuration()[i] / 3600.0;
                row[22] = String.format(Locale.US, "%.2f", daylightHours);//without Locale.US we get "16,20" ,but we want "16.20"!
                break;
            }
        }
    }
}