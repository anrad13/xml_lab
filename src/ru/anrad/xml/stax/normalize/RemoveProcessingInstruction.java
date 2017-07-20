package ru.anrad.xml.stax.normalize;

import javax.xml.stream.events.XMLEvent;

public class RemoveProcessingInstruction implements TransformFunction {
    public XMLEvent apply(XMLEvent inEvent) {
        XMLEvent outEvent = null;

        //Remove processing instruction
        if (inEvent.isProcessingInstruction()) {
            return outEvent;
        }
        else {
            return inEvent;
        }
    }

    public String toString() { return "RemoveProcessingInstruction"; }
}
