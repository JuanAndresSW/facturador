import React from "react";
import './Field.css';

type props = {
    label?: string;
    note?: string;
    type?: "text" | "number" | "password";
    icon?: JSX.Element;
    bind: [any, Function];
    validator?: boolean;
}

/**
 * Un campo de escritura.
 * @param label - El título del input.
 * @param note - Nota extra acerca del input.
 * @param type - Un tipo específico de input textual.
 * @param bind - Array desestructurado asociado al valor del input.
 */
export default function Field({ label = "", icon, note, type = "text", bind, validator=true }: props): JSX.Element {
    return (
        <label> {label}
        <span> {note}</span>

        <div className={validator?"field-wrap":"field-wrap invalid"}>
            {icon!==undefined?icon:null}

            <input
                className="field"
                type={type}
                value={bind[0]}
                onChange={(e) => bind[1](e.target.value)}
            ></input>
        </div>
        </label>
    );
};

