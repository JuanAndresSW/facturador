import React from "react";
import { Link } from "react-router-dom";
import './Option.css';

type props = {
    label: string,
    name:  string,
    link:  string,
    color?: string
}

/**
 * Un cuadro con un enlace 'link', un texto de dos caracteres 'label' y un título 'name'.
 * Para ser usado en menús de navegaciones.
 * @param props.label- Un texto de dos caracteres que se muestra en el centro del cuadro.
 * @param props.name - El nombre del enlace.
 * @param props.link - La dirección a la cual lleva el cuadro.
 * @param props.color- EL color del label.
 */
export default function Option({label, name, link, color="#000"}:props): JSX.Element {
    return (
        <Link to={link} data-option>
            <div style={{color:color, borderColor:color}}>
                <p>{label.charAt(0)}<span>{label.charAt(1)}</span></p>
            </div>
            <span><p><abbr title={name}>{name}</abbr></p></span>
        </Link>
    );
}