import React from "react";
import './Button.css';
type props = {
    text:string;
    type?: ("button"|"submit"|"delete");
    onClick?: Function;
};
export default function Button({text, type="button", onClick=()=>{return}}:props): JSX.Element {
    return (
        <button type={type==="submit"?"submit":"button"} 
        className={type==="delete"?"button delete":"button"}
        onClick={()=>onClick()}
        >{text}
        </button>
    )
}