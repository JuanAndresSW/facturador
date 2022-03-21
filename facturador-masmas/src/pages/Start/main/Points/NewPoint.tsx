import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

//Servicios y utilidades.
import Valid from 'utils/Valid';

//GUI.
import defaultLogo from 'assets/svg/logo.svg';
import { Field, Form, Select, Image, ErrorMessage, Button, Color } from "components/formComponents";
import { BiChevronLeft } from "react-icons/bi";
import { Retractable } from 'components/layout';



/**Formulario para crear un nuevo punto de venta. */
export default function NewPoint(): JSX.Element {

  const navigate = useNavigate();
  const [error, setError] = useState("");


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
  const [website,    setWebsite] =    useState();
  //Personalización.
  const [logo,       setLogo] =       useState();
  const [color,      setColor] =      useState("#ffffff");


  //  Controladores de elementos <Retractable/>  //
  const [boolAddress,     setBoolAddress] =     useState(true);
  const [boolContact,     setBoolContact] =     useState(false);
  const [boolPreferences, setBoolPreferences] = useState(false);

  
  //validación
  function validatePointOfSale(): void {
    //enviar objeto al servidor
    //Gateway.submitPoint(pointOfSale, window.location.href);
  };

  return (
    <Form title="Crea un punto de venta">

    <BiChevronLeft onClick={() => navigate(-1)} style={{margin:"1rem", fontSize:"2rem", color:"rgb(44,44,44)",cursor:"pointer"}} />

      <Field label="Nombre del comercio" bind={[name, setName]} placeholder={"Entre 3 y 20 caracteres"} />

      <Retractable label="Dirección" sync={boolAddress}
      onClick={(state:boolean)=>{setBoolAddress(state); setBoolContact(false); setBoolPreferences(false);}}>

        <Select label="Provincia"           bind={[province, setProvince]}     options={provinces} />
        <Field  label="Departamento"        bind={[department, setDepartment]}                     />
        <Field  label="Localidad"           bind={[locality, setLocality]}                         />
        <Field  label="Código postal"       bind={[postalCode, setPostalCode]} type="number"       />
        <Field  label="Calle"               bind={[street, setStreet]}                             />
        <Field  label="Número de dirección" bind={[number, setNumber]}         type="number"       />

      </Retractable>

      <Retractable label="Información de contacto" sync={boolContact} 
      onClick={(state:boolean)=>{setBoolContact(state); setBoolAddress(false); setBoolPreferences(false);}}>

        <Field label="Correo electrónico" note="(opcional)" bind={[email, setEmail]}     type="email" />
        <Field label="Número de teléfono" note="(opcional)" bind={[phone, setPhone]}     type="tel"   />
        <Field label="Sitio web"          note="(opcional)" bind={[website, setWebsite]} type="url"   />

      </Retractable>

      <Retractable label="Personalización" sync={boolPreferences} 
      onClick={(state:boolean)=>{setBoolPreferences(state); setBoolAddress(false); setBoolContact(false);}}>

        <Image label="Logo" note="(opcional)" img={logo} setter={setLogo} fallback={defaultLogo} />
        <Color label="Color de los documentos comerciales" value={color} onChange={setColor} />

      </Retractable>


      <ErrorMessage message={error} />

      <Button text="Crear" type="submit" />
    </Form>
  );
}

const provinces = [
  {id:"Buenos Aires",        value:"Buenos Aires"},
  {id:"Catamarca",           value:"Catamarca"},
  {id:"Chaco",               value:"Chaco"},
  {id:"Chubut",              value:"Chubut"},
  {id:"Córdoba",             value:"Córdoba"},
  {id:"Corrientes",          value:"Corrientes"},
  {id:"Entre Ríos",          value:"Entre Ríos"},
  {id:"Formosa",             value:"Formosa"},
  {id:"Jujuy",               value:"Jujuy"},
  {id:"Mendoza",             value:"Mendoza"},
  {id:"Misiones",            value:"Misiones"},
  {id:"Neuquén",             value:"Neuquén"},
  {id:"La Pampa",            value:"La Pampa"},
  {id:"La Rioja",            value:"La Rioja"},
  {id:"Río Negro",           value:"Río Negro"},
  {id:"Salta",               value:"Salta"},
  {id:"San Juan",            value:"San Juan"},
  {id:"San Luis",            value:"San Luis"},
  {id:"Santa Cruz",          value:"Santa Cruz"},
  {id:"Santa Fe",            value:"Santa Fe"},
  {id:"Santiago del Estero", value:"Santiago del Estero"},
  {id:"Tierra del Fuego",    value:"Tierra del Fuego, Antártida e Islas del Atlántico Sur"},
  {id:"Tucumán",             value:"Tucumán"}
]