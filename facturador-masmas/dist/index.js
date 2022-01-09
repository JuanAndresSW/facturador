import React, { Suspense, lazy } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ReactDOM from 'react-dom';
import reportWebVitals from './reportWebVitals';
import FallBack from './component/FallBack/FallBack';
import './global-style/normalize.css';
import './global-style/outer.css';
//importar diferidamente los contenedores
var Home = lazy(function () { return import('./container/Home'); });
var Start = lazy(function () { return import('./container/Start'); });
var Stats = lazy(function () { return import('./container/Stats'); });
var SignUp = lazy(function () { return import('./container/SignUp'); });
var LogIn = lazy(function () { return import('./container/LogIn'); });
var Account = lazy(function () { return import('./container/Account'); });
var About = lazy(function () { return import('./container/About'); });
var Error404 = lazy(function () { return import('./container/Error404'); });
//mostrar el contenedor indicado
ReactDOM.render(React.createElement(Router, null,
    React.createElement(Suspense, { fallback: React.createElement(FallBack, null) },
        React.createElement(Routes, null,
            React.createElement(Route, { path: "/", element: React.createElement(Home, null) }),
            React.createElement(Route, { path: "/start", element: React.createElement(Start, null) }),
            React.createElement(Route, { path: "/stats", element: React.createElement(Stats, null) }),
            React.createElement(Route, { path: "/signup", element: React.createElement(SignUp, null) }),
            React.createElement(Route, { path: "/account", element: React.createElement(Account, null) }),
            React.createElement(Route, { path: "/about", element: React.createElement(About, null) }),
            React.createElement(Route, { path: "*", element: React.createElement(Error404, null) })))), document.getElementById('root'));
reportWebVitals(console.log);
export default function isUserAuthenticated() {
    return false;
}
