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
  const [inputingPassword, setInputingPassword] = useState(false);
  const [error, setError] = useState("");
  //Valores del formulario.
  const [usernameOrEmail, setUsernameOrEmail] = useState("");
  const [password, setPassword] = useState("");
  
  //Reinicia los valores.
  function reset():void {
    setError("");
    setUsernameOrEmail("");
    setPassword("");
    setInputingPassword(true);
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
  async function send(): Promise<void> {
    setLoading(true);
    const response = await tryLogin(usernameOrEmail, password);
    setLoading(false);
    if (!response.ok) return setError(response.message);
    reset();
  }


  return (
    <div className="title-wrapper">
      <h1>Más que un facturador</h1>
      <h2>
        facturador++ fue diseñado para facilitar el proceso contable de empresas simuladas.
      </h2>

      <input
        type={inputingPassword?"password":"text"}
        placeholder={inputingPassword? "contraseña" : "nombre o email"}
        value=      {inputingPassword? password : usernameOrEmail}
        onChange=   {inputingPassword ?
                    e => setPassword(e.target.value) :
                    e => setUsernameOrEmail(e.target.value)}

        onKeyDown = {inputingPassword ?
          e => { if (e.key === "Enter") validate() } :
          e => { if (e.key === "Enter") {setError(""); setInputingPassword(true)}}
        }
      />

      {loading? <Loading/>:

      <button onClick={inputingPassword ? validate : ()=> {setError(""); setInputingPassword(true)}}>
        {inputingPassword ? 'Iniciar sesión' : "Siguiente"}
      </button>}

      <Message type="error" message={error}/>
    </div>
  );
}