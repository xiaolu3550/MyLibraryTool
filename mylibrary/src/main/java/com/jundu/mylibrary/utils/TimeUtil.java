package com.jundu.mylibrary.utils;

import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by 011 on 2017/7/20.
 */

public class TimeUtil {

    private static String mYear; // 当前年
    private static String mMonth; // 月
    private static String mDay;
    private static String mWay;

    public static String conversionDate(String oldDate, String newDate, String time) {
        Date date = null;
        String now = "";
        try {
            date = new SimpleDateFormat(oldDate).parse(time);
            now = new SimpleDateFormat(newDate).format(date);
        } catch (ParseException e) {
            return now;
        }
        return now;
    }

    /**
     * 获取当前的时间:date
     *
     * @param date 时间格式
     * @return String 日期
     */
    public static String getTodayDateTime(String date) {
        SimpleDateFormat format = new SimpleDateFormat(date, Locale.getDefault());
        return format.format(new Date());
    }

    /**
     * 掉此方法输入所要转换的时间输入例如 dates 返回时间戳
     *
     * @param dates 时间格式
     * @param time  时间
     * @return
     */
    public static String data(String dates, String time) {
        SimpleDateFormat sdr = new SimpleDateFormat(dates, Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }

    /**
     * 调用此方法输入所要转换的时间戳输出dates
     *
     * @param dates 时间格式
     * @param time
     * @return
     */
    public static String time(String time, String dates) {
        SimpleDateFormat sdr = new SimpleDateFormat(dates);
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    private static String getWeek(long timeStamp) {
        int mydate = 0;
        String week = null;
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date(timeStamp));
        mydate = cd.get(Calendar.DAY_OF_WEEK);
        // 获取指定日期转换成星期几
        if (mydate == 1) {
            week = "周日";
        } else if (mydate == 2) {
            week = "周一";
        } else if (mydate == 3) {
            week = "周二";
        } else if (mydate == 4) {
            week = "周三";
        } else if (mydate == 5) {
            week = "周四";
        } else if (mydate == 6) {
            week = "周五";
        } else if (mydate == 7) {
            week = "周六";
        }
        return week;
    }

    /**
     * 根据当前日期获得是星期几
     *
     * @return
     */
    public static String getWeek(String date, String time) {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat(date);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "周日";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "周一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "周二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "周三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "周四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "周五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "周六";
        }
        return Week;
    }

    /**
     * 并用分割符把时间分成时间数组
     *
     * @param time
     * @return
     */
    public static String[] timestamp(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        String[] fenge = times.split("[年月日时分秒]");
        return fenge;
    }

    /**
     * 分割符把时间分成时间数组
     *
     * @param time
     * @return
     */
    public String[] division(String time) {

        String[] fenge = time.split("[年月日时分秒]");

        return fenge;

    }

    /**
     * 输入时间戳变星期
     *
     * @param dates 时间格式
     * @param time
     * @return
     */
    public static String changeweek(String dates, String time) {
        SimpleDateFormat sdr = new SimpleDateFormat(dates);
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        Date date = null;
        int mydate = 0;
        String week = null;
        try {
            date = sdr.parse(times);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            mydate = cd.get(Calendar.DAY_OF_WEEK);
            // 获取指定日期转换成星期几
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mydate == 1) {
            week = "星期日";
        } else if (mydate == 2) {
            week = "星期一";
        } else if (mydate == 3) {
            week = "星期二";
        } else if (mydate == 4) {
            week = "星期三";
        } else if (mydate == 5) {
            week = "星期四";
        } else if (mydate == 6) {
            week = "星期五";
        } else if (mydate == 7) {
            week = "星期六";
        }
        return week;
    }


