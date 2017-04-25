package ru.anrad.xml.jaxbsearch;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement( name = "persons" )
public class Persons {
    List<Person> persons;

    @XmlElement( name = "person" )
    public void setPersons( List<Person> persons ) {
        this.persons = persons;
    }
    public List<Person> getPersons() {
        return this.persons ;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        persons.stream().forEach(person -> sb.append(person.toString()));
        return sb.toString();
    }

}
