package factory.conversions;

import factory.Conversion;

import java.time.Instant;

// Convert from Unix time (seconds) to ISO 8601 format
public class TimeISO extends Conversion {//unixtime → ISO 8601
    @Override
    public String makeTransformation(Number value){
        return Instant.ofEpochSecond(value.longValue()).toString();
    }
}
