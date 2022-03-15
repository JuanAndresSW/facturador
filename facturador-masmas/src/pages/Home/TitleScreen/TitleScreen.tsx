import React, { useState } from "react";
import Session from "services/Session";
import Valid from "utils/Valid";
import "./TitleScreen.css";
import { ErrorMessage } from "components/formComponents";

export default function TitleScreen(): JSX.Element {

  //Controladores del formulario.
  const [loading, setLoading] = useState(false);
  const [loginInputType, setLoginInputType] = useState("text");
  const [error, setError] = useState("");
  //Valores del formulario.
  const [usernameOrEmail, setUsernameOrEmail] = useState("");
  const [password, setPassword] = useState("");
  
  //Reinicia los valores.
  function reset():void {
    setError("");
    setUsernameOrEmail("");
    setPassword("");
    setLoginInputType("text");
  }

  //Validar los datos. Si son validados, se envían al servidor.
  function validate(): void {
    if ((Valid.names(usernameOrEmail) || Valid.email(usernameOrEmail)) && Valid.password(password)) {
      reset();
      send();
      return;
    }
    reset();
    setError("Nombre o contraseña incorrecta");
  }

  //Envía los datos de usuario al servidor.
  function send():void {
    setLoading(true);
    Session.getByCredentials(usernameOrEmail, password, handleResponse);
  }
    
  //Maneja la respuesta del servidor.
  function handleResponse(state:number, data:string):void {
    setLoading(false);
    if (state === 200) {
      reset();
      Session.setSession(JSON.parse(data));
      window.location.reload();
      return;
    }
    if (state === 404) {
      setError("Usuario o contraseña incorrecta");
      return;
    }
    reset();
    setError(data);
  }

  return (
    <div className="title-wrapper">
      <h1>Más que un facturador</h1>
      <h2>
        facturador++ fue diseñado para facilitar el proceso contable para
        pequeñas empresas y empresas simuladas.
      </h2>

      <input
        type={loginInputType}
        placeholder={loginInputType === "text" ? "nombre o email"   : "contraseña"}
        value=      {loginInputType === "text" ? usernameOrEmail    : password}
        onChange=   {loginInputType === "text" ? 
        e =>        setUsernameOrEmail(e.target.value.trim())       : e=> setPassword(e.target.value.trim())}
        onKeyPress= {loginInputType === "text" ?
        e => { if (e.key === "Enter") {setError(""); setLoginInputType("password")}}:
        e => { if (e.key === "Enter") validate() }}
      />

      <button disabled={loading} 
      onClick={loginInputType === "text" ?
      ()=> {setError(""); setLoginInputType("password")} : validate }>
      Iniciar sesión
      </button>

      <ErrorMessage message={error}/>
    </div>
  );
}