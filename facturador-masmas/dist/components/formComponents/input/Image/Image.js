import React from "react";
/**
 * Un input de archivos de tipo imágen. Acepta png, jpg y svg.
 * @param label - El título del input.
 * @param note - Nota adicional acerca del input.
 * @param setter - Función controladora del estado de la constante que almacena la imágen.
 */
export default function Image(_a) {
    var label = _a.label, note = _a.note, setter = _a.setter;
    return (React.createElement("label", null,
        label,
        React.createElement("span", null,
            " ",
            note),
        React.createElement("input", { type: "file", accept: ".png, .jpeg, .jpg, .svg", onChange: function (e) {
                if (e.target.files && e.target.files.length > 0)
                    setter(e.target.files.item(0));
            } })));
}
