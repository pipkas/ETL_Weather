package factory.conversions;

import factory.Conversion;

// Convert from inches to millimeters
public class InchesToMm extends Conversion {// дюймы → мм
    @Override
    public String makeTransformation(Number value){
        return String.valueOf((value.doubleValue() * 25.4));
    }
}
