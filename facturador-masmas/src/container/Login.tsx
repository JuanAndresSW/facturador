import React, { useEffect, useState } from "react";
import Valid from "../script/Valid";
import Session from "../script/Session";
import "../style/form.css";
import { useNavigate } from "react-router-dom";

//devuelve un formulario para iniciar sesión
export default function LogIn() {
  const navigate = useNavigate();

  useEffect(() => {
    //redirigir al inicio si ya existe una sesión
    if (Session.isAuthenticated()) navigate("/");
  }, []);

  const [user, setUser] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const submit = (
    e:
      | React.FormEvent<HTMLFormElement>
      | React.MouseEvent<HTMLButtonElement, MouseEvent>
  ): void => {
    e.preventDefault();
    if ((Valid.names(user) || Valid.email(user)) && Valid.password(password))
      authenticate();
    else setError("Usuario o contraseña incorrecta");
  };

  function authenticate() {
    if (Session.tryStart(user, password)) {
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

      <button type="submit" onMouseDown={(e) => submit(e)}>
        Ingresar
      </button>
    </form>
  );
}
