package ru.anrad.xml;

import ru.anrad.xml.jaxbsearch.JaxbSearchById;
import ru.anrad.xml.staxsearch.StaxSearchById;
import ru.anrad.xml.util.ObjectSizeFetcher;
import ru.anrad.xml.xpathsearch.XPathSearchById;



public class Main {
    private static String DEFAULT_INPUT_FILENAME = "src//ru//anrad//xml//test.xml";
    private static String DEFAULT_INPUT_FILENAME_100 = "src//ru//anrad//xml//PacketEPD100.xml";

    //@TODO make jaxb test class with the same operation
    //@TODO make VTD-XML test class with the same operation
    //@TODO make SAXON HE test class with the same operation
    //@TODO make test with big PacketEPD class

    public static void main(String[] args) {
        StaxSearchById.doTest(DEFAULT_INPUT_FILENAME_100);
        //XPathSearchById.doTest(DEFAULT_INPUT_FILENAME_100);
        //XPathSearchById.doTest(DEFAULT_INPUT_FILENAME);

        //JaxbSearchById.doTest(DEFAULT_INPUT_FILENAME);
        //JaxbSearchById.doTest(DEFAULT_INPUT_FILENAME);

    }
}
