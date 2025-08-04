package processing;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Loader {
    public static void saveToCsv(List<String[]> data, String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    if (row[i] == null || "null".equals(row[i])) {
                        row[i] = "";
                    }
                }
                writer.write(String.join(",", row));
                writer.write("\n");
            }
        }
    }
}