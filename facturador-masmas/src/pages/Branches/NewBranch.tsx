import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

//Modelos.
import branch from "./models/branch";

//Servicios.
import postBranch from './services/postBranch';

//Utilidades.
import provinces from './utils/provinces';

//GUI.
import defaultLogo from 'assets/svg/default-logo.svg';
import defaultPhoto from 'assets/svg/default-photo.svg';
import { Field, Form, Select, Image, Message, Button, Color } from "components/formComponents";
import { BiChevronLeft } from "react-icons/bi";
import { Loading } from 'components/standalone';
import { FlexDiv, Retractable } from "components/wrappers";
import Valid from "utilities/Valid";



/**Formulario para crear una nueva sucursal. */
export default function NewBranch(): JSX.Element {

  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState(false);


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
  const [photo,      setPhoto] =      useState();
  const [logo,       setLogo] =       useState();
  const [color,      setColor] =      useState("#ffffff");


  //  Controladores de elementos <Retractable/>  //
  const [boolAddress,     setBoolAddress] =     useState(true);
  const [boolContact,     setBoolContact] =     useState(false);
  const [boolPreferences, setBoolPreferences] = useState(false);

  function validate(): void {
    setError(undefined);
    if (!Valid.names(name, setError))            return;
    if (!Valid.address(department))              return setError("El departamento debe ser de entre 4 y 40 caracteres");
    if (!Valid.address(locality))                return setError("La localidad debe ser de entre 4 y 40 caracteres");
    if (!Valid.postalCode(postalCode, setError)) return;
    if (!Valid.address(street))                  return setError("La calle debe ser de entre 4 y 40 caracteres");
    if (!Valid.addressNumber(number, setError))  return;
    if (!Valid.email(email, setError))           return;
    if (!Valid.phone(phone, setError))           return;
    if (!Valid.image(photo, setError))           return;
    if (!Valid.image(logo))                      return setError("El logo no puede superar los 2MB");
    if (!Valid.hexColor(color, setError))        return;
    submit();
  }

  async function submit(): Promise<void> {
    setLoading(true);
    const branch: branch = {
      name: name,
      email: email,
      phone: phone,
      address: {
        province: province,
        department: department,
        locality: locality,
        postalCode: postalCode,
        street: street,
        addressNumber: number,
      },
      logo: logo,
      photo: photo,
      preferenceColor: color
    }
    const response = await postBranch(branch);
      
    setLoading(false);
    if (!response.ok) setError(response.message);
    else setSuccess(true);

  }

  return (
    <Form title="Crea una instalación" onSubmit={validate} >

    <BiChevronLeft onClick={() => navigate(-1)} style={{margin:"1rem", fontSize:"2rem", color:"rgb(44,44,44)",cursor:"pointer"}} />

      <Field label="Nombre comercial" validator={Valid.names(name)} bind={[name, setName]} placeholder={"Entre 3 y 20 caracteres"} />

      <Retractable label="Dirección" sync={boolAddress}
      onClick={(state:boolean)=>{setBoolAddress(state); setBoolContact(false); setBoolPreferences(false);}}>

        <FlexDiv>
          <Select label="Provincia"           value={province} onChange={setProvince}     options={provinces} />
          <Field  label="Departamento"        bind={[department, setDepartment]} validator={Valid.address(department)} />
          <Field  label="Localidad"           bind={[locality, setLocality]}     validator={Valid.address(locality)}   />
          <Field  label="Código postal"       bind={[postalCode, setPostalCode]} type="number" validator={Valid.postalCode(postalCode)} />
          <Field  label="Calle"               bind={[street, setStreet]}         validator={Valid.address(street)}                          />
          <Field  label="Altura " bind={[number, setNumber]}         type="number" validator={Valid.addressNumber(number)} />
        </FlexDiv>

      </Retractable>

      <Retractable label="Información de contacto" sync={boolContact} 
      onClick={(state:boolean)=>{setBoolContact(state); setBoolAddress(false); setBoolPreferences(false);}}>

        <FlexDiv>
          <Field label="Correo electrónico" bind={[email, setEmail]} type="email" validator={Valid.email(email)} />
          <Field label="Número de teléfono" bind={[phone, setPhone]} type="tel"   validator={Valid.phone(phone)}  />
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

      {success?<Message type="success" message={`Se ha creado el punto de venta "${name}"`} />:
      loading?<Loading />:<Button type="submit">Crear</Button>}
    </Form>
  );
}