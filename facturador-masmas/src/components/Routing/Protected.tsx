import React, { ReactNode } from "react";
import { Navigate } from "react-router-dom";
import Session from "utils/Session";

type props = {
  children: ReactNode;
  reverse?: boolean;
}
/**
 * Devuelve elementos hijos o un Navigate segun la sesión.
 * @param children Los elementos protegidos de los usuarios sin sesión.
 * @param reverse Cuando es verdadero, `children` se protege en su lugar de los usuarios con sesión.
 */
export default function Protected({ children, reverse = false }: props): JSX.Element {

  return (
    <>
      {
        Session.isAuthenticated() ? 
          reverse ? <Navigate to="/inicio" />  : children :
          reverse ? children                   : <Navigate to="/inicio" />
      }
    </>
  );
}


