import React, { ReactNode, useState } from "react";
import { BiChevronDown, BiChevronUp } from "react-icons/bi";
import './Retractable.css'


type props = {
    children: ReactNode;
    label: string;
    initial?: boolean;
    sync?: boolean;
    onClick?: Function;
}


/**
 * Un contenedor con título que puede ser contraído y extendido haciendo click sobre el encabezado.
 * @param props.label El título del contenedor.
 * @param props.initial? El valor boolean inicial. Pasar como false para que esté inicialmente retraído. Por defecto es true: extendido.
 * @param props.sync? Un valor boolean con el cual el elemento debe estar sincronizado. Si es undefined, es ignorado.
 * @param props.onClick? La función a ejecutar en el evento de click. Se pasa como argumento el valor boolean opuesto al valor actual del componente.
 */
export default function Retractable({ children, label, initial=true, sync, onClick }: props): JSX.Element {

    const [localState, setLocalState] = useState(initial);
    const extended = sync === undefined ? localState : sync;

    function change() {
        setLocalState(!extended);
        onClick(!extended);
    }

    return (
        <div className= { extended ? "retractable" : "retractable folded" }>

            <div onClick={change}>
                {label}
                {extended?<BiChevronUp/>:<BiChevronDown/>}
            </div>

            <div>
                {children}
            </div>
        </div>
    )
}