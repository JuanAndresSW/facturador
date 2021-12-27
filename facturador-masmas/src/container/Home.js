import React from "react";
import { Navigate } from "react-router-dom";
import isUserAuthenticated from "../index";
import NoLogHeader from "../component/NoLogHeader/NoLogHeader";
import SplashScreen from "../component/SplashScreen/SplashScreen";
import PreviewWindow from "../component/PreviewWindow/PreviewWindow";

export default function Home() {
    if (isUserAuthenticated()) {
        return <Navigate to="/Start" />} 
    else {
        return (
            <>
            <NoLogHeader />
            <SplashScreen />
            <PreviewWindow />
            </>
        )
    }
}