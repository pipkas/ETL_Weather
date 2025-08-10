package factory.conversions;

import factory.Conversion;

public class Degrees extends Conversion {//Температура (Фаренгейты → Цельсии)
    @Override
    public String makeTransformation(Number value){
        return String.valueOf((value.doubleValue() - 32) * 5 / 9);
    }
}
