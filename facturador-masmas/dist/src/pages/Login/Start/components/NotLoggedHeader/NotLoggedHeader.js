import React from "react";
import Headroom from "react-headroom";
import { Link } from "react-router-dom";
import logo from "assets/svg/logo.svg";
import './NotLoggedHeader.css';
/**
 * Un panel fijado en la parte superior de la pantalla.
 * El header cuando se no se ha detectado una sesión de usuario.
 */
export default function NotLoggedHeader() {
    return (React.createElement("header", null,
        React.createElement(Link, { to: "/", id: "logo" },
            React.createElement("img", { src: logo, alt: "" }),
            React.createElement("p", null, "facturador++")),
        React.createElement("div", { id: "header-links" },
            React.createElement("a", { href: "about:blank", target: "_blank" }, "Aplicaci\u00F3n m\u00F3vil"),
            React.createElement(Link, { to: "/ingresar" }, "Ingresar"),
            React.createElement(Headroom, null,
                React.createElement("button", { type: "button" },
                    React.createElement(Link, { to: "/registrarse" }, "Crea una cuenta"))))));
}
