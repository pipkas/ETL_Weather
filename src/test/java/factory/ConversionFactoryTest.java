package factory;

import data.Transformation;
import factory.conversions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConversionFactoryTest {

    @Test
    void getConversion_ReturnsNoChanges_WhenTransformationIsNull() {
        Conversion result = ConversionFactory.getConversion(null);
        assertInstanceOf(NoChanges.class, result);
    }

    @Test
    void getConversion_ReturnsCorrectConverter_ForEachTransformation() {
        assertInstanceOf(Degrees.class,
                ConversionFactory.getConversion(Transformation.DEGREES));

        assertInstanceOf(FeetToMeters.class,
                ConversionFactory.getConversion(Transformation.FT_TO_M));

        assertInstanceOf(Speed.class,
                ConversionFactory.getConversion(Transformation.WINDSPEED));

        assertInstanceOf(InchesToMm.class,
                ConversionFactory.getConversion(Transformation.INCH_TO_MM));

        assertInstanceOf(TimeISO.class,
                ConversionFactory.getConversion(Transformation.UNIX_TO_ISO8601));

        assertInstanceOf(LocalDate.class,
                ConversionFactory.getConversion(Transformation.UNIX_TO_LOCAL_DATE));

        assertInstanceOf(LocalTime.class,
                ConversionFactory.getConversion(Transformation.UNIX_TO_LOCAL_TIME));
    }

}