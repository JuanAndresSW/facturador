import React from "react";
import { Link } from "react-router-dom";
import documentHistoryItem from "../../../models/documentHistoryItem";
import documentNameToClassCode from "../../../utilities/conversions/documentNameToClassCode";
import "./DocumentHistoryItem.css";


export default function DocumentHistoryItem({item}:{item: documentHistoryItem}) {
    const URL = `/inicio/operacion/documento?id=${item.IDOperation}&class=${documentNameToClassCode(item.documentName)}`;

    return <Link to={URL} className="document-history-item">

        <h2>{item.documentName} {item.documentType}</h2>
        <h4>{item.documentNumber}</h4>

       
        <div>
            <p>{item.dateOfIssue}</p>
            <p><span>para:</span> {item.receiverName}</p>
            <p>{item.receiverCUIT}</p>
        </div>

    </Link>
}