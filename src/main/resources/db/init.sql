DROP TABLE IF EXISTS daily_data;
DROP TABLE IF EXISTS hourly_data;

CREATE TABLE daily_data
(
    id SERIAL PRIMARY KEY,
    local_day TEXT UNIQUE,
    avg_temperature_2m_24h DOUBLE PRECISION,
    avg_relative_humidity_2m_24h INTEGER,
    avg_dew_point_2m_24h DOUBLE PRECISION,
    avg_apparent_temperature_24h DOUBLE PRECISION,
    avg_temperature_80m_24h DOUBLE PRECISION,
    avg_temperature_120m_24h DOUBLE PRECISION,
    avg_wind_speed_10m_24h DOUBLE PRECISION,
    avg_wind_speed_80m_24h DOUBLE PRECISION,
    avg_visibility_24h DOUBLE PRECISION,
    total_rain_24h DOUBLE PRECISION,
    total_showers_24h DOUBLE PRECISION,
    total_snowfall_24h DOUBLE PRECISION,
    avg_temperature_2m_daylight DOUBLE PRECISION,
    avg_relative_humidity_2m_daylight INTEGER,
    avg_new_point_2m_daylight DOUBLE PRECISION,
    avg_apparent_temperature_daylight DOUBLE PRECISION,
    avg_temperature_80m_daylight DOUBLE PRECISION,
    avg_temperature_120m_daylight DOUBLE PRECISION,
    avg_wind_speed_10m_daylight DOUBLE PRECISION,
    avg_wind_speed_80m_daylight DOUBLE PRECISION,
    avg_visibility_daylight DOUBLE PRECISION,
    total_rain_daylight DOUBLE PRECISION,
    total_showers_daylight DOUBLE PRECISION,
    total_snowfall_daylight DOUBLE PRECISION,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE hourly_data
(
   id SERIAL PRIMARY KEY,
   local_day TEXT,
   local_time TEXT,
   wind_speed_10m_m_per_s DOUBLE PRECISION,
   wind_speed_80m_m_per_s DOUBLE PRECISION,
   temperature_2m_celsius DOUBLE PRECISION,
   apparent_temperature_celsius DOUBLE PRECISION,
   temperature_80m_celsius DOUBLE PRECISION,
   temperature_120m_celsius DOUBLE PRECISION,
   soil_temperature_0cm_celsius DOUBLE PRECISION,
   soil_temperature_6cm_celsius DOUBLE PRECISION,
   rain_mm DOUBLE PRECISION,
   showers_mm DOUBLE PRECISION,
   snowfall_mm DOUBLE PRECISION,
   daylight_hours DOUBLE PRECISION,
   sunrise_iso TEXT,
   sunset_iso TEXT,
   created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
   UNIQUE(local_day, local_time)
);

ALTER TABLE hourly_data
    ADD CONSTRAINT fk_hourly_data_daily_data
        FOREIGN KEY (local_day) REFERENCES daily_data(local_day) ON DELETE CASCADE;

