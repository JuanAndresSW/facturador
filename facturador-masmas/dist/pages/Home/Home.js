import React from "react";
import Header from "components/Header/Header";
import TitleScreen from "./TitleScreen/TitleScreen";
import Features from "./Features/Features";
import Phone from "./Phone/Phone";
import Footer from "components/Footer/Footer";
//Devuelve la página principal cuando no hay sesión válida en las cookies
export default function Home() {
    return (React.createElement(React.Fragment, null,
        React.createElement(Header, null),
        React.createElement(TitleScreen, null),
        React.createElement(Features, null),
        React.createElement(Phone, null),
        React.createElement(Footer, null)));
}
