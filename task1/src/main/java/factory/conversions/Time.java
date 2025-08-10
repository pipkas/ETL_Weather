package factory.conversions;

import factory.Conversion;

import java.time.Instant;

public class Time extends Conversion {//unixtime → ISO 8601
    @Override
    public String makeTransformation(Number value){
        return Instant.ofEpochSecond(value.longValue()).toString();
    }
}
