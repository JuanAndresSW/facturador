import React from "react";
import './Panel.css';
/**
 * Un envoltorio visible con título opcional 'label'.
 */
export default function Panel(_a) {
    var children = _a.children, title = _a.title;
    return (React.createElement("div", { className: "panel" },
        React.createElement("h1", null, title),
        children));
}
