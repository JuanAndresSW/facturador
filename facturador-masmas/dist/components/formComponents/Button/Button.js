import React from "react";
import './Button.css';
export default function Button(_a) {
    var text = _a.text, _b = _a.type, type = _b === void 0 ? "button" : _b, onClick = _a.onClick;
    return (React.createElement("button", { type: type === "submit" ? "submit" : "button", className: type === "delete" ? "button delete" : "button", onClick: function () { return onClick; } }, text));
}
