package ru.anrad.xml.stax.normalize;


import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

public class RemoveEmptyCharacter implements TransformFunction {
    public XMLEvent apply(XMLEvent e) {
        XMLEvent outEvent = e;

        //Remove empty characters
        if(e.isCharacters()) {
            Characters ch = e.asCharacters();
            String s = ch.getData().trim();
            if (s.isEmpty()) {
                //System.out.println("character empty = '" + s + "'");
                outEvent = null;
            }
        }
        return outEvent;
    }
    public String toString() { return "RemoveEmptyCharacter"; }
}
