import React, { useState } from "react";
import DocData from '../../../script/DocData';
import './Type.css';
//return options for document type A, B, C. (A and B) and C are exclusive
export default function PurchaseExtra(_a) {
    var type = _a.type;
    var _b = useState(true), disabled = _b[0], setDisabled = _b[1];
    var _c = useState(""), customCondition = _c[0], setCustomCondition = _c[1];
    return (type === "invoice" || "credit-note" || "debit-note" ?
        React.createElement("div", null, DocData.allowAB ? (React.createElement(React.Fragment, null,
            React.createElement("div", { className: "option" }),
            React.createElement("div", { className: "option" })))
            : React.createElement(React.Fragment, null))
        : React.createElement(React.Fragment, null));
}
