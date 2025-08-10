package factory.conversions;

import factory.Conversion;

public class InchesToMm extends Conversion {// дюймы → мм
    @Override
    public String makeTransformation(Number value){
        return String.valueOf((value.doubleValue() * 25.4));
    }
}
