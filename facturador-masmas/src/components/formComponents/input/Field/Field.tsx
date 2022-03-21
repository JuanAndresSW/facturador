import React from "react";
import './Field.css';

type props = {
    label?: string;
    note?: string;
    placeholder?:string;
    type?: "text" | "number" | "password" | "email" | "tel" | "url";
    icon?: JSX.Element;
    bind: [any, Function];
    validator?: boolean;
}

/**
 * Un campo de escritura.
 * @param props.label - El título del input.
 * @param props.note - Nota extra acerca del input.
 * @param props.type - Un tipo específico de input textual.
 * @param props.bind - Array desestructurado asociado al valor del input.
 */
export default function Field({ label = "", icon, note, placeholder, type = "text", bind, validator=true }: props): JSX.Element {
    return (
        <label> {label}
        <span> {note}</span>

        <div className={validator?"field-wrap":"field-wrap invalid"}>
            {icon!==undefined?icon:null}

            <input
                className="field"
                placeholder={placeholder}
                type={type}
                value={bind[0]?bind[0]:""}
                onChange={(e) => bind[1](e.target.value)}
            ></input>
        </div>
        </label>
    );
};

