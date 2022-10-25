import React, {useEffect, useState} from 'react';

import DocumentHistoryItem from "./DocumentHistoryItem/DocumentHistoryItem";
import { Select } from 'components/formComponents';

import getListOfBranchesAndPoints from 'pages/Operation/services/getListOfBranchesAndPoints';
import getDocumentHistory from "../../services/getDocumentHistory";

import documentHistoryItem from 'pages/Operation/models/documentHistoryItem';
import "./DocumentHistory.css";
import { Cond, FlexDiv } from 'components/wrappers';

const documentClasses = [
    {title: "Factura",          value: "Fa"},
    {title: "Nota de débito",   value: "Nd"},
    {title: "Nota de crédito",  value: "Nc"},
]


/**Historial de documentos comerciales. */
export default function DocumentHistory() {


    //Recuperar datos para mostrar los filtros.
    useEffect(retrieveFilteringData, []);
    const [branchesAndPoints, setBranchesAndPoints] = useState([]);
    function retrieveFilteringData() {

        getListOfBranchesAndPoints(true)
        .then(response => {
            if (!response.ok) return;
            setBranchesAndPoints(response.content);
        })
    }



    //Filtros del historial.
    const [IDBranch, setIDBranch] = useState(undefined);
    const [pointOfSaleNumber, setPointOfSaleNumber] = useState(undefined);
    const [documentClassCode, setDocumentClass] = useState(undefined);


    //Recuperar el historial.
    const [documentHistory, setDocumentHistory]: [documentHistoryItem[], Function] = useState([]);
    useEffect(searchInDocumentHistory, [IDBranch, pointOfSaleNumber, documentClassCode]);
    function searchInDocumentHistory() {
        const filters = {
            IDBranch:          IDBranch,
            pointOfSaleNumber: pointOfSaleNumber,
            documentClassCode: documentClassCode
        };

        getDocumentHistory(filters).then(response => {
            if (response.ok) setDocumentHistory(response.content);
        });
    }
    


    return <div className='document-history'>


        <Cond bool={documentHistory.length > 0}>
        <menu className='document-history-menu'>
            <Select 
            options={branchesAndPoints}
            value={IDBranch}                onChange={setIDBranch}         label="Sucursal"
            subValue={pointOfSaleNumber}    subOnChange={setPointOfSaleNumber} sublabel="Punto de venta" />


            <Select 
            options={documentClasses}
            value={documentClassCode}       onChange={setDocumentClass}    label="Documento"
            />
        </menu>
        </Cond>


       <FlexDiv justify='flex-start'>

        {
            documentHistory?.map((item) => (
                <DocumentHistoryItem item={item} key={item.IDOperation} />
            ))
        }

        </FlexDiv> 
        
    </div>
}