import React from "react";
import './Radio.css';

type props = {
    legend: string;
    note?: string;
    options: any[];
    bind: [any, Function];
}

/**
 * Un input de tipo radio.
 * @param props.legend  - El título del input.
 * @param props.note    - Nota extra acerca del input.
 * @param props.options - Array de nombres de las opciones, uno para cada radio.
 * @param props.bind    - Array desestructurado asociado al valor del input.
 */
export default function Radio({ legend = "", note, options, bind }: props): JSX.Element {
    return (
        <>
        <legend>{legend}</legend>
        <span> {note}</span>


            {options.map((option, index) =>
                <label className="radio" key={index}>
                    <input
                        type="radio"
                        name={legend}
                        checked={option === bind[0]}
                        className={option === bind[0]?"radio-checked":""}
                        value={option}
                        onChange={e => bind[1](e.target.value)}
                    />
                    {option}
                </label>
            )}            
        </>


    );
};

