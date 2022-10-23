import React from "react";
const html2pdf = require("html2pdf.js");

type props = {
    nodeReference: React.MutableRefObject<undefined>,
    filename: string
}

export default function FullSizeDocument({nodeReference, filename}: props): JSX.Element {
    const options = {
        margin: .5,
        filename: filename,
        image: { type: "jpeg", quality: 0.98 },
        html2canvas: { scale: 2 },
        jsPDF: { unit: "in", format: "letter", orientation: "portrait" }
    }

    function displayPDF(): void {
        html2pdf().set(options).from(nodeReference.current).save();
    }

    return <button 
    onClick={()=>displayPDF()}
    style={{
        backgroundColor: "#f33",
        width: "minContent",
        border: "none",
        padding: ".4rem 1.4rem",
        borderRadius: "5px",
        color: "#fff",
        fontFamily: "monospace",
        fontSize:"1.2rem",
        borderBottom: "2px solid #b00",
        cursor: "pointer"
    }}>descargar PDF</button>
}