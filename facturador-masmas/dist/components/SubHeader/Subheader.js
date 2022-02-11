import Session from "../../script/Session";
import React from "react";
import "./Subheader.css";
export default function Subheader() {
    return (React.createElement("div", { className: "subheader" },
        React.createElement("p", null,
            "activos: $",
            Session.get("active")),
        React.createElement("p", null,
            "pasivos: $",
            Session.get("passive")),
        React.createElement("p", null,
            "patrimonio neto: $",
            (parseFloat(Session.get("active")) - parseFloat(Session.get("passive"))).toPrecision(2))));
}
