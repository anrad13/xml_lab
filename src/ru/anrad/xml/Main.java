package ru.anrad.xml;

import ru.anrad.xml.jaxbsearch.JaxbSearchById;
import ru.anrad.xml.xpathsearch.XPathSearchById;



public class Main {
    private static String DEFAULT_INPUT_FILENAME = "src//ru//anrad//xml//test.xml";

    //@TODO make jaxb test class with the same operation
    //@TODO make VTD-XML test class with the same operation
    //@TODO make SAXON HE test class with the same operation
    //@TODO make test with big PacketEPD class

    public static void main(String[] args) {
        XPathSearchById.doTest(DEFAULT_INPUT_FILENAME);
        XPathSearchById.doTest(DEFAULT_INPUT_FILENAME);

        JaxbSearchById.doTest(DEFAULT_INPUT_FILENAME);
        JaxbSearchById.doTest(DEFAULT_INPUT_FILENAME);
    }
}
