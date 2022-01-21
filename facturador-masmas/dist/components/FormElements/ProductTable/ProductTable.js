import React from "react";
import { IoIosAdd } from 'react-icons/io';
import './ProductTable.css';
export default function ProductTable(_a) {
    var type = _a.type;
    return (type === ("factura" || "nota-de-credito" || "nota-de-debito" || "recibo-x" || "orden-de-compra" || "remito") ?
        React.createElement(React.Fragment, null,
            React.createElement("div", { id: "productos" },
                React.createElement("label", null, "Cantidad"),
                React.createElement("label", null, "Detalle"),
                React.createElement("label", null, "Precio"),
                React.createElement("input", { name: "c1", type: "number", className: "inputControl", max: "999999" }),
                React.createElement("input", { name: "d1", type: "text", className: "inputControl", maxLength: 20 }),
                React.createElement("input", { name: "p1", type: "number", className: "inputControl", max: "999999" })),
            React.createElement("div", { id: "btn-add-row" },
                React.createElement(IoIosAdd, null)))
        : React.createElement(React.Fragment, null));
}
