import React, { ReactNode, useState } from "react";
import { BiChevronDown, BiChevronUp } from "react-icons/bi";
import './Retractable.css'


type props = {
    children: ReactNode,
    label: string,
    initial?: boolean,
}

export default function Retractable({ children, label, initial=true }: props): JSX.Element {
    const [extended, setExtended] = useState(initial);

    return (
        <div className={extended ? "retractable" : "retractable folded"}>
            <div onClick={()=>setExtended(!extended)}>
                {label}
                {extended?<BiChevronUp/>:<BiChevronDown/>}
            </div>

            <div>
                {children}
            </div>
        </div>
    )
}