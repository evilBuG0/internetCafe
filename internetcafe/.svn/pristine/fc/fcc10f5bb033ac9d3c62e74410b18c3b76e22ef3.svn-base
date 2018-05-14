package com.ideal.oms.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description:
 * Author: <a href="mailto:nerdream@163.com">Wang Hongbo</a>
 * DateTime: 2015-07-20 17:02
 */
public class DateUtils {
    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static Date parseDate(String source){
        return parseDate(source, YYYY_MM_DD);
    }
    public static Date parseDate(String source, String format){
        Date returnValue = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            returnValue = dateFormat.parse(source);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return returnValue;
    }

    public static Date max(Date...params){
        Date maxDate = null;
        for(Date item : params) {
            if(maxDate == null){
                maxDate = item;
            }
            else if(item.after(maxDate)) {
                maxDate = item;
            }
        }
        return maxDate;
    }

    public static Date min(Date...params){
        Date minDate = null;
        for(Date item : params) {
            if(minDate == null){
                minDate = item;
            }
            else if(item.before(minDate)) {
                minDate = item;
            }
        }
        return minDate;
    }

    public static Date dayStartTime(Date date){
        return getStartTime(date);
    }

    public static Date dayEndTime(Date date){
        return getEndTime(date);
    }

    private static Date getStartTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private static Date getEndTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
