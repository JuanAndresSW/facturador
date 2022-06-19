import React from "react";  
import './Image.css';
import defaultAvatar from 'assets/svg/user.svg';
import { BsFillXCircleFill } from "react-icons/bs";

type props = {
    label: string;
    note?: string;
    fallback?: string;
    setter: React.Dispatch<React.SetStateAction<File>>;
    img: File;
}

/**
 * Un input de archivos de tipo imágen. Acepta png, jpg y svg.
 * @param props.label     - El título del input.
 * @param props.note      - Nota adicional acerca del input.
 * @param props.fallback  - URL de imágen a mostrar cuando no hay ninguna otra imágen. Por defecto es un icono de usuario.
 * @param props.setter    - Función controladora del estado de la constante que almacena la imágen.
 * @param props.img       - Valor File de la imágen a mostrar.
 */
export default function Image({label, note, fallback=defaultAvatar, setter, img}:props) {

  return (
    <label className="image">
        {label}<span> {note}</span>
            
        {img?.size<10? null :
        <BsFillXCircleFill onClick={e=>{
          e.preventDefault();
          setter(undefined);
          }} />}
            
        <input
          type="file"
          accept=".png, .jpeg, .jpg, .svg"
          onChange={e => {
            if (e.target.files && e.target.files.length > 0) setter(e.target.files.item(0));
          }}
        />
        <div>
        <img src={img?.size>10? URL.createObjectURL(img):fallback} />
        </div>

    </label>
  )
}