import React from "react";
type props = {
    flux: string
}
export default function Receipt({flux}:props) {
    return (
    <div>
        recibo:::: {flux}
    </div>
    )
}