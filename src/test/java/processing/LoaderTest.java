package processing;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoaderTest {
    @Test
    void shouldSaveDataToCsvFile() throws IOException {
        String[] colNames = {"col1", "col2", "col3"};
        String[][] data = {
                {"1", "Alice", "10.5"},
                {"2", "Bob", "20.0"}
        };

        Path tempFile = Files.createTempFile("test", ".csv");

        Loader.saveToCsv(data, colNames, tempFile.toString());

        String csvContent = Files.readString(tempFile);

        assertTrue(csvContent.contains("col1,col2,col3"));
        assertTrue(csvContent.contains("1,Alice,10.5"));
        assertTrue(csvContent.contains("2,Bob,20.0"));

        Files.deleteIfExists(tempFile);
    }

}
