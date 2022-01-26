import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Session from "../../script/Session";
import Valid from "../../script/Valid";
import "../../style/form.css";
import "./TitleScreen.css";

//objeto de usuario a enviar al servidor
const user = { name: "", password: "" };

export default function TitleScreen(): JSX.Element {
  const navigate = useNavigate();

  const [loginType, setLoginType] = useState("text");
  let placeholder = loginType === "text" ? "nombre" : "contraseña";
  const [loginValue, setLoginValue] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [disable, setDisable] = useState(false);

  //validar dato y cambiar el tipo de input
  function checkValidity(): void {
    if (loginType === "text") {
      if (Valid.names(loginValue.trim()) || Valid.email(loginValue.trim())) {
        user.name = loginValue.trim();
        setLoginValue("");
        setLoginType("password");
        setErrorMessage("");
      } else setErrorMessage("nombre inválido");
      return;
    }
    if (loginType === "password") {
      if (Valid.password(loginValue.trim())) {
        user.password = loginValue.trim();
        authenticate(user);
        setLoginValue("");
        setLoginType("text");
      } else setErrorMessage("Nombre o contraseña incorrecta");
      setLoginValue("");
      setLoginType("text");
    }
  }

  //autenticar el objeto de usuario
  function authenticate(user: { name: string; password: string }): void {
    if (Session.authenticate(user.name, user.password)) {
      setDisable(true);
      Session.open(user.name);
      window.location.reload();
    } else setErrorMessage("Nombre o contraseña incorrecta");
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
      <button type="button" onClick={() => checkValidity()}>
        Iniciar sesión
      </button>

      <p className="message">{errorMessage}</p>
    </div>
  );
}