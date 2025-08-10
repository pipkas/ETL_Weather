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

    private final int colCount = 38;
    private final int fullDayInHours = 24;
    private final int funcCount = 12;
    private WeatherData weatherData;
    private String[][] csvData;
    private Number[] values;
    Column[] columns;

    public Transformer(Column[] columns, WeatherData weatherData) {
        int recordCount = weatherData.getHourly().getTime().length;
        this.csvData = new String[recordCount + 1][colCount];
        this.values = new Number[colCount];
        this.columns = columns;
        this.weatherData = weatherData;
    }

    public String[][] transformData() throws IOException {

        for (int i = 0; i < colCount; i++) //заполняем названия столбцов
            csvData[0][i] = columns[i].columnName();

        for (int dayNum = 0; dayNum < weatherData.getDaily().getTime().length; dayNum++) {//цикл по дням
            Arrays.fill(values, 0);//начинается новый день
            LocalDateTime sunrise = LocalDateTime.ofInstant(Instant.ofEpochSecond(
                    weatherData.getDaily().getSunrise()[dayNum]),
                    ZoneId.of(weatherData.getTimezone()));

            LocalDateTime sunset = LocalDateTime.ofInstant(Instant.ofEpochSecond(
                            weatherData.getDaily().getSunset()[dayNum]),
                    ZoneId.of(weatherData.getTimezone()));

            int recordDaylightCount = 0;

            for (int j = 0; j < fullDayInHours; j++) {//вычисляем общее значение за день
                int recordNum = dayNum * fullDayInHours + j;
                fillNumberArray(0, recordNum);

                if (isDaylightRecord(recordNum, sunrise, sunset)) {//общее значение для светового дня
                    fillNumberArray(1, recordNum);
                    recordDaylightCount++;

                }
            }

            for (int colNum = 0; colNum < colCount; colNum++){//преобразовывает в нужные системы счисления и записываает данные
                final boolean isMiddleValueCol = (colNum >= 12 && colNum < 21) || (colNum < 9);
                final boolean isGeneralValueCol = (colNum >= 9 && colNum < 12) || (colNum >= 21 && colNum < 24);
                final boolean isDaylightCol = (colNum >= 12 && colNum < 24);
                final boolean isDailyCol = colNum >= 35 && colNum < 38;

                Conversion convert = ConversionFactory.getConversion(columns[colNum].transformation());
                if (convert == null)
                    throw new IOException("Error: Conversion could not be found: " +
                            columns[colNum].transformation());

                String convertedValue = "";
                if (isMiddleValueCol && !isDaylightCol){//преобразовали среднее значение за 24 часа
                    convertedValue = convert.makeTransformation(
                            divInNumberArray(values[colNum], fullDayInHours));
                }

                if (isMiddleValueCol && isDaylightCol){//преобразовали среднее значение за световое время(daylight)
                    convertedValue = convert.makeTransformation(
                            divInNumberArray(values[colNum], recordDaylightCount));
                }

                if (isGeneralValueCol)//преобразовали общее значение
                    convertedValue = convert.makeTransformation(values[colNum]);

                if (isDailyCol)
                    convertedValue = convert.makeTransformation(
                            columns[colNum].valueExtractor().apply(weatherData)[dayNum]);


                for (int recordNum = 0; recordNum < fullDayInHours; recordNum++){//вписали в каждую ячейку преобразованное значение
                    int curRecordNum = recordNum + dayNum * fullDayInHours;
                    if (!isMiddleValueCol && !isGeneralValueCol && !isDailyCol) {
                        convertedValue = convert.makeTransformation(
                                columns[colNum].valueExtractor().apply(weatherData)[curRecordNum]);

                    }
                    if (isDaylightCol && !(isDaylightRecord(curRecordNum, sunrise, sunset)))//время до заката или после рассвета
                        csvData[curRecordNum + 1][colNum] = "";
                    else
                        csvData[curRecordNum + 1][colNum] = convertedValue;
                }

            }
        }

        return csvData;
    }

    private void fillNumberArray(int partNum, int recordNum){
        int funcCount = 12;
        for (int i = 0; i < funcCount; i++) {
            addToNumberArray(values, i + partNum * funcCount,
                    columns[i + partNum * funcCount].valueExtractor().apply(weatherData)[recordNum]);
        }
    }

    boolean isDaylightRecord(int recordNum, LocalDateTime sunrise,  LocalDateTime sunset){
        LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochSecond(
                        weatherData.getHourly().getTime()[recordNum]),
                        ZoneId.of(weatherData.getTimezone()));

        return time.isBefore(sunset) && time.isAfter(sunrise);
    }


}
