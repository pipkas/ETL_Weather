package factory.conversions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DegreesTest {

    private final Degrees converter = new Degrees();

    @Test
    void makeTransformation_ConvertsZeroFahrenheitToCelsius() {
        String result = converter.makeTransformation(0);
        assertEquals("-17.77777777777778", result);
    }

    @Test
    void makeTransformation_ConvertsFreezingPointOfWater() {
        String result = converter.makeTransformation(32);
        assertEquals("0.0", result);
    }

    @Test
    void makeTransformation_ConvertsBoilingPointOfWater() {
        String result = converter.makeTransformation(212);
        assertEquals("100.0", result);
    }

    @Test
    void makeTransformation_ConvertsNegativeTemperature() {
        String result = converter.makeTransformation(-40);
        assertEquals("-40.0", result);
    }

    @Test
    void makeTransformation_HandlesDecimalValues() {
        String result = converter.makeTransformation(98.6);
        assertEquals("37.0", result);
    }

    @Test
    void makeTransformation_ReturnsStringForIntegerInput() {
        String result = converter.makeTransformation(100);
        assertInstanceOf(String.class, result);
    }

    @Test
    void makeTransformation_ReturnsStringForDoubleInput() {
        String result = converter.makeTransformation(100.5);
        assertInstanceOf(String.class, result);
    }
}