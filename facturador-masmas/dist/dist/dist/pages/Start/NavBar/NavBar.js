import React from "react";
import { NavLink } from "react-router-dom";
import "./NavBar.css";
export default function NavBar(_a) {
    var children = _a.children;
    return (React.createElement("ul", { className: "tab-set" }, children.map(function (tab) { return (React.createElement(NavLink, { key: tab.props.accessKey, to: tab.props.accessKey }, tab.props.tabHeader)); })));
}
