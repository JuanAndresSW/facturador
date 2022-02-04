import React, { Suspense, lazy } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import ReactDOM from "react-dom";
import FallBack from "./components/FallBack/FallBack";
import "./style/normalize.css";
import "./style/outer.css";
//importar diferidamente los componentes
var Home = lazy(function () { return import("./container/Home"); });
var SignUp = lazy(function () { return import("./container/SignUp"); });
var Login = lazy(function () { return import("./container/Login"); });
var Account = lazy(function () { return import("./container/Account"); });
var About = lazy(function () { return import("./container/About"); });
var Error404 = lazy(function () { return import("./container/Error404"); });
//procesar la dirección URL
ReactDOM.render(React.createElement(BrowserRouter, null,
    React.createElement(Suspense, { fallback: React.createElement(FallBack, null) },
        React.createElement(Routes, null,
            React.createElement(Route, { path: "/*", element: React.createElement(Home, null) }),
            React.createElement(Route, { path: "/signup", element: React.createElement(SignUp, null) }),
            React.createElement(Route, { path: "/login", element: React.createElement(Login, null) }),
            React.createElement(Route, { path: "/account/*", element: React.createElement(Account, null) }),
            React.createElement(Route, { path: "/about/*", element: React.createElement(About, null) }),
            React.createElement(Route, { path: "*", element: React.createElement(Error404, null) })))), document.getElementById("root"));
