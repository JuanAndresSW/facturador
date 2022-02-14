import React from "react";
import { BiChevronLeft } from "react-icons/bi";
import { NavLink, Route, Routes, useNavigate } from "react-router-dom";
import DocumentForm from "../OperationForm/OperationForm";
export default function Documents(_a) {
    var flux = _a.flux;
    var navigate = useNavigate();
    return (React.createElement(React.Fragment, null,
        React.createElement(BiChevronLeft, { onClick: function () { return navigate(-1); }, className: "back-arrow" }),
        React.createElement(Routes, null,
            React.createElement(Route, { index: true, element: start }),
            React.createElement(Route, { path: "/factura", element: React.createElement(DocumentForm, { flux: flux, type: "invoice" }) }),
            React.createElement(Route, { path: "/nota-de-credito", element: React.createElement(DocumentForm, { flux: flux, type: "credit-note" }) }),
            React.createElement(Route, { path: "/nota-de-debito", element: React.createElement(DocumentForm, { flux: flux, type: "debit-note" }) }),
            React.createElement(Route, { path: "/recibo-x", element: React.createElement(DocumentForm, { flux: flux, type: "receipt-x" }) }),
            React.createElement(Route, { path: "/recibo", element: React.createElement(DocumentForm, { flux: flux, type: "receipt" }) }),
            React.createElement(Route, { path: "/orden-de-compra", element: React.createElement(DocumentForm, { flux: flux, type: "purchase-order" }) }),
            React.createElement(Route, { path: "/remito", element: React.createElement(DocumentForm, { flux: flux, type: "remittance" }) }),
            React.createElement(Route, { path: "/cheque", element: React.createElement(DocumentForm, { flux: flux, type: "check" }) }),
            React.createElement(Route, { path: "/pagare", element: React.createElement(DocumentForm, { flux: flux, type: "promissory-note" }) }))));
}
var start = (React.createElement("div", { id: "transaction-options" },
    React.createElement("div", { className: "section" },
        React.createElement(NavLink, { to: "./factura", className: "option" }, "Factura"),
        React.createElement(NavLink, { to: "./nota-de-credito", className: "option" }, "Nota de cr\u00E9dito"),
        React.createElement(NavLink, { to: "./nota-de-debito", className: "option" }, "Nota de d\u00E9bito"),
        React.createElement(NavLink, { to: "./recibo-x", className: "option" }, "Recibo X"),
        React.createElement(NavLink, { to: "./recibo", className: "option" }, "Recibo"),
        React.createElement(NavLink, { to: "./orden-de-compra", className: "option" }, "Orden de compra"),
        React.createElement(NavLink, { to: "./remito", className: "option" }, "Remito"),
        React.createElement(NavLink, { to: "./cheque", className: "option" }, "Cheque"),
        React.createElement(NavLink, { to: "./pagare", className: "option" }, "Pagar\u00E9"))));
