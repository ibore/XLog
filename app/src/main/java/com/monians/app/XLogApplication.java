package com.monians.app;

import android.app.Application;

import com.monians.xlog.XLog;

/**
 * Created by ibore on 2016/6/6.
 */
public class XLogApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        XLog.init(BuildConfig.DEBUG, "我的TAG");
    }
}
