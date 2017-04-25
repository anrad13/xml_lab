package ru.anrad.xml.staxsearch;


import com.sun.xml.internal.stream.events.EndDocumentEvent;
import com.sun.xml.internal.stream.events.EndElementEvent;
import com.sun.xml.internal.stream.events.StartElementEvent;

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
        String eName = "ED1010";
        StringBuffer eText = new StringBuffer();


        Instant i1 = Instant.now();
        try {
            XMLEventReader reader = consumer.reader;
            while (reader.hasNext()) {
                XMLEvent e = reader.nextEvent();

                if (e.isStartElement() && ((StartElementEvent) e).getName().getLocalPart().equals(eName)) {
                //if (e.isStartElement() ) {
                    isE = true;
                    System.out.println( reader.getElementText() );
                    continue;
                }
                if (e.isEndElement() && ((EndElementEvent) e).getName().getLocalPart().equals(eName)) {
                    isE = false;
                    //System.out.println(e);
                    //QName qName = ((EndElementEvent) e).getName();
                    //QName qName = new QName("ED101010");
                    //((EndElementEvent) e).setName(qName);
                    //System.out.println(e);
                    break;
                    //continue;
                }
                if (e.isStartElement() && isE) {
                    isInnerE = true;
                    continue;
                }
                if (e.isEndElement() && isInnerE) {
                    isInnerE = false;
                    //System.out.println(e);
                    continue;
                }

                if (isE && e.isCharacters() && !isInnerE) {
                    //System.out.println(e);
                    if (((Characters) e).isWhiteSpace()) continue;
                    eText.append(e);
                }

                if (e.isEndDocument()) {
                    System.out.println( e );
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }



        Instant i2 = Instant.now();
        long delay = i1.until(i2, ChronoUnit.MILLIS);

        System.out.println("Value = " + eText.toString() + "; Delay = " + delay);

        /*
        try {
            XMLEventWriter writer = XMLOutputFactory.newInstance().createXMLEventWriter(System.out);
            writer.add(consumer.reader);
            writer.flush();
            writer.close();
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
        */
    }

}
