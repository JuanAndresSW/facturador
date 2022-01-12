import React from "react";
import './Subheader.css';
export default function Subheader() {
    return (React.createElement("div", { className: "subheader" },
        React.createElement("p", null, "activos: $000"),
        React.createElement("p", null, "pasivos: $000"),
        React.createElement("p", null, "patrimonio neto: $000")));
}
