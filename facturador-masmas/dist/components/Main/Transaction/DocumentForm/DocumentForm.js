import React from "react";
import SelectPartakers from "../../../FormElements/SelectPartakers";
import ProductTable from "../../../FormElements/ProductTable";
import Observations from "../../../FormElements/Observations";
import PurchaseExtra from "../../../FormElements/PurchaseExtra";
import Type from "../../../FormElements/Type";
import DocData from "../../../../script/DocData";
import "./DocumentForm.css";
//devuelve un formulario que recolecta los datos necesitados por el back-end para generar un documento
export default function DocumentForm(_a) {
    var flux = _a.flux, type = _a.type;
    DocData.fetchFormData();
    return (React.createElement(React.Fragment, null,
        React.createElement("form", { id: "form", method: "post", target: "_blank", className: "panel" },
            React.createElement("h1", { className: "title" }, getTitle(type, flux)),
            React.createElement(SelectPartakers, null),
            React.createElement(Type, { type: type }),
            React.createElement(ProductTable, { type: type }),
            React.createElement(Observations, { type: type }),
            React.createElement(PurchaseExtra, { type: type }),
            React.createElement("button", null, "Generar"))));
}
//encontrar un título apropiado para el formulario
function getTitle(type, flux) {
    var title;
    switch (type) {
        case "purchase-order":
            title = "Nueva orden de compra ";
            break;
        case "remit":
            title = "Nuevo remito ";
            break;
        case "invoice":
            title = "Nueva factura ";
            break;
        case "debit-note":
            title = "Nueva nota de débito ";
            break;
        case "credit-note":
            title = "Nueva nota de crédito ";
            break;
        case "receipt-x":
            title = "Nuevo recibo X ";
            break;
        case "receipt":
            title = "Nuevo recibo simple ";
            break;
        case "promissory-note":
            title = "Nuevo pagaré ";
            break;
        case "check":
            title = "Nuevo cheque ";
            break;
        case "other":
            title = "Nueva operación ";
            break;
        default:
            title = "Algo salió mal ";
    }
    switch (flux) {
        case "in":
            title += "de entrada";
            break;
        case "out":
            title += "de salida";
            break;
        default:
            return;
    }
    return title;
}
