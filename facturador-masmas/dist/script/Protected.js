import React from "react";
import Session from "./Session";
import { Navigate } from "react-router-dom";
//returns a redirect to login page if user is not logged in
export default function Protected(_a) {
    var children = _a.children;
    return (React.createElement(React.Fragment, null, Session.isAuthenticated() ? children : React.createElement(Navigate, { to: "/login" })));
}
