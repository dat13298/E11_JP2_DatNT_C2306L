package Global;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Format {
    public static LocalDateTime toLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }
    public static LocalDate toLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy");
        return LocalDate.parse(date, formatter);
    }
}
