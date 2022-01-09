import React from "react";
import { Navigate } from "react-router-dom";
import isUserAuthenticated from "../index";
import Header from "../component/Header/Header";
import TitleScreen from "../component/TitleScreen/TitleScreen";
import Features from "../component/Features/Features";
import Phone from "../component/Phone/Phone";
import Footer from "../component/Footer/Footer";

export default function Home():JSX.Element {
    return (isUserAuthenticated())? <Navigate to="/Start" /> :
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