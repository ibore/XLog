package com.monians.xlog;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 功能: 打印Json，标准Json格式输出
 * 作者: ibore
 * 时间: 2016/6/05 12:01
 * 邮箱: bore521@live.com
 */
public class LogJson {

    public static void printJson(String tagStr, Object... objects) {

        String[] contents = LogHelper.wrapperContent(tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];

        String message;

        try{
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(LogHelper.JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(LogHelper.JSON_INDENT);
            } else message = msg;

        } catch (JSONException e) {

            message = msg;
        }

        LogHelper.printLine(LogHelper.D, tag, LogHelper.TOP);
        LogHelper.printMsg(LogHelper.D, tag, "║ " + headString);
        LogHelper.printLine(LogHelper.D, tag, LogHelper.MIDDLE);
        String[] lines = message.split(LogHelper.LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        LogHelper.printLine(LogHelper.D, tag, LogHelper.BOTTOM);
    }
}
