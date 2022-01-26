import React, { ReactNode } from "react";
import { BiCaretDown, BiCaretUp } from "react-icons/bi";
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
            <div className={active ? "" : "disable"}>
                {children}
            </div>
        </div>
    )
}