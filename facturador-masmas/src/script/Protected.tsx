import React, { ReactNode } from "react";
import Session from "./Session";
import { Navigate } from "react-router-dom";

type props = {
    children: ReactNode
}
//returns a redirect to login page if user is not logged in
export default function Protected ({ children }:props):JSX.Element {
  return (
    <>
    {Session.isAuthenticated()?children:<Navigate to="/login" />}
    </>
  )
}
