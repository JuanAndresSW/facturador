import React from "react";  
import './Color.css';

type props = {
  label: string;
  note?: string;
  value: string;
  onChange: Function;
}

/**
 * Un input de selección de colores hexadecimales.
 * @param props.label     - El título del input.
 * @param props.note      - Nota extra acerca del input.
 * @param props.value     - El valor hexadecimal de 7 caracteres.
 * @param props.onChange  - La función a ejecutar en el evento de cambio del valor.
 */
export default function Image({label, note, value, onChange}:props) {
  return (
    <label className="color">
        {label}<span> {note}</span>
        <p>{value}</p>
        <input
          value={value}
          type="color"
          onChange={e => onChange(e.target.value)}
        />
    </label>
  )
}