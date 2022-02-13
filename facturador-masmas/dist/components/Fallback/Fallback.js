import React from 'react';
import './Fallback.css';
/**
 * Una pantalla de carga para cuando no es posible mostrar otro componente.
 */
export default function Fallback() {
    return (React.createElement("div", { className: 'fallback' },
        React.createElement("div", null)));
}
