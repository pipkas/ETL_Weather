package processing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberArrayWorkerTest {

    @Test
    void divInNumberArray_ShouldDivideDouble() {
        Number result = NumberArrayWorker.divInNumberArray(10.0, 2);
        assertEquals(5.0, result);
    }

    @Test
    void divInNumberArray_ShouldDivideInteger() {
        Number result = NumberArrayWorker.divInNumberArray(9, 2);
        assertEquals(4, result);
    }

    @Test
    void divInNumberArray_ShouldDivideLong() {
        Number result = NumberArrayWorker.divInNumberArray(100L, 4);
        assertEquals(25L, result);
    }

    @Test
    void divInNumberArray_ShouldReturnNullOnDivisionByZero() {
        Number result = NumberArrayWorker.divInNumberArray(10, 0);
        assertNull(result);
    }

    @Test
    void addToNumberArray_ShouldAddDoubleValues() {
        Number[] arr = new Number[1];
        NumberArrayWorker.addToNumberArray(arr, 0, 5.5);
        NumberArrayWorker.addToNumberArray(arr, 0, 4.5);
        assertEquals(10.0, arr[0]);
    }

    @Test
    void addToNumberArray_ShouldAddIntegerValues() {
        Number[] arr = new Number[1];
        NumberArrayWorker.addToNumberArray(arr, 0, 3);
        NumberArrayWorker.addToNumberArray(arr, 0, 7);
        assertEquals(10, arr[0]);
    }

    @Test
    void addToNumberArray_ShouldAddLongValues() {
        Number[] arr = new Number[1];
        NumberArrayWorker.addToNumberArray(arr, 0, 5L);
        NumberArrayWorker.addToNumberArray(arr, 0, 15L);
        assertEquals(20L, arr[0]);
    }

    @Test
    void addToNumberArray_ShouldInitializeNullCellToZero() {
        Number[] arr = new Number[1];
        NumberArrayWorker.addToNumberArray(arr, 0, 7);
        assertEquals(7, arr[0]);
    }
}
