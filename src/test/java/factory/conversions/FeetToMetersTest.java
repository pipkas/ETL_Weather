package factory.conversions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class FeetToMetersTest {
    private final FeetToMeters converter = new FeetToMeters();

    @Test
    void makeTransformation_ReturnsStringType() {
        String result = converter.makeTransformation(10);
        assertInstanceOf(String.class, result);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0.0",
            "1, 0.3048",
            "10, 3.048",
            "100, 30.48",
            "3.28084, 1.000000032"
    })
    void makeTransformation_ConvertsFeetToMeters(double feet, double expectedMeters) {
        String result = converter.makeTransformation(feet);
        assertEquals(String.valueOf(expectedMeters), result);
    }

    @Test
    void makeTransformation_HandlesNegativeValue() {
        String result = converter.makeTransformation(-5);
        assertEquals("-1.524", result);
    }

    @Test
    void makeTransformation_HandlesLargeNumber() {
        String result = converter.makeTransformation(1000000);
        assertEquals("304800.0", result);
    }

    @Test
    void makeTransformation_HandlesDecimalPrecision() {
        String result = converter.makeTransformation(12.3456);
        assertEquals("3.76293888", result);
    }
}