import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Session from "../../script/Session";
import Valid from "../../script/Valid";
import "../../style/form.css";
import "./TitleScreen.css";
//objeto de usuario a enviar al servidor
var user = { name: "", password: "" };
export default function TitleScreen() {
    var navigate = useNavigate();
    var _a = useState("text"), loginType = _a[0], setLoginType = _a[1];
    var placeholder = loginType === "text" ? "nombre" : "contraseña";
    var _b = useState(""), loginValue = _b[0], setLoginValue = _b[1];
    var _c = useState(""), errorMessage = _c[0], setErrorMessage = _c[1];
    var _d = useState(false), disable = _d[0], setDisable = _d[1];
    //validar dato y cambiar el tipo de input
    function checkValidity() {
        if (loginType === "text") {
            if (Valid.names(loginValue.trim()) || Valid.email(loginValue.trim())) {
                user.name = loginValue.trim();
                setLoginValue("");
                setLoginType("password");
                setErrorMessage("");
            }
            else
                setErrorMessage("nombre inválido");
            return;
        }
        if (loginType === "password") {
            if (Valid.password(loginValue.trim())) {
                user.password = loginValue.trim();
                authenticate(user);
                setLoginValue("");
                setLoginType("text");
            }
            else
                setErrorMessage("Nombre o contraseña incorrecta");
            setLoginValue("");
            setLoginType("text");
        }
    }
    //autenticar el objeto de usuario
    function authenticate(user) {
        if (Session.authenticate(user.name, user.password)) {
            setDisable(true);
            Session.open(user.name);
            window.location.reload();
        }
        else
            setErrorMessage("Nombre o contraseña incorrecta");
    }
    return (React.createElement("div", { className: "title-wrapper" },
        React.createElement("h1", null, "M\u00E1s que un facturador"),
        React.createElement("h2", null, "facturador++ fue dise\u00F1ado para facilitar el proceso contable para peque\u00F1as empresas y empresas simuladas."),
        React.createElement("input", { type: loginType, name: "name", placeholder: placeholder, value: loginValue, onChange: function (e) {
                setLoginValue(e.target.value);
            }, onKeyPress: function (e) {
                if (e.key === "Enter")
                    checkValidity();
            }, disabled: disable }),
        React.createElement("button", { type: "button", onClick: function () { return checkValidity(); } }, "Iniciar sesi\u00F3n"),
        React.createElement("p", { className: "message" }, errorMessage)));
}
