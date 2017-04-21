package ru.anrad.xml.xpathsearch;


import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.time.Instant;

public class XPathSearchById {
    private XPath xpath;
    private InputSource inputSource;
    private static String DEFAULT_INPUT_FILENAME = "src//ru//anrad//xml//xpathsearch//test.xml";

    public XPathSearchById() {
        this(DEFAULT_INPUT_FILENAME);
    }

    public XPathSearchById(String inputFileName) {
        System.out.println(Instant.now() + "start factory & file open");
        xpath = XPathFactory.newInstance().newXPath();
        inputSource = new InputSource(inputFileName);
        System.out.println(Instant.now() + "stop factory & file open");

    }
    public void accept(String expression) {

            try {
                System.out.println(Instant.now() + "start evaluate");
                Node node = (Node) xpath.evaluate(expression, inputSource, XPathConstants.NODE);
                System.out.println(Instant.now() + "stop evaluate" + node.getTextContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
