package ru.anrad.xml.jaxbsearch;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class JaxbSearchById {
    private static String DEFAULT_INPUT_FILENAME = "src//ru//anrad//xml//test.xml";

    private Persons persons;

    public JaxbSearchById() {
        this(DEFAULT_INPUT_FILENAME);
    }

    public JaxbSearchById(String xmlFilePath) {
        //System.out.println(Instant.now() + " start jaxb factory & file open");
        try {
            Instant i1 = Instant.now();
            File xmlFile = new File(xmlFilePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            persons = (Persons) jaxbUnmarshaller.unmarshal(xmlFile);
            Instant i2 = Instant.now();
            long delay = i1.until(i2, ChronoUnit.MILLIS);
            System.out.println(Instant.now() +  " stop jaxb factory & file open. Delay = " + delay);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        //System.out.println(Instant.now() +  " stop factory & file open");
    }

    public static void doTest(String inputFileName) {
        System.out.println(Instant.now() + " start JaxbSearchById");

        JaxbSearchById consumer = new JaxbSearchById(inputFileName);
        List<Person> persons = consumer.persons.getPersons();


        Instant i1 = Instant.now();
        //System.out.println(Instant.now() + " start first evaluate");

        Person p1 =  persons.get(0);
        //System.out.println(  p1.getFullName()   );

        //System.out.println(Instant.now() + " start second the same evaluate");
        //System.out.println(  p1.getFullName()   );

        //System.out.println(Instant.now() + " start another not the same evaluate");
        Person p2 =  persons.get(1);
        //System.out.println(  p2.getFullName()   );
        Instant i2 = Instant.now();
        long delay = i1.until(i2, ChronoUnit.MILLIS);
        System.out.println(Instant.now()+ " stop. Delay = " + delay);
    }

}


