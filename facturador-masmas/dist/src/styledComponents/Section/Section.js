import React from "react";
import './Section.css';
/**
 * Un envoltorio visible con título opcional 'label'.
 */
export default function Section(_a) {
    var children = _a.children, label = _a.label;
    return (React.createElement("div", { className: "section" },
        React.createElement("legend", null, label),
        children));
}
