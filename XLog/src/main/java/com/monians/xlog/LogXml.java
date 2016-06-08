package com.monians.xlog;

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
 * 功能: 打印Xml，标准xml格式输出
 * 作者: ibore
 * 时间: 2016/6/05 12:01
 * 邮箱: bore521@live.com
 */
public class LogXml {

    public static void printXml(String tagStr, Object... objects) {

        if (!XLog.IS_SHOW_LOG) {
            return;
        }

        String[] contents = LogHelper.wrapperContent(tagStr, objects);
        String tag = contents[0];
        String xml = contents[1];
        String headString = contents[2];

        if (xml != null) {
            xml = LogXml.formatXML(xml);
            xml = headString + "\n" + xml;
        } else xml = headString + LogHelper.NULL_TIPS;

        String[] lines = xml.split(LogHelper.LINE_SEPARATOR);

        // 打印XML
        LogHelper.printLine(LogHelper.D, tag, LogHelper.TOP);
        for(String line : lines) {
            if (!LogHelper.isEmpty(line)) {
                LogHelper.printMsg(LogHelper.D, tag, "║ " + line);
            }
        }
        LogHelper.printLine(LogHelper.D, tag, LogHelper.BOTTOM);

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
