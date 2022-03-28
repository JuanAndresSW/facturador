import React, { useEffect } from "react";
import Aos from "aos";
import "aos/dist/aos.css";
import "./FeaturesPreview.css";
import documento from "assets/svg/feat-doc.svg";
import punto from "assets/svg/feat-punto.svg";
import libro from "assets/svg/feat-libro.svg";
import grafico from "assets/svg/feat-grafico.svg";
import open from "assets/svg/feat-github.svg";
var feats = [
    React.createElement(React.Fragment, null, React.createElement("img", { src: documento }), React.createElement("h3", null, "Crea documentos comerciales"), React.createElement("p", null, "Lorem ipsum dolor sit amet consectetur adipisicing elit. Odio, reiciendis fuga dolore non veniam optio dolorem, beatae sunt libero modi quos assumenda ut consequuntur. Itaque aut molestias ullam cum quia.")),
    React.createElement(React.Fragment, null, React.createElement("img", { src: punto }), React.createElement("h3", null, "Administra puntos de venta"), React.createElement("p", null, "Lorem ipsum dolor sit amet consectetur adipisicing elit. Excepturi debitis in nemo amet temporibus incidunt eveniet quam error nostrum, omnis labore repudiandae perferendis repellendus, molestiae vitae voluptatum minus qui? Et!")),
    React.createElement(React.Fragment, null, React.createElement("img", { src: libro }), React.createElement("h3", null, "Libros autom\u00E1ticos"), React.createElement("p", null, "Lorem ipsum dolor sit amet consectetur adipisicing elit. Facere sapiente nulla recusandae! Facilis eveniet iste dolorum illo culpa temporibus architecto, corporis eligendi optio quos tempore ut in natus, neque excepturi?")),
    React.createElement(React.Fragment, null, React.createElement("img", { src: grafico }), React.createElement("h3", null, "Reportes personalizados"), React.createElement("p", null, "Lorem ipsum dolor sit amet consectetur adipisicing elit. Odio, reiciendis fuga dolore non veniam optio dolorem, beatae sunt libero modi quos assumenda ut consequuntur. Itaque aut molestias ullam cum quia.")),
    React.createElement(React.Fragment, null, React.createElement("img", { src: open }), React.createElement("h3", null, "Gratis y de acceso libre"), React.createElement("p", null, "Amet consectetur adipisicing elit. Odio, reiciendis fuga dolore non veniam optio dolorem, beatae sunt libero", " ")),
];
//returns a list of application features that animates on scroll
export default function FeaturesPreview() {
    useEffect(function () {
        Aos.init({}); //iniciar las animaciones globales de Aos
    }, []);
    return (React.createElement("div", { className: "features" }, feats.map(function (feat, index) { return (React.createElement("div", { key: index.toString(), "data-aos": index % 2 === 0 ? "fade-right" : "fade-left", "data-aos-anchor-placement": "center-bottom", "data-aos-offset": "150" }, feat)); })));
}
