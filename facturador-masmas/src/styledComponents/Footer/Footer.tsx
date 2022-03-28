import React from "react";
import { Link } from "react-router-dom";
import { DiGithubBadge } from "react-icons/di";
import emblem from 'assets/img/emblem.png';
import "./Footer.css";

/**
 * Una lista de enlaces e información de la aplicación.
 */
export default function Footer() {
  return (
    <div className="footer">
      <div>
        <img src={emblem} alt="" />
        <p>© Conjunto Solución 2022 (GNU V.3)</p>
        <Link to={"/about"}>Acerca de</Link>
      </div>
      <div>
        <a
          href="https://github.com/conjunto-solucion/facturador"
          className="github"
        >
          <DiGithubBadge />
        </a>
        <p>facturador++ versión 0.1.0</p>
      </div>
    </div>
  );
}
