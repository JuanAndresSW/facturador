import React from "react";
import './Retractable.css';
export default function Retractable(_a) {
    var children = _a.children, tabHeader = _a.tabHeader, active = _a.active;
    return (React.createElement("div", null,
        React.createElement("div", { className: "section-header" }, tabHeader),
        React.createElement("div", { className: active ? "" : "disable" }, children)));
}
