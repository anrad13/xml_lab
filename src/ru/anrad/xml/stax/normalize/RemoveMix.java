package ru.anrad.xml.stax.normalize;

import javax.xml.stream.events.XMLEvent;

/**
 * Created by Game on 20.07.2017.
 */
public class RemoveMix implements TransformFunction {

    private RemoveEmptyCharacter removeEmptyCharacter = new RemoveEmptyCharacter();
    private RemoveProcessingInstruction removeProcessingInstruction = new RemoveProcessingInstruction();
    private RemoveXMLSchemaInstanceAttr removeXMLSchemaInstanceAttr = new RemoveXMLSchemaInstanceAttr();
    private SortElementNS sortElementNS = new SortElementNS();

    public XMLEvent apply(XMLEvent e) {

        //Remove empty characters
        if (e.isCharacters()) {
            return removeEmptyCharacter.apply(e);
        }

        if (e.isProcessingInstruction()) {
            return removeProcessingInstruction.apply(e);
        }

        if (e.isStartElement()) {
            e = removeXMLSchemaInstanceAttr.apply(e);
            e = sortElementNS.apply(e);
            return e;
        }

        return e;
    }

    public String toString() {
        return "RemoveMix";
    }

}