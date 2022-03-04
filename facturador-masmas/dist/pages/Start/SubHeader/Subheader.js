import React from "react";
import "./Subheader.css";
export default function Subheader() {
    return (React.createElement("div", { className: "subheader" },
        React.createElement("p", null,
            "activos: ",
            "Session.getActive()"),
        React.createElement("p", null,
            "pasivos: ",
            "Session.getPassive()"),
        React.createElement("p", null,
            "patrimonio neto: ",
            "Session.getNetWorth()")));
}
