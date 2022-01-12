import React from "react";
import { Navigate } from "react-router-dom";
import {isUserAuthenticated} from "../script/cookies";
import Header from "../component/Header/Header";
import TitleScreen from "../component/TitleScreen/TitleScreen";
import Features from "../component/Features/Features";
import Phone from "../component/Phone/Phone";
import Footer from "../component/Footer/Footer";
import Start from "./Start";

export default function Home():JSX.Element {
    return (isUserAuthenticated())? <Start /> :
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