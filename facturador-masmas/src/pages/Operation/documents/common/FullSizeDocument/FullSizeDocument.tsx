import React from "react";
import "./FullSizeDocument.css";

type props = {
    children: React.ReactNode
}

export default function FullSizeDocument({children}: props): JSX.Element {
    return <div className="full-size-document">

    <div>
        <div></div>     <div></div>
    </div>

    <div></div>

    <div></div>

    <div></div>


    </div>
}