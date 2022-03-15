import React, { useState } from "react";

//Servicio.
import Session from "services/Session";

//Utilidades.
import Valid from "utils/Valid";

//Componentes de formulario.
import {Form, Field, ErrorMessage, Button} from 'components/formComponents';
import { BiKey, BiUser } from "react-icons/bi";
import { Loading } from "components/layout";


/**Devuelve un formulario para iniciar sesión.*/
export default function Login() {

  const [usernameOrEmail, setUser] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  function submit(): void {
    if ((Valid.names(usernameOrEmail) || Valid.email(usernameOrEmail)) && Valid.password(password)) {
      setLoading(true);
      Session.getByCredentials(usernameOrEmail, password, handleResponse);
    }
    else setError("Usuario o contraseña incorrecta");
  };

  function handleResponse(state:number, data:string):void {
    setLoading(false);
    console.log("LOGIN: "+data);
    if (state === 404) {
      setError("Usuario o contraseña incorrecta");
      return;
    }
    if (state === 200) {
      Session.setSession(JSON.parse(data));
      setError("");
      window.location.reload();
    } else setError(data);
  }

  return (
    <Form onSubmit={submit} title="Iniciar sesión">

      <Field icon={<BiUser />} label="Nombre o correo electrónico" bind={[usernameOrEmail, setUser]} />
      <Field icon={<BiKey />} label="Contraseña" type="password" bind={[password, setPassword]} />

      <ErrorMessage message={error} />

      {loading?<Loading />:<Button type="submit" text="Ingresar" />}

      <a href="about:blank" target="_blank" className="link">Olvidé mi contraseña</a>

    </Form>
  );
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