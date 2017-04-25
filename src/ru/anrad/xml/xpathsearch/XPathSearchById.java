package ru.anrad.xml.xpathsearch;


import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class XPathSearchById {
    private XPath xpath;
    private InputSource inputSource;
    private long initDelay = 0;
    private long opsDelay = 0;
    private long doDelay = 0;

    private static String DEFAULT_INPUT_FILENAME = "src//ru//anrad//xml//test.xml";

    public XPathSearchById() {
        this(DEFAULT_INPUT_FILENAME);
    }

    public XPathSearchById(String inputFileName) {
        //System.out.println(Instant.now() + " start factory & file open");

        Instant i1 = Instant.now();
        xpath = XPathFactory.newInstance().newXPath();
        inputSource = new InputSource(inputFileName);
        Instant i2 = Instant.now();
        initDelay = i1.until(i2, ChronoUnit.MILLIS);
        System.out.println(Instant.now() +  " stop factory & file open. Delay = " + initDelay);

    }
    public void accept(String expression) {

            try {
                //System.out.println(Instant.now() + " start evaluate");
                Node node = (Node) xpath.evaluate(expression, inputSource, XPathConstants.NODE);
                //System.out.println(Instant.now() + " stop evaluate" + node.getTextContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void doTest(String inputFileName) {
        System.out.println(Instant.now() + " start XPathSearchById");

        XPathSearchById consumer = new XPathSearchById(inputFileName);

        Instant i1 = Instant.now();
        System.out.println(Instant.now() + " start first evaluate");
        //consumer.accept("/persons/person[@id=972376]/fullName");
        consumer.accept("/Envelope/Header/MessageInfo/To");

        System.out.println(Instant.now() + " start second the same evaluate");
        //consumer.accept("/persons/person[@id=972376]/fullName");
        consumer.accept("/Envelope/Header/MessageInfo/From");

        System.out.println(Instant.now() + " start another not the same evaluate");
        //consumer.accept("/persons/person[@id=345623]/fullName");
        consumer.accept("/Envelope/Body/PacketEPD/ED101[@EDNo=577112924]/Payer");

        Instant i2 = Instant.now();
        long delay = i1.until(i2, ChronoUnit.MILLIS);

        System.out.println(Instant.now()+ " ops stop. Delay = " + delay);
    }
}
