import React from "react";
import "./Subheader.css";

/**Un encabezado con datos del patrimonio del comerciante. */
export default function Subheader(): JSX.Element {
  return (

    !(sessionStorage.getItem("actives") && sessionStorage.getItem("passives"))? null :
    <div className="subheader">
      <p>activos: {sessionStorage.getItem("actives")}</p>
      <p>pasivos: {sessionStorage.getItem("passives")}</p>
      <p>{"patrimonio neto: "}
        {parseFloat(sessionStorage.getItem("actives")) +
        parseFloat(sessionStorage.getItem("passives"))}</p>
    </div>
  );
}
