package com.xiaolu.mylibrary.log;

import android.util.Log;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.log
 * @ClassName: XmlLog
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2021/1/16 17:24
 * @UpdateUser: xiaol
 * @UpdateDate: 2021/1/16 17:24
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
public class XmlLog {
    public static void printXml(String tag, String xml, String headString) {

        if (xml != null) {
            xml = XmlLog.formatXML(xml);
            xml = headString + "\n" + xml;
        } else {
            xml = headString + LogUtil.NULL_TIPS;
        }

        Utils.printLine(tag, true);
        String[] lines = xml.split(LogUtil.LINE_SEPARATOR);
        for (String line : lines) {
            if (!Utils.isEmpty(line)) {
                Log.d(tag, "║ " + line);
            }
        }
        Utils.printLine(tag, false);
    }

    private static String formatXML(String inputXML) {
        try {
            Source xmlInput = new StreamSource(new StringReader(inputXML));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString().replaceFirst(">", ">\n");
        } catch (Exception e) {
            e.printStackTrace();
            return inputXML;
        }
    }

}
