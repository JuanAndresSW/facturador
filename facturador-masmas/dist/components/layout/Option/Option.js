import React from "react";
import { Link } from "react-router-dom";
import './Option.css';
export default function Option(_a) {
    var label = _a.label, icon = _a.icon, link = _a.link;
    return (React.createElement(Link, { to: link, className: "option" },
        icon,
        label));
}