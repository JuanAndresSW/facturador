import React from "react";
import './DateInput.css';
/**
 * Un campo de escritura.
 * @param label - El título del input.
 * @param note - Nota extra acerca del input.
 * @param nonPast - Si no debe aceptar valores anteriores a la fecha actual.
 * @param value - Array desestructurado asociado al valor del input.
 * @param onChange - Función que manejará el valor en el envento de un cambio.
 */
export default function DateInput(_a) {
    var _b = _a.label, label = _b === void 0 ? "" : _b, note = _a.note, value = _a.value, onChange = _a.onChange, _c = _a.nonPast, nonPast = _c === void 0 ? false : _c;
    var date = new Date();
    var minDate = ("".concat(date.getFullYear(), "-").concat(date.getMonth() + 1, "-").concat(date.getDate()));
    var change = function (value) {
        if (nonPast && Date.parse(value) >= Date.parse(minDate))
            onChange(value);
    };
    return (React.createElement("label", null, " ", label, React.createElement("span", null, " ", note), React.createElement("input", { min: minDate, type: "date", value: value, onChange: function (e) { return change(e.target.value); } })));
}
;
