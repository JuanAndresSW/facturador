import React from "react";
type props = {
    flux: string
}
export default function Cheque({flux}:props) {
    return (
    <div>
        Cheque:::: {flux}
    </div>
    )
}