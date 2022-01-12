import React, { useState } from 'react';
import { setCookie } from '../../scripts/cookies';
import './TitleScreen.css';
//objeto de usuario que representa un usuario en la base de datos
var tempUser = {
    name: 'niño',
    password: 'software'
};
//objeto de usuario a enviar al servidor
var user = { name: "", password: "" };
export default function TitleScreen() {
    var _a = useState('text'), loginType = _a[0], setLoginType = _a[1];
    var placeholder = (loginType === 'text') ? 'nombre' : 'contraseña';
    var _b = useState(''), loginValue = _b[0], setLoginValue = _b[1];
    var _c = useState(''), errorMessage = _c[0], setErrorMessage = _c[1];
    //comprobar la validez del valor de login
    function checkValidity() {
        if (loginType === 'text') {
            if (loginValue.length <= 20 && loginValue.length >= 3) {
                user.name = loginValue.trim();
                console.log(loginValue);
                console.log(user.name);
                setLoginValue("");
                setLoginType('password');
                setErrorMessage('');
            }
            else
                setErrorMessage('nombre inválido');
            return;
        }
        if (loginType === 'password') {
            if (loginValue.length <= 30 && loginValue.length >= 8) {
                user.password = loginValue.trim();
                authenticate(user);
                setLoginValue("");
                setLoginType('text');
            }
            else
                setErrorMessage("Nombre o contraseña incorrecta");
            setLoginValue("");
            setLoginType('text');
        }
    }
    //comprobar la autenticidad de los valores de login
    function authenticate(user) {
        //enviar el objeto al servidor, que devuelve una página con un objeto cookie o un mensaje de error
        //por ahora, se establece la cookie localmente (1209600=14d)
        if (user.name === tempUser.name && user.password === tempUser.password) {
            console.log(user === tempUser);
            setCookie(user.name);
            window.location.reload();
            return;
        }
        setErrorMessage("Nombre o contraseña incorrecta");
    }
    return (React.createElement("div", { className: 'title-wrapper' },
        React.createElement("h1", null, "M\u00E1s que un facturador"),
        React.createElement("h2", null, "facturador++ fue dise\u00F1ado para facilitar el proceso contable para peque\u00F1as empresas y empresas simuladas."),
        React.createElement("input", { type: loginType, name: 'name', placeholder: placeholder, value: loginValue, onChange: function (e) { setLoginValue(e.target.value); }, onKeyPress: function (e) { if (e.key === 'Enter')
                checkValidity(); } }),
        React.createElement("button", { type: 'button', onClick: function () { return checkValidity(); } }, "Iniciar sesi\u00F3n"),
        React.createElement("p", { className: 'message' }, errorMessage)));
}
