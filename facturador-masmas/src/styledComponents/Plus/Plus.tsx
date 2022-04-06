import React from "react";
import { BiPlusCircle } from "react-icons/bi";
import { Link } from "react-router-dom";
import './Plus.css';


/**
 * Una opción con el símbolo '+'.
 */
export default function Plus({link}:{link:string}): JSX.Element {
    return (
        <Link to={link} className="plus">
            <BiPlusCircle />
        </Link>
    );
}