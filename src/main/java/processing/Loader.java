package processing;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

//class loads the transformed data into a CSV file or database
public class Loader {
    public static void saveToCsv(String[][] data, String[] colNames, String filename){
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(String.join(",", colNames));
            writer.write("\n");

            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.write("\n");
            }
            System.out.println("Successfully saved data to csv file.");
        } catch (IOException e) {
            System.err.println("FileWriter error: " + e.getMessage());
        }
    }

    public static void saveToDb(String[][] data, String[] colNames) {
        final String DB_URL = "jdbc:postgresql://localhost:5432/weather_db";
        final String USER = "pipkas";
        final String PASS = "labubu";
        final String generalCol = "local_day";
        final int fullDayInHour = 24;

        String[] hourlySubarray = Arrays.copyOfRange(colNames, 26, 40);
        String[] dailySubarray =  Arrays.copyOfRange(colNames, 2, 26);
        int colCountDaily = dailySubarray.length + 1;
        int colCountHourly = hourlySubarray.length + 2;


        String sqlDaily = "INSERT INTO daily_data (" + colNames[0] + ", " +
                String.join(", ", dailySubarray) + ") VALUES (" +
                "?, ".repeat(colCountDaily - 1) + "?) " +
                "ON CONFLICT (local_day) DO UPDATE SET " +
                Arrays.stream(dailySubarray)
                        .map(col -> col + " = EXCLUDED." + col)
                        .collect(Collectors.joining(", "));

        String sqlHourly = "INSERT INTO hourly_data (" + colNames[0] + ", " + colNames[1] + ", " +
                String.join(", ", hourlySubarray) + ") VALUES (" +
                "?, ".repeat(colCountHourly - 1) + "?) " +
                "ON CONFLICT (local_day, local_time) DO UPDATE SET " +
                Arrays.stream(hourlySubarray)
                        .map(col -> col + " = EXCLUDED." + col)
                        .collect(Collectors.joining(", "));

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmtHourly = conn.prepareStatement(sqlHourly);
             PreparedStatement pstmtDaily = conn.prepareStatement(sqlDaily)) {

            for (int recordNum = 0; recordNum < data.length; recordNum++) {

                String[] row = data[recordNum];
                final boolean isNewDay = ((recordNum % fullDayInHour) == 0);

                if (isNewDay) {//filling table daily_data which includes daily data like *_24h and *_daylight
                    int colNumDaily = 1;
                    for (int colNum = 0; colNum < colNames.length; colNum++) {
                        final boolean isIntValue = (colNum == 3) || (colNum == 15);
                        final boolean isStringValue = (colNum == 0)  || (colNum == 1) || (colNum == 38) || (colNum == 39);

                        final boolean isDailyCol = (colNames[colNum].endsWith("_24h") || (colNames[colNum].endsWith("_daylight")))
                                || Objects.equals(colNames[colNum], generalCol);

                        if (isDailyCol) {
                            fillCellDb(pstmtDaily, colNumDaily++,  row[colNum], isIntValue, isStringValue);
                        }
                    }
                    pstmtDaily.addBatch();
                }

                //filling table hourly_data which includes data for every hour(record)
                int colNumHourly = 1;
                for (int colNum = 0; colNum < colNames.length; colNum++) {
                    final boolean isDailyCol = (colNames[colNum].endsWith("_24h") || (colNames[colNum].endsWith("_daylight")))
                            || Objects.equals(colNames[colNum], generalCol);
                    final boolean isHourlyCol = (!isDailyCol) || Objects.equals(colNames[colNum], generalCol);
                    final boolean isIntValue = (colNum == 3) || (colNum == 15);
                    final boolean isStringValue = (colNum == 0)  || (colNum == 1) || (colNum == 38) || (colNum == 39);
                    if (isHourlyCol) {
                        fillCellDb(pstmtHourly, colNumHourly++, row[colNum], isIntValue, isStringValue);
                    }
                }
                pstmtHourly.addBatch();

            }
            pstmtDaily.executeBatch();
            pstmtHourly.executeBatch();
            System.out.println("Successfully saved data to database.");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private static void fillCellDb(PreparedStatement pstmt, int colNum, String value, boolean isIntValue, boolean isStringValue) throws SQLException {

        if (value == null || value.trim().isEmpty()) {
            if (isIntValue)
                pstmt.setNull(colNum, java.sql.Types.INTEGER);
            else if (isStringValue)
                pstmt.setNull(colNum, java.sql.Types.VARCHAR);
            else
                pstmt.setNull(colNum, java.sql.Types.DOUBLE);

        } else {
            if (isIntValue) {
                pstmt.setInt(colNum, Integer.parseInt(value));
            } else if (isStringValue) {
                pstmt.setString(colNum, value);
            } else {
                pstmt.setDouble(colNum, Double.parseDouble(value));
            }
        }
    }

}