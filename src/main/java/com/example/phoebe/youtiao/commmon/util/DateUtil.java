package com.example.phoebe.youtiao.commmon.util;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
public class DateUtil {

    //private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss/SSS");

    private static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss/SSS");

    private static final Long ONE_DAY_MILLIS = Long.valueOf(1000 * 60 * 60 * 24);

    private static final DateTimeFormatter DEFAULT_DATE_FORMAT_2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String dateMillis2String(long dateMillis, String pattern) {
        StringBuilder sb = new StringBuilder();
        sb.append(pattern);
        SimpleDateFormat simpleFormatter = new SimpleDateFormat(sb.toString());
        String dateMillisFormat = simpleFormatter.format(dateMillis);
        return dateMillisFormat;
    }



    public static List<String> listDateBetweenStartTimeAndEndTime2String(Date startTime, Date endTime,String pattern) {
        Long timeMillisTemp = startTime.getTime();
        Long endTimeMillis = endTime.getTime();
        List<String> dateList = new ArrayList<>();
        while (timeMillisTemp.compareTo(endTimeMillis) <= 0) {
            dateList.add(dateMillis2String(new Date(timeMillisTemp).getTime(),pattern));
            timeMillisTemp += ONE_DAY_MILLIS;
        }
        return dateList;
    }

    public static List<Date> listDateBetweenStartTimeAndEndTime(Date startTime, Date endTime) {
        Long timeMillisTemp = startTime.getTime();
        Long endTimeMillis = endTime.getTime();
        List<Date> dateList = new ArrayList<>();
        while (timeMillisTemp.compareTo(endTimeMillis) <= 0) {
            dateList.add(new Date(timeMillisTemp));
            timeMillisTemp += ONE_DAY_MILLIS;
        }
        return dateList;
    }

    public static void main(String[] args) {
        System.out.println(new Date().getTime());
        System.out.println(DateTime.now().getMillis());
        System.out.println(getYearFirst(2018));
        System.out.println(getYearFirst(2019));
    }

    public static String dateTransfer(Date date) {
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(new Date());

        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);

        int currentYear = currentCalendar.get(Calendar.YEAR);
        int dateYear = dateCalendar.get(Calendar.YEAR);

        int currentMonth = currentCalendar.get(Calendar.MONTH);
        int dateMonth = dateCalendar.get(Calendar.MONTH);

        int currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH);
        int dateDay = dateCalendar.get(Calendar.DAY_OF_MONTH);

        if (currentYear != dateYear) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return formatter.format(date);
        } else if (currentMonth != dateMonth) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
            return formatter.format(date);
        } else {
            int dayDiff = currentDay - dateDay;
            if (dayDiff > 0) {
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                if (dayDiff > 2) {
                    formatter = new SimpleDateFormat("MM-dd HH:mm");
                    return formatter.format(date);
                } else if (dayDiff == 2) {
                    return "前天 " + formatter.format(date);
                } else if (dayDiff == 1) {
                    return "昨天 " + formatter.format(date);
                }
            } else {
                long currentTime = currentCalendar.getTimeInMillis() / 1000;
                long dateTime = dateCalendar.getTimeInMillis() / 1000;
                long diffTime = currentTime - dateTime;
                log.info("currentTime={}", currentTime);
                log.info("dateTime={}", dateTime);
                log.info("diffTime={}", diffTime);
                if ((diffTime / (60 * 60)) >= 1) {
                    return diffTime / (60 * 60) + "小时前";
                } else if ((diffTime / 60) >= 1) {
                    return diffTime / 60 + "分钟前";
                } else {
                    return "刚刚";
                }
            }
        }
        return date.toString();
    }

    public static boolean isToday(Date date) {
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(new Date());

        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);

        int currentYear = currentCalendar.get(Calendar.YEAR);
        int dateYear = dateCalendar.get(Calendar.YEAR);

        int currentMonth = currentCalendar.get(Calendar.MONTH);
        int dateMonth = dateCalendar.get(Calendar.MONTH);

        int currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH);
        int dateDay = dateCalendar.get(Calendar.DAY_OF_MONTH);

        return (currentYear == dateYear && currentMonth == dateMonth && currentDay == dateDay);
    }

    public static Date parse(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 想要获取的日期与传入日期的差值 比如想要获取传入日期前四天的日期 day=-4即可
     */
    public static Date getSomeDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    public static String format(Long date) {
        return format(new Date(date));
    }

  /*  public static String format(Date date) {
        return DEFAULT_DATE_FORMAT.format(date);
    }*/

    public static String format(Date date) {
        LocalDateTime ldt = dateConvertLocalDateTime(date);
        return DEFAULT_DATE_FORMAT.format(ldt);
    }

    public static String format2(Date date) {
        LocalDateTime ldt = dateConvertLocalDateTime(date);
        return DEFAULT_DATE_FORMAT_2.format(ldt);
    }

    public static String format(String pattern, Date date) {
        return new SimpleDateFormat(pattern).format(date);
    }

   /* public static Date parse(String date) {
        try {
            return DEFAULT_DATE_FORMAT.parse(date);
        } catch (Exception e) {
            return null;
        }
    }*/

    public static Date parse(String date) {
        try {
            LocalDateTime ldt = LocalDateTime.parse(date, DEFAULT_DATE_FORMAT);
            return localDateTimeConvertDate(ldt);
        } catch (Exception e) {
            return null;
        }
    }

    //获取当天00:00:00的时间戳
    public static Long getTimesMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (long) (cal.getTimeInMillis());
    }

    // 获得指定天24点时间戳
    public static Date getTimesNight(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取时间差(默认毫秒)
     */
//    public static Long getTimeDifference(Date startDateTime, Date endDateTime, Integer type) {
//        LocalDateTime startTime = dateConvertLocalDateTime(startDateTime);
//        LocalDateTime endTime = dateConvertLocalDateTime(endDateTime);
//        Duration duration = Duration.between(startTime, endTime);
//        if (type == TimeTypeEnum.MILLIS.getType()) {
//            return duration.toMillis();
//        } else if (type == TimeTypeEnum.MINUTES.getType()) {
//            return duration.toMinutes();
//        } else if (type == TimeTypeEnum.HOURS.getType()) {
//            return duration.toHours();
//        } else if (type == TimeTypeEnum.DAY.getType()) {
//            return duration.toDays();
//        }
//        return duration.toMillis();
//    }

    public static LocalDateTime dateConvertLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static Date localDateTimeConvertDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static Long getTimeStamp(Date date) {
        if (date == null) {
            return null;
        }
        return date.getTime();
    }

    public static Date fromTimestamp(Long timestamp) {
        if (timestamp == null) {
            return null;
        }
        return new Date(timestamp);
    }

    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }
}
