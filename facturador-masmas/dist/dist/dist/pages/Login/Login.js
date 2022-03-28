import React, { useState } from "react";
//Servicio.
import Session from "services/Session";
//Utilidades.
import Valid from "utilities/Valid";
//Componentes de formulario.
import { Form, Field, ErrorMessage, Button } from 'components/formComponents';
import { BiKey, BiUser } from "react-icons/bi";
import { Loading } from "styledComponents";
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
            setError("");
            localStorage.clear();
            Session.setSession(JSON.parse(data));
            window.location.reload();
        }
        else
            setError(data);
    }
    return (React.createElement(Form, { onSubmit: submit, title: "Iniciar sesi\u00F3n" }, React.createElement(Field, { icon: React.createElement(BiUser, null), label: "Nombre o correo electr\u00F3nico", bind: [usernameOrEmail, setUser] }), React.createElement(Field, { icon: React.createElement(BiKey, null), label: "Contrase\u00F1a", type: "password", bind: [password, setPassword] }), React.createElement(ErrorMessage, { message: error }), loading ? React.createElement(Loading, null) : React.createElement(Button, { type: "submit", text: "Ingresar" }), React.createElement("a", { href: "about:blank", target: "_blank", className: "link", style: { textDecoration: 'none' } }, "Olvid\u00E9 mi contrase\u00F1a"), React.createElement("p", { style: { textAlign: 'center', cursor: 'default' } }, '¿No tienes una cuenta? ', React.createElement("a", { href: "/registrarse", style: { textDecoration: 'none' } }, "Crea una nueva"))));
}
/**
 * {
 * "username":"test1",
 *
 * "rol":"MAIN",
 *
 * "activos":0,
 *
 * "pasivos":0,
 *
 * "accessToken":
 * "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MSIsImlzcyI6Ii9sb2dpbiIsImV4cCI6M
 * TY0NzM2OTI1OSwiaWF0IjoxNjQ3MzU0ODU5LCJyb2wiOiJNQUlOIn0.Dzg-RSnIRpBpZVhImEqqxesaTRJIOP3ddLL3sGVtYKo",
 *
 * refreshToken":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MSIsImlzcyI6Ii9sb2dpbiIsImV4c
 * CI6MTY0NzYyODQ1OSwiaWF0IjoxNjQ3MzU0ODU5fQ.51FtqSh-8CwiQxvmGNfBUThjo2_Xe3QuJIjauWyT2H8"
 * }
 */
