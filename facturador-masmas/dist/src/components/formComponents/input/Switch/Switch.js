import React from "react";
import './Switch.css';
/**
 * Un interruptor ligado a un boolean declarado con setState. El valor izquierdo es falso, el derecho verdadero.
 * @param label - El título del input.
 * @param note - Nota extra acerca del input.
 * @param falseIcon - Elemento SVG a mostrar para el valor falso.
 * @param trueIcon - Elemento SVG a mostrar para el valor verdadero.
 * @param bind - Array desestructurado asociado al valor del input.
 */
export default function Switch(_a) {
    var _b = _a.label, label = _b === void 0 ? "" : _b, note = _a.note, falseIcon = _a.falseIcon, trueIcon = _a.trueIcon, bind = _a.bind;
    return (React.createElement("label", { className: "select", style: (label.length === 0) ? { margin: "0 auto" } : {} },
        " ",
        label,
        " ",
        React.createElement("span", null,
            " ",
            note),
        React.createElement("div", { className: bind[0] ? "switch switch-true" : "switch switch-false", onClick: function () { return bind[1](!bind[0]); } },
            falseIcon,
            trueIcon)));
}
;
