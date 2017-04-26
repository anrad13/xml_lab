package ru.anrad.xml.staxsearch;


import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class ElementToken {

    private String name;
    private String namespaceURI;

    private boolean isNamespaceURI = true;
    private boolean isElement;
    private boolean isInnerElement;
    private boolean isSuccessful;

    private StartElement element;
    private String elementText;

    public ElementToken(String name, String namespaceURI) {
        this.name = name;
        this.namespaceURI = namespaceURI;
    }

    public ElementToken(String name) {
        this.name = name;
        this.isNamespaceURI = false;
    }

    public boolean pushEvent(XMLEvent e) {
        if (isSuccessful) return isSuccessful;

        if (e.isStartElement() && e.asStartElement().getName().getLocalPart().equals(name)) {
            if (isNamespaceURI && ! e.asStartElement().getName().getNamespaceURI().equals(namespaceURI)) {
                return false;
            }
            isElement = true;
            element = e.asStartElement();
            return false;
        }

        if (e.isEndElement() && e.asEndElement().getName().getLocalPart().equals(name)) {
            if (isNamespaceURI && ! e.asEndElement().getName().getNamespaceURI().equals(namespaceURI)) {
                return false;
            }
            isElement = false;
            isSuccessful = true;
            return true;
        }

        if (e.isStartElement() && isElement) {
            isInnerElement = true;
            return false;
        }

        if (e.isEndElement() && isInnerElement) {
            isInnerElement = false;
            return false;
        }

        if (e.isCharacters() && isElement && !isInnerElement) {
            Characters ch = e.asCharacters();
            if (ch.isWhiteSpace()) return false;
            if (ch.isIgnorableWhiteSpace()) return false;
            if (ch.isCData()) return false;
            elementText = e.asCharacters().getData();
        }

        return isSuccessful;

    }

    public String getElementText() {
        return elementText.toString();
    }
    public StartElement getElement() {
        return element;
    }

}
