import React from "react";
import './DateTime.css';

type props = {
    label?: string;
    type?: ("date"|"datetime"|"time"|"week"|"month");
    nonPast?: boolean;
    value: string;
    onChange: Function;
}

/**
 * Un campo de escritura.
 * @param label - El título del input.
 * @param type - Tipo de input temporal.
 * @param nonPast - Si no debe aceptar valores anteriores a la fecha actual.
 * @param value - Array desestructurado asociado al valor del input.
 * @param onChange - Función que manejará el valor en el envento de un cambio.
 */
export default function DateTime({ label = "", type="date", value, onChange, nonPast=false }: props): JSX.Element {

    let date = new Date();
    const minDate = (`${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`);

    const change = (value:string) => {
        if (nonPast && Date.parse(value) >= Date.parse(minDate)) onChange(value);
    }
    return (
        <label> {label}
            <input
                className="datetime"
                min={minDate}
                type={type}
                value={value}
                onChange={e => change(e.target.value)}
            ></input>
        </label>
    );
};

