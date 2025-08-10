package factory.conversions;

import factory.Conversion;

public class FeetToMeters extends Conversion {// Длина (футы → метры)
    @Override
    public String makeTransformation(Number value){
        return String.valueOf((value.doubleValue() * 0.3048));
    }
}
