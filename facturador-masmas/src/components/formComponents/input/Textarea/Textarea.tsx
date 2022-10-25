import React from "react";
import './Textarea.css';

type props = {
    label?:string;
    maxLength?:number;
    bind: [string, React.Dispatch<React.SetStateAction<string>>]
}

/**
 * Un elemento <textarea>
 * @param props.label - El título del input.
 * @param props.maxLength - Numero máximo de caracteres. Por defecto es 50.
 * @param props.bind - Array desestructurado asociado al valor del input.
 */
export default function Textarea({label, maxLength=50, bind}:props):JSX.Element {
    return (
        <label>{label}
        <textarea maxLength={maxLength} value={bind[0]} spellCheck={false}
        onChange={e=>bind[1](e.target.value)} />
        </label>
        
    )
}