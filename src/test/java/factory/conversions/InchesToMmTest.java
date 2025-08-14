package factory.conversions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class InchesToMmTest {
    private final InchesToMm converter = new InchesToMm();

    @Test
    void makeTransformation_ReturnsStringType() {
        String result = converter.makeTransformation(1);
        assertInstanceOf(String.class, result);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0.0",
            "1, 25.4",
            "2, 50.8",
            "0.5, 12.7",
            "12, 304.79999999999995"
    })
    void makeTransformation_ConvertsStandardValues(double inches, double expectedMm) {
        String result = converter.makeTransformation(inches);
        assertEquals(String.valueOf(expectedMm), result);
    }

    @Test
    void makeTransformation_HandlesNegativeValue() {
        String result = converter.makeTransformation(-1);
        assertEquals("-25.4", result);
    }

    @Test
    void makeTransformation_HandlesLargeNumber() {
        String result = converter.makeTransformation(1000);
        assertEquals("25400.0", result);
    }

    @Test
    void makeTransformation_HandlesPrecision() {
        String result = converter.makeTransformation(0.03937);
        assertEquals("0.999998", result);
    }

    @Test
    void makeTransformation_HandlesIntegerInput() {
        String result = converter.makeTransformation(3);
        assertEquals("76.19999999999999", result);
    }

    @Test
    void makeTransformation_HandlesDoubleInput() {
        String result = converter.makeTransformation(2.54);
        assertEquals("64.51599999999999", result);
    }
}