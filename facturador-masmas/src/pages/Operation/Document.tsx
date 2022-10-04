import React, { useEffect, useRef, useState } from "react";
import { Loading, PDFButton } from "components/standalone";
import { FullSizeDocument } from "./components/documents";
import { FlexDiv, Section } from "components/wrappers";
import getDocument from "./services/getDocument";
import documentIdentifier from "./models/documentIdentifier";

type props = { identifier: documentIdentifier }

/**La representación gráfica de un documento. Un documento es la entidad que resulta de una operación exitosa. */
export default function Document({identifier}: props): JSX.Element {

    const documentReference = useRef();
    const [comercialDocument, setComercialDocument] = useState(undefined);

    useEffect(retrieveDocumentByIdentifier, []);

    function retrieveDocumentByIdentifier() {
       getDocument(identifier).then(response => {
            if (response.ok) setComercialDocument(response.content)
       });
    }

    return <Section>

    {!comercialDocument===undefined? <Loading /> :
    
        <>
            <div ref={documentReference} style={{margin:"1rem", overflow:"auto"}}>
                <FullSizeDocument document={comercialDocument} />
            </div>

            <FlexDiv justify="flex-end">
            <PDFButton nodeReference={documentReference} />    
            </FlexDiv>
            
        </>
    }
    
    </Section>
}