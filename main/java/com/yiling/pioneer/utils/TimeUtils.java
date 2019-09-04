package com.yiling.pioneer.utils;

import java.util.Date;

/**
 * @Author: xc
 * @Date: 2019/7/10 16:15
 * @Description: 时间工具
 **/
public class TimeUtils {
    /**
     * 获得当前时间的时间戳
     * @return 时间戳
     */
    private static long getLongTime(){
        Date now = new Date();
        return now.getTime();
    }
    public static String TimeCrateID(){
        String timeNum = Long.toString(getLongTime());
        String timeID = timeNum.substring(5,13);
        return timeID;
    }
}
