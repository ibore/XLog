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

        XLogUtils.printLine(type, tag, XLogUtils.TOP);
        printSub(type, tag, headString);
        XLogUtils.printLine(type, tag, XLogUtils.MIDDLE);
        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + maxLenght);
                printSub(type, tag, msg.substring(index, msg.length()));
                index += maxLenght;
            }
            printSub(type, tag, msg.substring(index, msg.length()));
        } else printSub(type, tag, msg);

        XLogUtils.printLine(type, tag, XLogUtils.BOTTOM);
    }

    /**
     * 打印Log日志
     * @param type Log级别
     * @param tag TAG
     * @param msg 要打印的消息
     */
    private static void printSub(int type, String tag, String msg) {
        switch (type) {
            case XLogUtils.V:
                Log.v(tag, "║ " + msg);
                break;
            case XLogUtils.D:
                Log.d(tag, "║ " + msg);
                break;
            case XLogUtils.I:
                Log.i(tag, "║ " + msg);
                break;
            case XLogUtils.W:
                Log.w(tag, "║ " + msg);
                break;
            case XLogUtils.E:
                Log.e(tag, "║ " + msg);
                break;
            case XLogUtils.A:
                Log.wtf(tag, "║ " + msg);
                break;
        }
    }
}
