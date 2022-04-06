import React, { useState } from "react";
import { Link } from "react-router-dom";

//Servicios.
import tryLogin from "./services/tryLogin";

//Utilidades.
import Valid from "utilities/Valid";

//Componentes de formulario.
import {Form, Field, Message, Button} from 'components/formComponents';
import { BiKey, BiUser } from "react-icons/bi";
import { FlexDiv, Loading } from "styledComponents";

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
    setLoading(false);
    if (!ok) return setError(error);
    setError("");
  }

  return (
    <Form onSubmit={submit} title="Iniciar sesión">

      <Field icon={<BiUser />} label="Nombre o correo electrónico" bind={[usernameOrEmail, setUser]} />
      <Field icon={<BiKey />} label="Contraseña" type="password" bind={[password, setPassword]} />

      <Message type="error" message={error} />

      {loading?<Loading />:<Button type="submit" text="Ingresar" />}

      <FlexDiv justify='space-between'>
        <a href="about:blank" target="_blank" 
        style={{margin:'1rem 0'}}>Olvidé mi contraseña</a>

        <Link to="/registrarse" style={{ margin: '1rem 0'}}>
          Crea una nueva cuenta</Link>
        </FlexDiv>
      

    </Form>
  );
}