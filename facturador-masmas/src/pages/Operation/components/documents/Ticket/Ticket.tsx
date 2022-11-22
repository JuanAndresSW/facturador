import React, { Fragment } from "react";
import "./Ticket.css";

import document from "../../../models/document";
import numberToWords from "pages/Operation/utilities/conversions/numberToWords";

type props = {document: document};


/**Un documento comercial: (ticket). */
export default function Ticket({document}: props): JSX.Element {

    const productTotal = document.operationData.productTable.reduce((prev, sum) => prev+sum.price*sum.quantity, 0);

    return ( 
    
    <div className="ticket">


        <div className="doc-header-data">
            <p>  {document.sender.name}. {document.sender.VATCategory}  </p>
            <p>C.U.I.T. N° {document.sender.code}</p>
            <p className="small">Dirección: {document.sender.address}   </p>
            <p className="small">A consumidor final</p>
            <h2>Ticket N°{document.metadata.documentNumber}          </h2>
            <p>Emitido el {document.metadata.dateOfIssue}</p>
        </div>


        <div className="doc-body">
            <data>Cantidad</data><data>Descripción</data><data>Precio</data><data>Total</data>

            {document.operationData.productTable.map((row, i) => <Fragment key={i}>
                <data>{row.quantity}</data>
                <data>{row.detail}</data>
                <data>${row.price}</data>
                <data>${(row.price*row.quantity).toString().length > 7 ? (row.price*row.quantity).toExponential(2) : row.price*row.quantity }</data>
            </Fragment>)}
        </div>


        <div className="doc-footer">
            <p>CF-DGI</p>
            <p>Total neto: {numberToWords(productTotal).toLowerCase()} (${productTotal})</p>
        </div>


    </div>)
}