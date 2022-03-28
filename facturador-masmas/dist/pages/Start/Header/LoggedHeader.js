import React from "react";
import { Link } from "react-router-dom";
import logo from "assets/svg/logo.svg";
import ProfileMenu from "./ProfileMenu/ProfileMenu";
/**
 * Un panel fijado en la parte superior de la pantalla.
 * El header cuando se ha detectado una sesión de usuario.
 */
export default function LoggedHeader() {
    return (React.createElement("header", null,
        React.createElement(Link, { to: "/", id: "logo" },
            React.createElement("img", { src: logo, alt: "" })),
        React.createElement("div", { id: "logged-header" },
            React.createElement(ProfileMenu, null))));
}
