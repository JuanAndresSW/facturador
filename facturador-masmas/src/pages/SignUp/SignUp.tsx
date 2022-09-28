import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

//Componentes de formulario.
import { Button, Field, Form, Image, Message, Radio } from 'components/formComponents';
import { Loading } from "components/standalone";
import { FlexDiv } from "components/wrappers";
import { BiChevronLeft, BiHome } from "react-icons/bi";

//Relacionado a la cuenta.
import Valid from "utilities/Valid";
import account from './models/account';
import postAccount from "./services/postAccount";


/**Un formulario de 2 partes para crear una nueva cuenta de usuario.*/
export default function SignUp(): JSX.Element {

  const navigate = useNavigate();
  

  //Controladores del estado del formulario.
  const [active, setActive]: 
  [("user"|"trader"), React.Dispatch<React.SetStateAction<("user"|"trader")>>] = useState("user");
  const [sending, setSending] = useState(false);
  const [success, setSuccess] = useState(false);

  /*DATOS DEL FORMULARIO*****************************************************/

  //Datos del usuario.
  const [username, setUsername]           = useState("");
  const [email, setEmail]                 = useState("");
  const [password, setPassword]           = useState("");
  const [avatar, setAvatar]               = useState();
  const [passwordMatch, setPasswordMatch] = useState("");
  const [userError, setUserError]         = useState("");

  //Datos del comerciante.
  const [businessName, setBusinessName]   = useState("");
  const [VATCategory, setVATCategory]     = useState("");
  const [CUIT, setCUIT]                   = useState("");
  const [traderError, setTraderError]     = useState("");


  /*VALIDACIÓN***************************************************************/

  function userIsValid(): boolean {
    setUserError("");

    if (!Valid.names(username, setUserError))     return false;
    if (!Valid.email(email, setUserError))        return false; 
    if (!Valid.password(password, setUserError))  return false;
    if (password !== passwordMatch) {setUserError("Las contraseñas no coinciden"); return false}
    if (!Valid.image(avatar, setUserError))       return false;

    return true;
  };

  function traderIsValid(): boolean {
    setTraderError("");
    if (!Valid.names(businessName)) {setTraderError("La razón social debe ser de entre 3 y 20 caracteres"); return false}
    if (!Valid.vatCategory(VATCategory, setTraderError)) return false;
    if (!Valid.CUIT(CUIT, setTraderError)) return false;
    return true;
  };

  /*ENVIAR Y RECIBIR*************************************************/

  /**Envía al servidor los datos recolectados. */
  async function submit(): Promise<void> {

    const account: account = {
      user: {
        username: username,
        email: email,
        password: password,
        avatar: avatar,
      },
      trader: {
        businessName: businessName,
        VATCategory: VATCategory,
        CUIT: CUIT
      }
    }
    setSending(true);
    
    const response = await postAccount(account);
      
    setSending(false);
    if (!response.ok) return setTraderError(response.message);
    
    setSuccess(true);
    setTraderError("");
    navigate("/inicio");
  }

  /*FORMULARIO*****************************************************/

  return (
    active === "user" ?
    
    <Form title="Datos de la cuenta" onSubmit={()=>{if (userIsValid()) setActive("trader")}}>
      <Link to="/"><BiHome /></Link>
          
      
      <Field label="¿Cómo quieres que te identifiquemos?" 
      bind={[username, setUsername]} validator={Valid.names(username)} />
      <Field label="Tu dirección de correo electrónico"
      bind={[email, setEmail]} validator={Valid.email(email)} />

      <FlexDiv>
        <Field label="Elige una contraseña" 
        bind={[password, setPassword]} type="password" validator={Valid.password(password)} />
        <Field label="Vuelve a escribir la contraseña" bind={[passwordMatch, setPasswordMatch]}
        type="password" validator={password===passwordMatch} />
      </FlexDiv>
      
      <Image label="Foto de perfil" note="(opcional)" setter={setAvatar} img={avatar} />
            
      <Message type="error" message={userError} />

      <FlexDiv justify='space-between'>
        <Link to="/ingresar">Acceder</Link>

        <Button type="submit">Siguiente</Button>
      </FlexDiv>

    </Form>
    :
    active === "trader" ?

    <Form title="Datos del comercio" onSubmit={()=>{if (traderIsValid()) submit()}}>
      {sending? null : <BiChevronLeft onClick={() => setActive("user")} /> }

      <Field label="Escribe tu razón social" bind={[businessName, setBusinessName]}
      validator={Valid.names(businessName)} />

      <Radio legend="Selecciona una categoría:" bind={[VATCategory, setVATCategory]}
      options={["Responsable Inscripto", "Responsable Monotributista"]} />

      <Field label={"C.U.I.T."}
      bind={[CUIT, setCUIT]}
      validator={Valid.CUIT(CUIT)} />

      <Message type="error" message={traderError} />

      {success? <Message type="success" message={`Se ha creado la cuenta "${email}"`}/>:
      sending? <Loading /> : <Button type="submit">Enviar</Button>}
    </Form>
    : null
  );
}