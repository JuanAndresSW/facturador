import React from "react";
import { Link } from "react-router-dom";
import logo from "assets/svg/logo.svg";
import ProfileMenu from "./ProfileMenu/ProfileMenu";
import './LoggedHeader.css';

/**
 * Un panel fijado en la parte superior de la pantalla.
 * El header cuando se ha detectado una sesión de usuario.
 */
export default function LoggedHeader(): JSX.Element {
  
return (
  <header>
    <Link to="/" id="logo">
      <img src={logo} alt="" />
    </Link>
    
    <div id="logged-header"><ProfileMenu /></div>
  </header>
);
}
