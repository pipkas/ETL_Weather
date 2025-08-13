package factory.conversions;

import factory.Conversion;

//No changing, only convert to String
public class NoChanges extends Conversion {
    @Override
    public String makeTransformation(Number value){
        return String.valueOf(value);
    }
}
