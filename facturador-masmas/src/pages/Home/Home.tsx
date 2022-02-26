import React from "react";
import Header from "components/layout/Header/Header";
import TitleScreen from "./TitleScreen/TitleScreen";
import Features from "./Features/Features";
import Phone from "./Phone/Phone";
import Footer from "components/layout/Footer/Footer";

/**Devuelve la página principal cuando no hay sesión válida en las cookies*/
export default function Home(): JSX.Element {
  return (
    <>
      <Header isAuthenticated={false} />
      <TitleScreen />
      <Features />
      <Phone />
      <Footer />
    </>
  );
}
