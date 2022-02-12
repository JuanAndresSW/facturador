import React, { Suspense, lazy, useEffect, useState } from "react";
import FallBack from 'components/Fallback/Fallback';
import Const from "utils/Const";
//Controladores de la sesión.
import authenticate from "services/account/authenticate";
import Session from "utils/Session";
//Routing.
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Protected from 'components/Routing/Protected';
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
    var _a = useState(true), loading = _a[0], setLoading = _a[1];
    //Comprobar la sesión con el servidor en el primer renderizado.
    useEffect(function () {
        authenticate(handleResponse);
    }, []);
    //Borrar la sesión si no existe o no es válida.
    if (!Session.isAuthenticated()) {
        Session.close();
    }
    function handleResponse(status, data) {
        setLoading(false);
        if (status === Const.ok)
            Session.setSession(JSON.parse(data));
    }
    return (loading ? React.createElement(FallBack, null) :
        React.createElement(BrowserRouter, null,
            React.createElement(Suspense, { fallback: React.createElement(FallBack, null) },
                React.createElement(Routes, null,
                    React.createElement(Protected, { reverse: true },
                        React.createElement(Route, { path: "/*", element: React.createElement(Home, null) }),
                        React.createElement(Route, { path: "/login", element: React.createElement(Login, null) })),
                    React.createElement(Protected, null,
                        React.createElement(Route, { path: "/account/*", element: React.createElement(Account, null) }),
                        React.createElement(Route, { path: "/inicio/*", element: React.createElement(Start, null) })),
                    React.createElement(Route, { path: "/signup", element: React.createElement(SignUp, null) }),
                    React.createElement(Route, { path: "/about/*", element: React.createElement(About, null) }),
                    React.createElement(Route, { path: "*", element: React.createElement(Error404, null) })))));
}
