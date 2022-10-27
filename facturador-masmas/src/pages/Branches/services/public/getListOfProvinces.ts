import {SetStateAction} from "react";

/**
 * Pasa una lista de provincias (según la API del Servicio de Normalización de Datos Geográficos Argentina) a una función de estado setState.
 */
export default function getListOfProvinces(setStateAction: SetStateAction<any>): void {
    
    fetch("https://apis.datos.gob.ar/georef/api/provincias?campos=id,nombre")
    
    .then(response        => response.json()

    .then(listOfProvinces => setStateAction(listOfProvinces.provincias.map(

        (provincia: any)  => {
            return {
                value: provincia.nombre
            }
        }

    )))
    );
}