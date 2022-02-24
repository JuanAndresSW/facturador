import React from "react";
import './DateInput.css';

type props = {
    label?: string;
    note?: string;
    nonPast?: boolean;
    value: string;
    onChange: Function;
}

/**
 * Un campo de escritura.
 * @param label - El título del input.
 * @param note - Nota extra acerca del input.
 * @param nonPast - Si no debe aceptar valores anteriores a la fecha actual.
 * @param value - Array desestructurado asociado al valor del input.
 * @param onChange - Función que manejará el valor en el envento de un cambio.
 */
export default function DateInput({ label = "", note, value, onChange, nonPast=false }: props): JSX.Element {

    let date = new Date();
    const minDate = (`${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`);

    const change = (value:string) => {
        if (nonPast && Date.parse(value) >= Date.parse(minDate)) onChange(value);
    }
    return (
        <label> {label}
        <span> {note}</span>
            <input
                min={minDate}
                type={"date"}
                value={value}
                onChange={e => change(e.target.value)}
            ></input>
        </label>
    );
};

