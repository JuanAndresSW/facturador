import React from "react";
import { Navigate } from "react-router-dom";
import isUserAuthenticated from "../index";
import Header from "../component/Header/Header";
import TitleScreen from "../component/TitleScreen/TitleScreen";
import Features from "../component/Features/Features";
import Phone from "../component/Phone/Phone";
import Footer from "../component/Footer/Footer";
export default function Home() {
    return (isUserAuthenticated()) ? React.createElement(Navigate, { to: "/Start" }) :
        (React.createElement(React.Fragment, null,
            React.createElement(Header, null),
            React.createElement(TitleScreen, null),
            React.createElement(Features, null),
            React.createElement(Phone, null),
            React.createElement(Footer, null)));
}
