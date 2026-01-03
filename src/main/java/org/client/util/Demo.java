package org.client.util;

import org.apache.commons.lang3.time.DateUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        String a = "aaa";
        int b = 1;


        Date now = new Date();
        LocalDateTime oneYearLater = LocalDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault()).plusYears(1);
        Date result = Date.from(oneYearLater.atZone(ZoneId.systemDefault()).toInstant());
        result.getYear();
        result.getMonth();
        result.getDate();
    }
}