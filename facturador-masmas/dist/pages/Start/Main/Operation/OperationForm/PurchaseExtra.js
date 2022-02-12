import React, { useState } from "react";
export default function PurchaseExtra(_a) {
    var type = _a.type;
    var _b = useState(true), disabled = _b[0], setDisabled = _b[1];
    var _c = useState(""), customCondition = _c[0], setCustomCondition = _c[1];
    return (type === "purchase-order" ?
        React.createElement("div", null,
            React.createElement("label", null,
                "Vendedor",
                React.createElement("input", { type: "text", placeholder: "vendedor" })),
            React.createElement("div", null,
                React.createElement("label", null, "Condiciones de venta"),
                React.createElement("label", null,
                    React.createElement("input", { value: "Cuenta Corriente", type: "radio", name: "condiciones", id: "cond-cc", onChange: function () { return setDisabled(true); } }),
                    "Cuenta Corriente"),
                React.createElement("label", null,
                    React.createElement("input", { value: "Al contado", type: "radio", name: "condiciones", id: "cond-cont", onChange: function () { return setDisabled(true); } }),
                    "Al contado"),
                React.createElement("label", null,
                    React.createElement("input", { value: "Pagar\u00E9", type: "radio", name: "condiciones", id: "cond-pag", onChange: function () { return setDisabled(true); } }),
                    "Pagar\u00E9"),
                React.createElement("label", null,
                    React.createElement("input", { value: customCondition, type: "radio", name: "condiciones", id: "cond-otro", onChange: function () { return setDisabled(!disabled); } }),
                    "Otro:"),
                React.createElement("input", { disabled: disabled, type: "text", className: "inputControl", maxLength: 20, onChange: function (e) { return setCustomCondition(e.target.value); } })),
            React.createElement("label", null,
                "Fecha de entrega",
                React.createElement("input", { type: "date" })),
            React.createElement("label", { htmlFor: "dispatch-place" }, "Lugar de entrega"),
            React.createElement("input", { type: "text", id: "dispatch-place", placeholder: "lugar de entrega" }),
            React.createElement("label", { htmlFor: "carrier" }, "Transportista"),
            React.createElement("input", { type: "text", id: "carrier", placeholder: "transportista" }))
        : React.createElement(React.Fragment, null));
}
