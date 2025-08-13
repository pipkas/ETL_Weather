package processing;

//the class provides functions for working with Number
public class NumberArrayWorker {//содержит функции для работы с массивом из Number
    public static Number divInNumberArray(Number value, int divider) {
        if (divider == 0){
            System.out.println("Division by zero!!\n");
            return null;
        }
        if (value instanceof Double) {
            value = value.doubleValue() / divider;
        } else if (value instanceof Integer) {
            value = value.intValue() / divider;
        } else if (value instanceof Long) {
            value = value.longValue() / divider;
        }
        return value;
    }

    public static void addToNumberArray(Number[] array, int index, Number value) {
        if (array[index] == null) {
            array[index] = 0;
        }
        if (value instanceof Double) {
            array[index] = array[index].doubleValue() + value.doubleValue();
        } else if (value instanceof Integer) {
            array[index] = array[index].intValue() + value.intValue();
        } else if (value instanceof Long) {
            array[index] = array[index].longValue() + value.longValue();
        }
    }
}
