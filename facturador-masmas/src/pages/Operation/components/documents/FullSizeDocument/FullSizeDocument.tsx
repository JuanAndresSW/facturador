import React from "react";
import "./FullSizeDocument.css";
import testimg from 'assets/img/emblem.png';
import { DataBox } from "components/standalone";
import { FlexDiv } from "components/wrappers";
import numberToWords from "../../../utilities/numberToWords";

export default function FullSizeDocument(): JSX.Element {
    return ( 
    
    <div className="full-size-document">

        <div id="doc-header" >

            <div className="doc-header-advertisement">
                <img src={testimg} alt="" />
                <p>  Conjunto Solución S.H.                         </p>
                <p className="small">  Av. Santa Fe 1525 C.A.B.A.   </p>
                <p className="small">  contacto: 7345-6574          </p>
                <p className="small">  Responsable Inscripto        </p>
            </div>


            <div className="doc-header-data">
                <div className="doc-header-data-title">
                    <p> A               </p>
                    <h2>FACTURA         </h2>
                    <h2>N°0001-00000001 </h2>
                </div>
                
                <div className="doc-header-data-date">fecha
                    <p>31</p><p>12</p><p>2022</p>
                </div>

                <div className="doc-header-data-other">
                    <p>C.U.I.T. N°</p><p>11-11.111.111-1</p>
                    <p>Inicio de actividades:</p><p>11-11-11</p>
                </div>
            </div>
        </div>



        <div id="doc-subheader" >
            <p>Señor/es:</p>
            <p>C.P.:</p>
            <p>Domicilio:</p>
            <p>Localidad:</p>
            <p>I.V.A.:</p>
            <p>C.U.I.T.:</p>
            <p>Cond. de venta:Condiciones de ventaCondiciones de ventaCondiciones de ventaCondiciones de ventaCondiciones de ventaCondiciones de ventaCondiciones de venta</p>
            <p>Remito:</p>
        </div>

        <div id="doc-body" >
            
            <data>Cantidad</data><data>Descripción</data><data>Precio</data><data>Total</data>

            <data>1000000</data>
            <data>FilaFilaFilaFilaFilaFilaFilaFilaFilaFilaFilaFilaFilaFilaFilaFilaFilaFilaFila</data>
            <data>$1000000</data>
            <data>${(1234567).toString().length > 7 ? (1234567).toExponential(2) : 1234567 }</data>

            <data>10</data>
            <data>Fila</data>
            <data>$10</data>
            <data>$100</data>

                    
        </div>

        <div id="doc-footer" >
            <FlexDiv >
                <DataBox title="subtotal" value={"1000"} />
                <DataBox title="IVA 21%" value={"210"} />
                <DataBox title="total neto" value={"1210"} />
            </FlexDiv>

            <FlexDiv justify='space-between' align="flex-start">
                <div>
                    <p>Conjunto Solución © Uso Didáctico</p>
                    <div className="doc-footer-copies">original ⬜ duplicado ⬜ triplicado ⬜</div>
                </div>

                <DataBox title="son pesos" value={numberToWords(99999999).toLowerCase()} />
            </FlexDiv>
              
            
        </div>

    </div>)
}