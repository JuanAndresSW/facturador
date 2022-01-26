import React from "react";
import SelectPartakers from '../../../FormElements/SelectPartakers/SelectPartakers';
import ProductTable from '../../../FormElements/ProductTable/ProductTable';
import Observations from '../../../FormElements/Observations/Observations';
import PurchaseExtra from '../../../FormElements/PurchaseExtra/PurchaseExtra';
import Type from '../../../FormElements/Type/Type';
import DocData from '../../../../script/DocData';
import './DocumentForm.css';
//return a form element to gather data needed by the server to generate a new business document
export default function DocumentForm(_a) {
    var flux = _a.flux, type = _a.type;
    DocData.fetchFormData();
    return (React.createElement("form", { id: "form", method: "post", target: "_blank" },
        React.createElement("h1", null, getTitle(type, flux)),
        React.createElement(SelectPartakers, null),
        React.createElement(Type, { type: type }),
        React.createElement(ProductTable, { type: type }),
        React.createElement(Observations, { type: type }),
        React.createElement(PurchaseExtra, { type: type }),
        React.createElement("button", null, "Generar")));
}
//get a proper title for the form
function getTitle(type, flux) {
    var title;
    switch (type) {
        case 'purchase-order':
            title = "Nueva orden de compra ";
            break;
        case 'remit':
            title = "Nuevo remito ";
            break;
        case 'invoice':
            title = "Nueva factura ";
            break;
        case 'debit-note':
            title = "Nueva nota de débito ";
            break;
        case 'credit-note':
            title = "Nueva nota de crédito ";
            break;
        case 'receipt-x':
            title = "Nuevo recibo X ";
            break;
        case 'receipt':
            title = "Nuevo recibo simple ";
            break;
        case 'promissory-note':
            title = "Nuevo pagaré ";
            break;
        case 'check':
            title = "Nuevo cheque ";
            break;
        case 'other':
            title = "Nueva operación ";
            break;
        default: title = 'Algo salió mal ';
    }
    switch (flux) {
        case "in":
            title += "de entrada";
            break;
        case "out":
            title += "de salida";
            break;
        default: return;
    }
    return title;
}
