package data;

import com.fasterxml.jackson.annotation.JsonProperty;

//class which contains all data from the URL
public class WeatherData {
    private Double latitude;
    private Double longitude;
    private Double generationtime_ms;
    private Integer utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private Double elevation;

    @JsonProperty("hourly_units")
    private HourlyUnits hourlyUnits;
    @JsonProperty("hourly")
    private HourlyData hourly;
    @JsonProperty("daily_units")
    private DailyUnits dailyUnits;
    @JsonProperty("daily")
    private DailyData daily;

    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Double getGenerationtime_ms() {
        return generationtime_ms;
    }
    public void setGenerationtime_ms(Double generationtime_ms) {
        this.generationtime_ms = generationtime_ms;
    }
    public Integer getUtc_offset_seconds() {
        return utc_offset_seconds;
    }
    public void setUtc_offset_seconds(Integer utc_offset_seconds) {
        this.utc_offset_seconds = utc_offset_seconds;
    }
    public String getTimezone() {
        return timezone;
    }
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    public String getTimezone_abbreviation() {
        return timezone_abbreviation;
    }
    public void setTimezone_abbreviation(String timezone_abbreviation) {
        this.timezone_abbreviation = timezone_abbreviation;
    }
    public Double getElevation() {
        return elevation;
    }
    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }
    public HourlyUnits getHourlyUnits() {
        return hourlyUnits;
    }
    public void setHourlyUnits(HourlyUnits hourlyUnits) {
        this.hourlyUnits = hourlyUnits;
    }
    public HourlyData getHourly() {
        return hourly;
    }
    public void setHourly(HourlyData hourly) {
        this.hourly = hourly;
    }
    public DailyUnits getDailyUnits() {
        return dailyUnits;
    }
    public void setDailyUnits(DailyUnits dailyUnits) {
        this.dailyUnits = dailyUnits;
    }
    public DailyData getDaily() {
        return daily;
    }
    public void setDaily(DailyData daily) {
        this.daily = daily;
    }

    public class HourlyUnits {
        private String time;
        @JsonProperty("temperature_2m")
        private String temperature2m;
        @JsonProperty("relative_humidity_2m")
        private String relativeHumidity2m;
        @JsonProperty("dew_point_2m")
        private String dewPoint2m;
        @JsonProperty("apparent_temperature")
        private String apparentTemperature;
        @JsonProperty("temperature_80m")
        private String temperature80m;
        @JsonProperty("temperature_120m")
        private String temperature120m;
        @JsonProperty("wind_speed_10m")
        private String windSpeed10m;
        @JsonProperty("wind_speed_80m")
        private String windSpeed80m;
        @JsonProperty("wind_direction_10m")
        private String windDirection10m;
        @JsonProperty("wind_direction_80m")
        private String windDirection80m;
        private String visibility;
        private String evapotranspiration;
        @JsonProperty("weather_code")
        private String weatherCode;
        @JsonProperty("soil_temperature_0cm")
        private String soilTemperature0cm;
        @JsonProperty("soil_temperature_6cm")
        private String soilTemperature6cm;
        private String rain;
        private String showers;
        private String snowfall;

        public String getTime() {
            return time;
        }
        public void setTime(String time) {
            this.time = time;
        }
        public String getRelativeHumidity2m() {
            return relativeHumidity2m;
        }
        public void setRelativeHumidity2m(String relativeHumidity2m) {
            this.relativeHumidity2m = relativeHumidity2m;
        }
        public String getTemperature2m() {
            return temperature2m;
        }
        public void setTemperature2m(String temperature2m) {
            this.temperature2m = temperature2m;
        }
        public String getDewPoint2m() {
            return dewPoint2m;
        }
        public void setDewPoint2m(String dewPoint2m) {
            this.dewPoint2m = dewPoint2m;
        }
        public String getApparentTemperature() {
            return apparentTemperature;
        }
        public void setApparentTemperature(String apparentTemperature) {
            this.apparentTemperature = apparentTemperature;
        }
        public String getTemperature80m() {
            return temperature80m;
        }
        public void setTemperature80m(String temperature80m) {
            this.temperature80m = temperature80m;
        }
        public String getTemperature120m() {
            return temperature120m;
        }
        public void setTemperature120m(String temperature120m) {
            this.temperature120m = temperature120m;
        }
        public String getWindSpeed10m() {
            return windSpeed10m;
        }
        public void setWindSpeed10m(String windSpeed10m) {
            this.windSpeed10m = windSpeed10m;
        }
        public String getWindSpeed80m() {
            return windSpeed80m;
        }
        public void setWindSpeed80m(String windSpeed80m) {
            this.windSpeed80m = windSpeed80m;
        }
        public String getWindDirection10m() {
            return windDirection10m;
        }
        public void setWindDirection10m(String windDirection10m) {
            this.windDirection10m = windDirection10m;
        }
        public String getWindDirection80m() {
            return windDirection80m;
        }
        public void setWindDirection80m(String windDirection80m) {
            this.windDirection80m = windDirection80m;
        }
        public String getVisibility() {
            return visibility;
        }
        public void setVisibility(String visibility) {
            this.visibility = visibility;
        }
        public String getEvapotranspiration() {
            return evapotranspiration;
        }
        public void setEvapotranspiration(String evapotranspiration) {
            this.evapotranspiration = evapotranspiration;
        }
        public String getWeatherCode() {
            return weatherCode;
        }
        public void setWeatherCode(String weatherCode) {
            this.weatherCode = weatherCode;
        }
        public String getSoilTemperature0cm() {
            return soilTemperature0cm;
        }
        public void setSoilTemperature0cm(String soilTemperature0cm) {
            this.soilTemperature0cm = soilTemperature0cm;
        }
        public String getSoilTemperature6cm() {
            return soilTemperature6cm;
        }
        public void setSoilTemperature6cm(String soilTemperature6cm) {
            this.soilTemperature6cm = soilTemperature6cm;
        }
        public String getRain() {
            return rain;
        }
        public void setRain(String rain) {
            this.rain = rain;
        }
        public String getShowers() {
            return showers;
        }
        public void setShowers(String showers) {
            this.showers = showers;
        }
        public String getSnowfall() {
            return snowfall;
        }
        public void setSnowfall(String snowfall) {
            this.snowfall = snowfall;
        }
    }

    public class HourlyData {
        private Long[] time;
        @JsonProperty("temperature_2m")
        private Double[] temperature2m;
        @JsonProperty("relative_humidity_2m")
        private Integer[] relativeHumidity2m;
        @JsonProperty("dew_point_2m")
        private Double[] dewPoint2m;
        @JsonProperty("apparent_temperature")
        private Double[] apparentTemperature;
        @JsonProperty("temperature_80m")
        private Double[] temperature80m;
        @JsonProperty("temperature_120m")
        private Double[] temperature120m;
        @JsonProperty("wind_speed_10m")
        private Double[] windSpeed10m;
        @JsonProperty("wind_speed_80m")
        private Double[] windSpeed80m;
        @JsonProperty("wind_direction_10m")
        private Integer[] windDirection10m;
        @JsonProperty("wind_direction_80m")
        private Integer[] windDirection80m;
        private Double[] visibility;
        private Double[] evapotranspiration;
        @JsonProperty("weather_code")
        private Integer[] weatherCode;
        @JsonProperty("soil_temperature_0cm")
        private Double[] soilTemperature0cm;
        @JsonProperty("soil_temperature_6cm")
        private Double[] soilTemperature6cm;
        private Double[] rain;
        private Double[] showers;
        private Double[] snowfall;

        public Long[] getTime() {
            return time;
        }

        public void setTime(Long[] time) {
            this.time = time;
        }

        public Double[] getTemperature2m() {
            return temperature2m;
        }

        public void setTemperature2m(Double[] temperature2m) {
            this.temperature2m = temperature2m;
        }

        public Integer[] getRelativeHumidity2m() {
            return relativeHumidity2m;
        }

        public void setRelativeHumidity2m(Integer[] relativeHumidity2m) {
            this.relativeHumidity2m = relativeHumidity2m;
        }

        public Double[] getDewPoint2m() {
            return dewPoint2m;
        }

        public void setDewPoint2m(Double[] dewPoint2m) {
            this.dewPoint2m = dewPoint2m;
        }

        public Double[] getApparentTemperature() {
            return apparentTemperature;
        }

        public void setApparentTemperature(Double[] apparentTemperature) {
            this.apparentTemperature = apparentTemperature;
        }

        public Double[] getTemperature80m() {
            return temperature80m;
        }

        public void setTemperature80m(Double[] temperature80m) {
            this.temperature80m = temperature80m;
        }

        public Double[] getTemperature120m() {
            return temperature120m;
        }

        public void setTemperature120m(Double[] temperature120m) {
            this.temperature120m = temperature120m;
        }

        public Double[] getWindSpeed10m() {
            return windSpeed10m;
        }

        public void setWindSpeed10m(Double[] windSpeed10m) {
            this.windSpeed10m = windSpeed10m;
        }

        public Double[] getWindSpeed80m() {
            return windSpeed80m;
        }

        public void setWindSpeed80m(Double[] windSpeed80m) {
            this.windSpeed80m = windSpeed80m;
        }

        public Integer[] getWindDirection10m() {
            return windDirection10m;
        }

        public void setWindDirection10m(Integer[] windDirection10m) {
            this.windDirection10m = windDirection10m;
        }

        public Integer[] getWindDirection80m() {
            return windDirection80m;
        }

        public void setWindDirection80m(Integer[] windDirection80m) {
            this.windDirection80m = windDirection80m;
        }

        public Double[] getVisibility() {
            return visibility;
        }

        public void setVisibility(Double[] visibility) {
            this.visibility = visibility;
        }

        public Double[] getEvapotranspiration() {
            return evapotranspiration;
        }

        public void setEvapotranspiration(Double[] evapotranspiration) {
            this.evapotranspiration = evapotranspiration;
        }

        public Integer[] getWeatherCode() {
            return weatherCode;
        }

        public void setWeatherCode(Integer[] weatherCode) {
            this.weatherCode = weatherCode;
        }

        public Double[] getSoilTemperature0cm() {
            return soilTemperature0cm;
        }

        public void setSoilTemperature0cm(Double[] soilTemperature0cm) {
            this.soilTemperature0cm = soilTemperature0cm;
        }

        public Double[] getSoilTemperature6cm() {
            return soilTemperature6cm;
        }

        public void setSoilTemperature6cm(Double[] soilTemperature6cm) {
            this.soilTemperature6cm = soilTemperature6cm;
        }

        public Double[] getRain() {
            return rain;
        }

        public void setRain(Double[] rain) {
            this.rain = rain;
        }

        public Double[] getShowers() {
            return showers;
        }

        public void setShowers(Double[] showers) {
            this.showers = showers;
        }

        public Double[] getSnowfall() {
            return snowfall;
        }

        public void setSnowfall(Double[] snowfall) {
            this.snowfall = snowfall;
        }
    }

    public static class DailyUnits {
        private String time;
        private String sunrise;
        private String sunset;

        public String getTime() {
            return time;
        }
        public void setTime(String time) {
            this.time = time;
        }
        public String getSunrise() {
            return sunrise;
        }
        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }
        public String getSunset() {
            return sunset;
        }
        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

    }

    public class DailyData {
        private Long[] time;
        private Long[] sunrise;
        private Long[] sunset;

        public Long[] getTime() {
            return time;
        }

        public void setTime(Long[] time) {
            this.time = time;
        }

        public Long[] getSunrise() {
            return sunrise;
        }

        public void setSunrise(Long[] sunrise) {
            this.sunrise = sunrise;
        }

        public Long[] getSunset() {
            return sunset;
        }

        public void setSunset(Long[] sunset) {
            this.sunset = sunset;
        }

        public Double[] getDaylightDuration() {
            final int secInHour = 3600;
            Double[] daylightDuration = new Double[time.length];
            for (int dayNum = 0; dayNum < time.length; dayNum++)
                daylightDuration[dayNum] = (double) (sunset[dayNum] - sunrise[dayNum]) / secInHour;

            return daylightDuration;
        }

    }
}