import React from "react";
import { Link } from "react-router-dom";
import Headroom from "react-headroom";
import "./Header.css";
import logo from "../../asset/svg/logo.svg";
import CommandLine from "./CommandLine/CommandLine";
import ProfileMenu from "./ProfileMenu/ProfileMenu";
import Session from "../../script/Session";

const notLoggedHeader = (
  <div id="header-links">
    <a href="about:blank" target="_blank">
      Aplicación móvil
    </a>
    <Link to={"/login"}>Ingresar</Link>
    <button type="button">
      <Link to={"/signup"}>Crea una cuenta</Link>
    </button>
  </div>
);
const loggedHeader = (
  <div id="logged-header">
    <CommandLine />
    <ProfileMenu />
  </div>
);

export default function Header() {
  return (
    <Headroom>
      <header>
        <Link to="/" id="logo">
          <img src={logo} alt="" />
          <p>facturador++</p>
        </Link>
        {Session.isAuthenticated() ? loggedHeader : notLoggedHeader}
      </header>
    </Headroom>
  );
}
