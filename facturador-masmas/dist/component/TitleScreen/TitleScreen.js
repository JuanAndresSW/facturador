import React, { useState } from 'react';
import './TitleScreen.css';
export default function TitleScreen() {
    //objeto de usuario a enviar al servidor
    var user = { name: "", password: "" };
    var _a = useState('text'), currentLoginValue = _a[0], setCurrentLoginValue = _a[1];
    var _b = useState('Nombre'), placeholder = _b[0], setPlaceHolder = _b[1];
    var _c = useState(''), errorMessage = _c[0], setErrorMessage = _c[1];
    //comprobar la validez del valor de login
    function checkValidity() {
        if (currentLoginValue === 'text') {
            setErrorMessage('Todavía no implementado :^)');
            //enviar el nombre al servidor
            //si valida, guardar el nombre y cambiar el estado a password
            //si no valida, cambiar el texto de mensaje
            //luego, enviar a validar $user y redirigir acorde a la respuesta
        }
    }
    return (React.createElement("div", { className: 'title-wrapper' },
        React.createElement("h1", null, "M\u00E1s que un facturador"),
        React.createElement("h2", null, "facturador++ fue dise\u00F1ado para facilitar el proceso contable para peque\u00F1as empresas y empresas simuladas."),
        React.createElement("input", { type: currentLoginValue, name: 'name', placeholder: placeholder }),
        React.createElement("button", { type: 'button', onClick: function () { return checkValidity(); } }, "Iniciar sesi\u00F3n"),
        React.createElement("p", { className: 'message' }, errorMessage)));
}
