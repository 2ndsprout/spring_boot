package com.example.spring_tboard.domain.article.base;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class CommonUtil {
    public String  getCurrentDateTime  ()  {
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return now.format(formatter);
    }
}
