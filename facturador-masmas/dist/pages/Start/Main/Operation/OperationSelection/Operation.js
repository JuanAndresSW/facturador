import React from "react";
import { BiHistory, BiLogInCircle, BiLogOutCircle, BiTransferAlt, } from "react-icons/bi";
import Documents from "./Documents";
import { NavLink, Route, Routes } from "react-router-dom";
export default function Operation() {
    return (React.createElement(Routes, null,
        React.createElement(Route, { index: true, element: start }),
        React.createElement(Route, { path: "/enviar/*", element: React.createElement(Documents, { flux: "out" }) }),
        React.createElement(Route, { path: "/recibir/*", element: React.createElement(Documents, { flux: "in" }) }),
        React.createElement(Route, { path: "/registro", element: React.createElement(React.Fragment, null, "REGISTRO") }),
        React.createElement(Route, { path: "/historial", element: React.createElement(React.Fragment, null, "HISTORIAL") })));
}
var start = (React.createElement("div", { id: "transaction-options" },
    React.createElement("label", null, "Nuevo documento"),
    React.createElement("div", { className: "section" },
        React.createElement(NavLink, { to: "./enviar", className: "option" },
            React.createElement(BiLogOutCircle, null),
            "Enviar"),
        React.createElement(NavLink, { to: "./recibir", className: "option" },
            React.createElement(BiLogInCircle, null),
            "Recibir")),
    React.createElement("label", null, "Otras operaciones"),
    React.createElement("div", { className: "section" },
        React.createElement(NavLink, { to: "./registro", className: "option" },
            React.createElement(BiTransferAlt, null),
            "Registar un egreso o ingreso"),
        React.createElement(NavLink, { to: "./historial", className: "option" },
            React.createElement(BiHistory, null),
            "Ver o continuar con una transacci\u00F3n"))));
