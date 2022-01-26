import React, { useState } from "react";
import DocumentForm from './DocumentForm/DocumentForm';
import './Transaction.css';
export default function Transaction() {
    var _a = useState(React.createElement(React.Fragment, null)), outlet = _a[0], setOutlet = _a[1];
    //if an outlet wasn't produced, then the initial options are active
    var disabled = outlet.type === (React.createElement(React.Fragment, null)).type ? false : true;
    //returns either an outlet, or the options to select an outlet
    return (disabled ? outlet :
        React.createElement("div", { id: "transaction-options" },
            React.createElement("p", null, "Nueva transacci\u00F3n"),
            React.createElement("div", { onMouseDown: function () { return setOutlet(transactionTypes("out")); } }, "Enviar"),
            React.createElement("div", { onMouseDown: function () { return setOutlet(transactionTypes("in")); } }, "Recibir"),
            React.createElement("p", null, "Historial de transacciones"),
            React.createElement("div", { onMouseDown: function () { return setOutlet(React.createElement("p", null, "History")); } }, "Ver o continuar con una transacci\u00F3n")));
    function transactionTypes(flux) {
        return (React.createElement("div", null,
            React.createElement("div", { id: "transaction-options" },
                React.createElement("div", { onMouseDown: function () { return setOutlet(React.createElement(DocumentForm, { type: "invoice", flux: flux })); } }, " Factura "),
                React.createElement("div", { onMouseDown: function () { return setOutlet(React.createElement(DocumentForm, { type: "credit-note", flux: flux })); } }, " Nota de cr\u00E9dito "),
                React.createElement("div", { onMouseDown: function () { return setOutlet(React.createElement(DocumentForm, { type: "debit-note", flux: flux })); } }, "Nota de d\u00E9bito "),
                React.createElement("div", { onMouseDown: function () { return setOutlet(React.createElement(DocumentForm, { type: "receipt-x", flux: flux })); } }, " Recibo X"),
                React.createElement("div", { onMouseDown: function () { return setOutlet(React.createElement(DocumentForm, { type: "receipt", flux: flux })); } }, " Recibo "),
                React.createElement("div", { onMouseDown: function () { return setOutlet(React.createElement(DocumentForm, { type: "purchase-order", flux: flux })); } }, " Orden de compra "),
                React.createElement("div", { onMouseDown: function () { return setOutlet(React.createElement(DocumentForm, { type: "remmitance", flux: flux })); } }, " Remito "),
                React.createElement("div", { onMouseDown: function () { return setOutlet(React.createElement(DocumentForm, { type: "check", flux: flux })); } }, " Cheque "),
                React.createElement("div", { onMouseDown: function () { return setOutlet(React.createElement(DocumentForm, { type: "promissory-note", flux: flux })); } }, " Pagar\u00E9 "),
                React.createElement("div", { onMouseDown: function () { return setOutlet(React.createElement(DocumentForm, { type: "other", flux: flux })); } }, " Otro "))));
    }
}
