package ru.anrad.xml.stax.normalize;


import javax.xml.stream.events.XMLEvent;

public interface TransformFunction {
    //Function interface
    XMLEvent apply(XMLEvent xmlEvent);
}

