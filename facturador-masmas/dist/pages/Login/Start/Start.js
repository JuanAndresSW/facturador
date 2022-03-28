import React from "react";
import NotLoggedHeader from "./components/NotLoggedHeader/NotLoggedHeader";
import TitleScreen from "./components/TitleScreen/TitleScreen";
import Features from "./components/FeaturesPreview/FeaturesPreview";
import Phone from "./components/PhonePreview/PhonePreview";
import { Footer } from "styledComponents";
/**Devuelve la página principal cuando no hay sesión válida en las cookies*/
export default function Start() {
    return (React.createElement(React.Fragment, null,
        React.createElement(NotLoggedHeader, null),
        React.createElement(TitleScreen, null),
        React.createElement(Features, null),
        React.createElement(Phone, null),
        React.createElement(Footer, null)));
}
