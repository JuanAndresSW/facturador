import React from "react";
import './Submit.css';
type props = {text:string};
export default function Submit({text}:props): JSX.Element {
    return (
        <button type="submit">{text}</button>
    )
}