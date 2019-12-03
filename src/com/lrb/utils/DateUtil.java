package com.lrb.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lrbin
 * @version 1.0.0
 * @company
 * @create 2019/12/3 14:55
 * @Description
 */
public class DateUtil {
    public static String getDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
