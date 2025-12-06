package data;

import java.util.function.Function;

//record consists of info about column in the table
public record Column(
        Transformation transformation,

        //function to get an array with all data in this column
        Function<WeatherData, Number[]> valueExtractor
){}
