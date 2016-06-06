package com.monians.xlog;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by ibore on 2016/6/5.
 */
public class XLogFile {
    public static void printFile(String tag, File targetDirectory, String fileName, String headString, String msg) {

        fileName = (fileName == null) ? getFileName() : fileName;

        if (save(targetDirectory, fileName, msg)) {
            XLogHelper.printLine(XLogHelper.D, tag, XLogHelper.TOP);
            XLogHelper.printMsg(XLogHelper.D, tag, "║ " + headString);
            XLogHelper.printLine(XLogHelper.D, tag, XLogHelper.MIDDLE);
            XLogHelper.printMsg(XLogHelper.D, tag, "║ save log success ! location is >>>" + targetDirectory.getAbsolutePath() + "/" + fileName);
            XLogHelper.printLine(XLogHelper.D, tag, XLogHelper.BOTTOM);
        } else {
            XLogHelper.printLine(XLogHelper.E, tag, XLogHelper.TOP);
            XLogHelper.printMsg(XLogHelper.E, tag, "║ " + headString);
            XLogHelper.printLine(XLogHelper.E, tag, XLogHelper.MIDDLE);
            XLogHelper.printMsg(XLogHelper.E, tag, "║ save log fails !");
            XLogHelper.printLine(XLogHelper.E, tag, XLogHelper.BOTTOM);
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
        return "KLog_" + Long.toString(System.currentTimeMillis() + random.nextInt(10000)).substring(4) + ".txt";
    }
}
