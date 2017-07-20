package ru.anrad.xml.stax.normalize;


import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


public class Transformer {

    public byte[] apply(byte[] in, TransformFunction transformFunction) throws Exception {
        //make input Source
        ByteArrayInputStream inStream = new ByteArrayInputStream(in);
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = inputFactory.createXMLEventReader(inStream);

        //make output Source
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLEventWriter writer = outputFactory.createXMLEventWriter(outStream);

        //Start main loop
        while (reader.hasNext()) {
            XMLEvent inEvent = reader.nextEvent();
            XMLEvent outEvent = transformFunction.apply(inEvent);
            if (outEvent == null) { continue; }
            writer.add(outEvent);
        }

        //Convert out stream to byte{} and return result
        return outStream.toByteArray();
    }

}
