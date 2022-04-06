import React from "react";
import NotLoggedHeader from "./components/NotLoggedHeader/NotLoggedHeader";
import TitleScreen from "./components/TitleScreen/TitleScreen";
import Features from "./components/FeaturesPreview/FeaturesPreview";
import Phone from "./components/PhonePreview/PhonePreview";
import {Footer} from "styledComponents";
import background from 'assets/img/main2.jpg';

/**Devuelve la página principal cuando no hay sesión válida en las cookies*/
export default function Start(): JSX.Element {
  return (
    <>
      <div style={{background: `top / cover url(${background}) no-repeat fixed`}}>
      <NotLoggedHeader />
      <TitleScreen />
      </div>
      <Features />
      <Phone />
      <Footer />
    </>
  );
}
