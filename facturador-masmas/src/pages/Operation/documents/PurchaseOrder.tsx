import React from "react";
type props = {
    flux: string
}
export default function PurchaseOrder({flux}:props) {
    return (
    <div>
        OrdenDeCompra:::: {flux}
    </div>
    )
}