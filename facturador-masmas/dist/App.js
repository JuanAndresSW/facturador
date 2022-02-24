import React, { Suspense, lazy, useEffect, useState } from "react";
import FallBack from 'components/Fallback/Fallback';
//Controladores de la sesión.
import authenticate from "services/account/authenticate";
import Session from "utils/Session";
//Routing.
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
//Estilos globales.
import "styles/normalize.css";
import "styles/outer.css";
//Importar diferidamente los componentes.
var Home = lazy(function () { return import('pages/Home/Home'); });
var Start = lazy(function () { return import('pages/Start/Start'); });
var SignUp = lazy(function () { return import("pages/SignUp/SignUp"); });
var Login = lazy(function () { return import("pages/Login/Login"); });
var Account = lazy(function () { return import("pages/Account/Account"); });
var About = lazy(function () { return import("pages/About/About"); });
var Error404 = lazy(function () { return import("pages/Error/Error404"); });
/**El componente global de la aplicación. */
export default function App() {
    var _a = useState(undefined), auth = _a[0], setAuth = _a[1];
    //Comprobar la sesión con el servidor en el primer renderizado.
    useEffect(function () {
        authenticate(handleResponse);
    }, []);
    function handleResponse(status, data) {
        if (status === 200) {
            setAuth(true);
            Session.setSession({ token: data, name: "test", active: "0", passive: "0" });
        }
        else
            setAuth(false);
    }
    return ((auth === undefined) ? React.createElement(FallBack, null) :
        React.createElement(BrowserRouter, null,
            React.createElement(Suspense, { fallback: React.createElement(FallBack, null) },
                React.createElement(Routes, null,
                    React.createElement(Route, { path: "/", element: auth ? React.createElement(Home, null) : React.createElement(Navigate, { to: "/inicio" }) }),
                    React.createElement(Route, { path: "/login", element: !auth ? React.createElement(Login, null) : React.createElement(Navigate, { to: "/inicio" }) }),
                    React.createElement(Route, { path: "/inicio/*", element: !auth ? React.createElement(Start, null) : React.createElement(Navigate, { to: "/login" }) }),
                    React.createElement(Route, { path: "/cuenta", element: auth ? React.createElement(Account, null) : React.createElement(Navigate, { to: "/login" }) }),
                    React.createElement(Route, { path: "/signup", element: React.createElement(SignUp, null) }),
                    React.createElement(Route, { path: "/acerca-de/*", element: React.createElement(About, null) }),
                    React.createElement(Route, { path: "*", element: React.createElement(Error404, null) })))));
}