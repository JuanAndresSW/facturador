import React, { useState } from "react";
import tryLogin from "../../../services/tryLogin";
import Valid from "utilities/Valid";
import "./TitleScreen.css";
import { Message } from "components/formComponents";
import { Loading } from "components/standalone";

/**Título de la aplicación con inputs de credenciales para iniciar sesión. */
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
    tryLogin(usernameOrEmail, password, sideEffects);
  }
    
  //Maneja la respuesta del servidor.
  function sideEffects(ok:boolean, error:string):void {
    setLoading(false);
    if (!ok) return setError(error);
    reset();
  }

  return (
    <div className="title-wrapper">
      <h1>Más que un facturador</h1>
      <h2>
        facturador++ fue diseñado para facilitar el proceso contable de empresas simuladas.
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

      {loading? <Loading/>:
      <button
      onClick={loginInputType === "text" ?
      ()=> {setError(""); setLoginInputType("password")} : validate }>
      {loginInputType === "text" ? 'Siguiente':'Iniciar sesión'}
      </button>}

      <Message type="error" message={error}/>
    </div>
  );
}