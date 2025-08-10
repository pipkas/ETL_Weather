package factory.conversions;

import factory.Conversion;

public class Speed extends Conversion {// Скорость ветра (узлы → м/с)
    @Override
    public String makeTransformation(Number value){
        return String.valueOf(value.doubleValue() * 0.514444);
    }
}
