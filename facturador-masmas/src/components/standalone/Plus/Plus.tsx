import React from "react";
import { BiPlusCircle } from "react-icons/bi";
import { MdAddCircleOutline } from "react-icons/md";
import { Link } from "react-router-dom";
import './Plus.css';


/**
 * Una opción de navegación con el símbolo '+'.
 */
export default function Plus({link}:{link:string}): JSX.Element {
    return (
        <Link to={link} className="plus">
            <MdAddCircleOutline />
        </Link>
    );
}