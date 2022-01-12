import React from 'react';
import { Link } from 'react-router-dom';
import Headroom from 'react-headroom';
import './Header.css';
import logo from '../../asset/vector/logo.svg';
import { getCookie } from '../../scripts/cookies';
import CommandLine from './CommandLine/CommandLine';
import ProfileMenu from './ProfileMenu/ProfileMenu';
var notLoggedHeader = (React.createElement("div", { id: 'header-links' },
    React.createElement("a", { href: 'about:blank', target: '_blank' }, "Aplicaci\u00F3n m\u00F3vil"),
    React.createElement(Link, { to: '/LogIn', target: '_blank' }, "Ingresar"),
    React.createElement("button", { type: 'button' },
        React.createElement(Link, { to: '/signup' }, "Crea una cuenta"))));
var loggedHeader = (React.createElement("div", { id: 'logged-header' },
    React.createElement(CommandLine, null),
    React.createElement(ProfileMenu, null)));
export default function Header() {
    return (React.createElement(Headroom, null,
        React.createElement("header", null,
            React.createElement("div", { id: "logo" },
                React.createElement("img", { src: logo, alt: 'Facturador++' }),
                React.createElement("p", null, "facturador++")),
            (getCookie('username') === "") ? notLoggedHeader : loggedHeader)));
}
