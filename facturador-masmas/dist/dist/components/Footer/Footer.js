import React from "react";
import { Link } from "react-router-dom";
import { DiGithubBadge } from "react-icons/di";
import "./Footer.css";
/**
 * Una lista de enlaces e información de la aplicación.
 */
export default function Footer() {
    return (React.createElement("div", { className: "footer" }, React.createElement("div", null, React.createElement("p", null, "\u00A9 Conjunto Soluci\u00F3n 2022 (GNU V.3)"), React.createElement(Link, { to: "/about" }, "Acerca de")), React.createElement("div", null, React.createElement("a", { href: "https://github.com/conjunto-solucion/facturador", className: "github" }, React.createElement(DiGithubBadge, null)), React.createElement("p", null, "facturador++ versi\u00F3n 0"))));
}
