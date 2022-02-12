import React, { useState } from "react";
import Valid from "utils/Valid";
import signUp from "services/account/signUp";
import "styles/form.css";
import { useNavigate } from "react-router-dom";
import { BiChevronLeft } from "react-icons/bi";
/**
 * Devuelve un formulario de 2 partes para crear una nueva cuenta y comerciante.
 */
export default function SignUp() {
  const navigate = useNavigate();

  /*DATOS DEL FORMULARIO*****************************************************/

  //Controladores del estado del formulario.
  const [active, setActive] = useState("user");
  const [submitButton, setSubmitButton] = useState("Comprobar");

  //Datos del usuario.
  const [user, setUser] = useState({
    username: "",
    email: "",
    password: "",
    avatar: null,
  });
  const [passwordMatch, setPasswordMatch] = useState("");
  const [userError, setUserError] = useState("");

  //Datos del comerciante.
  const [trader, setTrader] = useState({
    businessName: "",
    vatCategory: "",
    code: "",
    grossIncome: "",
  });
  const [traderError, setTraderError] = useState("");

  /*VALIDACIÓN***************************************************************/

  /**Valida los datos del usuario. */
  const validateUser = (
    e: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ): void => {
    setUserError("");
    e.preventDefault();
    if (!Valid.names(user.username)) {
      setUserError("El nombre debe ser de entre 3 y 20 caracteres");
      return;
    }
    if (!Valid.email(user.email)) {
      setUserError("Ingrese una dirección válida de email");
      return;
    }
    if (!Valid.password(user.password)) {
      setUserError("La contraseña debe ser de entre 8 y 40 caracteres");
      return;
    }
    if (user.password !== passwordMatch) {
      setUserError("Las contraseñas no coinciden");
      return;
    }
    if (!Valid.image(user.avatar)) {
      setUserError("La imágen no debe superar los 2MB");
      return;
    }
    setActive("trader");
  };

  /**Valida los datos del comerciante. */
  const validateTrader = (
    e: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ): void => {
    setTraderError("");
    e.preventDefault();
    if (!Valid.names(trader.businessName)) {
      setTraderError("La razón social debe ser de entre 3 y 20 caracteres");
      return;
    }
    if (!Valid.vatCategory(trader.vatCategory)) {
      setTraderError("Seleccione una categoría");
      return;
    }
    if (!Valid.code(trader.code)) {
      setTraderError(
        `Ingrese un${
          trader.vatCategory === "Monotributista"
            ? " C.U.I.L. válido"
            : "a C.U.I.T válida"
        }`
      );
      return;
    }
    if (!Valid.code(trader.grossIncome)) {
      setTraderError("Ingrese un número de ingresos brutos válido");
      return;
    }

    //Si todo fue validado, se envian los datos.
    submit();
  };

  /*ENVIAR Y RECIBIR*************************************************/

  /**Envía al servidor los datos recolectados. */
  function submit(): void {
    signUp({ user, trader }, handleResponse);
    setSubmitButton("Cargando...");
  }

  /**Maneja la respuesta recibida del servidor. */
  function handleResponse(state: number, data: string) {
    switch (state) {
      case 0: setTraderError("No se ha podido establecer la comunicación con el servidor");
        break;
      case 200: setTraderError("La cuenta se ha creado correctamente");
        console.log(data);
        break;
      case 400: setTraderError("Hubo un error al validar los datos");
        break;
      case 500: setTraderError("Hubo un problema con el servidor");
        break;
      default: setTraderError("Hubo un error desconocido al procesar tus datos");
        break;
    }
  }

  /*FORMULARIO*****************************************************/

  return (
    <form className="panel" method="post">
      {active === "user" ? (
        <>
          <h1 className="title">Datos de la cuenta</h1>
          <label>
            {"¿Cómo quieres que te identifiquemos?"}
            <input
              type="text"
              maxLength={20}
              value={user.username}
              onChange={(e) => setUser({ ...user, username: e.target.value })}
              required
            ></input>
          </label>

          <label>
            {"Tu dirección de correo electrónico"}
            <input
              type="email"
              maxLength={254}
              value={user.email}
              onChange={(e) => setUser({ ...user, email: e.target.value })}
              required
            ></input>
          </label>

          <label>
            {"Elige una contraseña"}
            <input
              type="password"
              maxLength={40}
              value={user.password}
              onChange={(e) => setUser({ ...user, password: e.target.value })}
              required
            />
          </label>

          <label>
            {"Vuelve a escribir la contraseña"}
            <input
              type="password"
              maxLength={128}
              value={passwordMatch}
              onChange={(e) => setPasswordMatch(e.target.value)}
              required
            ></input>
          </label>

          <label>
            {"Foto de perfil"}
            <span> (opcional)</span>
            <input
              type="file"
              accept=".png, .jpeg, .jpg, .svg"
              onChange={(e) => {
                if (e.target.files && e.target.files.length > 0)
                  setUser({
                    ...user,
                    avatar: e.target.files.item(0),
                  });
              }}
            ></input>
          </label>

          <p className="error">{userError}</p>

          <button onClick={(e) => validateUser(e)}>Siguiente</button>
        </>
      ) : /***************************************************************************/

      active === "trader" ? (
        <>
          {submitButton === "Comprobar" ? (
            <BiChevronLeft
              onClick={() => {
                setActive("user");
              }}
            />
          ) : (
            <></>
          )}
          <h1 className="title">Datos del comercio</h1>
          <label>
            {"Escribe tu razón social"}
            <input
              type="text"
              maxLength={20}
              value={trader.businessName}
              onChange={(e) =>
                setTrader({ ...trader, businessName: e.target.value })
              }
              required
            ></input>
          </label>

          <label>
            {"Selecciona una categoría:"}
            <label className="small">
              <input
                type="radio"
                name="vat"
                checked={trader.vatCategory === "Responsable Inscripto"}
                value={"Responsable Inscripto"}
                onChange={(e) =>
                  setTrader({ ...trader, vatCategory: e.target.value })
                }
                required
              />
              Responsable Inscripto
            </label>
            <label className="small">
              <input
                type="radio"
                name="vat"
                checked={trader.vatCategory === "Monotributista"}
                value={"Monotributista"}
                onChange={(e) =>
                  setTrader({ ...trader, vatCategory: e.target.value })
                }
                required
              />
              Responsable Monotributista
            </label>
            <label className="small">
              <input
                type="radio"
                name="vat"
                checked={trader.vatCategory === "Sujeto Exento"}
                value={"Sujeto Exento"}
                onChange={(e) =>
                  setTrader({ ...trader, vatCategory: e.target.value })
                }
                required
              />
              Exento
            </label>
          </label>

          <label>
            {"C.U.I." + (trader.vatCategory === "Monotributista" ? "L." : "T.")}
            <span> (si no eliges uno, se generará uno falso)</span>
            <input
              type="text"
              maxLength={20}
              value={trader.code}
              onChange={(e) => setTrader({ ...trader, code: e.target.value })}
            ></input>
          </label>

          {trader.vatCategory === "Responsable Inscripto" ? (
            <label>
              {"Número de ingresos brutos"}
              <span> (si no eliges uno, se generará uno falso)</span>
              <input
                type="text"
                maxLength={20}
                value={trader.grossIncome}
                onChange={(e) =>
                  setTrader({ ...trader, grossIncome: e.target.value })
                }
              ></input>
            </label>
          ) : (
            <></>
          )}

          <p className="error">{traderError}</p>

          {submitButton === ("Comprobar" || "Cargando...") ? (
            <button
              disabled={submitButton !== "Comprobar"}
              onClick={(e) => validateTrader(e)}
            >
              {submitButton}
            </button>
          ) : (
            <></>
          )}
        </>
      ) : (
        <>????</>
      )}
    </form>
  );
}
