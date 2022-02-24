import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import login from "services/account/login";
//Utilidades.
import Valid from "utils/Valid";
import Session from "utils/Session";
//Componentes de formulario.
import { Form, Field, ErrorMessage, Submit } from 'components/formComponents';
import { BiKey, BiUser } from "react-icons/bi";
/**Devuelve un formulario para iniciar sesión.*/
export default function Login() {
    var navigate = useNavigate();
    var _a = useState(""), user = _a[0], setUser = _a[1];
    var _b = useState(""), password = _b[0], setPassword = _b[1];
    var _c = useState(""), error = _c[0], setError = _c[1];
    function submit() {
        if ((Valid.names(user) || Valid.email(user)) && Valid.password(password))
            login(user, password, handleResponse);
        else
            setError("Usuario o contraseña incorrecta");
    }
    ;
    function handleResponse(state, data) {
        if (state === 200) {
            setError("");
            Session.setSession({ token: data, name: "test", passive: "0", active: "0" });
            navigate("/");
        }
        else
            setError(data);
    }
    return (React.createElement(Form, { onSubmit: submit, title: "Iniciar sesi\u00F3n" },
        React.createElement(Field, { icon: React.createElement(BiUser, null), label: "Nombre o correo electr\u00F3nico", bind: [user, setUser] }),
        React.createElement(Field, { icon: React.createElement(BiKey, null), label: "Contrase\u00F1a", type: "password", bind: [password, setPassword] }),
        React.createElement(ErrorMessage, { message: error }),
        React.createElement(Submit, { text: "Ingresar" }),
        React.createElement("a", { href: "about:blank", target: "_blank", className: "link" }, "Olvid\u00E9 mi contrase\u00F1a")));
}
