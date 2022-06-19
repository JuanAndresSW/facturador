import React from "react";
import { Link } from "react-router-dom";
import './Option.css';

type props = {
    label: string;
    icon?: JSX.Element;
    link: string;
}

/**
 * Un cuadro grande con un enlace 'link', un texto 'label', y un ícono 'icon'.
 * Para ser usado en menús de navegaciones.
 * @param props.label - El título de la opción.
 * @param props.icon - Ícono SVG opcional que acompaña al label.
 * @param props.link - La dirección a la cual lleva la opción.
 */
export default function Option({label, icon, link}:props): JSX.Element {
    return (
        <Link to={link} className="option">
            {icon}{label}
        </Link>
    );
}