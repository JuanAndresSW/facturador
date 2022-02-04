import React from "react";
export default function Observation(_a) {
    var type = _a.type;
    return (type === "purchase-order" || "remittance" || "invoice" || "debit-note" || "credit-note" ?
        React.createElement("label", null,
            "Observaciones",
            React.createElement("textarea", { maxLength: 40 }))
        :
            React.createElement(React.Fragment, null));
}
