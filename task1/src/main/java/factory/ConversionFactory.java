package factory;

import data.Transformation;
import factory.conversions.*;

public class ConversionFactory {
    public static Conversion getConversion(Transformation transformation) {
        if (transformation == null)
            return new NoChanges();
        switch (transformation) {
            case DEGREES: return new Degrees();
            case FT_TO_M: return new FeetToMeters();
            case WINDSPEED: return new Speed();
            case INCH_TO_MM: return new InchesToMm();
            case UNIX_TO_ISO8601: return new Time();
            default: return null;
        }
    }
}