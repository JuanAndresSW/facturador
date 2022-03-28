import React from "react";
type props = {
    flux: string
}
export default function Check({flux}:props) {
    return (
    <div>
        Cheque:::: {flux}
    </div>
    )
}