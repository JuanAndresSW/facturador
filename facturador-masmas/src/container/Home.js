import React from "react";
import { Navigate } from "react-router-dom";
import isUserAuthenticated from "../index";
import Header from "../component/Header/Header";
import SplashScreen from "../component/SplashScreen/SplashScreen";
import PreviewWindow from "../component/PreviewWindow/PreviewWindow";
import WhyThisApp from "./../component/WhyThisApp/WhyThisApp";
import Footer from "./../component/Footer/Footer";


export default function Home() {
    if (isUserAuthenticated()) {
        return <Navigate to="/Start" />} 
    else {
        return (
            <>
            <Header />
            <SplashScreen />
            <PreviewWindow />
            <WhyThisApp />
            <Footer />
            </>
        )
    }
}