import React, { useEffect, useState } from "react";
import Valid from "../script/Valid";
import Session from "../script/Session";
import "../style/form.css";
import { useNavigate } from "react-router-dom";
/**devuelve un formulario para iniciar sesión*/
export default function LogIn() {
    var navigate = useNavigate();
    useEffect(function () {
        //redirigir al inicio si ya existe una sesión
        if (Session.isAuthenticated())
            navigate("/");
    }, []);
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
        Session.tryStart(user, password, handleResponse);
    }
    function handleResponse(state, data) {
        switch (state) {
            case 0:
                setError("No se ha podido establecer la comunicación con el servidor");
                break;
            case 200:
                setError("");
                var usr = JSON.parse(data);
                Session.setSession(usr.code, usr.name, usr.passive, usr.active);
                navigate("/");
                break;
            case 400:
                setError("Usuario o contraseña incorrecta");
                break;
            case 500:
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
