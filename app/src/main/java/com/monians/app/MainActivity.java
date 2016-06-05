package com.monians.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.monians.xlog.XLog;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_MSG = "KLog is a so cool Log Tool!";
    private static final String TAG = "KLog";
    private static final String URL_XML = "https://raw.githubusercontent.com/ZhaoKaiQiang/KLog/master/app/src/main/AndroidManifest.xml";
    private static String XML = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><!--  Copyright w3school.com.cn --><note><to>George</to><from>John</from><heading>Reminder</heading><body>Don't forget the meeting!</body></note>";
    private static String JSON;
    private static String JSON_LONG;
    private static String STRING_LONG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        JSON_LONG = getResources().getString(R.string.json_long);
        JSON = getResources().getString(R.string.json);
        STRING_LONG = getString(R.string.string_long);
    }

    public void log(View view) {
        XLog.v();
        XLog.d();
        XLog.i();
        XLog.w();
        XLog.e();
        XLog.a();
    }

    public void logWithNull(View view) {
        XLog.v(null);
        XLog.d(null);
        XLog.i(null);
        XLog.w(null);
        XLog.e(null);
        XLog.a(null);
    }

    public void logWithMsg(View view) {
        XLog.v(LOG_MSG);
        XLog.d(LOG_MSG);
        XLog.i(LOG_MSG);
        XLog.w(LOG_MSG);
        XLog.e(LOG_MSG);
        XLog.a(LOG_MSG);
    }

    public void logWithTag(View view) {
        XLog.v(TAG, LOG_MSG);
        XLog.d(TAG, LOG_MSG);
        XLog.i(TAG, LOG_MSG);
        XLog.w(TAG, LOG_MSG);
        XLog.e(TAG, LOG_MSG);
        XLog.a(TAG, LOG_MSG);
    }

    public void logWithLong(View view) {
        XLog.d(TAG, STRING_LONG);
    }

    public void logWithParams(View view) {
        XLog.v(TAG, LOG_MSG, "params1", "params2", this);
        XLog.d(TAG, LOG_MSG, "params1", "params2", this);
        XLog.i(TAG, LOG_MSG, "params1", "params2", this);
        XLog.w(TAG, LOG_MSG, "params1", "params2", this);
        XLog.e(TAG, LOG_MSG, "params1", "params2", this);
        XLog.a(TAG, LOG_MSG, "params1", "params2", this);
    }


    public void logWithJson(View view) {
        XLog.json("12345");
        XLog.json(null);
        XLog.json(JSON);
    }

    public void logWithLongJson(View view) {
        XLog.json(JSON_LONG);
    }

    public void logWithJsonTag(View view) {
        XLog.json(TAG, JSON);
    }

    public void logWithFile(View view) {
        XLog.file(Environment.getExternalStorageDirectory(), JSON_LONG);
        XLog.file(TAG, Environment.getExternalStorageDirectory(), JSON_LONG);
        XLog.file(TAG, Environment.getExternalStorageDirectory(), "test.txt", JSON_LONG);
    }

    public void logWithXml(View view) {
        XLog.xml("12345");
        XLog.xml(null);
        XLog.xml(XML);
    }

    public void logWithXmlFromNet(View view) {
//        httpClient.get(this, URL_XML, new TextHttpResponseHandler() {
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                KLog.e(responseString);
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                KLog.xml(responseString);
//            }
//        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // MENU
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_github:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ibore/XLog")));
                break;
            case R.id.action_csdn:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.monians.com")));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
