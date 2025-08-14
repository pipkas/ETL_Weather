package factory.conversions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LocalDateTest {
    private final LocalDate converter = new LocalDate();

    @Test
    void makeTransformation_ConvertsUnixTimeToCorrectDateFormat() {
        String result = converter.makeTransformation(1577836800L);
        assertEquals("01.01.2020", result);
    }

    @Test
    void makeTransformation_HandlesLeapYear() {
        String result = converter.makeTransformation(1582934400L);
        assertEquals("29.02.2020", result);
    }

    @Test
    void makeTransformation_HandlesTimeZoneCorrectly() {
        String result = converter.makeTransformation(0L);
        assertEquals("01.01.1970", result);
    }

    @Test
    void makeTransformation_HandlesCurrentTime() {
        long currentUnixTime = System.currentTimeMillis() / 1000;
        String result = converter.makeTransformation(currentUnixTime);
        assertNotNull(result);
        assertEquals(10, result.length());
    }

    @Test
    void makeTransformation_HandlesLargeValues() {
        String result = converter.makeTransformation(2147483647L);
        assertEquals("19.01.2038", result);
    }

    @Test
    void makeTransformation_ReturnsCorrectType() {
        String result = converter.makeTransformation(1609459200L);
        assertInstanceOf(String.class, result);
    }

    @Test
    void makeTransformation_ContainsCorrectDateFormat() {
        String result = converter.makeTransformation(1638316800L);
        assertTrue(result.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$"));
    }
}