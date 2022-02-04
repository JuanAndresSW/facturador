import React, { useEffect, useState } from "react";
import Valid from "../script/Valid";
import Session from "../script/Session";
import "../style/form.css";
import { useNavigate } from "react-router-dom";

/**devuelve un formulario para iniciar sesión*/
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
    Session.tryStart(user, password, handleResponse)
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
        const usr = JSON.parse(data);
        Session.setSession(usr.code, usr.name, usr.passive, usr.active);
        navigate("/");
        break;
      case 400:
        setError("Usuario o contraseña incorrecta");
        break;
      case 500:
        setError("Hubo un problema con el servidor");
        break;
      default:
        setError("Hubo un error desconocido al procesar tus datos");
        break;
    }
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
