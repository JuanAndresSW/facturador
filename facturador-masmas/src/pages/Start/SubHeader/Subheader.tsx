import React from "react";
import "./Subheader.css";
export default function Subheader(): JSX.Element {
  return (
    <div className="subheader">
      <p>activos: {sessionStorage.getItem("activos")}</p>
      <p>pasivos: {sessionStorage.getItem("pasivos")}</p>
      <p>{"patrimonio neto: "}
        {parseFloat(sessionStorage.getItem("activos")) +
        parseFloat(sessionStorage.getItem("pasivos"))}</p>
    </div>
  );
}
