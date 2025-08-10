package data;

import java.util.function.Function;

public record Column(
        String columnName,
        Transformation transformation,
        Function<WeatherData, Number[]> valueExtractor
){}
