package ru.anrad.xml.jaxbsearch;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType( propOrder = { "fullName", "address", "dateOfBirth" } )
@XmlRootElement (name = "person")
public class Person {
    private int id;
    private String fullName;
    private String address;
    private String dateOfBirth;

    public  Person() {}

    public Person(int id,String fullName, String address, String dateOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    @XmlElement
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    @XmlElement
    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @XmlElement
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("fullName = ").append(this.fullName);
        return sb.toString();
    }

}
