import Session from "../../script/Session";
import React from "react";
import "./Subheader.css";
export default function Subheader(): JSX.Element {
  return (
    <div className="subheader">
      <p>activos: ${Session.get("active")}</p>
      <p>pasivos: ${Session.get("passive")}</p>
      <p>
        patrimonio neto: $
        {(parseFloat(Session.get("active")) - parseFloat(Session.get("passive"))).toPrecision(2)}
      </p>
    </div>
  );
}
