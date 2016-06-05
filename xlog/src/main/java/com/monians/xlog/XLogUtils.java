package com.monians.xlog;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by ibore on 2016/6/5.
 */
public class XLogUtils {

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
            case XLogUtils.V:
                if (TOP == site) Log.v(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
                if (MIDDLE == site) Log.v(tag, "╠═══════════════════════════════════════════════════════════════════════════════════════");
                if (BOTTOM == site) Log.v(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
                break;
            case XLogUtils.D:
                if (TOP == site) Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
                if (MIDDLE == site) Log.d(tag, "╠═══════════════════════════════════════════════════════════════════════════════════════");
                if (BOTTOM == site) Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
                break;
            case XLogUtils.I:
                if (TOP == site) Log.i(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
                if (MIDDLE == site) Log.i(tag, "╠═══════════════════════════════════════════════════════════════════════════════════════");
                if (BOTTOM == site) Log.i(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
                break;
            case XLogUtils.W:
                if (TOP == site) Log.w(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
                if (MIDDLE == site) Log.w(tag, "╠═══════════════════════════════════════════════════════════════════════════════════════");
                if (BOTTOM == site) Log.w(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
                break;
            case XLogUtils.E:
                if (TOP == site) Log.e(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
                if (MIDDLE == site) Log.e(tag, "╠═══════════════════════════════════════════════════════════════════════════════════════");
                if (BOTTOM == site) Log.e(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
                break;
            case XLogUtils.A:
                if (TOP == site) Log.wtf(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
                if (MIDDLE == site) Log.wtf(tag, "╠═══════════════════════════════════════════════════════════════════════════════════════");
                if (BOTTOM == site) Log.wtf(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
                break;
        }

    }
}
