import React, { ReactNode } from "react";
import './Retractable.css'


type props = {
    children: ReactNode,
    tabHeader: ReactNode,
    active: boolean
}

export default function Retractable({ children, tabHeader, active }: props): JSX.Element {
    return (
        <div>
            <div className="section-header">{tabHeader}</div>
            <div className={active ? "active" : ""}>
                {children}
            </div>
        </div>
    )
}