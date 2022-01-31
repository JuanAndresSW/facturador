import React, { useState } from "react";

type props = {
    type: string
}

export default function PurchaseExtra({ type }: props): JSX.Element {
    const [disabled, setDisabled] = useState(true);
    const [customCondition, setCustomCondition] = useState("");

    return (
        type === "purchase-order" ?
            <div>
                <label>Vendedor<input type="text" placeholder="vendedor"></input></label>
                <div>
                    <label>Condiciones de venta</label>
                    <label><input value="Cuenta Corriente" type="radio" name="condiciones" id="cond-cc" onChange={()=>setDisabled(true)} />Cuenta Corriente</label>
                    <label><input value="Al contado" type="radio" name="condiciones" id="cond-cont" onChange={()=>setDisabled(true)} />Al contado</label>
                    <label><input value="Pagaré" type="radio" name="condiciones" id="cond-pag" onChange={()=>setDisabled(true)} />Pagaré</label>
                    <label><input value={customCondition} type="radio" name="condiciones" id="cond-otro" onChange={()=>setDisabled(!disabled)} />Otro:</label>
                    <input disabled={disabled} type="text" className="inputControl" maxLength={20} onChange={(e)=>setCustomCondition(e.target.value)} />
                </div>
                <label>Fecha de entrega<input type="date"></input></label>
                <label htmlFor="dispatch-place">Lugar de entrega</label>
                <input type="text" id="dispatch-place" placeholder="lugar de entrega"></input>
                <label htmlFor="carrier">Transportista</label>
                <input type="text" id="carrier" placeholder="transportista"></input>
            </div>
        :<></>
    )
}