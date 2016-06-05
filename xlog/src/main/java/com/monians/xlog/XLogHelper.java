package com.monians.xlog;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by ibore on 2016/6/5.
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
    public static final int JSON = 0x7;
    public static final int XML = 0x8;

    public static final int TOP = 0x10;
    public static final int MIDDLE = 0x11;
    public static final int BOTTOM = 0x12;

    public static boolean isEmpty(String line) {
        return TextUtils.isEmpty(line) || line.equals("\n") || line.equals("\t") || TextUtils.isEmpty(line.trim());
    }

    public static void printLine(int type, String tag, int site) {
        switch (type) {
            case XLogHelper.V:
                if (TOP == site) Log.v(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
                if (MIDDLE == site) Log.v(tag, "╠═══════════════════════════════════════════════════════════════════════════════════════");
                if (BOTTOM == site) Log.v(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
                break;
            case XLogHelper.D:
                if (TOP == site) Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
                if (MIDDLE == site) Log.d(tag, "╠═══════════════════════════════════════════════════════════════════════════════════════");
                if (BOTTOM == site) Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
                break;
            case XLogHelper.I:
                if (TOP == site) Log.i(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
                if (MIDDLE == site) Log.i(tag, "╠═══════════════════════════════════════════════════════════════════════════════════════");
                if (BOTTOM == site) Log.i(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
                break;
            case XLogHelper.W:
                if (TOP == site) Log.w(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
                if (MIDDLE == site) Log.w(tag, "╠═══════════════════════════════════════════════════════════════════════════════════════");
                if (BOTTOM == site) Log.w(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
                break;
            case XLogHelper.E:
                if (TOP == site) Log.e(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
                if (MIDDLE == site) Log.e(tag, "╠═══════════════════════════════════════════════════════════════════════════════════════");
                if (BOTTOM == site) Log.e(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
                break;
            case XLogHelper.A:
                if (TOP == site) Log.wtf(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
                if (MIDDLE == site) Log.wtf(tag, "╠═══════════════════════════════════════════════════════════════════════════════════════");
                if (BOTTOM == site) Log.wtf(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
                break;
        }

    }
}
