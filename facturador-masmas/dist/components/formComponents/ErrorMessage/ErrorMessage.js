import React from "react";
import './ErrorMessage.css';
/**
 * @returns Un mensaje de error sincronizado con 'message', siendo una pieza de estado.
 */
export default function ErrorMessage(_a) {
    var message = _a.message;
    return (React.createElement("p", { className: "error" },
        (message === null || message === void 0 ? void 0 : message.length) > 1 ? "🚫 " : null,
        message));
}
;
