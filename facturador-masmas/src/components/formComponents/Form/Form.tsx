import React, { ReactNode } from "react";
import './Form.css';

type props = {
    title: string;
    children: ReactNode;
    onSubmit?: Function;
}

/**
 * Un formulario.
 * @param props.title - El título del formulario.
 * @param props.onSubmit - La función a ejecutar en el evento de envío.
 */
export default function Form({ title="", children, onSubmit }: props): JSX.Element {
    return (
        <form onSubmit={e => { e.preventDefault(); onSubmit() }}>
            <h1 className="title">{title}</h1>
            {children}
        </form>
    );
};

