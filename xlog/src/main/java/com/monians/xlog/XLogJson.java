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
