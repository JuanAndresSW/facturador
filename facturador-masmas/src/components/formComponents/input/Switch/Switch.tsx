import React from "react";
import './Switch.css';

type props = {
    label?: string;
    note?: string;
    falseIcon?: React.SVGProps<SVGSVGElement>;
    trueIcon?: React.SVGProps<SVGSVGElement>;
    bind: [boolean, React.Dispatch<React.SetStateAction<boolean>>];
}

/**
 * Un interruptor ligado a un boolean declarado con setState. El valor izquierdo es falso, el derecho verdadero.
 * @param props.label       - El título del input.
 * @param props.note        - Nota extra acerca del input.
 * @param props.falseIcon   - Elemento SVG a mostrar para el valor falso.
 * @param props.trueIcon    - Elemento SVG a mostrar para el valor verdadero.
 * @param props.bind        - Array desestructurado asociado al valor del input.
 */
export default function Switch({ label = "", note, falseIcon, trueIcon, bind }: props): JSX.Element {

    return (
        <label className="select" style={(label.length===0)?{margin:"0 auto"}:{}}> {label} <span> {note}</span>
            <div className={bind[0]?"switch switch-true":"switch switch-false"}
            onClick={()=>bind[1](!bind[0])}>
                {falseIcon}
                {trueIcon}
            </div>
        </label>
    );
};