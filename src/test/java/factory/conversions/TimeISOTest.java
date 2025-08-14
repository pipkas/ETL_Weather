package factory.conversions;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeISOTest {

    @Test
    void shouldConvertEpochZero() {
        TimeISO timeISO = new TimeISO();
        String result = timeISO.makeTransformation(0);
        assertEquals("1970-01-01T00:00:00Z", result);
    }

    @Test
    void shouldConvertPositiveUnixTime() {
        TimeISO timeISO = new TimeISO();
        long unixTime = 1_600_000_000L;
        String result = timeISO.makeTransformation(unixTime);
        assertEquals(Instant.ofEpochSecond(unixTime).toString(), result);
    }

    @Test
    void shouldConvertNegativeUnixTime() {
        TimeISO timeISO = new TimeISO();
        long unixTime = -1_000L;
        String result = timeISO.makeTransformation(unixTime);
        assertEquals(Instant.ofEpochSecond(unixTime).toString(), result);
    }

    @Test
    void shouldConvertFromDoubleValue() {
        TimeISO timeISO = new TimeISO();
        double unixTime = 1_650_000_000.0;
        String result = timeISO.makeTransformation(unixTime);
        assertEquals(Instant.ofEpochSecond((long) unixTime).toString(), result);
    }
}
