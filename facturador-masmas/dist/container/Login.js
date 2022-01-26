import React, { useState } from "react";
import Valid from "../script/Valid";
import Session from "../script/Session";
import "../style/form.css";
import { useNavigate } from "react-router-dom";
//devuelve un formulario para iniciar sesión
export default function LogIn(_a) {
    var _b = _a.origin, origin = _b === void 0 ? "/" : _b;
    var navigate = useNavigate();
    var _c = useState(""), user = _c[0], setUser = _c[1];
    var _d = useState(""), password = _d[0], setPassword = _d[1];
    var _e = useState(""), error = _e[0], setError = _e[1];
    var validate = function () {
        if ((Valid.names(user) || Valid.email(user)) && Valid.password(password))
            authenticate();
        else
            setError("Usuario o contraseña incorrecta");
    };
    var submit = function (e) {
        e.preventDefault();
        validate();
    };
    function authenticate() {
        console.log("%cAuthenticación", "background: linear-gradient(90deg, rgba(131,58,180,1) 0%, rgba(253,29,29,1) 50%, rgba(252,176,69,1) 100%);padding:1rem;display:block;width:min-content;font-size:2rem;text-align:center;border-radius:333px");
        if (Session.authenticate(user, password)) {
            Session.open(user);
            navigate("/");
        }
        else
            setError("Usuario o contraseña incorrecta");
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
        React.createElement("button", { type: "submit", onMouseDown: validate }, "Ingresar")));
}
