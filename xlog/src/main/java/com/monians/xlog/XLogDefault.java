package com.monians.xlog;

import android.util.Log;

/**
 * Created by ibore on 2016/6/6.
 */
public class XLogDefault {
    /**
     * 默认的Log日志打印
     * @param type Log级别
     * @param tag TAG
     * @param headString 头消息
     * @param msg 要打印的消息
     */
    public static void printDefault(int type, String tag, String headString, String msg) {
        int index = 0;
        int maxLenght = 4000;
        int countOfSub = msg.length() / maxLenght;

        XLogHelper.printLine(type, tag, XLogHelper.TOP);
        XLogHelper.printMsg(type, tag, "║ " + headString);
        XLogHelper.printLine(type, tag, XLogHelper.MIDDLE);
        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + maxLenght);
                XLogHelper.printMsg(type, tag, "║ " + msg.substring(index, msg.length()));
                index += maxLenght;
            }
            XLogHelper.printMsg(type, tag, "║ " + msg.substring(index, msg.length()));
        } else XLogHelper.printMsg(type, tag, "║ " + msg);

        XLogHelper.printLine(type, tag, XLogHelper.BOTTOM);
    }

}
