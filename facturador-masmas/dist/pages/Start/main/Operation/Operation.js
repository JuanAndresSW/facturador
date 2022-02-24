import React from "react";
import { Route, Routes, useNavigate } from "react-router-dom";
import { BiChevronLeft, BiHistory, BiLogInCircle, BiLogOutCircle, BiTransferAlt, } from "react-icons/bi";
import { Section, Option } from 'components/layout';
import DocumentForm from "./OperationForm";
export default function Operation() {
    return (React.createElement(Routes, null,
        React.createElement(Route, { index: true, element: OperationSelectionScreen }),
        React.createElement(Route, { path: "/enviar/*", element: React.createElement(Documents, { flux: "out" }) }),
        React.createElement(Route, { path: "/recibir/*", element: React.createElement(Documents, { flux: "in" }) }),
        React.createElement(Route, { path: "/registro", element: React.createElement(React.Fragment, null, "REGISTRO") }),
        React.createElement(Route, { path: "/historial", element: React.createElement(React.Fragment, null, "HISTORIAL") })));
}
var OperationSelectionScreen = (React.createElement(React.Fragment, null,
    React.createElement(Section, { label: "Nuevo documento" },
        React.createElement(Option, { label: "Enviar", icon: React.createElement(BiLogOutCircle, null), link: "./enviar" }),
        React.createElement(Option, { label: "Recibir", icon: React.createElement(BiLogInCircle, null), link: "./recibir" })),
    React.createElement(Section, { label: "Otras operaciones" },
        React.createElement(Option, { label: "Registar un egreso o ingreso", icon: React.createElement(BiTransferAlt, null), link: "./registro" }),
        React.createElement(Option, { label: "Ver o continuar con una transacci\u00F3n", icon: React.createElement(BiHistory, null), link: "./historial" }))));
//Pantalla de selección de tipos de documentos.
function Documents(_a) {
    var flux = _a.flux;
    var navigate = useNavigate();
    return (React.createElement(React.Fragment, null,
        React.createElement(BiChevronLeft, { onClick: function () { return navigate(-1); }, style: { margin: "1rem", fontSize: "2rem", color: "rgb(44,44,44)", cursor: "pointer" } }),
        React.createElement(Routes, null,
            React.createElement(Route, { index: true, element: documentsSelectionScreen }),
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
var documentsSelectionScreen = (React.createElement(Section, null,
    React.createElement(Option, { label: "Factura", link: "./factura" }),
    React.createElement(Option, { label: "Nota de cr\u00E9dito", link: "./nota-de-credito" }),
    React.createElement(Option, { label: "Nota de d\u00E9bito", link: "./nota-de-debito" }),
    React.createElement(Option, { label: "Recibo X", link: "./recibo-x" }),
    React.createElement(Option, { label: "Recibo", link: "./recibo" }),
    React.createElement(Option, { label: "Orden de compra", link: "./orden-de-compra" }),
    React.createElement(Option, { label: "Remito", link: "./remito" }),
    React.createElement(Option, { label: "Pagar\u00E9", link: "./pagare" }),
    React.createElement(Option, { label: "Cheque", link: "./cheque" })));
