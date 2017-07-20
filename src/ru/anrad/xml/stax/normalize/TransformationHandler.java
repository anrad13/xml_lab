package ru.anrad.xml.stax.normalize;

public class TransformationHandler {

    public byte[] apply(byte[] in) throws Exception {

            Transformer transformer = new Transformer();

            TransformFunction[] functions = {
                    new RemoveMix(),
                    //new RemoveProcessingInstruction(),
                    //new RemoveXMLSchemaInstanceAttr(),
                    //new SortElementNS(),
                    //new RemoveEmptyCharacter()
            };

            byte[] out = in;
            for(TransformFunction f : functions) {
                out = transformer.apply(out, f);
                //System.out.println("Step " + f.toString());
                //System.out.println(new String(out));
            }

            return out;
    }
}
