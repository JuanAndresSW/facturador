import { Button } from "components/formComponents";
import React, { ReactNode, useState } from "react";
import './Confirm.css'

type props = {
    children: ReactNode;
    label: string;
    onConfirm: Function;
}

/**
 * Una ventana emergente en click para la confirmación de una función.
 * @param props.label El texto de la ventana de confirmación.
 * @param props.children El/los elementos que activarán en click la ventana.
 * @param props.onConfirm La función que se ejecuta al confirmar.
 */
export default function Confirm({ children, label, onConfirm }: props): JSX.Element {
    const [active, setActive] = useState(false);

    return (
        <>
            { !active? null :
            <div data-confirm-cover>
                <div data-confirm>
                    <h5>{label}</h5>
                    <div>
                    <Button type="delete" onClick={()=>setActive(false)}>cancelar</Button>
                    <Button onClick={()=>{setActive(false);onConfirm()}}>ok</Button>
                    </div>
                </div>
            </div>
            }
            
    
            <div style={{display:"inline-block"}} onClick={()=>{
                if (!active) setActive(true);
            }}>
                {children}
            </div>
        </>
    )
}