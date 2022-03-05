import Session from "services/Session";
import React from "react";
import "./Subheader.css";
export default function Subheader(): JSX.Element {
  return (
    <div className="subheader">
      <p>activos: {"Session.getActive()"}</p>
      <p>pasivos: {"Session.getPassive()"}</p>
      <p>patrimonio neto: {"Session.getNetWorth()"}</p>
    </div>
  );
}
