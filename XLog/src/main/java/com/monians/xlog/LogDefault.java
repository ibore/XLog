package com.monians.xlog;

/**
 * 功能: 默认的系统Log打印方式
 * 作者: ibore
 * 时间: 2016/6/05 12:01
 * 邮箱: bore521@live.com
 */
public class LogDefault {

    public static void printLog(int type, String tagStr, Object... objects) {

        if (!XLog.IS_SHOW_LOG) {
            return;
        }

        String[] contents = LogHelper.wrapperContent(tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];

        int index = 0;
        int maxLenght = 4000;
        int countOfSub = msg.length() / maxLenght;

        LogHelper.printLine(type, tag, LogHelper.TOP);
        LogHelper.printMsg(type, tag, "║ " + headString);
        LogHelper.printLine(type, tag, LogHelper.MIDDLE);
        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + maxLenght);
                LogHelper.printMsg(type, tag, "║ " + msg.substring(index, msg.length()));
                index += maxLenght;
            }
            LogHelper.printMsg(type, tag, "║ " + msg.substring(index, msg.length()));
        } else LogHelper.printMsg(type, tag, "║ " + msg);

        LogHelper.printLine(type, tag, LogHelper.BOTTOM);
    }
}
