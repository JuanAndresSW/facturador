import React from "react";
import Session from "../script/Session";
import Header from "../components/Header/Header";
import TitleScreen from "../components/TitleScreen/TitleScreen";
import Features from "../components/Features/Features";
import Phone from "../components/Phone/Phone";
import Footer from "../components/Footer/Footer";
import Start from "./Start";

//devuelve la página principal cuando no hay sesión válida en las cookies
export default function Home(): JSX.Element {
  window.history.pushState("", "", "/");
  return Session.isAuthenticated() ? (
    <Start />
  ) : (
    <>
      <Header />
      <TitleScreen />
      <Features />
      <Phone />
      <Footer />
    </>
  );
}
