package factory.conversions;

import factory.Conversion;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

// Convert from Unix time (seconds) to HH:mm:ss format
public class LocalTime extends Conversion {
    @Override
    public String makeTransformation(Number value){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timezone = "Asia/Novosibirsk";

        LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochSecond(
                        value.longValue()),
                ZoneId.of(timezone));

        return time.format(timeFormatter);
    }
}
