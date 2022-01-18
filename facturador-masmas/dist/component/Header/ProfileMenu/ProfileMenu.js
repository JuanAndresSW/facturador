import React, { useRef, useState } from "react";
import { BiChevronDown, BiChevronUp } from 'react-icons/bi';
import { clearCookies, getCookie } from "../../../script/cookies";
import OutsideClickHandler from 'react-outside-click-handler';
import './ProfileMenu.css';
import profilePicture from '../../../asset/vector/solucion.svg';
export default function ProfileMenu() {
    var _a = useState(false), active = _a[0], setActive = _a[1];
    var wrapperRef = useRef(null);
    return (React.createElement(React.Fragment, null,
        React.createElement(OutsideClickHandler, { onOutsideClick: function () { return setActive(false); } },
            React.createElement("div", { id: "profile-menu", onMouseDown: function () { return setActive(!active); } },
                React.createElement("img", { src: profilePicture }),
                active ? React.createElement(BiChevronUp, null) : React.createElement(BiChevronDown, null)),
            React.createElement("div", { id: "profile-menu-list", className: active ? 'active' : '', ref: wrapperRef },
                React.createElement("ul", null,
                    React.createElement("li", null, getCookie('username') + ": "),
                    React.createElement("li", null, "Configuraci\u00F3n"),
                    React.createElement("li", { onMouseDown: function () { return logOut(); } }, "Cerrar sesi\u00F3n"))))));
}
function logOut() {
    clearCookies();
    window.location.reload();
}
