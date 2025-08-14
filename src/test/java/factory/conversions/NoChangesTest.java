package factory.conversions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoChangesTest {

    @Test
    void shouldReturnStringRepresentationOfInteger() {
        NoChanges noChanges = new NoChanges();
        String result = noChanges.makeTransformation(123);
        assertEquals("123", result);
    }

    @Test
    void shouldReturnStringRepresentationOfDouble() {
        NoChanges noChanges = new NoChanges();
        String result = noChanges.makeTransformation(45.67);
        assertEquals("45.67", result);
    }

    @Test
    void shouldHandleNullValue() {
        NoChanges noChanges = new NoChanges();
        String result = noChanges.makeTransformation(null);
        assertEquals("null", result);
    }

    @Test
    void shouldReturnStringRepresentationOfNegativeNumber() {
        NoChanges noChanges = new NoChanges();
        String result = noChanges.makeTransformation(-999);
        assertEquals("-999", result);
    }
}
