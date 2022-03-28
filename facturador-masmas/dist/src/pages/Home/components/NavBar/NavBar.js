import React from "react";
import { NavLink } from "react-router-dom";
import "./NavBar.css";
export default function NavBar(_a) {
    var tabs = _a.tabs;
    return (React.createElement("ul", { className: "tab-set" }, tabs.map(function (tab, index) { return (React.createElement(NavLink, { key: index, to: "." + tab.path },
        tab.icon,
        tab.label)); })));
}
