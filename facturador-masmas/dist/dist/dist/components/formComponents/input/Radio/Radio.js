import React from "react";
import './Radio.css';
/**
 * Un input de tipo radio.
 * @param legend - El título del input.
 * @param note - Nota extra acerca del input.
 * @param options - Array de valores, uno para cada radio.
 * @param bind - Array desestructurado asociado al valor del input.
 */
export default function Radio(_a) {
    var _b = _a.legend, legend = _b === void 0 ? "" : _b, note = _a.note, options = _a.options, bind = _a.bind;
    return (React.createElement(React.Fragment, null, React.createElement("legend", null, legend), React.createElement("span", null, " ", note), options.map(function (option, index) {
        return React.createElement("label", { className: "radio", key: index }, React.createElement("input", { type: "radio", name: legend, checked: option === bind[0], className: option === bind[0] ? "radio-checked" : "", value: option, onChange: function (e) { return bind[1](e.target.value); } }), option);
    })));
}
;
