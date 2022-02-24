import React from "react";
import { BiErrorCircle } from "react-icons/bi";
import './ErrorMessage.css';

type props = {
    message: string;
}

/**
 * @returns Un formulario.
 */
export default function Form({message}:props): JSX.Element {
    return (
        <p className="error">
            {message.length > 1? <BiErrorCircle /> : null}
            {message}
        </p>
    );
};

