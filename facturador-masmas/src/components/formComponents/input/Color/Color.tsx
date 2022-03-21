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
 */
export default function Image({label, note, value, onChange}:props) {
  return (
    <label className="color">
        {label}<span> {note}</span>
        <p style={{color:value}}>{value}</p>
        <input
          value={value}
          type="color"
          onChange={e => onChange(e.target.value)}
        />
    </label>
  )
}