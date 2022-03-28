import React from "react";
import './Color.css';
/**
 * Un input de selección de colores hexadecimales.
 */
export default function Image(_a) {
    var label = _a.label, note = _a.note, value = _a.value, onChange = _a.onChange;
    return (React.createElement("label", { className: "color" },
        label,
        React.createElement("span", null,
            " ",
            note),
        React.createElement("p", { style: { textDecoration: 'underline 2px dotted' + value, textUnderlineOffset: '3px', fontSize: 'small' } }, value),
        React.createElement("input", { value: value, type: "color", onChange: function (e) { return onChange(e.target.value); } })));
}
