import React, { useState } from "react";
import DocData from '../../../script/DocData';
import './Type.css';

type props = {
    type: string
}

//return options for document type A, B, C. (A and B) and C are exclusive
export default function PurchaseExtra({ type }: props): JSX.Element {
    const [disabled, setDisabled] = useState(true);
    const [customCondition, setCustomCondition] = useState("");

    return (
        type === "invoice" || "credit-note" || "debit-note" ?
            <div>
                {DocData.allowAB ? (
                    <>
                        <div className="option"></div>
                        <div className="option"></div>
                    </>
                )
                    : <></>}
            </div>
            : <></>
    )
}