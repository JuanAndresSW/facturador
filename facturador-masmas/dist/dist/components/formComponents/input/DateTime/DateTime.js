import React from "react";
import './DateTime.css';
/**
 * Un campo de escritura.
 * @param label - El título del input.
 * @param type - Tipo de input temporal.
 * @param nonPast - Si no debe aceptar valores anteriores a la fecha actual.
 * @param value - Array desestructurado asociado al valor del input.
 * @param onChange - Función que manejará el valor en el envento de un cambio.
 */
export default function DateTime(_a) {
    var _b = _a.label, label = _b === void 0 ? "" : _b, _c = _a.type, type = _c === void 0 ? "date" : _c, value = _a.value, onChange = _a.onChange, _d = _a.nonPast, nonPast = _d === void 0 ? false : _d;
    var date = new Date();
    var minDate = ("".concat(date.getFullYear(), "-").concat(date.getMonth() + 1, "-").concat(date.getDate()));
    var change = function (value) {
        if (nonPast && Date.parse(value) >= Date.parse(minDate))
            onChange(value);
    };
    return (React.createElement("label", null, " ", label, React.createElement("input", { className: "datetime", min: minDate, type: type, value: value, onChange: function (e) { return change(e.target.value); } })));
}
;
