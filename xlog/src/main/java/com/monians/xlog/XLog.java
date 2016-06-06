package com.monians.xlog;

import java.io.File;

/**
 * 功能: 自定义的日志打印工具，用来替代{@link android.util.Log}
 * 作者: ibore
 * 时间: 2016/6/05 12:01
 * 邮箱: bore521@live.com
 */
public final class XLog {



    public static String TAG;
    public static boolean IS_SHOW_LOG = true;

    private XLog() {
        throw new RuntimeException("不能被实例化");
    }

    /**
     * 初始化（在Application中）
     * @param isShowLog 是否显示Log
     */
    public static void init(boolean isShowLog) {
        init(isShowLog, null);
    }

    /**
     * 初始化（在Application中）
     * @param isShowLog 是否显示Log
     * @param tag TAG（标记）
     */
    public static void init(boolean isShowLog, String tag) {
        IS_SHOW_LOG = isShowLog;
        if (tag != null) TAG = tag;
        else TAG = XLogHelper.DEFAULT_TAG;
    }

    public static void v() {
        XLogDefault.printLog(XLogHelper.V, null, XLogHelper.DEFAULT_MESSAGE);
    }

    public static void v(Object msg) {
        XLogDefault.printLog(XLogHelper.V, null, msg);
    }

    public static void v(String tag, Object... objects) {
        XLogDefault.printLog(XLogHelper.V, tag, objects);
    }

    public static void d() {
        XLogDefault.printLog(XLogHelper.D, null, XLogHelper.DEFAULT_MESSAGE);
    }

    public static void d(Object msg) {
        XLogDefault.printLog(XLogHelper.D, null, msg);
    }

    public static void d(String tag, Object... objects) {
        XLogDefault.printLog(XLogHelper.D, tag, objects);
    }

    public static void i() {
        XLogDefault.printLog(XLogHelper.I, null, XLogHelper.DEFAULT_MESSAGE);
    }

    public static void i(Object msg) {
        XLogDefault.printLog(XLogHelper.I, null, msg);
    }

    public static void i(String tag, Object... objects) {
        XLogDefault.printLog(XLogHelper.I, tag, objects);
    }

    public static void w() {
        XLogDefault.printLog(XLogHelper.W, null, XLogHelper.DEFAULT_MESSAGE);
    }

    public static void w(Object msg) {
        XLogDefault.printLog(XLogHelper.W, null, msg);
    }

    public static void w(String tag, Object... objects) {
        XLogDefault.printLog(XLogHelper.W, tag, objects);
    }

    public static void e() {
        XLogDefault.printLog(XLogHelper.E, null, XLogHelper.DEFAULT_MESSAGE);
    }

    public static void e(Object msg) {
        XLogDefault.printLog(XLogHelper.E, null, msg);
    }

    public static void e(String tag, Object... objects) {
        XLogDefault.printLog(XLogHelper.E, tag, objects);
    }

    public static void a() {
        XLogDefault.printLog(XLogHelper.A, null, XLogHelper.DEFAULT_MESSAGE);
    }

    public static void a(Object msg) {
        XLogDefault.printLog(XLogHelper.A, null, msg);
    }

    public static void a(String tag, Object... objects) {
        XLogDefault.printLog(XLogHelper.A, tag, objects);
    }

    public static void json(String jsonFormat) {
        XLogJson.printJson(null, jsonFormat);
    }

    public static void json(String tag, String jsonFormat) {
        XLogJson.printJson(tag, jsonFormat);
    }

    public static void xml(String xml) {
        XLogXml.printXml(null, xml);
    }

    public static void xml(String tag, String xml) {
        XLogXml.printXml(tag, xml);
    }

    public static void file(File targetDirectory, Object msg) {
        XLogFile.printFile(null, targetDirectory, null, msg);
    }

    public static void file(String tag, File targetDirectory, Object msg) {
        XLogFile.printFile(tag, targetDirectory, null, msg);
    }

    public static void file(String tag, File targetDirectory, String fileName, Object msg) {
        XLogFile.printFile(tag, targetDirectory, fileName, msg);
    }
}
