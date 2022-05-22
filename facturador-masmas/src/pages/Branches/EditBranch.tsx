import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import branch from './models/branch';

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

//Servicios.
import updateBranch from './services/updateBranch';
function getBranchLogo(callback: Function) {return ""}

type props = {
  branch: branchesContent;
}

/**Formulario para visualizar y cambiar datos de una instalación/sucursal.*/
export default function EditBranch({branch}:props): JSX.Element {

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
  const [province,   setProvince] =   useState();
  const [department, setDepartment] = useState();
  const [locality,   setLocality] =   useState();
  const [postalCode, setPostalCode] = useState();
  const [street,     setStreet] =     useState();
  const [addressNumber,     setAddressNumber] =     useState();
  //Contacto.
  const [email,      setEmail] =      useState();
  const [phone,      setPhone] =      useState();
  //Personalización.
  const [photo,      setPhoto] =      useState(null);
  const [logo,       setLogo] =       useState(null);
  const [color,      setColor] =      useState(branch.preferenceColor);


  useEffect(()=>{
    base64ToBlob(branch.photo).then(convertedPhoto=>setPhoto(convertedPhoto));

    /* getBranchLogo((ok:boolean, content:string) => {
      if (ok) base64ToBlob(content).then(convertedLogo=>setLogo(convertedLogo));
    }) */
  },[]);

  function validate():void {
    setError(undefined);
    if (name          && !Valid.names(name, setError)) return;
    if (department    && !Valid.address(department)) return setError("El departamento debe ser de entre 4 y 40 caracteres");
    if (locality      && !Valid.address(locality)) return setError("La localidad debe ser de entre 4 y 40 caracteres");
    if (postalCode    && !Valid.postalCode(postalCode, setError)) return;
    if (street        && !Valid.address(street)) return setError("La calle debe ser de entre 4 y 40 caracteres");
    if (addressNumber && !Valid.addressNumber(addressNumber, setError)) return;
    if (email         && !Valid.email(email, setError)) return;
    if (phone         && !Valid.phone(phone, setError)) return;
    if (!Valid.image(photo, setError)) return;
    if (!Valid.image(logo)) return setError("El logo no puede superar los 2MB");
    if (!Valid.hexColor(color, setError)) return;
    submit();
  }

  function submit():void {
    setLoading(true);
    const updatedBranch: branch = {
      name: name,
      email: email,
      phone: phone,
      address: {
        province: province,
        department: department,
        locality: locality,
        postalCode: postalCode,
        street: street,
        addressNumber: addressNumber
      },
      logo: logo,
      photo: photo,
      color: color
    }


    updateBranch(branch.branchId, updatedBranch, (ok:boolean, message:string) => {
      if (ok) setSuccess(true)
      else setError(message);
    });
    setLoading(false)
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
          <Field  label="Altura" bind={[addressNumber, setAddressNumber]}         type="number" validator={Valid.addressNumber(addressNumber)}
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

      {success?<Message type="success" message={`Se han guardado los datos de "${name?name:branch.name}"`} />:
      loading?<Loading />:<Button text="Guardar" type="submit" />}
    </Form>
  );
}