import React from "react";
type props = {
    flux: string
}
export default function Factura({flux}:props) {
    return (
    <form className="big-layout">
        <div>
            <div></div>
            <div></div>
        </div>
        FACTURA:::: {flux}
    </form>
    )
}