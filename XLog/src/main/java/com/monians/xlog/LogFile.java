package com.monians.xlog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * 功能: 保存Log到手机上
 * 作者: ibore
 * 时间: 2016/6/05 12:01
 * 邮箱: bore521@live.com
 */
public class LogFile {

    public static void printFile(String tagStr, File targetDirectory, String fileName, Object objectMsg) {
        if (!XLog.IS_SHOW_LOG) {
            return;
        }
        String[] contents = LogHelper.wrapperContent(tagStr, objectMsg);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];
        fileName = (fileName == null) ? getFileName() : fileName;

        if (save(targetDirectory, fileName, msg)) {
            LogHelper.printLine(LogHelper.D, tag, LogHelper.TOP);
            LogHelper.printMsg(LogHelper.D, tag, "║ " + headString);
            LogHelper.printLine(LogHelper.D, tag, LogHelper.MIDDLE);
            LogHelper.printMsg(LogHelper.D, tag, "║ save log success ! location is >>>" + targetDirectory.getAbsolutePath() + "/" + fileName);
            LogHelper.printLine(LogHelper.D, tag, LogHelper.BOTTOM);
        } else {
            LogHelper.printLine(LogHelper.E, tag, LogHelper.TOP);
            LogHelper.printMsg(LogHelper.E, tag, "║ " + headString);
            LogHelper.printLine(LogHelper.E, tag, LogHelper.MIDDLE);
            LogHelper.printMsg(LogHelper.E, tag, "║ save log fails !");
            LogHelper.printLine(LogHelper.E, tag, LogHelper.BOTTOM);
        }

    }

    private static boolean save(File dic, String fileName, String msg) {

        File file = new File(dic, fileName);

        try {
            OutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            outputStreamWriter.write(msg);
            outputStreamWriter.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static String getFileName() {
        Random random = new Random();
        return LogHelper.DEFAULT_TAG + Long.toString(System.currentTimeMillis() + random.nextInt(10000)).substring(4) + ".txt";
    }
}
