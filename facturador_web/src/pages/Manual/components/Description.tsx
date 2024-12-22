import React from "react";
import "./manual.css";
import {Section} from "components/wrappers";

//Imágenes tutorial.
import signup from "../assets/signup.png";
import branch from "../assets/create-branch.png";
import selectBranch from "../assets/select-branch.png";
import creatingPoint from "../assets/creating-point.png";
import createDoc from "../assets/create-doc.png";
//Videoturorial.
import tutorial from "../assets/facturador-tutorial.mp4";



/**Muestra una descripción de la aplicación y cómo usarla. */
export default function Description() {
    return <Section>
        <h2>¿Qué es facturador++?</h2>
        <p>Bienvenido a facturador++. Esta aplicación web está hecha por y para los estudiantes de la E.P.E.T. 4.</p>
        <p>Lo que puedes hacer en ella se resume en:</p>
        <ul>
            <li>Crear documentos comerciales;</li>
            <li>Ver el registro de documentos y descargarlos como PDF y;</li>
            <li>Administrar sucursales de tu empresa, con puntos de venta para emitir documentos.</li>
        </ul>
        <p>La aplicación fue ideada para facilitar el proceso de enseñanza de documentos comerciales en las asignaturas de contabilidad.</p>

        <h2>Comienza a usar la aplicación</h2>

        <video controls width="250">
            <source src={tutorial} type="video/mp4" />
        </video>

        <h3>1: Crea una cuenta de comerciante</h3>
        <img src={signup} alt="" />

        <h3>2: Registra una sucursal que te pertenezca</h3>
        <img src={branch} alt="" />

        <h3>3: Ingresa a la pantalla de una sucursal y crea un nuevo punto de venta</h3>
        <img src={selectBranch} alt="" />
        <img src={creatingPoint} alt="" />

        <h3>4: Ve a la sección de operaciones. Deberás seleccionar la sucursal y el punto desde el cual quieres emitir tu documento</h3>
        <img src={createDoc} alt="" />

    </Section>
}