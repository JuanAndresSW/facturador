import React, { useState } from "react";
import Valid from "../script/Valid";
import Session from "../script/Session";
import "../style/form.css";
import { useNavigate } from "react-router-dom";

type props = {
  origin?: string;
};

//devuelve un formulario para iniciar sesión
export default function LogIn({ origin = "/" }: props) {
  const navigate = useNavigate();

  const [user, setUser] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const validate = () => {
    if ((Valid.names(user) || Valid.email(user)) && Valid.password(password))
      authenticate();
    else setError("Usuario o contraseña incorrecta");
  };

  const submit = (e: React.FormEvent<HTMLFormElement>): void => {
    e.preventDefault();
    validate();
  };

  function authenticate() {
    console.log(
      "%cAuthenticación",
      "background: linear-gradient(90deg, rgba(131,58,180,1) 0%, rgba(253,29,29,1) 50%, rgba(252,176,69,1) 100%);padding:1rem;display:block;width:min-content;font-size:2rem;text-align:center;border-radius:333px"
    );
    if (Session.authenticate(user, password)) {
      Session.open(user);
      navigate("/");
    } else setError("Usuario o contraseña incorrecta");
  }

  return (
    <form className="panel" method="post" onSubmit={(e) => submit(e)}>
      <h1 className="title">Iniciar sesión</h1>

      <label>
        {" "}
        Nombre o correo electrónico
        <input
          name="name"
          type="text"
          maxLength={254}
          value={user}
          onChange={(e) => setUser(e.target.value)}
          required
        ></input>
      </label>

      <label>
        {" "}
        Contraseña
        <input
          name="password"
          type="password"
          maxLength={128}
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        ></input>
      </label>
      <a href="about:blank" target="_blank" className="special">
        Olvidé mi contraseña
      </a>

      <p className="error">{error}</p>

      <button type="submit" onMouseDown={validate}>
        Ingresar
      </button>
    </form>
  );
}
