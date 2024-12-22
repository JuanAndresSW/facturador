import React from "react";
import { useNavigate } from "react-router-dom";
import Button from "../Button/Button";
import './Message.css';

type props = {
    type: "error"|"success";
    message: string;
}

/**
 * Un mensaje de error sincronizado con 'message', siendo una pieza de estado.
 * @param props.type    - Naturaleza del mensaje. Puede ser error o success.
 * @param props.message - String a mostrar como mensaje.
 */
export default function Message({type, message}:props): JSX.Element {
    const navigate = useNavigate();
    return (
        type==="error"?
        <p className="error">
            {message?.length > 1? "🚫 " : null}
            {message}
        </p>:
        <div className="success">
            <p>💾 {message}</p>
            <Button onClick={() => navigate(-1)}>Regresar</Button>
        </div>
        
    );
};

