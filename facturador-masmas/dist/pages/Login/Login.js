import React, { useState } from "react";
import Valid from "utils/Valid";
import login from "services/account/login";
import Session from "utils/Session";
import Const from "utils/Const";
import "styles/form.css";
import { useNavigate } from "react-router-dom";
/**Devuelve un formulario para iniciar sesión.*/
export default function Login() {
    var navigate = useNavigate();
    var _a = useState(""), user = _a[0], setUser = _a[1];
    var _b = useState(""), password = _b[0], setPassword = _b[1];
    var _c = useState(""), error = _c[0], setError = _c[1];
    var submit = function (e) {
        e.preventDefault();
        if ((Valid.names(user) || Valid.email(user)) && Valid.password(password))
            authenticate();
        else
            setError("Usuario o contraseña incorrecta");
    };
    function authenticate() {
        login(user, password, handleResponse);
    }
    function handleResponse(state, data) {
        switch (state) {
            case Const.error:
                setError("No se ha podido establecer la comunicación con el servidor");
                break;
            case Const.ok:
                setError("");
                Session.setSession(JSON.parse(data));
                navigate("/");
                break;
            case Const.bad:
                setError("Usuario o contraseña incorrecta");
                break;
            case Const.exception:
                setError("Hubo un problema con el servidor");
                break;
            default:
                setError("Hubo un error desconocido al procesar tus datos");
                break;
        }
    }
    return (React.createElement("form", { className: "panel", method: "post", onSubmit: function (e) { return submit(e); } },
        React.createElement("h1", { className: "title" }, "Iniciar sesi\u00F3n"),
        React.createElement("label", null,
            " ",
            "Nombre o correo electr\u00F3nico",
            React.createElement("input", { name: "name", type: "text", maxLength: 254, value: user, onChange: function (e) { return setUser(e.target.value); }, required: true })),
        React.createElement("label", null,
            " ",
            "Contrase\u00F1a",
            React.createElement("input", { name: "password", type: "password", maxLength: 128, value: password, onChange: function (e) { return setPassword(e.target.value); }, required: true })),
        React.createElement("a", { href: "about:blank", target: "_blank", className: "special" }, "Olvid\u00E9 mi contrase\u00F1a"),
        React.createElement("p", { className: "error" }, error),
        React.createElement("button", { type: "submit", onMouseDown: function (e) { return submit(e); } }, "Ingresar")));
}
