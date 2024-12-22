import React from "react";
import { Link } from "react-router-dom";
import { documentClassCode } from "../../models/operation";
import getColorByDocClassCode from "../../utilities/getColorByDocClassCode";
import documentClassCodeToDocumentName from "../../utilities/conversions/documentClassCodeToDocumentName";
import './OperationSelectionItem.css';

type props = {
    documentClassCode: documentClassCode,
    link:  string,
    disabled?: boolean
}

/**
 * Un cuadro con un enlace 'link', un texto de dos caracteres y un título.
 * Para ser usado en el menú de selección de operación.
 */
export default function OperationSelectionItem({documentClassCode, link, disabled=false}:props): JSX.Element {

    const color = getColorByDocClassCode(disabled? "default" : documentClassCode);

    return (
        <Link to={link} data-option onClick={(e)=>disabled?e.preventDefault():null}>

            <div style={{color:color, borderColor: color}}>

                <p>{documentClassCode.charAt(0)}<span>{documentClassCode.charAt(1)}</span></p>

            </div>

            <span><p>{documentClassCodeToDocumentName(documentClassCode, true)}</p></span>

        </Link>
    );
}