import React, { useState } from "react";
import Session from "services/Session";
import Valid from "utilities/Valid";
import "./TitleScreen.css";
import { ErrorMessage } from "components/formComponents";
import { Loading } from "styledComponents";
export default function TitleScreen() {
    //Controladores del formulario.
    var _a = useState(false), loading = _a[0], setLoading = _a[1];
    var _b = useState("text"), loginInputType = _b[0], setLoginInputType = _b[1];
    var _c = useState(""), error = _c[0], setError = _c[1];
    //Valores del formulario.
    var _d = useState(""), usernameOrEmail = _d[0], setUsernameOrEmail = _d[1];
    var _e = useState(""), password = _e[0], setPassword = _e[1];
    //Reinicia los valores.
    function reset() {
        setError("");
        setUsernameOrEmail("");
        setPassword("");
        setLoginInputType("text");
    }
    //Validar los datos. Si son validados, se envían al servidor.
    function validate() {
        if ((Valid.names(usernameOrEmail) || Valid.email(usernameOrEmail)) && Valid.password(password)) {
            reset();
            send();
            return;
        }
        reset();
        setError("Nombre o contraseña incorrecta");
    }
    //Envía los datos de usuario al servidor.
    function send() {
        setLoading(true);
        Session.getByCredentials(usernameOrEmail, password, handleResponse);
    }
    //Maneja la respuesta del servidor.
    function handleResponse(state, data) {
        setLoading(false);
        if (state === 200) {
            reset();
            localStorage.clear();
            Session.setSession(JSON.parse(data));
            window.location.reload();
            return;
        }
        if (state === 404) {
            setError("Usuario o contraseña incorrecta");
            return;
        }
        reset();
        setError(data);
    }
    return (React.createElement("div", { className: "title-wrapper" }, React.createElement("h1", null, "M\u00E1s que un facturador"), React.createElement("h2", null, "facturador++ fue dise\u00F1ado para facilitar el proceso contable para peque\u00F1as empresas y empresas simuladas."), React.createElement("input", { type: loginInputType, placeholder: loginInputType === "text" ? "nombre o email" : "contraseña", value: loginInputType === "text" ? usernameOrEmail : password, onChange: loginInputType === "text" ?
            function (e) { return setUsernameOrEmail(e.target.value.trim()); } : function (e) { return setPassword(e.target.value.trim()); }, onKeyPress: loginInputType === "text" ?
            function (e) {
                if (e.key === "Enter") {
                    setError("");
                    setLoginInputType("password");
                }
            } :
            function (e) {
                if (e.key === "Enter")
                    validate();
            } }), loading ? React.createElement(Loading, null) :
        React.createElement("button", { onClick: loginInputType === "text" ?
                function () { setError(""); setLoginInputType("password"); } : validate }, "Iniciar sesi\u00F3n"), React.createElement(ErrorMessage, { message: error })));
}
