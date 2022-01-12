import React from "react";
import { isUserAuthenticated } from "../scripts/cookies";
import Header from "../component/Header/Header";
import TitleScreen from "../component/TitleScreen/TitleScreen";
import Features from "../component/Features/Features";
import Phone from "../component/Phone/Phone";
import Footer from "../component/Footer/Footer";
import Start from "./Start";
export default function Home() {
    return (isUserAuthenticated()) ? React.createElement(Start, null) :
        (React.createElement(React.Fragment, null,
            React.createElement(Header, null),
            React.createElement(TitleScreen, null),
            React.createElement(Features, null),
            React.createElement(Phone, null),
            React.createElement(Footer, null)));
}
