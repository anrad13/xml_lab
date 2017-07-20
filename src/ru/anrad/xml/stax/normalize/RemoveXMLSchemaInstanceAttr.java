package ru.anrad.xml.stax.normalize;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class RemoveXMLSchemaInstanceAttr implements TransformFunction {
    public XMLEvent apply(XMLEvent e) {
        //get event factory to make new event
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();

        if (e.isStartElement()) {
            StartElement startElement = e.asStartElement();

            //System.out.println("QName :" + startElement.getName());
            //System.out.println("NamespaceContext :" + startElement.getNamespaceContext().);
            //System.out.println("SchemaType :" + startElement.getSchemaType());

            List<Attribute> attrList = new ArrayList<>();
            Iterator<Attribute> i = startElement.getAttributes();
            while (i.hasNext()) {
                Attribute attr = i.next();
                /*
                System.out.println("Attr :" + attr.getName() + "=" + attr.getValue()
                        + ", NameLocalPart=" + attr.getName().getLocalPart()
                        + ", NamespaceURI=" + attr.getName().getNamespaceURI()
                        + ", Prefix=" + attr.getName().getPrefix()
                );
                */
                /*
                Если данный дочерний узел является узлом атрибута,
                причем данный атрибут принадлежит пространству имен http://www.w3.org/2001/XMLSchema-instance
                и локальное имя атрибута совпадает с одним из следующих идентификаторов: schemaLocation,
                noNamespaceSchemaLocation, type или nil, он удаляется.
                */
                if ( attr.getName().getNamespaceURI().equals("http://www.w3.org/2001/XMLSchema-instance") ) {
                    switch (attr.getName().getLocalPart()) {
                        case "schemaLocation":
                        case "noNamespaceSchemaLocation":
                        case "type":
                        case "nil":
                            System.out.println("Attr must be ignored");
                            break;
                        default:
                            attrList.add(attr);
                            break;
                    }
                } else {
                    attrList.add(attr);
                }

            }
            //createStartElement(String prefix, String namespaceUri, String localName, Iterator attributes, Iterator namespaces, NamespaceContext context)
            //createStartElement(QName name, Iterator attributes, Iterator namespaces)
            e = eventFactory.createStartElement(
                    startElement.getName(),
                    attrList.iterator(),
                    startElement.getNamespaces()
            );
        }
        return e;
    }

    public String toString() { return "RemoveXMLSchemaInstanceAttr"; }
}
