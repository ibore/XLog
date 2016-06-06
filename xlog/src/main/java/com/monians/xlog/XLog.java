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



    public static String TAG;
    private static boolean IS_SHOW_LOG = true;

    private XLog() {
        throw new RuntimeException("不能被实例化");
    }

    public static void init(boolean isShowLog) {
        init(isShowLog, null);
    }

    public static void init(boolean isShowLog, String tag) {
        IS_SHOW_LOG = isShowLog;
        if (tag != null) TAG = tag;
        else TAG = XLogHelper.DEFAULT_TAG;
    }

    public static void v() {
        printLog(XLogHelper.V, null, XLogHelper.DEFAULT_MESSAGE);
    }

    public static void v(Object msg) {
        printLog(XLogHelper.V, null, msg);
    }

    public static void v(String tag, Object... objects) {
        printLog(XLogHelper.V, tag, objects);
    }

    public static void d() {
        printLog(XLogHelper.D, null, XLogHelper.DEFAULT_MESSAGE);
    }

    public static void d(Object msg) {
        printLog(XLogHelper.D, null, msg);
    }

    public static void d(String tag, Object... objects) {
        printLog(XLogHelper.D, tag, objects);
    }

    public static void i() {
        printLog(XLogHelper.I, null, XLogHelper.DEFAULT_MESSAGE);
    }

    public static void i(Object msg) {
        printLog(XLogHelper.I, null, msg);
    }

    public static void i(String tag, Object... objects) {
        printLog(XLogHelper.I, tag, objects);
    }

    public static void w() {
        printLog(XLogHelper.W, null, XLogHelper.DEFAULT_MESSAGE);
    }

    public static void w(Object msg) {
        printLog(XLogHelper.W, null, msg);
    }

    public static void w(String tag, Object... objects) {
        printLog(XLogHelper.W, tag, objects);
    }

    public static void e() {
        printLog(XLogHelper.E, null, XLogHelper.DEFAULT_MESSAGE);
    }

    public static void e(Object msg) {
        printLog(XLogHelper.E, null, msg);
    }

    public static void e(String tag, Object... objects) {
        printLog(XLogHelper.E, tag, objects);
    }

    public static void a() {
        printLog(XLogHelper.A, null, XLogHelper.DEFAULT_MESSAGE);
    }

    public static void a(Object msg) {
        printLog(XLogHelper.A, null, msg);
    }

    public static void a(String tag, Object... objects) {
        printLog(XLogHelper.A, tag, objects);
    }

    public static void json(String jsonFormat) {
        printLog(XLogHelper.JSON, null, jsonFormat);
    }

    public static void json(String tag, String jsonFormat) {
        printLog(XLogHelper.JSON, tag, jsonFormat);
    }

    public static void xml(String xml) {
        printLog(XLogHelper.XML, null, xml);
    }

    public static void xml(String tag, String xml) {
        printLog(XLogHelper.XML, tag, xml);
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

        String[] contents = XLogHelper.wrapperContent(tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];

        switch (type) {
            case XLogHelper.V:
            case XLogHelper.D:
            case XLogHelper.I:
            case XLogHelper.W:
            case XLogHelper.E:
            case XLogHelper.A:
                XLogDefault.printDefault(type, tag, headString, msg);
                break;
            case XLogHelper.JSON:
                XLogJson.printJson(tag, msg, headString);
                break;
            case XLogHelper.XML:
                XLogXml.printXml(tag, msg, headString);
                break;
        }
    }

    private static void printFile(String tagStr, File targetDirectory, String fileName, Object objectMsg) {

        if (!IS_SHOW_LOG) {
            return;
        }

        String[] contents = XLogHelper.wrapperContent(tagStr, objectMsg);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];

        XLogFile.printFile(tag, targetDirectory, fileName, headString, msg);
    }
}
