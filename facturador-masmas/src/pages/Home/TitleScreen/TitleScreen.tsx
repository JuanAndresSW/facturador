import React, { useState } from "react";
import Session from "utils/Session";
import login from 'services/account/login';
import Valid from "utils/Valid";
import "styles/form.css";
import "./TitleScreen.css";
import { useNavigate } from "react-router-dom";

//objeto de usuario a enviar al servidor
const user = { name: "", password: "" };

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
        user.name = loginValue.trim();
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

  //autenticar el objeto de usuario
  function authenticate(user: { name: string; password: string }): void {
    login(user.name, user.password, handleResponse);
  }
  function handleResponse(state:number, data:string) {
    if (state === 200) {
      setError("");
      Session.setSession({token: data, name: "test", passive: "0", active: "0"});
      window.location.reload();
    } else setError(data);
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