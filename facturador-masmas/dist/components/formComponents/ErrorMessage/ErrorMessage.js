import React from "react";
import { BiErrorCircle } from "react-icons/bi";
import './ErrorMessage.css';
/**
 * @returns Un mensaje de error sincronizado con 'message', siendo una pieza de estado.
 */
export default function ErrorMessage(_a) {
    var message = _a.message;
    return (React.createElement("p", { className: "error" },
        message.length > 1 ? React.createElement(BiErrorCircle, null) : null,
        message));
}
;
