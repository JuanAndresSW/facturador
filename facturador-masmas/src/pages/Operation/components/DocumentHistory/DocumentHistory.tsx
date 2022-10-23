import React, {useEffect, useState} from 'react';

import DocumentHistoryItem from "./DocumentHistoryItem/DocumentHistoryItem";
import { Select } from 'components/formComponents';

import getListOfBranchesAndPoints from 'pages/Operation/services/getListOfBranchesAndPoints';
import getDocumentHistory from "../../services/getDocumentHistory";

import documentHistoryItem from 'pages/Operation/models/documentHistoryItem';
import "./DocumentHistory.css";
import { FlexDiv } from 'components/wrappers';

const documentClasses = [
    {title: "Factura",          value: "Fa"},
    {title: "Nota de débito",   value: "Nd"},
    {title: "Nota de crédito",  value: "Nc"},
]


const placeHolderHistory: documentHistoryItem[] = [{
    documentNumber: "0777-42012142",
    documentName: "Nota de crédito",
    documentType: "A",
    dateOfIssue: "1000-01-01",
    IDOperation: 23,
    receiverName: "All Clean SRL"
}]

/**Historial de documentos comerciales. */
export default function DocumentHistory() {


    //Recuperar datos para mostrar los filtros.
    useEffect(retrieveFilteringData, []);
    const [branchesAndPoints, setBranchesAndPoints] = useState([]);
    function retrieveFilteringData() {

        getListOfBranchesAndPoints()
        .then(response => {
            if (!response.ok) return;
            setBranchesAndPoints(response.content);
        })
    }



    //Filtros del historial.
    const [IDBranch, setIDBranch] = useState();
    const [IDPointOfSale, setIDPointOfSale] = useState();
    const [documentClassCode, setDocumentClass] = useState();


    //Recuperar el historial.
    const [documentHistory, setDocumentHistory]: [documentHistoryItem[], Function] = useState(placeHolderHistory);
    useEffect(searchInDocumentHistory, [IDBranch, IDPointOfSale, documentClassCode]);
    function searchInDocumentHistory() {
        const filters = {
            IDBranch:          IDBranch,
            IDPointOfSale:     IDPointOfSale,
            documentClassCode: documentClassCode
        };

        getDocumentHistory(filters).then(response => {
            if (response.ok) setDocumentHistory(response.content);
        });
    }
    


    return <div className='document-history'>

        <menu className='document-history-menu'>

      
            <Select 
            options={branchesAndPoints}
            value={IDBranch}            onChange={setIDBranch}         label="Sucursal"
            subValue={IDPointOfSale}    subOnChange={setIDPointOfSale} sublabel="Punto de venta" />


            <Select 
            options={documentClasses}
            value={documentClassCode}       onChange={setDocumentClass}    label="Documento"
            />
        </menu>

       <FlexDiv justify='flex-start'>

        {
            documentHistory?.map((item) => (
                <DocumentHistoryItem item={item} />
            ))
        }

        </FlexDiv> 
        
    </div>
}