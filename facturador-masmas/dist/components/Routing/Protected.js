import React from "react";
import { Navigate } from "react-router-dom";
import Session from "utils/Session";
/**
 * Devuelve elementos hijos o un Navigate segun la sesión.
 * @param children Los elementos protegidos de los usuarios sin sesión.
 * @param reverse Cuando es verdadero, `children` se protege en su lugar de los usuarios con sesión.
 */
export default function Protected(_a) {
    var children = _a.children, _b = _a.reverse, reverse = _b === void 0 ? false : _b;
    return (React.createElement(React.Fragment, null, Session.isAuthenticated() ?
        reverse ? React.createElement(Navigate, { to: "/inicio" }) : children :
        reverse ? children : React.createElement(Navigate, { to: "/inicio" })));
}
