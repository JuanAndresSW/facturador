import React from "react";
import './Image.css';
import defaultAvatar from 'assets/img/punto.jpg';
import { BsFillXCircleFill } from "react-icons/bs";
/**
 * Un input de archivos de tipo imágen. Acepta png, jpg y svg.
 * @param props.label - El título del input.
 * @param props.note - Nota adicional acerca del input.
 * @param props.setter - Función controladora del estado de la constante que almacena la imágen.
 * @param props.img - Valor File de la imágen a mostrar.
 */
export default function Image(_a) {
    var label = _a.label, note = _a.note, setter = _a.setter, img = _a.img;
    return (React.createElement("label", { className: "image" },
        label,
        React.createElement("span", null,
            " ",
            note),
        img ? React.createElement(BsFillXCircleFill, { onClick: function (e) { e.preventDefault(); setter(undefined); } }) : null,
        React.createElement("input", { type: "file", accept: ".png, .jpeg, .jpg, .svg", onChange: function (e) {
                if (e.target.files && e.target.files.length > 0)
                    setter(e.target.files.item(0));
            } }),
        React.createElement("img", { src: !!img ?
                URL.createObjectURL(img) : defaultAvatar })));
}
