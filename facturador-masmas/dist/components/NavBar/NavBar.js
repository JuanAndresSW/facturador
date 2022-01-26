import React from "react";
import { NavLink } from "react-router-dom";
import Protected from "../../script/Protected";
import "./NavBar.css";
export default function Main(_a) {
    var children = _a.children;
    return (React.createElement(Protected, null,
        React.createElement("ul", { className: "tab-set" }, children.map(function (tab) { return (React.createElement(NavLink, { key: tab.props.accessKey, to: tab.props.accessKey }, tab.props.tabHeader)); }))));
}
