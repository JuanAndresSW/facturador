import React from "react";

type props = {
    elementToDisplay: JSX.Element
}

export default function FullSizeDocument({elementToDisplay}: props): JSX.Element {
    function displayPDF(): void {
        
    }

    return <div 
    onClick={()=>displayPDF()}
    style={{
        backgroundColor: "#f33",
        padding: ".4rem 1.4rem",
        borderRadius: "5px",
        color: "#fff",
        fontFamily: "monospace",
        fontSize:"1.2rem",
        borderBottom: "2px solid #b00",
        cursor: "pointer"
    }}>PDF</div>
}