import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
//GUI.
import defaultLogo from 'assets/svg/logo.svg';
import { Field, Form, Select, Image, ErrorMessage, Button, Color } from "components/formComponents";
import { BiChevronLeft } from "react-icons/bi";
import { Retractable } from 'components/layout';
/**Formulario para crear un nuevo punto de venta. */
export default function NewPoint() {
    var navigate = useNavigate();
    var _a = useState(""), error = _a[0], setError = _a[1];
    //  Datos del punto de venta.  //
    var _b = useState(), name = _b[0], setName = _b[1];
    //Dirección.
    var _c = useState("Buenos Aires"), province = _c[0], setProvince = _c[1];
    var _d = useState(), department = _d[0], setDepartment = _d[1];
    var _e = useState(), locality = _e[0], setLocality = _e[1];
    var _f = useState(), postalCode = _f[0], setPostalCode = _f[1];
    var _g = useState(), street = _g[0], setStreet = _g[1];
    var _h = useState(), number = _h[0], setNumber = _h[1];
    //Contacto.
    var _j = useState(), email = _j[0], setEmail = _j[1];
    var _k = useState(), phone = _k[0], setPhone = _k[1];
    var _l = useState(), website = _l[0], setWebsite = _l[1];
    //Personalización.
    var _m = useState(), logo = _m[0], setLogo = _m[1];
    var _o = useState("#ffffff"), color = _o[0], setColor = _o[1];
    //  Controladores de elementos <Retractable/>  //
    var _p = useState(true), boolAddress = _p[0], setBoolAddress = _p[1];
    var _q = useState(false), boolContact = _q[0], setBoolContact = _q[1];
    var _r = useState(false), boolPreferences = _r[0], setBoolPreferences = _r[1];
    //validación
    function validatePointOfSale() {
        //enviar objeto al servidor
        //Gateway.submitPoint(pointOfSale, window.location.href);
    }
    ;
    return (React.createElement(Form, { title: "Crea un punto de venta" },
        React.createElement(BiChevronLeft, { onClick: function () { return navigate(-1); }, style: { margin: "1rem", fontSize: "2rem", color: "rgb(44,44,44)", cursor: "pointer" } }),
        React.createElement(Field, { label: "Nombre del comercio", bind: [name, setName], placeholder: "Entre 3 y 20 caracteres" }),
        React.createElement(Retractable, { label: "Direcci\u00F3n", sync: boolAddress, onClick: function (state) { setBoolAddress(state); setBoolContact(false); setBoolPreferences(false); } },
            React.createElement(Select, { label: "Provincia", bind: [province, setProvince], options: provinces }),
            React.createElement(Field, { label: "Departamento", bind: [department, setDepartment] }),
            React.createElement(Field, { label: "Localidad", bind: [locality, setLocality] }),
            React.createElement(Field, { label: "C\u00F3digo postal", bind: [postalCode, setPostalCode], type: "number" }),
            React.createElement(Field, { label: "Calle", bind: [street, setStreet] }),
            React.createElement(Field, { label: "N\u00FAmero de direcci\u00F3n", bind: [number, setNumber], type: "number" })),
        React.createElement(Retractable, { label: "Informaci\u00F3n de contacto", sync: boolContact, onClick: function (state) { setBoolContact(state); setBoolAddress(false); setBoolPreferences(false); } },
            React.createElement(Field, { label: "Correo electr\u00F3nico", note: "(opcional)", bind: [email, setEmail], type: "email" }),
            React.createElement(Field, { label: "N\u00FAmero de tel\u00E9fono", note: "(opcional)", bind: [phone, setPhone], type: "tel" }),
            React.createElement(Field, { label: "Sitio web", note: "(opcional)", bind: [website, setWebsite], type: "url" })),
        React.createElement(Retractable, { label: "Personalizaci\u00F3n", sync: boolPreferences, onClick: function (state) { setBoolPreferences(state); setBoolAddress(false); setBoolContact(false); } },
            React.createElement(Image, { label: "Logo", note: "(opcional)", img: logo, setter: setLogo, fallback: defaultLogo }),
            React.createElement(Color, { label: "Color de los documentos comerciales", value: color, onChange: setColor })),
        React.createElement(ErrorMessage, { message: error }),
        React.createElement(Button, { text: "Crear", type: "submit" })));
}
var provinces = [
    { id: "Buenos Aires", value: "Buenos Aires" },
    { id: "Catamarca", value: "Catamarca" },
    { id: "Chaco", value: "Chaco" },
    { id: "Chubut", value: "Chubut" },
    { id: "Córdoba", value: "Córdoba" },
    { id: "Corrientes", value: "Corrientes" },
    { id: "Entre Ríos", value: "Entre Ríos" },
    { id: "Formosa", value: "Formosa" },
    { id: "Jujuy", value: "Jujuy" },
    { id: "Mendoza", value: "Mendoza" },
    { id: "Misiones", value: "Misiones" },
    { id: "Neuquén", value: "Neuquén" },
    { id: "La Pampa", value: "La Pampa" },
    { id: "La Rioja", value: "La Rioja" },
    { id: "Río Negro", value: "Río Negro" },
    { id: "Salta", value: "Salta" },
    { id: "San Juan", value: "San Juan" },
    { id: "San Luis", value: "San Luis" },
    { id: "Santa Cruz", value: "Santa Cruz" },
    { id: "Santa Fe", value: "Santa Fe" },
    { id: "Santiago del Estero", value: "Santiago del Estero" },
    { id: "Tierra del Fuego", value: "Tierra del Fuego, Antártida e Islas del Atlántico Sur" },
    { id: "Tucumán", value: "Tucumán" }
];
