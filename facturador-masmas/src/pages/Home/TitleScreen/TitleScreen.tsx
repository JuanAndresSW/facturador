import React, { useState } from "react";
import Session from "services/Session";
import Valid from "utils/Valid";
import "./TitleScreen.css";
import { useNavigate } from "react-router-dom";

//objeto de usuario a enviar al servidor
const user = { usernameOrEmail: "", password: "" };

export default function TitleScreen(): JSX.Element {

  //Objeto de navegación de rutas;
  const navigate = useNavigate();


  const [loginInputType, setLoginInputType] = useState("text");
  let placeholder = loginInputType === "text" ? "nombre o email" : "contraseña";
  const [loginValue, setLoginValue] = useState("");
  const [error, setError] = useState("");
  const [disable, setDisable] = useState(false);

  //validar dato y cambiar el tipo de input
  function checkValidity(): void {
    if (loginInputType === "text") {
      if (Valid.names(loginValue.trim()) || Valid.email(loginValue.trim())) {
        user.usernameOrEmail = loginValue.trim();
        setLoginValue("");
        setLoginInputType("password");
        setError("");
      } else setError("nombre inválido");
      return;
    }
    if (loginInputType === "password") {
      if (Valid.password(loginValue.trim())) {
        user.password = loginValue.trim();

        //Verificar con el servidor la autenticidad;
        authenticate(user);

        setLoginValue("");
        setLoginInputType("text");
      } else setError("Nombre o contraseña incorrecta");
      setLoginValue("");
      setLoginInputType("text");
    }
  }

  //Autenticar el objeto de usuario.
  function authenticate(user: { usernameOrEmail: string; password: string }): void {
    Session.getByCredentials(user.usernameOrEmail, user.password, handleResponse);
  }
  function handleResponse(state:number, data:string) {
    if (state === 200) {
      console.log("data: "+data);
      setError("");
      window.location.reload();
    } else setError(data); console.log("data: "+data);
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
        name="name"
        placeholder={placeholder}
        value={loginValue}
        onChange={(e) => {
          setLoginValue(e.target.value);
        }}
        onKeyPress={(e) => {
          if (e.key === "Enter") checkValidity();
        }}
        disabled={disable}
      ></input>
      <button disabled={disable} type="button" onClick={() => checkValidity()}>
        Iniciar sesión
      </button>

      <p className="message">{error}</p>
    </div>
  );
}