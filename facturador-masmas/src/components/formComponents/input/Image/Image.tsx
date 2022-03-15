import React from "react";
import './Image.css';
import defaultAvatar from 'assets/img/punto.jpg';
import { BsFillXCircleFill } from "react-icons/bs";

type props = {
    label: string;
    note?: string;
    setter: React.Dispatch<React.SetStateAction<File>>;
    img: File;
}

/**
 * Un input de archivos de tipo imágen. Acepta png, jpg y svg.
 * @param label - El título del input.
 * @param note - Nota adicional acerca del input.
 * @param setter - Función controladora del estado de la constante que almacena la imágen.
 */
export default function Image({label, note, setter, img}:props) {
    return (
        <label className="image">
            {label}<span> {note}</span>
            {img?<BsFillXCircleFill onClick={(e)=>{e.preventDefault();setter(undefined)}} />:null}
            <input
              type="file"
              accept=".png, .jpeg, .jpg, .svg"
              onChange={e => {
                if (e.target.files && e.target.files.length > 0) setter(e.target.files.item(0));
              }}
            />

            <img src={!!img?
              URL.createObjectURL(img):defaultAvatar} />
        </label>
    )
}