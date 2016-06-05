package com.monians.xlog;

import android.text.TextUtils;

import java.io.File;

/**
 * 功能: 自定义的日志打印工具，用来替代{@link android.util.Log}
 * 作者: ibore
 * 时间: 2016/6/05 12:01
 * 邮箱: bore521@live.com
 */
public final class XLog {

    /** 默认的TAG */
    public static final String DEFAULT_TAG = "XLog";

    private static String TAG = "";
    /** 默认的消息（当消息为空时） */
    public static final String DEFAULT_MESSAGE = "execute";
    /** 获取系统的换行符 */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    /** 当需要打印的日志为空时的提示 */
    public static final String NULL_TIPS = "Log with null project";

    public static final int JSON_INDENT = 4;

    private static boolean IS_SHOW_LOG = true;
    private static final int STACK_TRACE_INDEX = 5;

    private XLog() {
        throw new RuntimeException("不能被实例化");
    }

    public static void init(boolean isShowLog) {
        init(isShowLog, null);
    }

    public static void init(boolean isShowLog, String tag) {
        IS_SHOW_LOG = isShowLog;
        if (tag != null) TAG = tag;
    }

    public static void v() {
        printLog(XLogUtils.V, null, DEFAULT_MESSAGE);
    }

    public static void v(Object msg) {
        printLog(XLogUtils.V, null, msg);
    }

    public static void v(String tag, Object... objects) {
        printLog(XLogUtils.V, tag, objects);
    }

    public static void d() {
        printLog(XLogUtils.D, null, DEFAULT_MESSAGE);
    }

    public static void d(Object msg) {
        printLog(XLogUtils.D, null, msg);
    }

    public static void d(String tag, Object... objects) {
        printLog(XLogUtils.D, tag, objects);
    }

    public static void i() {
        printLog(XLogUtils.I, null, DEFAULT_MESSAGE);
    }

    public static void i(Object msg) {
        printLog(XLogUtils.I, null, msg);
    }

    public static void i(String tag, Object... objects) {
        printLog(XLogUtils.I, tag, objects);
    }

    public static void w() {
        printLog(XLogUtils.W, null, DEFAULT_MESSAGE);
    }

    public static void w(Object msg) {
        printLog(XLogUtils.W, null, msg);
    }

    public static void w(String tag, Object... objects) {
        printLog(XLogUtils.W, tag, objects);
    }

    public static void e() {
        printLog(XLogUtils.E, null, DEFAULT_MESSAGE);
    }

    public static void e(Object msg) {
        printLog(XLogUtils.E, null, msg);
    }

    public static void e(String tag, Object... objects) {
        printLog(XLogUtils.E, tag, objects);
    }

    public static void a() {
        printLog(XLogUtils.A, null, DEFAULT_MESSAGE);
    }

    public static void a(Object msg) {
        printLog(XLogUtils.A, null, msg);
    }

    public static void a(String tag, Object... objects) {
        printLog(XLogUtils.A, tag, objects);
    }

    public static void json(String jsonFormat) {
        printLog(XLogUtils.JSON, null, jsonFormat);
    }

    public static void json(String tag, String jsonFormat) {
        printLog(XLogUtils.JSON, tag, jsonFormat);
    }

    public static void xml(String xml) {
        printLog(XLogUtils.XML, null, xml);
    }

    public static void xml(String tag, String xml) {
        printLog(XLogUtils.XML, tag, xml);
    }

    public static void file(File targetDirectory, Object msg) {
        printFile(null, targetDirectory, null, msg);
    }

    public static void file(String tag, File targetDirectory, Object msg) {
        printFile(tag, targetDirectory, null, msg);
    }

    public static void file(String tag, File targetDirectory, String fileName, Object msg) {
        printFile(tag, targetDirectory, fileName, msg);
    }

    private static void printLog(int type, String tagStr, Object... objects) {

        if (!IS_SHOW_LOG) {
            return;
        }

        String[] contents = wrapperContent(tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];

        switch (type) {
            case XLogUtils.V:
            case XLogUtils.D:
            case XLogUtils.I:
            case XLogUtils.W:
            case XLogUtils.E:
            case XLogUtils.A:
                XLogDefault.printDefault(type, tag, headString, msg);
                break;
            case XLogUtils.JSON:
                XLogJson.printJson(tag, msg, headString);
                break;
            case XLogUtils.XML:
                XLogXml.printXml(tag, msg, headString);
                break;
        }
    }

    private static void printFile(String tagStr, File targetDirectory, String fileName, Object objectMsg) {

        if (!IS_SHOW_LOG) {
            return;
        }

        String[] contents = wrapperContent(tagStr, objectMsg);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];

        XLogFile.printFile(tag, targetDirectory, fileName, headString, msg);
    }
    private static String[] wrapperContent(String tagStr, Object... objects) {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement targetElement = stackTrace[STACK_TRACE_INDEX];
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

        String tag = (tagStr == null ? TAG : tagStr);
        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        }
        String msg = (objects == null) ? NULL_TIPS : getObjectsString(objects);
        String headString = "[ (" + className + ":" + lineNumber + ")#" + methodNameShort + " ] ";

        return new String[]{tag, msg, headString};
    }

    /**
     * 将Object类型转换成String类型
     * @param objects
     * @return
     */
    private static String getObjectsString(Object... objects) {

        if (objects.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            for (int i = 0; i < objects.length; i++) {
                Object object = objects[i];
                if (object == null) {
                    stringBuilder.append("Param").append("[").append(i).append("]").append(" = ").append("null").append("\n");
                } else {
                    stringBuilder.append("Param").append("[").append(i).append("]").append(" = ").append(object.toString()).append("\n");
                }
            }
            return stringBuilder.toString();
        } else {
            Object object = objects[0];
            return object == null ? "null" : object.toString();
        }
    }



}
