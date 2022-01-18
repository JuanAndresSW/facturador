import React, { Suspense, lazy } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ReactDOM from 'react-dom';
import FallBack from './component/FallBack/FallBack';
import './global-style/normalize.css';
import './global-style/outer.css';
//importar diferidamente los contenedores
var Home = lazy(function () { return import('./container/Home'); });
var SignUp = lazy(function () { return import('./container/SignUp'); });
var LogIn = lazy(function () { return import('./container/LogIn'); });
var Account = lazy(function () { return import('./container/Account'); });
var About = lazy(function () { return import('./container/About'); });
var Error404 = lazy(function () { return import('./container/Error404'); });
//procesar la dirección URL
ReactDOM.render(React.createElement(Router, null,
    React.createElement(Suspense, { fallback: React.createElement(FallBack, null) },
        React.createElement(Routes, null,
            React.createElement(Route, { path: "/", element: React.createElement(Home, null) }),
            React.createElement(Route, { path: "/signup", element: React.createElement(SignUp, null) }),
            React.createElement(Route, { path: "/account", element: React.createElement(Account, null) }),
            React.createElement(Route, { path: "/about", element: React.createElement(About, null) }),
            React.createElement(Route, { path: "*", element: React.createElement(Error404, null) })))), document.getElementById('root'));
