import React from "react";
import "./FullSizeDocument.css";
import testimg from 'assets/img/emblem.png';
import { DataBox } from "components/standalone";
import { FlexDiv } from "components/wrappers";
import numberToWords from "../../../utilities/numberToWords";

import document from "../../../models/document";

type props = {document: document};


export default function FullSizeDocument({document}: props): JSX.Element {

    const productSubtotal = document.operationData.productTable.reduce((prev, sum) => prev+sum.price, 0);
    const productTotal = productSubtotal + productSubtotal * (document.operationData.VAT/100);

    return ( 
    
    <div className="full-size-document">

        <div id="doc-header" >

            <div className="doc-header-advertisement">
                <img src={testimg} alt="" />
                <p>  {document.sender.name}                                 </p>
                <p className="small">  {document.sender.address}            </p>
                <p className="small">  contacto: {document.sender.contact}  </p>
                <p className="small">  {document.sender.VATCategory}        </p>
            </div>


            <div className="doc-header-data">
                <div className="doc-header-data-title">
                    <p>  {document.metadata.documentType}              </p>
                    <h2> {document.metadata.documentName.toUpperCase()}</h2>
                    <h2> N°{document.metadata.documentNumber}          </h2>
                </div>
                
                <div className="doc-header-data-date">fecha
                    <p>{document.metadata.dateOfIssue}</p><p>12</p><p>2022</p>
                </div>

                <div className="doc-header-data-other">
                    <p>C.U.I.T. N°</p><p>{document.sender.code}</p>
                    <p>Inicio de actividades:</p><p>05-10-22</p>
                </div>
            </div>
        </div>



        <div id="doc-subheader" >
            <p>Señor/es: {document.receiver.name}</p>
            <p>C.P.: {document.receiver.postalCode}</p>
            <p>Domicilio: {document.receiver.address}</p>
            <p>Localidad: {document.receiver.locality}</p>
            <p>I.V.A.: {document.receiver.VATCategory}</p>
            <p>C.U.I.T.: {document.receiver.code}</p>
            <p>Cond. de venta: {document.operationData.sellConditions}</p>
            <p>Remito:</p>
        </div>

        <div id="doc-body" >
            
            <data>Cantidad</data><data>Descripción</data><data>Precio</data><data>Total</data>

            {document.operationData.productTable.map(row => <>
                <data>{row.quantity}</data>
                <data>{row.detail}</data>
                <data>${row.price}</data>
                <data>${(row.price*row.quantity).toString().length > 7 ? (row.price*row.quantity).toExponential(2) : row.price*row.quantity }</data>
            </>)}
                           
        </div>

        <div id="doc-footer" >
            <FlexDiv >
                <DataBox title="subtotal" value={productSubtotal} />
                <DataBox title={`IVA ${document.operationData.VAT}%`} value={productSubtotal * (document.operationData.VAT/100)} />
                <DataBox title="total neto" value={productTotal} />
            </FlexDiv>

            <FlexDiv justify='space-between' align="flex-start">
                <div>
                    <p>Conjunto Solución © Uso Didáctico</p>
                    <div className="doc-footer-copies">original ⬜ duplicado ⬜ triplicado ⬜</div>
                </div>

                <DataBox title="son pesos" value={numberToWords(productTotal).toLowerCase()} />
            </FlexDiv>
              
            
        </div>

    </div>)
}