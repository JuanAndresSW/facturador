import React, { Suspense, lazy, useEffect, useState } from "react";
import { SplashScreen, Error404 } from 'styledComponents';
//Controladores de la sesión.
import Session from "services/Session";
//Routing.
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
//Estilos globales.
import "styles/normalize.css";
import "styles/outer.css";
//Importar diferidamente los componentes.
var Start = lazy(function () { return import('pages/Login/Start/Start'); });
var Home = lazy(function () { return import('pages/Home/Home'); });
var SignUp = lazy(function () { return import("pages/SignUp/SignUp"); });
var Login = lazy(function () { return import("pages/Login/Login"); });
var Account = lazy(function () { return import("pages/Account/Account"); });
var About = lazy(function () { return import("pages/About/About"); });
/**El componente global de la aplicación. */
export default function App() {
    //Determina si se le debe dar permisos de sesión al usuario.
    var _a = useState(undefined), auth = _a[0], setAuth = _a[1];
    //Comprobar la sesión con el servidor en el primer renderizado.
    useEffect(function () { return Session.getByToken(handleResponse); }, []);
    //Almacenar la respuesta final del servidor.
    function handleResponse(status, data) {
        try {
            Session.setSession(JSON.parse(data));
        }
        catch (_a) {
            console.error(data);
        }
        setAuth(status === 200);
    }
    return ((auth === undefined) ? React.createElement(SplashScreen, null) :
        React.createElement(BrowserRouter, null, React.createElement(Suspense, { fallback: React.createElement(SplashScreen, null) }, React.createElement(Routes, null, React.createElement(Route, { path: "/", element: !auth ? React.createElement(Start, null) : React.createElement(Navigate, { to: "/inicio" }) }), React.createElement(Route, { path: "/ingresar", element: !auth ? React.createElement(Login, null) : React.createElement(Navigate, { to: "/inicio" }) }), React.createElement(Route, { path: "/inicio/*", element: !auth ? React.createElement(Home, null) : React.createElement(Navigate, { to: "/ingresar" }) }), React.createElement(Route, { path: "/cuenta", element: auth ? React.createElement(Account, null) : React.createElement(Navigate, { to: "/ingresar" }) }), React.createElement(Route, { path: "/registrarse", element: React.createElement(SignUp, null) }), React.createElement(Route, { path: "/acerca-de/*", element: React.createElement(About, null) }), React.createElement(Route, { path: "*", element: React.createElement(Error404, null) })))));
}
