package processing;

import data.WeatherData;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    @Test
    void shouldFetchWeatherDataSuccessfully() throws IOException, InterruptedException {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=55.0344&longitude=82.9434" +
                "&daily=sunrise,sunset&hourly=temperature_2m,relative_humidity_2m,dew_point_2m," +
                "apparent_temperature,temperature_80m,temperature_120m,wind_speed_10m,wind_speed_80m," +
                "wind_direction_10m,wind_direction_80m,visibility,evapotranspiration,weather_code," +
                "soil_temperature_0cm,soil_temperature_6cm,rain,showers,snowfall" +
                "&timezone=auto&timeformat=unixtime&wind_speed_unit=kn&temperature_unit=fahrenheit" +
                "&precipitation_unit=inch&start_date=2025-07-16&end_date=2025-07-30";

        WeatherData data = Converter.fetchDataFromApi(url);

        assertNotNull(data);
        assertEquals(55.0, data.getLatitude(), 0.0001);
        assertEquals(83.0, data.getLongitude(), 0.0001);

        assertNotNull(data.getHourly());
        assertTrue(data.getHourly().getTemperature2m().length > 0, "Hourly temperature should not be empty");

        assertNotNull(data.getDaily());
        assertTrue(data.getDaily().getTime().length > 0, "Daily time should not be empty");
    }

}
