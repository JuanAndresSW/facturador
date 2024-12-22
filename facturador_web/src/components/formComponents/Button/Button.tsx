import React from "react";
import './Button.css';
type props = {
    children: React.ReactNode;
    type?: ("button"|"submit"|"delete");
    onClick?: Function;
};

/**
 * Un botón rectangular.
 * @param props.text - El contenido de texto del botón.
 * @param props.type - El tipo de botón.
 * @param props.onClick - La función de evento de clic.
 */
export default function Button({children, type="button", onClick=()=>{return}}:props): JSX.Element {
    return (
        <button type={type==="submit"?"submit":"button"} 
        className={type==="delete"?"button delete":"button"}
        onClick={()=>onClick()}>
            {children}
        </button>
    )
}