package ru.anrad.xml.stax.normalize;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.util.*;


public class SortElementNS implements TransformFunction {
    public XMLEvent apply(XMLEvent e) {
        //get event factory to make new event
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();

        if (e.isStartElement()) {
            StartElement startElement = e.asStartElement();

            //System.out.println("QName :" + startElement.getName());
            //System.out.println("Name.NamespaceURI :" + startElement.getName().getNamespaceURI());
            //System.out.println("Name.Prefix :" + startElement.getName().getPrefix());
            //System.out.println("Name.LocalPart :" + startElement.getName().getLocalPart());

            //Step A-1
            List<Attribute> lAttributes = new ArrayList<>();
            Iterator<Attribute> iAttribute = startElement.getAttributes();
            while (iAttribute.hasNext()) { lAttributes.add(iAttribute.next()); }

            Set<String> nsURISet = new HashSet<>();

            if (! startElement.getName().getNamespaceURI().isEmpty()) {
                nsURISet.add(startElement.getName().getNamespaceURI());
            }

            for (Attribute attr: lAttributes) {
                String ns_uri =  attr.getName().getNamespaceURI();
                if (!ns_uri.isEmpty() ) {
                    nsURISet.add(ns_uri);
                }
            }
            //System.out.println("Step A-1. Element NS collection without equals:" + Arrays.toString(nsURISet.toArray()));

            //Step A-2
            List<String> nsURI = new ArrayList(Arrays.asList(nsURISet.toArray()));
            Collections.sort(nsURI);
            //System.out.println("Step A-2. Element NS collection sorted:" + Arrays.toString(nsURI.toArray()));

            //Step A-3
            Map<String,String> nsPrefixMap = new HashMap<>();
            for(int i = 1; i <= nsURI.size(); i++) {
                String ns_uri = nsURI.get(i-1);
                String ns_prefix = "n" + i;
                nsPrefixMap.put(ns_uri, ns_prefix);
            }
            //System.out.println("Step A-3. Element NS collection prefix:" + Arrays.toString(nsURI.toArray()));

            //Step B
            String elementNamespaceURI, elementPrefix, elementLocalName;
            elementNamespaceURI = startElement.getName().getNamespaceURI();
            elementPrefix = nsPrefixMap.get(elementNamespaceURI);
                if (elementPrefix == null) {
                    elementPrefix = "";
                }
            elementLocalName = startElement.getName().getLocalPart();
            /*System.out.println("Step B. Element: "
                    + ", Name.NamespaceURI = " + elementNamespaceURI
                    + ", Name.Prefix = " + elementPrefix
                    + ", Name.LocalPart = " + elementLocalName
                    );*/

            //Step C
            //System.out.println("Step C ");
            Attribute attr;
            Iterator<Attribute> attrIterator = startElement.getAttributes();
            List<Attribute> attrList = new ArrayList<>();
            while (attrIterator.hasNext()) {
                attr = attrIterator.next();
                /*System.out.println("Attr     :" + attr.getName() + "=" + attr.getValue()
                        + ", Name.LocalPart=" + attr.getName().getLocalPart()
                        + ", Name.NamespaceURI=" + attr.getName().getNamespaceURI()
                        + ", Name.Prefix=" + attr.getName().getPrefix()
                        + ", SchemaType=" + attr.getSchemaType()
                );*/
                String attrPrefix = nsPrefixMap.get(attr.getName().getNamespaceURI());

                if (attrPrefix != null) {
                    //createAttribute(String prefix, String namespaceURI, String localName, String value)
                    attr = eventFactory.createAttribute(
                            attrPrefix,
                            attr.getName().getNamespaceURI(),
                            attr.getName().getLocalPart(),
                            attr.getValue()
                    );
                }
                /*System.out.println("Attr NEW :" + attr.getName() + "=" + attr.getValue()
                        + ", Name.LocalPart=" + attr.getName().getLocalPart()
                        + ", Name.NamespaceURI=" + attr.getName().getNamespaceURI()
                        + ", Name.Prefix=" + attr.getName().getPrefix()
                        + ", SchemaType=" + attr.getSchemaType()
                );*/
                attrList.add(attr);
            }

            //Step D
            //System.out.println("Step D ");
            List<Namespace> nsList = new ArrayList<>();
            for(String ns_uri : nsURI) {
                String nsPrefix = nsPrefixMap.get(ns_uri);
                //createNamespace(String prefix, String namespaceUri)
                Namespace ns = eventFactory.createNamespace(
                        nsPrefix,
                        ns_uri
                );
                /*System.out.println("NS NEW:" + ns.getName() + "=" + ns.getValue()
                        + ", Name.LocalPart=" + ns.getName().getLocalPart()
                        + ", Name.NamespaceURI=" + ns.getName().getNamespaceURI()
                        + ", Name.Prefix=" + ns.getName().getPrefix()
                        + ", NamespaceURI=" + ns.getNamespaceURI()
                        + ", Prefix=" + ns.getPrefix()
                );*/
                nsList.add(ns);
            }

            //createStartElement(String prefix, String namespaceUri, String localName, Iterator attributes, Iterator namespaces, NamespaceContext context)
            e = eventFactory.createStartElement(
                    elementPrefix,
                    elementNamespaceURI,
                    elementLocalName,
                    attrList.iterator(),
                    nsList.iterator()
            );
        }
        return e;
    }

    public String toString() { return "SortElementNS"; }

}
