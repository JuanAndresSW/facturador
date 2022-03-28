import React from "react";
import './Form.css';
/**
 * @returns Un formulario.
 */
export default function Form(_a) {
    var _b = _a.title, title = _b === void 0 ? "" : _b, children = _a.children, onSubmit = _a.onSubmit;
    return (React.createElement("form", { onSubmit: function (e) { e.preventDefault(); onSubmit(); } }, React.createElement("h1", { className: "title" }, title), children));
}
;
