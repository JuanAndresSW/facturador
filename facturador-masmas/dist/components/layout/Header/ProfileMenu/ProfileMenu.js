import React, { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";
import { BiChevronDown, BiChevronUp } from 'react-icons/bi';
import OutsideClickHandler from 'react-outside-click-handler';
import Session from "services/Session";
import UserAvatar from 'services/UserAvatar';
import defaultImg from 'assets/img/punto.jpg';
import './ProfileMenu.css';
export default function ProfileMenu() {
    //Imágen de avatar de usuario.
    var _a = useState(defaultImg), img = _a[0], setImg = _a[1];
    //Pedir la imágen en el primer renderizado.
    useEffect(function () {
        UserAvatar.retrieve(function (HTTPState, blob) {
            if (HTTPState === 200)
                setImg(URL.createObjectURL(blob));
        });
    }, []);
    //Navegación al cerrar sesión.
    function logOut() {
        Session.close();
        window.location.reload();
    }
    //Controlador de estado del menú.
    var _b = useState(false), active = _b[0], setActive = _b[1];
    return (React.createElement(OutsideClickHandler, { onOutsideClick: function () { return setActive(false); } },
        React.createElement("div", { id: "profile-menu", onMouseDown: function () { return setActive(!active); } },
            React.createElement("img", { src: img }),
            active ? React.createElement(BiChevronUp, null) : React.createElement(BiChevronDown, null)),
        React.createElement("div", { id: "profile-menu-list", className: active ? 'extended' : '' },
            React.createElement("p", null, sessionStorage.getItem("username") ?
                sessionStorage.getItem("username") + ': '
                : "???"),
            React.createElement("ul", null,
                React.createElement("li", null,
                    React.createElement(NavLink, { to: "/cuenta" }, "Configuraci\u00F3n")),
                React.createElement("li", { onMouseDown: function () { return logOut(); } }, "Cerrar sesi\u00F3n")))));
}
