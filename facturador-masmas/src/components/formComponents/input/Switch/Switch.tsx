import React from "react";
import { BiCheck, BiX } from "react-icons/bi";
import './Switch.css';

type props = {
    label?: string,
    falseIcon?: React.SVGProps<SVGSVGElement>,
    trueIcon?: React.SVGProps<SVGSVGElement>,
    value: boolean,
    setter: React.Dispatch<React.SetStateAction<boolean>>
}

/**
 * Un interruptor ligado a un boolean declarado con setState. El valor izquierdo es falso, el derecho verdadero.
 * @param props.label       - El título del input.
 * @param props.falseIcon   - Elemento SVG a mostrar para el valor falso.
 * @param props.trueIcon    - Elemento SVG a mostrar para el valor verdadero.
 * @param props.value       - Valor del input.
 * @param props.setter      - Función de evento de clic, llamada con el valor opuesto al actual.
 */
export default function Switch({ label = "", falseIcon=<BiX/>, trueIcon=<BiCheck/>, value, setter }: props): JSX.Element {

    return (
        <label style={{margin:label.length===0?"0 auto":""}}> {label}

            <div className={value?"switch switch-true":"switch switch-false"}
            onClick={()=>setter(!value)}>
                {falseIcon}
                {trueIcon}
            </div>

        </label>
    );
};