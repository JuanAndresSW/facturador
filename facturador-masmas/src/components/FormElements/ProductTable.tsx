import React from "react";
import {IoIosAdd} from 'react-icons/io';

type props = {
    type: string
}

export default function ProductTable({type}:props):JSX.Element { 
    return (
        type === ("invoice" || "credit-note" || "debit-note" || "receipt-x" || "remittance") ?
        
        <>
            <div id="products">
                <label>Cantidad</label><label>Detalle</label><label>Precio</label>

                <input name="c1" type="number" className="inputControl" max="999999" />
                <input name="d1" type="text" className="inputControl" maxLength={20} />
                <input name="p1" type="number" className="inputControl" max="999999" />
            </div>
            <div id="btn-add-row"><IoIosAdd /></div>
        </>
        : <></>
    )
}