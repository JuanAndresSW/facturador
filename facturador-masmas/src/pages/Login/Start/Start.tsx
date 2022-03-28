import React from "react";
import NotLoggedHeader from "./components/NotLoggedHeader/NotLoggedHeader";
import TitleScreen from "./components/TitleScreen/TitleScreen";
import Features from "./components/FeaturesPreview/FeaturesPreview";
import Phone from "./components/PhonePreview/PhonePreview";
import {Footer} from "styledComponents";

/**Devuelve la página principal cuando no hay sesión válida en las cookies*/
export default function Start(): JSX.Element {
  return (
    <>
      <NotLoggedHeader />
      <TitleScreen />
      <Features />
      <Phone />
      <Footer />
    </>
  );
}
