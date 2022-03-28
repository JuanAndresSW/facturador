import React from "react";
import './Field.css';
/**
 * Un campo de escritura.
 * @param props.label - El título del input.
 * @param props.note - Nota extra acerca del input.
 * @param props.type - Un tipo específico de input textual.
 * @param props.bind - Array desestructurado asociado al valor del input.
 */
export default function Field(_a) {
    var _b = _a.label, label = _b === void 0 ? "" : _b, icon = _a.icon, note = _a.note, placeholder = _a.placeholder, _c = _a.type, type = _c === void 0 ? "text" : _c, bind = _a.bind, _d = _a.validator, validator = _d === void 0 ? true : _d;
    return (React.createElement("label", null,
        " ",
        label,
        React.createElement("span", null,
            " ",
            note),
        React.createElement("div", { className: validator ? "field-wrap" : "field-wrap invalid" },
            icon !== undefined ? icon : null,
            React.createElement("input", { className: "field", placeholder: placeholder, type: type, value: bind[0] ? bind[0] : "", onChange: function (e) { return bind[1](e.target.value); } }))));
}
;
