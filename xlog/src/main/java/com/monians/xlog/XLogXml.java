package com.monians.xlog;

import android.util.Log;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Created by ibore on 2016/6/5.
 */
public class XLogXml {

    public static void printXml(String tag, String xml, String headString) {

        if (xml != null) {
            xml = XLogXml.formatXML(xml);
            xml = headString + "\n" + xml;
        } else xml = headString + XLogHelper.NULL_TIPS;

        String[] lines = xml.split(XLogHelper.LINE_SEPARATOR);

        // 打印XML
        XLogHelper.printLine(XLogHelper.D, tag, XLogHelper.TOP);
        for(String line : lines) {
            if (!XLogHelper.isEmpty(line)) {
                Log.d(tag, "║ " + line);
            }
        }
        XLogHelper.printLine(XLogHelper.D, tag, XLogHelper.BOTTOM);

    }

    private static String formatXML(String xml) {
        try {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString().replace(">", ">\n");
        } catch (TransformerException e) {
            e.printStackTrace();
            return xml;
        }
    }
}
