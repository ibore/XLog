package com.monians.xlog;

/**
 * 功能: 默认的系统Log打印方式
 * 作者: ibore
 * 时间: 2016/6/05 12:01
 * 邮箱: bore521@live.com
 */
public class XLogDefault {

    public static void printLog(int type, String tagStr, Object... objects) {

        if (!XLog.IS_SHOW_LOG) {
            return;
        }

        String[] contents = XLogHelper.wrapperContent(tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];

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
