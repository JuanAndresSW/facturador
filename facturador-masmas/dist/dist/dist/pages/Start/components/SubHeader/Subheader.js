import React from "react";
import "./Subheader.css";
export default function Subheader() {
    return (React.createElement("div", { className: "subheader" }, React.createElement("p", null, "activos: ", sessionStorage.getItem("actives")), React.createElement("p", null, "pasivos: ", sessionStorage.getItem("passives")), React.createElement("p", null, "patrimonio neto: ", parseFloat(sessionStorage.getItem("actives")) +
        parseFloat(sessionStorage.getItem("passives")))));
}
