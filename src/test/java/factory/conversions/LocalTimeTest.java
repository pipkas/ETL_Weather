package factory.conversions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class LocalTimeTest {
    private final LocalTime converter = new LocalTime();

    @Test
    void makeTransformation_ReturnsStringType() {
        String result = converter.makeTransformation(0L);
        assertInstanceOf(String.class, result);
    }

    @ParameterizedTest
    @CsvSource({
            "0, '07:00:00'",
            "3600, '08:00:00'",
            "1640995200, '07:00:00'",
            "1641020400, '14:00:00'",
            "1641033999, '17:46:39'"
    })
    void makeTransformation_ConvertsUnixTimeToCorrectTimeFormat(long unixTime, String expectedTime) {
        String result = converter.makeTransformation(unixTime);
        assertEquals(expectedTime, result);
    }

    @Test
    void makeTransformation_HandlesMidnight() {
        String result = converter.makeTransformation(1640995200L);
        assertEquals("07:00:00", result);
    }

    @Test
    void makeTransformation_HandlesNoon() {
        String result = converter.makeTransformation(1641038400L);
        assertEquals("19:00:00", result);
    }

    @Test
    void makeTransformation_ValidatesTimeFormat() {
        String result = converter.makeTransformation(1641020400L);
        assertTrue(result.matches("^([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$"));
    }

    @Test
    void makeTransformation_UsesCorrectTimeZone() {
        String result = converter.makeTransformation(0L);
        assertEquals("07:00:00", result);
    }

    @Test
    void makeTransformation_HandlesDaylightSavingTime() {
        String result = converter.makeTransformation(1300000000L);
        assertEquals("13:06:40", result);
    }
}