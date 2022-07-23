import React from "react";
import './Dropdown.css';

type props = {
    options: {name:string, value:string}[],
    value: string,
    onChange: Function
}

/**
 * Un dropdown de selección de opciones alternativo.
 * @param props.options  - Array de opciones, con un valor y un nombre.
 * @param props.value    - Valor del input.
 * @param props.onChange - Función de evento de cambio de valor.
 */
export default function Dropdown({options, value, onChange}: props): JSX.Element {

    return (

        <select data-dropdown
        onChange={e=>onChange(e.target.value)}
        value={value}
        >
            {
                options.map((option, index) => 
                    <option 
                    key={index} 
                    title={option.name}
                    value={option.value}>
                        {option.name}
                    </option>
                )
            }
        </select>
    );
};

