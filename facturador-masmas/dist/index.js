import React, { Suspense, lazy } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import ReactDOM from "react-dom";
import FallBack from "./components/FallBack/FallBack";
import "./style/normalize.css";
import "./style/outer.css";
//deferred import components
var Home = lazy(function () { return import("./container/Home"); });
var SignUp = lazy(function () { return import("./container/SignUp"); });
var Login = lazy(function () { return import("./container/Login"); });
var Account = lazy(function () { return import("./container/Account"); });
var About = lazy(function () { return import("./container/About"); });
var Error404 = lazy(function () { return import("./container/Error404"); });
//process url address
ReactDOM.render(React.createElement(Router, null,
    React.createElement(Suspense, { fallback: React.createElement(FallBack, null) },
        React.createElement(Routes, null,
            React.createElement(Route, { path: "/*", element: React.createElement(Home, null) }),
            React.createElement(Route, { path: "/signup", element: React.createElement(SignUp, null) }),
            React.createElement(Route, { path: "/login", element: React.createElement(Login, null) }),
            React.createElement(Route, { path: "/account/*", element: React.createElement(Account, null) }),
            React.createElement(Route, { path: "/about/*", element: React.createElement(About, null) }),
            React.createElement(Route, { path: "*", element: React.createElement(Error404, null) })))), document.getElementById("root"));
