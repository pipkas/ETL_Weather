package factory.conversions;

import factory.Conversion;

// Convert from Fahrenheit to Celsius
public class Degrees extends Conversion {//Температура (Фаренгейты → Цельсии)
    @Override
    public String makeTransformation(Number value){
        return String.valueOf((value.doubleValue() - 32) * 5 / 9);
    }
}
