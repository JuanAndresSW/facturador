import React, { useEffect, useRef, useState } from "react";

import { Loading, PDFButton } from "components/standalone";
import { FullSizeDocument } from "./components/documents";
import { FlexDiv, Section } from "components/wrappers";

import getDocument from "./services/getDocument";

import documentIdentifier from "./models/documentIdentifier";
import document from "./models/document";
import { documentClassCode } from "./models/operation";


/**La representación gráfica de un documento. Un documento es la entidad que resulta de una operación exitosa. */
export default function Document(): JSX.Element {

    //Identificar qué documento se debe pedir al servidor.
    const params = new URLSearchParams(window.document.location.search)
    const documentIdentifier: documentIdentifier = {
        IDOperation:        parseInt(params.get("id")),
        documentClassCode:  params.get("class") as documentClassCode,
    }


    //Crear el documento a mostrar.
    const documentReference = useRef();
    const [comercialDocument, setComercialDocument]: [document, Function] = useState(undefined);

    //...y rellenar sus datos.
    useEffect(retrieveDocumentByIdentifier, []);
    function retrieveDocumentByIdentifier() {
       getDocument(documentIdentifier).then(response => {
            if (response.ok) setComercialDocument(response.content)
       });
    }

    return <Section>

    {comercialDocument===undefined? <Loading /> :
    
        <>
            <div ref={documentReference} style={{margin:"1rem", overflow:"auto"}}>
                <FullSizeDocument document={comercialDocument} />
            </div>

            <FlexDiv justify="flex-end">
            <PDFButton nodeReference={documentReference} 
            filename={`${comercialDocument.metadata.documentName} ${comercialDocument.metadata.documentType} ${comercialDocument.metadata.documentNumber}`} />    
            </FlexDiv>
            
        </>
    }
    
    </Section>
}