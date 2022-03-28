import React from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
//Componentes.
import LoggedHeader from './components/LoggedHeader/LoggedHeader';
import Subheader from './components/SubHeader/Subheader';
import NavBar from './components/NavBar/NavBar';
//Stateless.
import { Footer } from 'styledComponents';
import { AiFillDollarCircle } from 'react-icons/ai';
import { MdClass, MdPoll, MdPinDrop } from 'react-icons/md';
//Páginas.
import Operation from 'pages/Operation/Operation';
import Books from 'pages/Books/Books';
import Stats from 'pages/Stats/Stats';
import Points from 'pages/Points/Points';
var paths = {
    operation: "/operacion",
    books: "/libros",
    stats: "/estadisticas",
    points: "/puntos-de-venta"
};
var tabs = [
    { path: paths.operation, icon: React.createElement(AiFillDollarCircle, null), label: 'Operación' },
    { path: paths.books, icon: React.createElement(MdClass, null), label: 'Libros' },
    { path: paths.stats, icon: React.createElement(MdPoll, null), label: 'Estadística' },
    { path: paths.points, icon: React.createElement(MdPinDrop, null), label: 'Puntos de venta' }
];
//devuelve la página principal dependiente de una sesión iniciada
export default function Home() {
    return (React.createElement(React.Fragment, null,
        React.createElement(LoggedHeader, null),
        React.createElement(Subheader, null),
        React.createElement(NavBar, { tabs: tabs }),
        React.createElement(Routes, null,
            React.createElement(Route, { index: true, element: React.createElement(Navigate, { to: '.' + paths.operation }) }),
            React.createElement(Route, { path: paths.operation + "/*", element: React.createElement(Operation, null) }),
            React.createElement(Route, { path: paths.books + "/*", element: React.createElement(Books, null) }),
            React.createElement(Route, { path: paths.stats + "/*", element: React.createElement(Stats, null) }),
            React.createElement(Route, { path: paths.points + "/*", element: React.createElement(Points, null) }),
            React.createElement(Route, { path: "*", element: React.createElement(Navigate, { to: "/" }) })),
        React.createElement(Footer, null)));
}
