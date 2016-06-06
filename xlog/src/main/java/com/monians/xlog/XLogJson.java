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
public class XLogJson {

    public static void printJson(String tagStr, Object... objects) {

        String[] contents = XLogHelper.wrapperContent(tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];

        String message;

        try{
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(XLogHelper.JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(XLogHelper.JSON_INDENT);
            } else message = msg;

        } catch (JSONException e) {

            message = msg;
        }

        XLogHelper.printLine(XLogHelper.D, tag, XLogHelper.TOP);
        XLogHelper.printMsg(XLogHelper.D, tag, "║ " + headString);
        XLogHelper.printLine(XLogHelper.D, tag, XLogHelper.MIDDLE);
        String[] lines = message.split(XLogHelper.LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        XLogHelper.printLine(XLogHelper.D, tag, XLogHelper.BOTTOM);
    }
}
