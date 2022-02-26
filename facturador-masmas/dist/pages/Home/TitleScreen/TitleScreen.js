import React, { useState } from "react";
import Session from "services/Session";
import Valid from "utils/Valid";
import "styles/form.css";
import "./TitleScreen.css";
import { useNavigate } from "react-router-dom";
//objeto de usuario a enviar al servidor
var user = { usernameOrEmail: "", password: "" };
export default function TitleScreen() {
    //Objeto de navegación de rutas;
    var navigate = useNavigate();
    var _a = useState("text"), loginInputType = _a[0], setLoginInputType = _a[1];
    var placeholder = loginInputType === "text" ? "nombre o email" : "contraseña";
    var _b = useState(""), loginValue = _b[0], setLoginValue = _b[1];
    var _c = useState(""), error = _c[0], setError = _c[1];
    var _d = useState(false), disable = _d[0], setDisable = _d[1];
    //validar dato y cambiar el tipo de input
    function checkValidity() {
        if (loginInputType === "text") {
            if (Valid.names(loginValue.trim()) || Valid.email(loginValue.trim())) {
                user.usernameOrEmail = loginValue.trim();
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
                //Verificar con el servidor la autenticidad;
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
    //Autenticar el objeto de usuario.
    function authenticate(user) {
        Session.getByCredentials(user.usernameOrEmail, user.password, handleResponse);
    }
    function handleResponse(state, data) {
        if (state === 200) {
            setError("");
            window.location.reload();
        }
        else
            setError(data);
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
