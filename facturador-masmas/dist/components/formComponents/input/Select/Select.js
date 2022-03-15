import { Loading } from "components/layout";
import React from "react";
import './Select.css';
/**
 * Un dropdown de selección de opciones.
 * @param label - El título del input.
 * @param fallback - Texto a mostrar cuando hay 0 opciones.
 * @param options - Array de opciones, con un valor clave, un nombre y un tooltip opcional.
 * @param bind - Array desestructurado asociado al valor del input.
 */
export default function Select(_a) {
    var _b = _a.label, label = _b === void 0 ? "" : _b, _c = _a.fallback, fallback = _c === void 0 ? "" : _c, options = _a.options, bind = _a.bind;
    if (options === undefined) {
        return React.createElement(Loading, null);
    }
    if (options.length === 0) {
        return React.createElement("span", null, fallback);
    }
    if (options.length === 1) {
        bind[1](options[0].id);
        return React.createElement("p", null, options[0].value);
    }
    return (React.createElement("label", { className: "select", style: (label.length === 0) ? { margin: "0 auto" } : {} },
        " ",
        label,
        React.createElement("select", { onChange: function (e) { return bind[1](e.target.value); }, value: bind[0] }, options.map(function (option) {
            return React.createElement("option", { key: option.id, title: option.tooltip, value: option.id }, option.value);
        }))));
}
;
