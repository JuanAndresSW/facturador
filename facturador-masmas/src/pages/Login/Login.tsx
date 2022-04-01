import React, { useState } from "react";
import { Link } from "react-router-dom";

//Servicios.
import tryLogin from "./services/tryLogin";

//Utilidades.
import Valid from "utilities/Valid";

//Componentes de formulario.
import {Form, Field, Message, Button} from 'components/formComponents';
import { BiKey, BiUser } from "react-icons/bi";
import { Loading } from "styledComponents";

/**Devuelve un formulario para iniciar sesión.*/
export default function Login(): JSX.Element {

  const [usernameOrEmail, setUser] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  function submit(): void {
    if ((Valid.names(usernameOrEmail) || Valid.email(usernameOrEmail)) && Valid.password(password)) {
      setLoading(true);
      tryLogin(usernameOrEmail, password, sideEffects);
    }
    else setError("Usuario o contraseña incorrecta");
  };

  function sideEffects(ok:boolean, error:string):void {
    if (!ok) return setError(error);
    setError("");
  }

  return (
    <Form onSubmit={submit} title="Iniciar sesión">

      <Field icon={<BiUser />} label="Nombre o correo electrónico" bind={[usernameOrEmail, setUser]} />
      <Field icon={<BiKey />} label="Contraseña" type="password" bind={[password, setPassword]} />

      <Message type="error" message={error} />

      {loading?<Loading />:<Button type="submit" text="Ingresar" />}

      <a href="about:blank" target="_blank" className="link" style={{textDecoration:'none'}}>Olvidé mi contraseña</a>
      <p style={{textAlign:'center', cursor:'default'}}>
        {'¿No tienes una cuenta? '}
        <Link to="registrarse" style={{textDecoration:'none'}}>Crea una nueva</Link>
      </p>

    </Form>
  );
}