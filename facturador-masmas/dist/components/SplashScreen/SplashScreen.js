import React from 'react';
import './SplashScreen.css';
/**
 * Una pantalla de carga para cuando no es posible mostrar otro componente.
 */
export default function SplashScreen() {
    return (React.createElement("div", { className: 'fallback' },
        React.createElement("div", null)));
}