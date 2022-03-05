import React, { Suspense, lazy, useEffect, useState } from "react";
import SplashScreen from 'components/SplashScreen/SplashScreen';
//Controladores de la sesión.
import Session from "services/Session";
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
    //Determina si se le debe dar permisos de sesión al usuario.
    var _a = useState(undefined), auth = _a[0], setAuth = _a[1];
    //Comprobar la sesión con el servidor en el primer renderizado.
    useEffect(function () { return Session.getByToken(handleResponse); }, []);
    //Comprobar la respuesta final del servidor.
    function handleResponse(status) {
        if (status === 200) {
            setAuth(true);
        }
        else
            setAuth(false);
    }
    return ((auth === undefined) ? React.createElement(SplashScreen, null) :
        React.createElement(BrowserRouter, null,
            React.createElement(Suspense, { fallback: React.createElement(SplashScreen, null) },
                React.createElement(Routes, null,
                    React.createElement(Route, { path: "/", element: !auth ? React.createElement(Home, null) : React.createElement(Navigate, { to: "/inicio" }) }),
                    React.createElement(Route, { path: "/login", element: !auth ? React.createElement(Login, null) : React.createElement(Navigate, { to: "/inicio" }) }),
                    React.createElement(Route, { path: "/inicio/*", element: auth ? React.createElement(Start, null) : React.createElement(Navigate, { to: "/login" }) }),
                    React.createElement(Route, { path: "/cuenta", element: auth ? React.createElement(Account, null) : React.createElement(Navigate, { to: "/login" }) }),
                    React.createElement(Route, { path: "/signup", element: React.createElement(SignUp, null) }),
                    React.createElement(Route, { path: "/acerca-de/*", element: React.createElement(About, null) }),
                    React.createElement(Route, { path: "*", element: React.createElement(Error404, null) })))));
}
/**
 * ESTA ES EL HEADER DE LA REQUEST:
 *
 * POST /api/auth/init undefined
 * Host: localhost:8080
 * User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:96.0) Gecko/20100101 Firefox/96.0
 * Accept: +/* (ESE + ES UN ASTERISCO XD)
 * Accept-Language: en-US,en;q=0.5
 * Accept-Encoding: gzip, deflate
 * Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MSIsImlzcyI6Ii9sb2dpbiIsImV4cCI6MTY0NjI4NTU4OCwiaWF0IjoxNjQ2MjU2Nzg4LCJyb2wiOiJNQUlOIn0.xauytCdRgtoKK2BvD9nFHNRkQPn5CTo6P4H4JW-xN-U
 * Origin: http://localhost:3000
 * DNT: 1
 * Connection: keep-alive
 * Referer: http://localhost:3000/
 * Sec-Fetch-Dest: empty
 * Sec-Fetch-Mode: cors
 * Sec-Fetch-Site: same-site
 */ 
