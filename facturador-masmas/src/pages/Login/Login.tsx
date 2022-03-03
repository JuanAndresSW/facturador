import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

//Servicio.
import Session from "services/Session";

//Utilidades.
import Valid from "utils/Valid";

//Componentes de formulario.
import {Form, Field, ErrorMessage, Submit} from 'components/formComponents';
import { BiKey, BiUser } from "react-icons/bi";


/**Devuelve un formulario para iniciar sesión.*/
export default function Login() {
  const navigate = useNavigate();

  const [usernameOrEmail, setUser] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  function submit(): void {
    if ((Valid.names(usernameOrEmail) || Valid.email(usernameOrEmail)) && Valid.password(password))
      Session.getByCredentials(usernameOrEmail, password, handleResponse)
    else setError("Usuario o contraseña incorrecta");
  };

  function handleResponse(state:number, data:string):void {
    if (state === 404) {
      setError("Usuario o contraseña incorrecta");
      return;
    }
    if (state === 200) {
      Session.setSession(JSON.parse(data));
      setError("");
      navigate("/");
    } else setError(data);
  }

  return (
    <Form onSubmit={submit} title="Iniciar sesión">

      <Field icon={<BiUser />} label="Nombre o correo electrónico" bind={[usernameOrEmail, setUser]} />
      <Field icon={<BiKey />} label="Contraseña" type="password" bind={[password, setPassword]} />

      <ErrorMessage message={error} />

      <Submit text="Ingresar" />

      <a href="about:blank" target="_blank" className="link">Olvidé mi contraseña</a>

    </Form>
  );
}
