import React from "react";
import { getCookie } from "../../script/cookies";
import './Subheader.css';
export default function Subheader():JSX.Element {
    return (
    <div className="subheader">
        <p>activos: $000</p><p>pasivos: $000</p><p>patrimonio neto: $000</p>
    </div>
    )
}