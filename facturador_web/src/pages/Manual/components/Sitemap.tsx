import React from "react";
import "./manual.css";
import sitemap from "../assets/sitemap.png";
import {Section} from "components/wrappers";

export default function Sitemap() {
    return (
    <Section>
        <h2>Lista de páginas:</h2>
        <img src={sitemap} alt="" />
        <p>La aplicación tiene 5 páginas.</p>
        <ul>
            <li>inicio;</li>
            <li>registrarse;</li>
            <li>ingresar;</li>
            <li>cuenta;</li>
            <li>operaciones y;</li>
            <li>sucursales;</li>
        </ul>


        <h3>Inicio</h3>
        <p>Esta página te dará la bienvenida a la aplicación. También desde aquí puedes iniciar sesión con tu cuenta. Si ya habías iniciado sesión, no podrás visualizar esta página.</p>


        <h2>Páginas de la cuenta de usuario</h2>

        <h3>Registrarse</h3>
        <p>Este es un formulario para crear una nueva cuenta de comerciante.</p>

        <h3>Ingresar</h3>
        <p>Desde aquí puedes ingresar a tu cuenta con tu nombre o correo y tu contraseña.</p>

        <h3>Cuenta</h3>
        <p>Puedes acceder a esta pantalla desde el menú superior haciendo clic en el ícono circular de tu cuenta y "administrar". Te permitirá editar la información de tu cuenta.</p>

        <h2>Páginas de trabajo</h2>

        <h3>Operaciones</h3>
        <p>Aquí puedes seleccionar uno de los documentos comerciales disponibles para iniciar el proceso de creación. También verás el registro de los documentos comerciales que hayas creado.</p>

        <h3>Sucursales</h3>
        <p>En esta página tendrás la opción de crear sucursales. Las sucursales son los edificios que alojan a los puntos de venta. Antes de realizar cualquier operación, debes primero crear al menos una sucursal y luego un punto de venta.</p>
    

    </Section>)
}