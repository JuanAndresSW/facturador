import React, { useState } from "react";
//Servicio.
import Session from "services/Session";
//Utilidades.
import Valid from "utils/Valid";
//Componentes de formulario.
import { Form, Field, ErrorMessage, Button } from 'components/formComponents';
import { BiKey, BiUser } from "react-icons/bi";
import { Loading } from "components/layout";
/**Devuelve un formulario para iniciar sesión.*/
export default function Login() {
    var _a = useState(""), usernameOrEmail = _a[0], setUser = _a[1];
    var _b = useState(""), password = _b[0], setPassword = _b[1];
    var _c = useState(""), error = _c[0], setError = _c[1];
    var _d = useState(false), loading = _d[0], setLoading = _d[1];
    function submit() {
        if ((Valid.names(usernameOrEmail) || Valid.email(usernameOrEmail)) && Valid.password(password)) {
            setLoading(true);
            Session.getByCredentials(usernameOrEmail, password, handleResponse);
        }
        else
            setError("Usuario o contraseña incorrecta");
    }
    ;
    function handleResponse(state, data) {
        setLoading(false);
        if (state === 404) {
            setError("Usuario o contraseña incorrecta");
            return;
        }
        if (state === 200) {
            Session.setSession(JSON.parse(data));
            setError("");
            window.location.reload();
        }
        else
            setError(data);
    }
    return (React.createElement(Form, { onSubmit: submit, title: "Iniciar sesi\u00F3n" },
        React.createElement(Field, { icon: React.createElement(BiUser, null), label: "Nombre o correo electr\u00F3nico", bind: [usernameOrEmail, setUser] }),
        React.createElement(Field, { icon: React.createElement(BiKey, null), label: "Contrase\u00F1a", type: "password", bind: [password, setPassword] }),
        React.createElement(ErrorMessage, { message: error }),
        loading ? React.createElement(Loading, null) : React.createElement(Button, { type: "submit", text: "Ingresar" }),
        React.createElement("a", { href: "about:blank", target: "_blank", className: "link" }, "Olvid\u00E9 mi contrase\u00F1a")));
}
