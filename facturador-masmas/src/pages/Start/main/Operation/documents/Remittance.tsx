import React from "react";
type props = {
    flux: string
}
export default function Remittance({flux}:props) {
    return (
    <div>
        Remito:::: {flux}
    </div>
    )
}