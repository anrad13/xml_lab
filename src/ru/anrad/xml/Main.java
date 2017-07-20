package ru.anrad.xml;

import ru.anrad.xml.stax.normalize.TransformationHandler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class Main {
    private static String DEFAULT_INPUT_FILENAME = "src//ru//anrad//xml//test4.xml";
    private static String DEFAULT_INPUT_FILENAME_100 = "src//ru//anrad//xml//PacketEPD100.xml";

    //@TODO make jaxb test class with the same operation
    //@TODO make VTD-XML test class with the same operation
    //@TODO make SAXON HE test class with the same operation
    //@TODO make test with big PacketEPD class

    public static void main(String[] args) throws Exception {
        normalization(DEFAULT_INPUT_FILENAME_100);
    }

    public static void normalization(String inputFileName) throws Exception {
        Path path = Paths.get(inputFileName);
        byte[] in = Files.readAllBytes(path);
        TransformationHandler handler = new TransformationHandler();

        Instant i1 = Instant.now();
        byte[] out = handler.apply(in);
        Instant i2 = Instant.now();

        System.out.println(new String(out));

        long initDelay = i1.until(i2, ChronoUnit.MILLIS);
        System.out.println(Instant.now() +  "Transform Delay (millis) = " + initDelay);
    }

}
