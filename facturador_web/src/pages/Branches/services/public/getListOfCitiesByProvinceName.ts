import {SetStateAction} from "react";

/**
 * Pasa una lista de municipios (según la API del Servicio de Normalización de Datos Geográficos Argentina) a una función de estado setState.
 */
export default function getListOfCitiesByProvinceName(provinceName: string, setStateAction: SetStateAction<any>): void {
    
    fetch(`https://apis.datos.gob.ar/georef/api/municipios?provincia=${provinceName}&campos=nombre&max=99`)
    
    .then(response        => response.json()

    .then(listOfCities => setStateAction(listOfCities.municipios.map(

        (municipio: any)  => {
            return {
                value: municipio.nombre
            }
        }

    )))
    );
}