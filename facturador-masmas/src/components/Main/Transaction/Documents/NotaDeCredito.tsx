import React from "react";
type props = {
    flux: string
}
export default function NotaDeCredito({flux}:props) {
    return (
    <div>
        Nota de creadito:::: {flux}
    </div>
    )
}