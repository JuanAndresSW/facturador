import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

//Modelos.
import branch from "./models/branch";

//Servicios.
import postBranch from './services/postBranch';
import getListOfProvinces from './services/public/getListOfProvinces';
import getListOfCitiesByProvinceName from './services/public/getListOfCitiesByProvinceName';

//GUI.
import defaultLogo from 'assets/svg/default-logo.svg';
import defaultPhoto from 'assets/svg/default-photo.svg';
import { Field, Form, Select, Image, Message, Button, Color } from "components/formComponents";
import { BiChevronLeft } from "react-icons/bi";
import { Loading } from 'components/standalone';
import { FlexDiv, Retractable } from "components/wrappers";

//Utilidades.
import Valid from "utilities/Valid";
import isValidBranch from "./utilities/isValidBranch";

/**Formulario para crear una nueva sucursal. */
export default function NewBranch(): JSX.Element {

  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState(false);
  
  

  //  Datos del punto de venta.  //
  const [name,       setName] =       useState();
  //Dirección.
  const [province,   setProvince] =   useState("Misiones");
  const [city,       setCity] =       useState();
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


  //  Datos de opciones de formulario.  //
  const [provinces, setProvinces] = useState([]);
  const [cities, setCities] = useState([]);


  useEffect(()=>getListOfProvinces(setProvinces), []);
  useEffect(()=>getListOfCitiesByProvinceName(province, setCities), [province]);


  //  Controladores de elementos <Retractable/>  //
  const [boolAddress,     setBoolAddress] =     useState(true);
  const [boolContact,     setBoolContact] =     useState(false);
  const [boolPreferences, setBoolPreferences] = useState(false);

  async function submitBranchIfValid(): Promise<void> {

    const branch: branch = {
      name: name,
      email: email,
      phone: phone,
      address: {
        province: province,
        city: city,
        postalCode: postalCode,
        street: street,
        addressNumber: number,
      },
      logo: logo,
      photo: photo,
      preferenceColor: color
    }

    if (!isValidBranch(branch, setError)) return;


    setLoading(true);
    const response = await postBranch(branch);
    setLoading(false);

    if (!response.ok) setError(response.message);
    else setSuccess(true);

  }

  return (
    <Form title="Crea una instalación" onSubmit={submitBranchIfValid} >

    <BiChevronLeft onClick={() => navigate(-1)} style={{margin:"1rem", fontSize:"2rem", color:"rgb(44,44,44)",cursor:"pointer"}} />

      <Field label="Nombre comercial" validator={Valid.names(name)} bind={[name, setName]} placeholder={"Entre 3 y 20 caracteres"} />

      <Retractable label="Dirección" sync={boolAddress}
      onClick={(state:boolean)=>{setBoolAddress(state); setBoolContact(false); setBoolPreferences(false);}}>

        <FlexDiv>
          <Select label="Provincia"           value={province} onChange={setProvince}     options={provinces} />
          <Select label="Municipio"           value={city} onChange={setCity}     options={cities} />
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

      {success?<Message type="success" message={`Se ha creado la sucursal "${name}"`} />:
      loading?<Loading />:<Button type="submit">Crear</Button>}
    </Form>
  );
}