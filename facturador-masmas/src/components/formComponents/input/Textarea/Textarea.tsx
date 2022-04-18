import React from "react";
import './Textarea.css';

type props = {
    label?:string;
    maxLength?:number;
    bind: [string, React.Dispatch<React.SetStateAction<string>>]
}

export default function Textarea({label, maxLength=50, bind}:props):JSX.Element {
    return (
        <label>{label}
        <textarea maxLength={maxLength} value={bind[0]} spellCheck={false}
        onChange={e=>bind[1](e.target.value)} />
        </label>
        
    )
}