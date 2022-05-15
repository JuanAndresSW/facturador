import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

//GUI.
import defaultLogo from 'assets/svg/default-logo.svg';
import defaultPhoto from 'assets/svg/default-photo.svg';
import { Field, Form, Select, Image, Message, Button, Color } from "components/formComponents";
import { BiChevronLeft } from "react-icons/bi";
import { Retractable } from 'components/layout';
import { FlexDiv, Loading } from "styledComponents";


//Utilities.
import Valid from "utilities/Valid";
import provinces from "./utils/provinces";

import { branchesContent } from "./models/branches";
import { base64ToBlob } from "utilities/conversions";


type props = {
  branch: branchesContent;
}

/**Formulario para visualizar y cambiar datos de un punto de venta.
 * Precisa del parámetro ID para recuperar los datos del punto específico.*/
export default function PointOptions({branch}:props): JSX.Element {

  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState(false);

  //  Controladores de elementos <Retractable/>  //
  const [boolAddress,     setBoolAddress] =     useState(true);
  const [boolContact,     setBoolContact] =     useState(false);
  const [boolPreferences, setBoolPreferences] = useState(false);

  //  Datos del punto de venta.  //
  const [name,       setName] =       useState();
  //Dirección.
  const [province,   setProvince] =   useState("Buenos Aires");
  const [department, setDepartment] = useState();
  const [locality,   setLocality] =   useState();
  const [postalCode, setPostalCode] = useState();
  const [street,     setStreet] =     useState();
  const [number,     setNumber] =     useState();
  //Contacto.
  const [email,      setEmail] =      useState();
  const [phone,      setPhone] =      useState();
  //Personalización.
  const [photo,      setPhoto] =      useState(null);
  const [logo,       setLogo] =       useState(null);
  const [color,      setColor] =      useState("#ffffff");

  function getLogo(callback: Function) {return ""}


  useEffect(()=>{
    base64ToBlob(branch.logo.logo).then(convertedPhoto=>setPhoto(convertedPhoto));
    getLogo((ok:boolean, content:string) => {
      if (ok) base64ToBlob(content).then(convertedLogo=>setLogo(convertedLogo));
    })
  },[]);

  function validate():void {
    setError(undefined);
    if (!Valid.names(name, setError)) return;
    if (!Valid.address(department)) return setError("El departamento debe ser de entre 4 y 40 caracteres");
    if (!Valid.address(locality)) return setError("La localidad debe ser de entre 4 y 40 caracteres");
    if (!Valid.postalCode(postalCode, setError)) return;
    if (!Valid.address(street)) return setError("La calle debe ser de entre 4 y 40 caracteres");
    if (!Valid.addressNumber(number, setError)) return;
    if (!Valid.email(email, setError)) return;
    if (!Valid.phone(phone, setError)) return;
    if (!Valid.image(photo, setError)) return;
    if (!Valid.image(logo)) return setError("El logo no puede superar los 2MB");
    if (!Valid.hexColor(color, setError)) return;
    submit();
  }

  function submit():void {
    updateBranch();
  }

  return (
    <Form title={"Editando sucursal "+branch.name} onSubmit={validate} >

    <BiChevronLeft onClick={() => navigate(-1)} style={{margin:"1rem", fontSize:"2rem", color:"rgb(44,44,44)",cursor:"pointer"}} />

      <Field label="Nombre de fantasía" validator={Valid.names(name)} 
      bind={[name, setName]} placeholder={branch.name} />

      <Retractable label="Dirección" sync={boolAddress}
      onClick={(state:boolean)=>{setBoolAddress(state); setBoolContact(false); setBoolPreferences(false);}}>

        <FlexDiv>
          <Select label="Provincia"           bind={[province?province:branch.province, setProvince]}     options={provinces} />
          <Field  label="Departamento"        bind={[department, setDepartment]} validator={Valid.names(department)}
          placeholder={branch.department} />
          <Field  label="Localidad"           bind={[locality, setLocality]}     validator={Valid.names(locality)}
          placeholder={branch.locality}   />
          <Field  label="Código postal"       bind={[postalCode, setPostalCode]} type="number" validator={Valid.postalCode(postalCode)}
          placeholder={branch.postalCode} />
          <Field  label="Calle"               bind={[street, setStreet]}         validator={Valid.names(street)}
          placeholder={branch.street}                          />
          <Field  label="Altura" bind={[number, setNumber]}         type="number" validator={Valid.addressNumber(number)}
           placeholder={branch.numberAddress}/>
        </FlexDiv>

      </Retractable>

      <Retractable label="Información de contacto" sync={boolContact} 
      onClick={(state:boolean)=>{setBoolContact(state); setBoolAddress(false); setBoolPreferences(false);}}>

        <FlexDiv>
          <Field label="Correo electrónico" bind={[email, setEmail]} type="email" validator={Valid.email(email)}
          placeholder={branch.email} />
          <Field label="Número de teléfono" bind={[phone, setPhone]} type="tel"   validator={Valid.phone(phone)}
          placeholder={branch.phone}  />
        </FlexDiv>

      </Retractable>

      <Retractable label="Personalización (opcional)" sync={boolPreferences} 
      onClick={(state:boolean)=>{setBoolPreferences(state); setBoolAddress(false); setBoolContact(false);}}>

        <FlexDiv>
          <Image label="Foto del lugar" img={photo} setter={setPhoto} fallback={defaultPhoto} />
          <Image label="Logo" img={logo} setter={setLogo} fallback={defaultLogo} />
        </FlexDiv>
        <Color label="Color de los documentos comerciales" value={color} onChange={setColor} />
        

      </Retractable>


      <Message type="error" message={error} />

      {success?<Message type="success" message={`Se han guardado los datos de "${name}"`} />:
      loading?<Loading />:<Button text="Guardar" type="submit" />}
    </Form>
  );
}