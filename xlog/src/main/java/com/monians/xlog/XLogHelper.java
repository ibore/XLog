package com.monians.xlog;

import android.text.TextUtils;
import android.util.Log;

/**
 * 功能: XLog的帮助类
 * 作者: ibore
 * 时间: 2016/6/05 12:01
 * 邮箱: bore521@live.com
 */
public class XLogHelper {

    /** 默认的TAG */
    public static final String DEFAULT_TAG = "XLog";

    /** 默认的消息（当消息为空时） */
    public static final String DEFAULT_MESSAGE = "execute";
    /** 获取系统的换行符 */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    /** 当需要打印的日志为空时的提示 */
    public static final String NULL_TIPS = "Log with null project";

    public static final int JSON_INDENT = 4;

    public static final int STACK_TRACE_INDEX = 5;

    public static final int V = 0x1;
    public static final int D = 0x2;
    public static final int I = 0x3;
    public static final int W = 0x4;
    public static final int E = 0x5;
    public static final int A = 0x6;

    public static final int TOP = 0x10;
    public static final int MIDDLE = 0x11;
    public static final int BOTTOM = 0x12;

    public static boolean isEmpty(String line) {
        return TextUtils.isEmpty(line) || line.equals("\n") || line.equals("\t") || TextUtils.isEmpty(line.trim());
    }

    /**
     * 打印Log日志
     * @param type Log级别
     * @param tag TAG
     * @param msg 要打印的消息
     */
    public static void printMsg(int type, String tag, String msg) {
        switch (type) {
            case XLogHelper.V:
                Log.v(tag, msg);
                break;
            case XLogHelper.D:
                Log.d(tag, msg);
                break;
            case XLogHelper.I:
                Log.i(tag, msg);
                break;
            case XLogHelper.W:
                Log.w(tag, msg);
                break;
            case XLogHelper.E:
                Log.e(tag, msg);
                break;
            case XLogHelper.A:
                Log.wtf(tag, msg);
                break;
        }
    }

    public static void printLine(int type, String tag, int site) {
        switch (site) {
            case TOP:
                printMsg(type, tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
                break;
            case MIDDLE:
                printMsg(type, tag, "╠═══════════════════════════════════════════════════════════════════════════════════════");
                break;
            case BOTTOM:
                printMsg(type, tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
                break;
        }
    }

    /**
     * 将Object类型转换成String类型
     * @param objects
     * @return
     */
    public static String getObjectsString(Object... objects) {

        if (objects.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < objects.length; i++) {
                Object object = objects[i];
                if (object == null) {
                    if (i == 0) stringBuilder.append("Param").append("[").append(i).append("]").append(" = ").append("null").append("\n");
                    else stringBuilder.append("║ ").append("Param").append("[").append(i).append("]").append(" = ").append("null").append("\n");
                } else {
                    if (i == 0) stringBuilder.append("Param").append("[").append(i).append("]").append(" = ").append(object.toString()).append("\n");
                    else stringBuilder.append("║ ").append("Param").append("[").append(i).append("]").append(" = ").append(object.toString()).append("\n");
                }
            }
            return stringBuilder.toString();
        } else {
            Object object = objects[0];
            return object == null ? "null" : object.toString();
        }
    }

    public static String[] wrapperContent(String tagStr, Object... objects) {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement targetElement = stackTrace[XLogHelper.STACK_TRACE_INDEX];
        String className = targetElement.getClassName();
        String[] classNameInfo = className.split("\\.");
        if (classNameInfo.length > 0) {
            className = classNameInfo[classNameInfo.length - 1] + ".java";
        }
        String methodName = targetElement.getMethodName();
        int lineNumber = targetElement.getLineNumber();

        if (lineNumber < 0) {
            lineNumber = 0;
        }

        String methodNameShort = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

        String tag = (tagStr == null ? XLog.TAG : tagStr);
        if (TextUtils.isEmpty(tag)) {
            tag = XLog.TAG;
        }
        String msg = (objects == null) ? XLogHelper.NULL_TIPS : XLogHelper.getObjectsString(objects);
        String headString = "(" + className + ":" + lineNumber + ")#" + methodNameShort;

        return new String[]{tag, msg, headString};
    }

}