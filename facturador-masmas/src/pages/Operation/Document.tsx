import React, { useRef } from "react";
import { PDFButton } from "components/standalone";
import { FullSizeDocument } from "./components/documents";
import { Section } from "components/wrappers";
import { operationCode } from "./models/operation";

type props = { type: operationCode }

/**La representación gráfica de un documento. Un documento es la entidad que resulta de una operación exitosa. */
export default function Document({type}: props): JSX.Element {
    const documentReference = useRef();

    return <Section>
    <div ref={documentReference} style={{margin:"1rem", overflow:"auto"}}>
        <FullSizeDocument />
    </div>
    
    <PDFButton nodeReference={documentReference} />
    </Section>
}