    /**
     * 获取今天往后一周的日期（几月几号）
     */
    public static String getLastDate(String dateType, String lastDay) {
        SimpleDateFormat sdr = new SimpleDateFormat(dateType);
        Date date = null;
        int year = 0;
        int month = 0;
        int days = 0;
        try {
            date = sdr.parse(lastDay);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            int day = cd.get(Calendar.DATE);
            cd.set(Calendar.DATE, day - 1);
            year = cd.get(Calendar.YEAR);
            month = cd.get(Calendar.MONTH) + 1;
            days = cd.get(Calendar.DATE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return year + "-" + month + "-" + days;
    }

    /**
     * 获取今天往后numb周的集合
     *
     * @param numb     天数
     * @param dateType 日期的类型
     * @return List 周的集合
     */
    public static List<String> get7week(int numb, String dateType) {
        String week = "";
        List<String> weeksList = new ArrayList<>();
        List<String> dateList = get7date(dateType, numb);
        for (String s : dateList) {
            week = getWeek(dateType, s);
            weeksList.add(week);
        }
        return weeksList;
    }

    /**
     * 获取后numb天的日期
     *
     * @param dateType 日期格式
     * @param numb     天数
     * @return List 天数的集合
     */
    public static List<String> get7date(String dateType, int numb) {
        List<String> dates = new ArrayList<>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        SimpleDateFormat sim = new SimpleDateFormat(dateType);
        //String date = sim.format(c.getTime());
        //dates.add(date);
        for (int i = 0; i < numb; i++) {
            c.add(Calendar.DAY_OF_MONTH, 1);
            String date = sim.format(c.getTime());
            dates.add(date);
        }
        return dates;
    }

    /**
     * 获取今天往后一周的日期（几月几号）
     */
    public static List<String> getSevendate(int numb) {
        List<String> dates = new ArrayList<>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        for (int i = 0; i < numb; i++) {
            mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
            mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
            mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + i);// 获取当前日份的日期号码
            if (Integer.parseInt(mDay) > MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (i + 1))) {
                mDay = String.valueOf(MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (i + 1)));
            }
            String date = mMonth + "月" + mDay + "日";
            dates.add(date);
        }
        return dates;
    }

    /**
     * 获取当前年月日
     *
     * @return
     */
    public static String StringData() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        if (Integer.parseInt(mDay) > MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (Integer.parseInt(mMonth)))) {
            mDay = String.valueOf(MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (Integer.parseInt(mMonth))));
        }
        return mYear + "-" + (mMonth.length() == 1 ? "0" + mMonth : mMonth) + "-" + (mDay.length() == 1 ? "0" + mDay : mDay);
    }

    /**
     * 得到当年当月的最大日期
     **/
    public static int MaxDayFromDay_OF_MONTH(int year, int month) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, year);
        time.set(Calendar.MONTH, month - 1);//注意,Calendar对象默认一月为0
        int day = time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
        return day;
    }

    /**
     * 计算时间差
     *
     * @param starTime 开始时间
     * @param endTime  结束时
     * @return 返回时间差
     */
    public static String getTimeDifference(String date, String starTime, String endTime) {
        String timeString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat(date);

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endTime);

            long diff = parse1.getTime() - parse.getTime();

            long day = diff / (24 * 60 * 60 * 1000);
            long hour = (diff / (60 * 60 * 1000) - day * 24);
            long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            long ms = (diff - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                    - min * 60 * 1000 - s * 1000);
            long hour1 = diff / (60 * 60 * 1000);
            String hourString = hour1 + "";
            long min1 = ((diff / (60 * 1000)) - hour1 * 60);
            timeString = hour1 + "小时" + min1 + "分";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeString;

    }

    /**
     * 计算相差的小时
     *
     * @param starTime
     * @param endTime
     * @return
     */
    public static String getTimeDifferenceHour(String date, String starTime, String endTime) {
        if (starTime == null || endTime == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(date);
        Date parse = null;
        Date parse1 = null;
        try {
            parse = dateFormat.parse(starTime);
            parse1 = dateFormat.parse(endTime);
        } catch (ParseException e) {
            return "";
        }
        Long timeLong = parse1.getTime() - parse.getTime();
        if (timeLong < 60 * 1000)
            return timeLong / 1000 + "秒前";
        else if (timeLong < 60 * 60 * 1000) {
            timeLong = timeLong / 1000 / 60;
            return timeLong + "分钟前";
        } else if (timeLong < 60 * 60 * 24 * 1000) {
            timeLong = timeLong / 60 / 60 / 1000;
            return timeLong + "小时前";
        } else if (timeLong < 60 * 60 * 24 * 1000 * 7) {
            timeLong = timeLong / 1000 / 60 / 60 / 24;
            return timeLong + "天前";
        } else if (timeLong < 60l * 60l * 24l * 1000l * 7l * 4l) {
            timeLong = timeLong / 1000 / 60 / 60 / 24 / 7;
            return timeLong + "周前";
        } else if (timeLong < 60l * 60l * 24l * 1000l * 7l * 4l * 30l) {
            timeLong = timeLong / 1000 / 60 / 60 / 24 / 30;
            return timeLong + "月前";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(date);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
            return sdf.format(parse);
        }
    }

    /**
     * 获取两个日期之间的间隔天数
     *
     * @return
     */
    public static int getGapCount(String startDate, String endDate, String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(date);
        try {
            Date parse = dateFormat.parse(startDate);
            Date parse1 = dateFormat.parse(endDate);
            Calendar fromCalendar = Calendar.getInstance();
            fromCalendar.setTime(parse);
            fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
            fromCalendar.set(Calendar.MINUTE, 0);
            fromCalendar.set(Calendar.SECOND, 0);
            fromCalendar.set(Calendar.MILLISECOND, 0);

            Calendar toCalendar = Calendar.getInstance();
            toCalendar.setTime(parse1);
            toCalendar.set(Calendar.HOUR_OF_DAY, 0);
            toCalendar.set(Calendar.MINUTE, 0);
            toCalendar.set(Calendar.SECOND, 0);
            toCalendar.set(Calendar.MILLISECOND, 0);
            return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 比较两个日期是否相等
     *
     * @param str1 the first date
     * @param str2 the second date
     * @param date 日期格式
     * @return true <br/>false
     */
    public static boolean isDateTheSame(String str1, String str2, String date) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat(date);
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() == dt2.getTime()) {
            isBigger = true;
        } else {
            isBigger = false;
        }
        return isBigger;
    }

    /**
     * 比较两个日期的大小，日期格式为date
     *
     * @param str1 the first date
     * @param str2 the second date
     * @param date 日期格式
     * @return true <br/>false
     */
    public static boolean isDateSwichBig(String str1, String str2, String date) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat(date);
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            return isBigger;
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = true;
        } else if (dt1.getTime() < dt2.getTime()) {
            isBigger = false;
        } else {
            isBigger = false;
        }
        return isBigger;
    }

    /**
     * 判断是否为今天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean IsToday(String dates, String day) {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try {
            date = getDateFormat(dates).parse(day);
            cal.setTime(date);
            if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
                int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                        - pre.get(Calendar.DAY_OF_YEAR);

                if (diffDay == 0) {
                    return true;
                }
            }
        } catch (ParseException e) {
            return false;
        }
        return false;
    }

    /**
     * 获取前n天日期、后n天日期
     *
     * @param distanceDay 前几天 如获取前7天日期则传-7即可；如果后7天则传7
     * @return
     */
    public static String getOldDate(String dates, int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat(dates);
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    public static SimpleDateFormat getDateFormat(String date) {
        if (null == DateLocal.get()) {
            DateLocal.set(new SimpleDateFormat(date, Locale.CHINA));
        }
        return DateLocal.get();
    }


    private static ThreadLocal<SimpleDateFormat> DateLocal = new ThreadLocal<SimpleDateFormat>();

    public static void main(String[] args) {
        String yyyyMMddHHmm = getTimeDifferenceHour("yyyyMMddHHmmss", "20181029175021", getTodayDateTime("yyyyMMddHHmmss"));
        System.out.println(yyyyMMddHHmm);
    }
}


