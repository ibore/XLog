package com.monians.xlog;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ibore on 2016/6/5.
 */
public class XLogJson {

    public static void printJson(String tag, String msg, String headString) {

        String message;

        try{
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(XLog.JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(XLog.JSON_INDENT);
            } else message = msg;

        } catch (JSONException e) {

            message = msg;
        }

        XLogUtils.printLine(XLogUtils.D, tag, XLogUtils.TOP);
        message = headString + XLog.LINE_SEPARATOR + message;
        String[] lines = message.split(XLog.LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        XLogUtils.printLine(XLogUtils.D, tag, XLogUtils.BOTTOM);
    }
}
