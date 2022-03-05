import React from "react";
type props = {
    flux: string
}
export default function DebitNote({flux}:props) {
    return (
    <div>
        nota de debito:::: {flux}
    </div>
    )
}