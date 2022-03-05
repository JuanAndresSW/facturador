import React from "react";

type props = {
    label: string;
    note?: string;
    setter: React.Dispatch<React.SetStateAction<File>>;
}

/**
 * Un input de archivos de tipo imágen. Acepta png, jpg y svg.
 * @param label - El título del input.
 * @param note - Nota adicional acerca del input.
 * @param setter - Función controladora del estado de la constante que almacena la imágen.
 */
export default function Image({label, note, setter}:props) {
    return (
        <label>
            {label}<span> {note}</span>
            <input
              type="file"
              accept=".png, .jpeg, .jpg, .svg"
              onChange={e => {
                if (e.target.files && e.target.files.length > 0) setter(e.target.files.item(0));
              }}
            ></input>
        </label>
    )
}