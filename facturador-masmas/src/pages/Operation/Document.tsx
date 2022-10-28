import React, { Fragment, useEffect, useRef, useState } from "react";
import { useSearchParams } from "react-router-dom";

import { Loading, PDFButton } from "components/standalone";
import { InvoiceLikeDocument, Ticket } from "./components/documents";
import { FlexDiv, Section } from "components/wrappers";

import getDocument from "./services/getDocument";

import documentIdentifier from "./models/documentIdentifier";
import document from "./models/document";
import { documentClassCode } from "./models/operation";



/**
 * La representación gráfica de un documento comercial.
 * Un documento es la entidad que resulta de una operación exitosa.
 * El tipo de documento se determina por los parámetros de URL.
*/
export default function Document(): JSX.Element {


    //Identificar qué documento se debe pedir al servidor.
    const params = useSearchParams()[0];
    const documentClassCode = params.get("class") as documentClassCode;
    const documentIdentifier: documentIdentifier = {
        IDOperation:        parseInt(params.get("id")),
        documentClassCode:  documentClassCode,
    }


    //Crear el documento a mostrar.
    const documentReference = useRef();
    const [comercialDocument, setComercialDocument]: [document, Function] = useState(undefined);

    //...y llamar al servidor para rellenar sus datos.
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
                <AnyComercialDocument comercialDocument={comercialDocument} dcc={documentClassCode} />
            </div>

            <FlexDiv justify="flex-end">
                <PDFButton nodeReference={documentReference} 
                filename={`${comercialDocument.metadata.documentName} ${comercialDocument.metadata?.documentType} ${comercialDocument.metadata.documentNumber}`} />    
            </FlexDiv>
            
        </>
    }
    
    </Section>
}

function AnyComercialDocument({comercialDocument, dcc}: {comercialDocument: document, dcc: documentClassCode}): JSX.Element {

    const documentMap = {
        Fa: <InvoiceLikeDocument document={comercialDocument} />,
        Nc: <InvoiceLikeDocument document={comercialDocument} />,
        Nd: <InvoiceLikeDocument document={comercialDocument} />,

        Tk: <Ticket document={comercialDocument} />,

        Oc: <Fragment />,
        Rm: <Fragment />,
        Rx: <Fragment />,
        Rs: <Fragment />,
        Pa: <Fragment />,
        Ch: <Fragment />,
        default: <Fragment />
    }


    return documentMap[dcc];
    
}