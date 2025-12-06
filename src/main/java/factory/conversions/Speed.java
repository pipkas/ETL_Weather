package factory.conversions;

import factory.Conversion;

// Convert from knots to m/s
public class Speed extends Conversion {// Скорость ветра (узлы → м/с)
    @Override
    public String makeTransformation(Number value){
        return String.valueOf(value.doubleValue() * 0.514444);
    }
}
