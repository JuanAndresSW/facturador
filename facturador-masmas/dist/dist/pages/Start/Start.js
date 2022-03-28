import React from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import LoggedHeader from './components/Header/LoggedHeader';
import Subheader from './components/SubHeader/Subheader';
import NavBar from './components/NavBar/NavBar';
import Footer from 'styledComponents/Footer/Footer';
import { Operation, Books, Stats, Points } from './components/main';
import { AiFillDollarCircle } from 'react-icons/ai';
import { MdClass, MdPoll, MdPinDrop } from 'react-icons/md';
var paths = {
    operation: "/operacion",
    books: "/libros",
    stats: "/estadisticas",
    points: "/puntos-de-venta"
};
//key, si bien obligatoria, no puede ser leída como propiedad, por tanto el uso de accessKey
var tabs = [
    React.createElement("div", { tabHeader: React.createElement(React.Fragment, null, React.createElement(AiFillDollarCircle, null), "Operaci\u00F3n"), key: paths.operation, accessKey: '.' + paths.operation }, React.createElement(Operation, null)),
    React.createElement("div", { tabHeader: React.createElement(React.Fragment, null, React.createElement(MdClass, null), "Libros"), key: paths.books, accessKey: '.' + paths.books }, React.createElement(Books, null)),
    React.createElement("div", { tabHeader: React.createElement(React.Fragment, null, React.createElement(MdPoll, null), "Estad\u00EDstica"), key: paths.stats, accessKey: '.' + paths.stats }, React.createElement(Stats, null)),
    React.createElement("div", { tabHeader: React.createElement(React.Fragment, null, React.createElement(MdPinDrop, null), "Puntos de venta"), key: paths.points, accessKey: '.' + paths.points }, React.createElement(Points, null))
];
//devuelve la página principal dependiente de una sesión iniciada
export default function Start() {
    return (React.createElement(React.Fragment, null, React.createElement(LoggedHeader, null), React.createElement(Subheader, null), React.createElement(NavBar, null, tabs), React.createElement(Routes, null, React.createElement(Route, { index: true, element: React.createElement(Navigate, { to: '.' + paths.operation }) }), React.createElement(Route, { path: paths.operation + "/*", element: React.createElement(Operation, null) }), React.createElement(Route, { path: paths.books + "/*", element: React.createElement(Books, null) }), React.createElement(Route, { path: paths.stats + "/*", element: React.createElement(Stats, null) }), React.createElement(Route, { path: paths.points + "/*", element: React.createElement(Points, null) }), React.createElement(Route, { path: "*", element: React.createElement(Navigate, { to: "/" }) })), React.createElement(Footer, null)));
}
