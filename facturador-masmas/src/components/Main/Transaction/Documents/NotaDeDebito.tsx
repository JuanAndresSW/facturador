import React from "react";
type props = {
    flux: string
}
export default function NotaDeDebito({flux}:props) {
    return (
    <div>
        nota de debito:::: {flux}
    </div>
    )
}