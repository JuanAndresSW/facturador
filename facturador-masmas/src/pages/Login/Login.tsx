import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import login from "services/account/login";

//Utilidades.
import Valid from "utils/Valid";
import Session from "utils/Session";

//Componentes de formulario.
import {Form, Field, ErrorMessage, Submit} from 'components/formComponents';
import { BiKey, BiUser } from "react-icons/bi";


/**Devuelve un formulario para iniciar sesión.*/
export default function Login() {
  const navigate = useNavigate();

  const [user, setUser] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  function submit(): void {
    if ((Valid.names(user) || Valid.email(user)) && Valid.password(password))
      login(user, password, handleResponse)
    else setError("Usuario o contraseña incorrecta");
  };

  function handleResponse(state:number, data:string) {
    if (state === 200) {
      setError("");
      Session.setSession({token: data, name: "test", passive: "0", active: "0"});
      navigate("/");
    } else setError(data);
  }

  return (
    <Form onSubmit={submit} title="Iniciar sesión">

      <Field icon={<BiUser />} label="Nombre o correo electrónico" bind={[user, setUser]} />
      <Field icon={<BiKey />} label="Contraseña" type="password" bind={[password, setPassword]} />

      <ErrorMessage message={error} />

      <Submit text="Ingresar" />

      <a href="about:blank" target="_blank" className="link">Olvidé mi contraseña</a>

    </Form>
  );
}
