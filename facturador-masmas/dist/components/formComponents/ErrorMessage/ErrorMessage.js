import React from "react";
import { BiErrorCircle } from "react-icons/bi";
import './ErrorMessage.css';
/**
 * @returns Un formulario.
 */
export default function Form(_a) {
    var message = _a.message;
    return (React.createElement("p", { className: "error" },
        message.length > 1 ? React.createElement(BiErrorCircle, null) : null,
        message));
}
;
