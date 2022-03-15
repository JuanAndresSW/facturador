import React, { ReactNode } from "react";
import './Panel.css';

type props = {
    children: ReactNode;
    title?: string;
}

/**
 * Un envoltorio visible con título opcional 'label'.
 */
export default function Panel({children, title}:props): JSX.Element {
    return (
        <div className="panel">
            <h1>{title}</h1>
            {children}
        </div>
    );
}