import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

//GUI.
import defaultLogo from 'assets/svg/default-logo.svg';
import defaultPhoto from 'assets/svg/default-photo.svg';
import { Field, Form, Select, Image, Message, Button, Color } from "components/formComponents";
import { BiChevronLeft } from "react-icons/bi";
import { Loading } from 'components/standalone';
import { FlexDiv, Retractable } from "components/wrappers";

//Utilities.
import Valid from "utilities/Valid";
import isValidBranch from "./utilities/isValidBranch";
import branch from "./models/branch";
import { base64ToBlob } from "utilities/conversions";

//Servicios.
import putBranch from './services/putBranch';
import getBranchLogo from './services/getBranchLogo';
import getListOfProvinces from './services/public/getListOfProvinces';
import getListOfCitiesByProvinceName from './services/public/getListOfCitiesByProvinceName';

type props = {
  branch: branch
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
  const [city,       setCity] =       useState();
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

  //  Datos de opciones de formulario.  //
  const [provinces, setProvinces] = useState([]);
  const [cities, setCities] = useState([]);


  useEffect(()=>getListOfProvinces(setProvinces), []);
  useEffect(()=>getListOfCitiesByProvinceName(province, setCities), [province]);

  
  useEffect(()=>{
    setPhoto(branch.photo);

    getBranchLogo(branch.ID).then (response => {
      if (response.ok) base64ToBlob(response.content).then(convertedLogo=>setLogo(convertedLogo));
    });
<<<<<<< HEAD
  }, [branch.photo, branch.ID]);
=======
  },[]);

  function validate():void {
    setError(undefined);
    if (name          && !Valid.names(name, setError)) return;
    if (city          && !Valid.address(city)) return setError("Elija un municipio");
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
>>>>>>> a9cfebedc654b8758b9fa3fb96f1f9126b50f7cc


  async function submitBranchIfValid(): Promise<void> {
    
    const updatedBranch: branch = {
      name: name,
      email: email,
      phone: phone,
      address: {
        province: province,
        city: city,
        postalCode: postalCode,
        street: street,
        addressNumber: addressNumber
      },
      logo: logo,
      photo: photo,
      preferenceColor: color
    }

    if (!isValidBranch(updatedBranch, setError)) return;

    setLoading(true);
    const response = await putBranch(branch.ID, updatedBranch);
    if (response.ok) setSuccess(true)
    else setError(response.message);
    setLoading(false)
  }


  return (
    <Form title={"Editando sucursal "+branch.name} onSubmit={submitBranchIfValid} >

    <BiChevronLeft onClick={() => navigate(-1)} style={{margin:"1rem", fontSize:"2rem", color:"rgb(44,44,44)",cursor:"pointer"}} />

      <Field label="Nombre de fantasía" validator={Valid.names(name)} 
      bind={[name, setName]} placeholder={branch.name} />

      <Retractable label="Dirección" sync={boolAddress}
      onClick={(state:boolean)=>{setBoolAddress(state); setBoolContact(false); setBoolPreferences(false);}}>

        <FlexDiv>
          <Select label="Provincia"           value={province} onChange={setProvince}     options={provinces} />
          <Select label="Municipio"           value={city} onChange={setCity}             options={cities} />
      
          <Field  label="Código postal"       bind={[postalCode, setPostalCode]} type="number" validator={Valid.postalCode(postalCode)}
          placeholder={branch.address.postalCode} />
          <Field  label="Calle"               bind={[street, setStreet]}         validator={Valid.names(street)}
          placeholder={branch.address.street}                          />
          <Field  label="Altura" bind={[addressNumber, setAddressNumber]}         type="number" validator={Valid.addressNumber(addressNumber)}
           placeholder={branch.address.addressNumber.toString()}/>
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
      loading?<Loading />:<Button type="submit">Guardar</Button>}
    </Form>
  );
}