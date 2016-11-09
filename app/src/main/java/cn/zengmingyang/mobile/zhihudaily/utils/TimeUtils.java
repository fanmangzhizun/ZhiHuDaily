package cn.zengmingyang.mobile.zhihudaily.utils;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by simonla on 2016/11/6.
 * 上午11:34
 */

public class TimeUtils {
    public static int pageAdapter(int page) {
        Calendar calendar = Calendar.getInstance();
        BigInteger delay = new BigInteger(String.valueOf(page)).multiply(new BigInteger(String.valueOf(86400000)));
        calendar.setTimeInMillis(System.currentTimeMillis() - Long.valueOf(delay.toString()));
        String result = String.valueOf(calendar.get(Calendar.YEAR)) +
                repairCalendar(String.valueOf(calendar.get(Calendar.MONTH) + 1)) +
                repairCalendar(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        return Integer.valueOf(result);
    }

    private static String repairCalendar(String s) {
        return s.length() != 2 ? "0" + s : s;
    }

    public static String timeStampToStr(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(timeStamp * 1000);
        return date;
    }
}
