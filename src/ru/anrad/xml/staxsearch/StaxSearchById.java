package ru.anrad.xml.staxsearch;


import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import java.io.File;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static java.lang.System.out;

public class StaxSearchById {

    XMLEventReader reader;

    public StaxSearchById(String inputFileName) {
        Instant i1 = Instant.now();
        try {

            File xmlFile = new File(inputFileName);
            Source source = new StreamSource(xmlFile);
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            reader = inputFactory.createXMLEventReader(source);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Instant i2 = Instant.now();
        long delay = i1.until(i2, ChronoUnit.MILLIS);
        System.out.println(Instant.now() +  " stop factory & file open. Delay = " + delay);

    }
    public static void doTest(String inputFileName) {
        System.out.println(Instant.now() + " start XPathSearchById");

        StaxSearchById consumer = new StaxSearchById(inputFileName);

        boolean isE = false;
        boolean isInnerE = false;
        String eName = "CorrelationMessageID";
        StringBuffer eText = new StringBuffer();

        ElementToken token = new ElementToken("CorrelationMessageID", "urn:cbr-ru:msg:props:v1.3");
        ElementToken token2 = new ElementToken("ED1010");

        Instant i1 = Instant.now();
        try {
            XMLEventReader reader = consumer.reader;
            while (reader.hasNext()) {
                XMLEvent e = reader.nextEvent();
                token.pushEvent(e);
                token2.pushEvent(e);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Instant i2 = Instant.now();
        long delay = i1.until(i2, ChronoUnit.MILLIS);

        System.out.println("Element = " + token.getElement().getName().getNamespaceURI() + "; Value = " + token.getElementText() + "; Delay = " + delay);
        System.out.println("Element = " + token2.getElement().getName().getNamespaceURI() + "; Value = " + token2.getElementText() + "; Delay = " + delay);


    }

}
