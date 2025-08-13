package factory.conversions;

import factory.Conversion;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

// Convert from Unix time (seconds) to dd.MM.yyyy format
public class LocalDate extends Conversion {//unixtime → ISO 8601
    @Override
    public String makeTransformation(Number value){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String timezone = "Asia/Novosibirsk";

        LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochSecond(
                value.longValue()),
                ZoneId.of(timezone));

        return time.format(dateFormatter);
    }
}