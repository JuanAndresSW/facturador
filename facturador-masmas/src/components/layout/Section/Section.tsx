import React, { ReactNode } from "react";
import './Section.css';

type props = {
    children: ReactNode;
    label?: string;
}

/**
 * Un envoltorio visible con título opcional 'label'.
 */
export default function Section({children, label}:props): JSX.Element {
    return (
        <div className="section">
            <legend>{label}</legend>
            {children}
        </div>
    );
}