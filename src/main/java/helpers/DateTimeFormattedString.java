package helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateTimeFormattedString {

    public static String now() {

        String date = LocalDateTime
        .now()
        .format(
                DateTimeFormatter
                .ofPattern("yyyy-MM-dd"));

        String time = LocalDateTime
                .now()
                .format(
                        DateTimeFormatter
                                .ofPattern("hh-mm-ss"));

        return date + "-T" + time;
    }
}
