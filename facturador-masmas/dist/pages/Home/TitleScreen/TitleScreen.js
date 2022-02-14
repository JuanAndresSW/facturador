import React, { useState } from "react";
import Const from "utils/Const";
import Session from "utils/Session";
import login from 'services/account/login';
import Valid from "utils/Valid";
import "styles/form.css";
import "./TitleScreen.css";
//objeto de usuario a enviar al servidor
var user = { name: "", password: "" };
export default function TitleScreen() {
    var _a = useState("text"), loginInputType = _a[0], setLoginInputType = _a[1];
    var placeholder = loginInputType === "text" ? "nombre o email" : "contraseña";
    var _b = useState(""), loginValue = _b[0], setLoginValue = _b[1];
    var _c = useState(""), error = _c[0], setError = _c[1];
    var _d = useState(false), disable = _d[0], setDisable = _d[1];
    //validar dato y cambiar el tipo de input
    function checkValidity() {
        if (loginInputType === "text") {
            if (Valid.names(loginValue.trim()) || Valid.email(loginValue.trim())) {
                user.name = loginValue.trim();
                setLoginValue("");
                setLoginInputType("password");
                setError("");
            }
            else
                setError("nombre inválido");
            return;
        }
        if (loginInputType === "password") {
            if (Valid.password(loginValue.trim())) {
                user.password = loginValue.trim();
                authenticate(user);
                setLoginValue("");
                setLoginInputType("text");
            }
            else
                setError("Nombre o contraseña incorrecta");
            setLoginValue("");
            setLoginInputType("text");
        }
    }
    //autenticar el objeto de usuario
    function authenticate(user) {
        login(user.name, user.password, handleResponse);
    }
    function handleResponse(state, data) {
        switch (state) {
            case Const.error:
                setError("No se ha podido establecer la comunicación con el servidor");
                break;
            case Const.ok:
                setError("");
                setDisable(true);
                Session.setSession(JSON.parse(data));
                window.location.reload();
                break;
            case Const.bad:
                setError("Usuario o contraseña incorrecta");
                setDisable(false);
                break;
            case Const.exception:
                setDisable(true);
                setError("Hubo un problema con el servidor");
                break;
            default:
                setError("Hubo un error desconocido al procesar tus datos");
                break;
        }
    }
    return (React.createElement("div", { className: "title-wrapper" },
        React.createElement("h1", null, "M\u00E1s que un facturador"),
        React.createElement("h2", null, "facturador++ fue dise\u00F1ado para facilitar el proceso contable para peque\u00F1as empresas y empresas simuladas."),
        React.createElement("input", { type: loginInputType, name: "name", placeholder: placeholder, value: loginValue, onChange: function (e) {
                setLoginValue(e.target.value);
            }, onKeyPress: function (e) {
                if (e.key === "Enter")
                    checkValidity();
            }, disabled: disable }),
        React.createElement("button", { disabled: disable, type: "button", onClick: function () { return checkValidity(); } }, "Iniciar sesi\u00F3n"),
        React.createElement("p", { className: "message" }, error)));
}
