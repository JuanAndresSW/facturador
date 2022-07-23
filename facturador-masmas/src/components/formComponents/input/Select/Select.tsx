import React, { useState } from "react";
import {Loading} from "components/standalone";
import './Select.css';

type props = {
    label?: string,
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
 * Un dropdown de selección de opciones.
 * @param props.label    - El título del input.
 * @param props.fallback - Texto a mostrar cuando hay 0 opciones.
 * @param props.options  - Array de opciones.
 * @param props.options.title - Nombre de la opción a mostrar. Por defecto es igual al valor.
 * @param props.options.value - Valor de la opción a almacenar cuando es elegida.
 * @param props.options.subOptions - Array opcional de sub opciones que se muestran al elegir la opción padre.
 * @param props.value
 * @param props.onChange
 * @param props.subValue
 * @param props.subOnChange
 */
export default function Select({ label, fallback, options, value, onChange, subValue, subOnChange=()=>{} }: props): JSX.Element {

    const [indexOfSelected, setIndexOfSelected] = useState(-1);

    if (options?.length === 1) {
        onChange(options[0].value)
        return <p>{options[0].title}</p>
    }

    return <>
       
        <select
        className="select"
        onChange={ e => {subOnChange(undefined); onChange(e.target.value)} }
        value={value}
        defaultValue={0}
        >
            <option disabled value={0}>
                {options === undefined? "Cargando..." : (options?.length === 0) ? fallback : label}   
            </option>

            {   !options? null :
                options?.map((option, index) => 
                    <option 
                    key={option.value} 
                    title={option.title? option.title : ''+option.value}
                    value={option.value}
                    onClick={()=>setIndexOfSelected(index)}>
                        {option.title? option.title : option.value}
                    
                    </option>
                )
            }
        </select>
        
        {(indexOfSelected===-1 || !options[indexOfSelected]?.subOptions) ? null :

        <select
        className="sub-select"
        onChange={ e => subOnChange(e.target.value) }
        value={subValue}
        defaultValue={"0"}>

            <option value={"0"} />
        
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
        }

    </>
};

