import React from "react";
import { Link } from "react-router-dom";

import Headroom from "react-headroom";
import ProfileMenu from "./ProfileMenu/ProfileMenu";

import Session from "utils/Session";
import logo from "assets/svg/logo.svg";
import "./Header.css";


//El header cuando se no se ha detectado una sesión de usuario.
const notLoggedHeader = (
  <Headroom>
    <header>

      <Link to="/" id="logo">
        <img src={logo} alt="" />
        <p>facturador++</p>
      </Link>
      
      <div id="header-links">
        <a href="about:blank" target="_blank">Aplicación móvil</a>
        <Link to={"/login"}>Ingresar</Link>
        <button type="button"><Link to={"/signup"}>Crea una cuenta</Link></button>
      </div>

    </header>
  </Headroom>
);

//El header cuando se ha detectado una sesión de usuario.
const loggedHeader = (
  <header>
    <Link to="/" id="logo">
      <img src={logo} alt="" />
    </Link>
    
    <div id="logged-header"><ProfileMenu /></div>
  </header>
);

/**
 * Un panel fijado en la parte superior de la pantalla.
 */
export default function Header() {
  return Session.isAuthenticated() ? loggedHeader : notLoggedHeader;
}
