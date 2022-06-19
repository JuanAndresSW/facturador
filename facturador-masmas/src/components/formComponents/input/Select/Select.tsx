import React from "react";
import {Loading} from "components/standalone";
import './Select.css';

type props = {
    label?: string;
    options: string[];
    fallback?: string;
    bind: [string, React.Dispatch<React.SetStateAction<string>>];
}

/**
 * Un dropdown de selección de opciones.
 * @param props.label    - El título del input.
 * @param props.fallback - Texto a mostrar cuando hay 0 opciones.
 * @param props.options  - Array de opciones, con un valor clave, un nombre y un tooltip opcional.
 * @param props.bind     - Array desestructurado asociado al valor del input.
 */
export default function Select({ label = "", fallback="", options, bind }: props): JSX.Element {

    if (options === undefined)  return <Loading />;
    if (options.length === 0)   return <span>{fallback}</span>;
    if (options.length === 1) {
        bind[1](options[0])
        return <p>{options[0]}</p>
    }

    return (
        <label className="select" style={(label.length===0)?{margin:"0 auto"}:{}}> {label}

        <select
        onChange={e=>bind[1](e.target.value)}
        value={bind[0]}
        >
            {
                options.map(option => 
                    <option 
                    key={option} 
                    title={option}
                    value={option}>
                        {option}
                    </option>
                )
            }
        </select>

        </label>
    );
};

