import React from "react";
import './Observations.css';

type props = {
    type:string
}

export default function Observation({type}:props):JSX.Element {
    return (
        type === "purchase-order" || "remittance" || "invoice" || "debit-note" || "credit-note" ?
        <label>Observaciones<textarea maxLength={40}></textarea></label>
        :
        <></>
    )
}