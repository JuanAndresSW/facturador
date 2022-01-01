import React, {useState} from 'react';
//import './DocumentWrapper.css';
import isUserAuthenticated from '../../index';
import {Factura, NotaDeCredito, NotaDeDebito, Recibo, OrdenDeCompra, Remito, Cheque,
Pagare, Otro} from './Documents';

export default function DocumentGroup() {
  /* const [currentSocio, setCurrentSocio] = useState({});// (agregar json vacío)
  const [currentDocumento, setCurrentDocumento] = useState(documentos.children[0].label); */

  return (
    <form className='document-group'>
      <div className='document-options'>

        <select>
        {
         /*  documentos.props.children.map(documento => (
            <option key={documento.label} onMouseDown={setCurrentDocumento(documento.label)}>
              {documento.label}
            </option>
          )) */
        }
        </select>
        <label>Emisión<input type='radio' name="flux" value="out"></input></label>
        <label>Recepción<input type='radio' name="flux" value="in"></input></label>
        {//HERE
          //generar opciones de socio si existe una sesión
          () => {if (isUserAuthenticated()) {
            //retrieve socios, =>
            return (
              <select>
                <option selected disabled></option>
                {{/* 
                  socios.map(socio => (
                    <option key={socio.nombre} onMouseDown={setCurrentSocio(socio)}>
                      {socio.nombre}
                    </option>
                  )) */}
                }
              </select>
            )
          }}
        }
      </div>
     
      {
        /* ()=> {for (let documento of documentos.children) {
          if (documento.props.label === currentDocumento) {
            return documento;
          }
        }} */
      }

    </form>
  );
}