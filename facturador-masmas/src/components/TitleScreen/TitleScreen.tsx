import React, { useState } from "react";
import Session from "../../script/Session";
import Valid from "../../script/Valid";
import "../../style/form.css";
import "./TitleScreen.css";

//objeto de usuario a enviar al servidor
const user = { name: "", password: "" };

export default function TitleScreen(): JSX.Element {

  const [loginType, setLoginType] = useState("text");
  let placeholder = loginType === "text" ? "nombre o email" : "contraseña";
  const [loginValue, setLoginValue] = useState("");
  const [error, setError] = useState("");
  const [disable, setDisable] = useState(false);

  //validar dato y cambiar el tipo de input
  function checkValidity(): void {
    if (loginType === "text") {
      if (Valid.names(loginValue.trim()) || Valid.email(loginValue.trim())) {
        user.name = loginValue.trim();
        setLoginValue("");
        setLoginType("password");
        setError("");
      } else setError("nombre inválido");
      return;
    }
    if (loginType === "password") {
      if (Valid.password(loginValue.trim())) {
        user.password = loginValue.trim();
        authenticate(user);
        setLoginValue("");
        setLoginType("text");
      } else setError("Nombre o contraseña incorrecta");
      setLoginValue("");
      setLoginType("text");
    }
  }

  //autenticar el objeto de usuario
  function authenticate(user: { name: string; password: string }): void {
    Session.tryStart(user.name, user.password, handleResponse);
  }
  function handleResponse(state:number, data:string) {
    switch (state) {
      case 0:
        setError(
          "No se ha podido establecer la comunicación con el servidor"
        );
        break;
      case 200:
        setError("");
        setDisable(true);
        const usr = JSON.parse(data);
        Session.setSession(usr.code, usr.name, usr.passive, usr.active);
        window.location.reload();
        break;
      case 400:
        setError("Usuario o contraseña incorrecta");
        setDisable(false);
        break;
      case 500:
        setDisable(true);
        setError("Hubo un problema con el servidor");
        break;
      default:
        setError("Hubo un error desconocido al procesar tus datos");
        break;
    }
  }

  return (
    <div className="title-wrapper">
      <h1>Más que un facturador</h1>
      <h2>
        facturador++ fue diseñado para facilitar el proceso contable para
        pequeñas empresas y empresas simuladas.
      </h2>

      <input
        type={loginType}
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