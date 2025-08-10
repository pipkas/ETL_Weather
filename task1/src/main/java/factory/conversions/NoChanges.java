package factory.conversions;

import factory.Conversion;

public class NoChanges extends Conversion {
    @Override
    public String makeTransformation(Number value){
        return String.valueOf(value);
    }
}
