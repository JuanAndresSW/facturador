import React, { useEffect, useState } from "react";
import { BiChevronDown, BiChevronUp, BiUserCircle } from 'react-icons/bi';
import Session from "services/Session";
import UserAvatar from 'services/UserAvatar';
import OutsideClickHandler from 'react-outside-click-handler';
import './ProfileMenu.css';
export default function ProfileMenu() {
    //Imágen de avatar de usuario.
    var _a = useState(undefined), img = _a[0], setImg = _a[1];
    useEffect(function () {
        UserAvatar.getAvatar(handleResponse);
    }, []);
    function handleResponse(state, data) {
        setImg(URL.createObjectURL(new Blob([data])));
    }
    //Navegación al cerrar sesión.
    function logOut() {
        Session.close();
        window.location.reload();
    }
    //Controlador de estado del menú.
    var _b = useState(false), active = _b[0], setActive = _b[1];
    return (React.createElement(OutsideClickHandler, { onOutsideClick: function () { return setActive(false); } },
        React.createElement("div", { id: "profile-menu", onMouseDown: function () { return setActive(!active); } },
            (img === undefined) ? React.createElement(BiUserCircle, null) :
                React.createElement("img", { src: img }),
            active ? React.createElement(BiChevronUp, null) : React.createElement(BiChevronDown, null)),
        React.createElement("div", { id: "profile-menu-list", className: active ? 'active' : '' },
            React.createElement("ul", null,
                React.createElement("li", null, "Session.getUsername()" + ': '),
                React.createElement("li", null, "Configuraci\u00F3n"),
                React.createElement("li", { onMouseDown: function () { return logOut(); } }, "Cerrar sesi\u00F3n")))));
}
