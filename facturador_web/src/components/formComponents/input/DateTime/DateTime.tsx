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
 * Un campo de selección de valores de fecha.
 * @param props.label       - El título del input.
 * @param props.type        - Tipo de input de tiempo. Por defecto es "date".
 * @param props.nonPast     - Si no debe aceptar valores anteriores a la fecha actual. Por defecto es falso.
 * @param props.value       - Valor actual en forma de string.
 * @param props.onChange    - Función que recibe el valor en el envento de un cambio.
 */
export default function DateTime({ label = "", type="date", value, onChange, nonPast=false }: props): JSX.Element {

    let date = new Date();
    const currentDate = (`${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`);

    const change = (value:string) => {
        if (nonPast && Date.parse(value) < Date.parse(currentDate))
        return;
        onChange(value);
    }

    return (
        <label> {label}
            <input
                className="datetime"
                min={currentDate}
                type={type}
                value={value}
                onChange={e => change(e.target.value)}
            ></input>
        </label>
    );
};

