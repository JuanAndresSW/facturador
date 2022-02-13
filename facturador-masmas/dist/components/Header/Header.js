import React from "react";
import { Link } from "react-router-dom";
import Headroom from "react-headroom";
import ProfileMenu from "./ProfileMenu/ProfileMenu";
import Session from "utils/Session";
import logo from "assets/svg/logo.svg";
import "./Header.css";
//El header cuando se no se ha detectado una sesión de usuario.
var notLoggedHeader = (React.createElement(Headroom, null,
    React.createElement("header", null,
        React.createElement(Link, { to: "/", id: "logo" },
            React.createElement("img", { src: logo, alt: "" }),
            React.createElement("p", null, "facturador++")),
        React.createElement("div", { id: "header-links" },
            React.createElement("a", { href: "about:blank", target: "_blank" }, "Aplicaci\u00F3n m\u00F3vil"),
            React.createElement(Link, { to: "/login" }, "Ingresar"),
            React.createElement("button", { type: "button" },
                React.createElement(Link, { to: "/signup" }, "Crea una cuenta"))))));
//El header cuando se ha detectado una sesión de usuario.
var loggedHeader = (React.createElement("header", null,
    React.createElement(Link, { to: "/", id: "logo" },
        React.createElement("img", { src: logo, alt: "" })),
    React.createElement("div", { id: "logged-header" },
        React.createElement(ProfileMenu, null))));
/**
 * Un panel fijado en la parte superior de la pantalla.
 */
export default function Header() {
    return Session.isAuthenticated() ? loggedHeader : notLoggedHeader;
}
