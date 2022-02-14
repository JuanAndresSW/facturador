import React, { useState } from "react";
import Valid from "utils/Valid";
import login from "services/account/login";
import Session from "utils/Session";
import Const from "utils/Const";
import "styles/form.css";
import { useNavigate } from "react-router-dom";

/**Devuelve un formulario para iniciar sesión.*/
export default function Login() {
  const navigate = useNavigate();

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
    login(user, password, handleResponse)
  }

  function handleResponse(state:number, data:string) {
    switch (state) {
      case Const.error:
        setError("No se ha podido establecer la comunicación con el servidor");
        break;
      case Const.ok:
        setError("");
        Session.setSession({token: data, name: "test", passive: "0", active: "0"});
        navigate("/");
        break;
      default:
        setError(data);
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
