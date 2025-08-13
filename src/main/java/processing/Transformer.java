package processing;

import data.Column;
import data.WeatherData;
import factory.Conversion;
import factory.ConversionFactory;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

import static processing.NumberArrayWorker.addToNumberArray;
import static processing.NumberArrayWorker.divInNumberArray;


public class Transformer {

    private final int colCount = 40;
    private final int fullDayInHours = 24;
    private WeatherData weatherData;
    private String[][] csvData;
    private Number[] values;
    Column[] columns;

    public Transformer(Column[] columns, WeatherData weatherData) {
        int recordCount = weatherData.getHourly().getTime().length;
        this.csvData = new String[recordCount][colCount];
        this.values = new Number[colCount];
        this.columns = columns;
        this.weatherData = weatherData;
    }

//this main method transform input data to the output array
    public String[][] transformData() throws IOException {

        for (int dayNum = 0; dayNum < weatherData.getDaily().getTime().length; dayNum++) {//cycle by day
            Arrays.fill(values, 0);//starting a new day
            LocalDateTime sunrise = LocalDateTime.ofInstant(Instant.ofEpochSecond(
                    weatherData.getDaily().getSunrise()[dayNum]),
                    ZoneId.of(weatherData.getTimezone()));

            LocalDateTime sunset = LocalDateTime.ofInstant(Instant.ofEpochSecond(
                            weatherData.getDaily().getSunset()[dayNum]),
                    ZoneId.of(weatherData.getTimezone()));

            int recordDaylightCount = 0;

            for (int j = 0; j < fullDayInHours; j++) {//calculating general value for the day
                int recordNum = dayNum * fullDayInHours + j;
                fillNumberArray(0, recordNum);

                if (isDaylightRecord(recordNum, sunrise, sunset)) {// general value for the daylight
                    fillNumberArray(1, recordNum);
                    recordDaylightCount++;

                }
            }

            for (int colNum = 0; colNum < colCount; colNum++){//converts into the required format and writes data
                final boolean isMiddleValueCol = (colNum >= 14 && colNum < 23) || (colNum > 1 && colNum < 11);
                final boolean isGeneralValueCol = (colNum >= 11 && colNum < 14) || (colNum >= 23 && colNum < 26);
                final boolean isDaylightCol = (colNum >= 14 && colNum < 26);
                final boolean isDailyCol = (colNum >= 37) || (colNum == 0);

                Conversion convert = ConversionFactory.getConversion(columns[colNum].transformation());
                if (convert == null)
                    throw new IOException("Error: Conversion could not be found: " +
                            columns[colNum].transformation());

                String convertedValue = "";
                if (isMiddleValueCol && !isDaylightCol){//converted to 24 hour average
                    convertedValue = convert.makeTransformation(
                            divInNumberArray(values[colNum], fullDayInHours));
                }

                if (isMiddleValueCol && isDaylightCol){//converted to daylight average
                    convertedValue = convert.makeTransformation(
                            divInNumberArray(values[colNum], recordDaylightCount));
                }

                if (isGeneralValueCol)//converted the general value
                    convertedValue = convert.makeTransformation(values[colNum]);

                if (isDailyCol)//transformed the columns that describes the day
                    convertedValue = convert.makeTransformation(
                            columns[colNum].valueExtractor().apply(weatherData)[dayNum]);


                for (int recordNum = 0; recordNum < fullDayInHours; recordNum++){//entered the transformed value into each cell
                    int curRecordNum = recordNum + dayNum * fullDayInHours;
                    if (!isMiddleValueCol && !isGeneralValueCol && !isDailyCol) {
                        convertedValue = convert.makeTransformation(
                                columns[colNum].valueExtractor().apply(weatherData)[curRecordNum]);

                    }
                    csvData[curRecordNum][colNum] = convertedValue;
                }

            }
        }

        return csvData;
    }

    //helper method for filling array of general values
    private void fillNumberArray(int partNum, int recordNum){
        int funcCount = 12;
        for (int i = 2; i < funcCount; i++) {
            addToNumberArray(values, i + partNum * funcCount,
                    columns[i + partNum * funcCount].valueExtractor().apply(weatherData)[recordNum]);
        }
    }

    //determines whether the recording was made in daylight or not
    private boolean isDaylightRecord(int recordNum, LocalDateTime sunrise,  LocalDateTime sunset){
        LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochSecond(
                        weatherData.getHourly().getTime()[recordNum]),
                        ZoneId.of(weatherData.getTimezone()));

        return time.isBefore(sunset) && time.isAfter(sunrise);
    }
}
