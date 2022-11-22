import getColorByDocClassCode from "pages/Operation/utilities/getColorByDocClassCode";
import React from "react";
import { Link } from "react-router-dom";
import documentHistoryItem from "../../../models/documentHistoryItem";
import documentNameToClassCode from "../../../utilities/conversions/documentNameToClassCode";
import "./DocumentHistoryItem.css";


export default function DocumentHistoryItem({item}:{item: documentHistoryItem}) {

    //Personalización de item de historial.
    const docClassCode = documentNameToClassCode(item.documentName);
    const color = getColorByDocClassCode(docClassCode);
    const URL = `/inicio/operacion/documento?id=${item.IDOperation}&class=${docClassCode}`;


    return <Link to={URL} className="document-history-item" >

        <h2 style={{color:color}}>
            {item.documentName} {item.documentType}
        </h2>
        <h4>{item.documentNumber}</h4>

       
        <div>
            <p>{item.dateOfIssue}</p>
            {item.receiverName?<p><span>para:</span> {item.receiverName}</p>:null}
            <p>{item.receiverCUIT}</p>
        </div>

    </Link>
}