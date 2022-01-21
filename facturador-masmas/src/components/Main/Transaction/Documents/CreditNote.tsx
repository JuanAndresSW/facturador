import React from "react";
type props = {
    flux: string
}
export default function CreditNote({flux}:props) {
    return (
    <div>
        Nota de credito:::: {flux}
    </div>
    )
}