import React, { useState } from "react";
import { BiChevronDown, BiChevronUp } from "react-icons/bi";
import './Retractable.css';
/**
 * Un contenedor con título que puede ser contraído y extendido haciendo click sobre el encabezado.
 * @param props.label El título del contenedor.
 * @param props.initial? El valor boolean inicial. Pasar como false para que esté inicialmente retraído. Por defecto es true: extendido.
 * @param props.sync? Un valor boolean con el cual el elemento debe estar sincronizado. Si es undefined, es ignorado.
 * @param props.onClick? La función a ejecutar en el evento de click. Se pasa como argumento el valor boolean opuesto al valor actual del componente.
 */
export default function Retractable(_a) {
    var children = _a.children, label = _a.label, _b = _a.initial, initial = _b === void 0 ? true : _b, sync = _a.sync, onClick = _a.onClick;
    var _c = useState(initial), localState = _c[0], setLocalState = _c[1];
    var extended = sync === undefined ? localState : sync;
    function change() {
        setLocalState(!extended);
        if (onClick)
            onClick(!extended);
    }
    return (React.createElement("div", { className: extended ? "retractable" : "retractable folded" },
        React.createElement("div", { onClick: change },
            label,
            extended ? React.createElement(BiChevronUp, null) : React.createElement(BiChevronDown, null)),
        React.createElement("div", null, children)));
}
