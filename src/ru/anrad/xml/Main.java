package ru.anrad.xml;

import ru.anrad.xml.xpathsearch.XPathSearchById;

import java.time.Instant;

public class Main {
    //@TODO make jaxb test class with the same operation
    //@TODO make VTD-XML test class with the same operation
    //@TODO make SAXON HE test class with the same operation
    //@TODO make test with big PacketEPD class

    public static void main(String[] args) {
        System.out.println(Instant.now() + "start");
        XPathSearchById consumer = new XPathSearchById();

        System.out.println(Instant.now() + "start first evaluate");
        consumer.accept("/Persons/person[@id=972376]/fullName");

        System.out.println(Instant.now() + "start second the same evaluate");
        consumer.accept("/Persons/person[@id=972376]/fullName");

        System.out.println(Instant.now() + "start another not the same evaluate");
        consumer.accept("/Persons/person[@id=345623]/fullName");

        System.out.println(Instant.now()+ "stop");
    }
}
