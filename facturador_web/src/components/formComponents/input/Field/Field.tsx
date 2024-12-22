import React from "react";
import './Field.css';

type props = {
    label?: string;
    note?: string;
    placeholder?:string;
    type?: "text" | "number" | "password" | "email" | "tel" | "url" | string;
    bind: [any, Function];
    validator?: boolean;
}

/**
 * Un campo de escritura.
 * @param props.label - El título del input.
 * @param props.note - Nota extra acerca del input.
 * @param props.type - Un tipo específico de input textual.
 * @param props.bind - Array desestructurado asociado al valor del input.
 * @param props.validator - Boolean que determina si el valor es válido o no.
 */
export default function Field({ label = "", note, placeholder, type = "text", bind, validator=true }: props): JSX.Element {
    return (
        <label> {label}
        <span> {note}</span>
            <input
                className={validator?"field":"field invalid"}
                placeholder={placeholder}
                type={type}
                value={bind[0]?bind[0]:""}
                onChange={(e) => bind[1](e.target.value)}
            ></input>
        </label>
    );
};

