import React from "react";
import { Navigate } from "react-router-dom";
import Session from "../script/Session";
import Header from "../components/Header/Header";
import TitleScreen from "../components/TitleScreen/TitleScreen";
import Features from "../components/Features/Features";
import Phone from "../components/Phone/Phone";
import Footer from "../components/Footer/Footer";
import Start from "./Start";

export default function Home():JSX.Element {
    window.history.pushState("","","/");
    return (Session.isAuthenticated())? <Start /> :
    (
        <>
            <Header />
            <TitleScreen />
            <Features />
            <Phone />
            <Footer />
        </>
    )
            
}