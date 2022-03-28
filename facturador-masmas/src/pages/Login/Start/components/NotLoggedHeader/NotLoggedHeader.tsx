import React from "react";
import Headroom from "react-headroom";
import { Link } from "react-router-dom";
import logo from "assets/svg/logo.svg";
import './NotLoggedHeader.css';

/**
 * Un panel fijado en la parte superior de la pantalla.
 * El header cuando se no se ha detectado una sesión de usuario.
 */
export default function NotLoggedHeader(): JSX.Element {
  
return (
  
  <header>

    <Link to="/" id="logo">
      <img src={logo} alt="" />
      <p>facturador++</p>
    </Link>
    
    <div id="header-links">
      <a href="about:blank" target="_blank">Aplicación móvil</a>
      <Link to={"/ingresar"}>Ingresar</Link>
      <Headroom>
      <button type="button"><Link to={"/registrarse"}>Crea una cuenta</Link></button>
      </Headroom>
    </div>

  </header>

);
}
