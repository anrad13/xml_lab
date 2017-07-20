package ru.anrad.xml.staxsearch;


import com.ibm.xml.xlxp.api.stax.serializer.SingleByteWriter;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.Writer;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static java.lang.System.out;

public class StaxSearchById {

    XMLEventReader reader;
    XMLEventWriter writer;

    public StaxSearchById(String inputFileName) {
        Instant i1 = Instant.now();
        try {

            File xmlFile = new File(inputFileName);
            Source source = new StreamSource(xmlFile);
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            reader = inputFactory.createXMLEventReader(source);

            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            writer = outputFactory.createXMLEventWriter(System.out);

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

        ElementToken token = new ElementToken("CorrelationMessageID", "urn:cbr-ru:msg:props:v1.3");
        ElementToken token2 = new ElementToken("ED101");

        Instant i1 = Instant.now();
        try {
            XMLEventReader reader = consumer.reader;
            XMLEventWriter writer = consumer.writer;
            //
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            //
            Writer writer2 = new CharArrayWriter();

            while (reader.hasNext()) {
                XMLEvent e = reader.nextEvent();
                token.pushEvent(e);
                token2.pushEvent(e);
                //
                e.writeAsEncodedUnicode(writer2);
                //
                if(e.isCharacters()) {
                    Characters ch = e.asCharacters();
                    if (!ch.isWhiteSpace()) {
                        e = eventFactory.createCharacters("test" + e.asCharacters().getData());
                    }
                }
                //writer.add(e);
            }
            System.out.println(writer2.toString());
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
