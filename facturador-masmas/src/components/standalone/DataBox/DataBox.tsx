import React from "react";
import './DataBox.css';

type props = {
    title: string,
    value: string | number
}

/**Una caja informativa que muestra un dato con su nombre en el margen superior, el titulo. */
export default function DataBox({title, value}: props): JSX.Element {
    return <div className="data-box">
        <h3>{title}</h3>
        <data>{value}</data>
    </div>
}