import React, { useState } from "react";
import './Select.css';
import { Cond } from "components/wrappers";

type props = {
    label?: string,
    sublabel?: string,
    fallback?: string,
    options: {
        title?: string,
        value: string | number,
        subOptions?: {title?: string, value: string | number}[]
    }[],
    value: string | number,
    onChange: Function,
    subValue?: string | number,
    subOnChange?: Function
}

/**
 * Un dropdown de selección de opciones. Puede contener más subopciones por cada opción.
 * @param props.label               - El título del input.
 * @param props.sublabel            - El título del subconjunto de opciones.
 * @param props.fallback            - Texto a mostrar cuando hay 0 opciones.
 * @param props.options             - Array de opciones.
 * @param props.options.title       - Nombre de la opción a mostrar. Por defecto es igual al valor.
 * @param props.options.value       - Valor de la opción a almacenar cuando es elegida.
 * @param props.options.subOptions  - Array opcional de sub opciones que se muestran al elegir la opción padre.
 * @param props.value               - Valor del input.
 * @param props.onChange            - Función de evento de cambio de valor.
 * @param props.subValue            - Sub valor del input.
 * @param props.subOnChange         - Función de evento de cambio del sub valor.
 */
export default function Select({ label, sublabel, fallback, options=[], value, onChange, subValue, subOnChange=()=>{} }: props): JSX.Element {

    const [indexOfSelected, setIndexOfSelected] = useState(-1);

    return options?.length === 0 ? <p>{fallback}</p> :
       

    <>
    <select
    className="select"
    onChange={ e => {subOnChange(undefined); onChange(e.target.value)} }
    value={value}
    >
        <option value={undefined}>{options?.length? label : "Cargando..."}</option>

        {options?.map((option, index) => 

        <option 
        key={option.value} 
        title={option.title? option.title : ''+option.value}
        value={option.value}
        onClick={()=>setIndexOfSelected(index)}>

        {option.title? option.title : option.value}
            
        </option>)}

    </select>
    
    <Cond bool={indexOfSelected!==-1 && !!options[indexOfSelected]?.subOptions}>
    <select
    className="sub-select"
    onChange={ e => subOnChange(e.target.value) }
    value={subValue}>

        <option value={undefined}>{sublabel}</option>
    
        {
            options[indexOfSelected]?.subOptions?.map(suboption => 
                <option 
                key={suboption.value} 
                title={suboption.title? suboption.title : ''+suboption.value}
                value={suboption.value}>
                    {suboption.title? suboption.title : suboption.value}
                </option>
            )
        }
    </select>
    </Cond>

    </>

};

