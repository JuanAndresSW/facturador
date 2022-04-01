import React from "react";
import './ErrorMessage.css';

type props = {
    message: string;
}

/**
 * @returns Un mensaje de error sincronizado con 'message', siendo una pieza de estado.
 */
export default function ErrorMessage({message}:props): JSX.Element {
    return (
        <p className="error">
            {message?.length > 1? "🚫 " : null}
            {message}
        </p>
    );
};

