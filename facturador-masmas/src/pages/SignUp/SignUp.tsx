import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

//Componentes de formulario.
import { Form, Field, Image, ErrorMessage, Submit, Radio } from 'components/formComponents';
import { BiAt, BiChevronLeft, BiHash, BiHome, BiIdCard, BiKey, BiText, BiWallet } from "react-icons/bi";

//Relacionado a la cuenta.
import Valid from "utils/Valid";
import MainAccount, {mainAccount} from "services/MainAccount";
import Session from "services/Session";


/**
 * Devuelve un formulario de 2 partes para crear una nueva cuenta y comerciante.
 */
export default function SignUp() {

  const navigate = useNavigate();
  

  //Controladores del estado del formulario.
  const [active, setActive]: 
  [("user"|"trader"), React.Dispatch<React.SetStateAction<("user"|"trader")>>] = useState("user");
  const [sending, setSending] = useState(false);

  /*DATOS DEL FORMULARIO*****************************************************/

  //Datos del usuario.
  const [username, setUsername]           = useState("");
  const [email, setEmail]                 = useState("");
  const [password, setPassword]           = useState("");
  const [avatar, setAvatar]               = useState(null);
  const [passwordMatch, setPasswordMatch] = useState("");
  const [userError, setUserError]         = useState("");

  //Datos del comerciante.
  const [businessName, setBusinessName]   = useState("");
  const [vatCategory, setVatCategory]     = useState("");
  const [code, setCode]                   = useState("");
  const [grossIncome, setGrossIncome]     = useState("");
  const [traderError, setTraderError]     = useState("");


  /*VALIDACIÓN***************************************************************/

  /**Valida los datos del usuario. */
  function validateUser(): void {
    setUserError("");

    if (!Valid.names(username))      { setUserError("El nombre debe ser de entre 3 y 20 caracteres");     return; }
    if (!Valid.email(email))         { setUserError("Ingrese una dirección válida de email");             return; }
    if (!Valid.password(password))   { setUserError("La contraseña debe ser de entre 8 y 40 caracteres"); return; }
    if (password !== passwordMatch)  { setUserError("Las contraseñas no coinciden");                      return; }
    if (!Valid.image(avatar))        { setUserError("La imágen no debe superar los 2MB");                 return; }

    setActive("trader");
  };

  /**Valida los datos del comerciante. */
  function validateTrader(): void {
    setTraderError("");

    if (!Valid.names(businessName))       { setTraderError("La razón social debe ser de entre 3 y 20 caracteres");  return; }
    if (!Valid.vatCategory(vatCategory))  { setTraderError("Seleccione una categoría");                             return; }
    if (!Valid.code(code))                { setTraderError
      (`Ingrese un${vatCategory === "Monotributista"? " C.U.I.L. válido": "a C.U.I.T. válida"}`);                   return; }
    if (!Valid.code(grossIncome))         { setTraderError("Ingrese un número de ingresos brutos válido");          return; }

    //Si todo fue validado, se envían los datos.
    submit();
  };

  /*ENVIAR Y RECIBIR*************************************************/

  /**Envía al servidor los datos recolectados. */
  function submit(): void {
    const account: mainAccount = {
      user: {
        username: username,
        email: email,
        password: password,
        avatar: avatar,
      },
      trader: {
        businessName: businessName,
        vatCategory: vatCategory,
        code: code,
        grossIncome: grossIncome,
      }
    }
    MainAccount.register(account, handleResponse);
    setSending(true);
  }

  /**Maneja la respuesta recibida del servidor. */
  function handleResponse(state: number, data: string) {
    setSending(false);
    if (state === 201) {
      Session.setSession(JSON.parse(data))
      setTraderError("");
      navigate("/inicio");
      window.location.reload();
    } else setTraderError(data);
  }

  /*FORMULARIO*****************************************************/

  return (
    active === "user" ?
    
    <Form title="Datos de la cuenta" onSubmit={validateUser}>
      <Link to="/"><BiHome /></Link>
          
      <Field icon={<BiText />} label="¿Cómo quieres que te identifiquemos?" 
      bind={[username, setUsername]} validator={Valid.names(username)} />
      <Field icon={<BiAt />} label="Tu dirección de correo electrónico"
      bind={[email, setEmail]} validator={Valid.email(email)} />
      <Field icon={<BiKey/>} label="Elige una contraseña" 
      bind={[password, setPassword]} type="password" validator={Valid.password(password)} />
      <Field label="Vuelve a escribir la contraseña" bind={[passwordMatch, setPasswordMatch]}
      type="password" validator={password===passwordMatch} />
      <Image label="Foto de perfil" note="(opcional)" setter={setAvatar} />
            
      <ErrorMessage message={userError} />

      <Submit text="Siguiente" />

    </Form>
    :
    active === "trader" ?

    <Form title="Datos del comercio" onSubmit={validateTrader}>
      {sending? null : <BiChevronLeft onClick={() => setActive("user")} /> }

      <Field icon={<BiIdCard />}label="Escribe tu razón social" bind={[businessName, setBusinessName]}
      validator={Valid.names(businessName)} />

      <Radio legend="Selecciona una categoría:" bind={[vatCategory, setVatCategory]}
      options={["Responsable Inscripto", "Monotributista", "Sujeto Exento"]} />

      <Field label={"C.U.I." + (vatCategory === "Monotributista" ? "L." : "T.")}
      note="(si no eliges uno, se generará uno falso)" bind={[code, setCode]}
      validator={Valid.code(code)} icon={<BiHash />} />

      <Field label="Número de ingresos brutos" note="(si no eliges uno, se generará uno falso)"
      bind={[grossIncome, setGrossIncome]} icon={<BiWallet />} validator={Valid.code(grossIncome)} />

      <ErrorMessage message={traderError} />

      {sending? "Cargando..." : <Submit text="Enviar" />}
    </Form>
    : null
  );
}