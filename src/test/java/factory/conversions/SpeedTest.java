package factory.conversions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpeedTest {

    @Test
    void shouldConvertKnotsToMetersPerSecond_IntegerInput() {
        Speed speed = new Speed();
        String result = speed.makeTransformation(10);
        double expected = 10 * 0.514444;
        assertEquals(String.valueOf(expected), result);
    }

    @Test
    void shouldConvertKnotsToMetersPerSecond_DoubleInput() {
        Speed speed = new Speed();
        String result = speed.makeTransformation(5.5);
        double expected = 5.5 * 0.514444;
        assertEquals(String.valueOf(expected), result);
    }

    @Test
    void shouldConvertZeroKnotsToZeroMetersPerSecond() {
        Speed speed = new Speed();
        String result = speed.makeTransformation(0);
        assertEquals("0.0", result);
    }

    @Test
    void shouldConvertNegativeKnots() {
        Speed speed = new Speed();
        String result = speed.makeTransformation(-3);
        double expected = -3 * 0.514444;
        assertEquals(String.valueOf(expected), result);
    }
}
