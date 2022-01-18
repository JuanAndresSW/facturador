import React, { useState } from "react";
import DocumentForm from './DocumentForm/DocumentForm'
import './Transaction.css';

export default function Transaction() {

    const [outlet, setOutlet] = useState(<></>);
    //if an outlet wasn't produced, then the initial options are active
    const disabled = outlet.type === (<></>).type?false:true;

    //returns either an outlet, or the options to select an outlet
    return (
    disabled? outlet:

    <div id="transaction-options">
        <p>Nueva transacción</p>
        <div onMouseDown={()=>setOutlet(transactionTypes("out"))}>Enviar</div>
        <div onMouseDown={()=>setOutlet(transactionTypes("in"))}>Recibir</div>
        <p>Historial de transacciones</p>
        <div onMouseDown={()=>setOutlet(<p>History</p>)}>Ver o continuar con una transacción</div>
    </div>
    )

    function transactionTypes(flux:string):JSX.Element {
        return (
            <div id="transaction-options">
                <div onMouseDown={()=>setOutlet(<DocumentForm type="factura" flux={flux} />)}> Factura </div>
                <div onMouseDown={()=>setOutlet(<DocumentForm type="nota-de-credito" flux={flux} />)}> Nota de crédito </div>
                <div onMouseDown={()=>setOutlet(<DocumentForm type="nota-de-debito" flux={flux} />)}>Nota de débito </div>
                <div onMouseDown={()=>setOutlet(<DocumentForm type="recibo-x" flux={flux} />)}> Recibo X</div>
                <div onMouseDown={()=>setOutlet(<DocumentForm type="recibo" flux={flux} />)}> Recibo </div>
                <div onMouseDown={()=>setOutlet(<DocumentForm type="orden-de-compra" flux={flux} />)}> Orden de compra </div>
                <div onMouseDown={()=>setOutlet(<DocumentForm type="remito" flux={flux} />)}> Remito </div>
                <div onMouseDown={()=>setOutlet(<DocumentForm type="cheque" flux={flux} />)}> Cheque </div>
                <div onMouseDown={()=>setOutlet(<DocumentForm type="pagare" flux={flux} />)}> Pagaré </div>
                <div onMouseDown={()=>setOutlet(<DocumentForm type="otro" flux={flux} />)}> Otro </div>
            </div>
        )
    }
}
/*  <Factura flux={flux} />
    <NotaDeCredito flux={flux} />
    <NotaDeDebito flux={flux} />
    <Recibo flux={flux} />
    <OrdenDeCompra flux={flux} />
    <Remito flux={flux} />
    <Cheque flux={flux} />
    <Pagare flux={flux} />
    <Otro flux={flux} /> */