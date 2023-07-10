package com.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataFaker {
    public static String generateTimeStampString(String pattern){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        return df.format(now);
    }

}
