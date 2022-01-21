import React from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import Header from '../components/Header/Header';
import Subheader from '../components/SubHeader/Subheader';
import NavBar from '../components/NavBar/NavBar';
import Footer from '../components/Footer/Footer';
import { Transaction, Books, Stats, Spots } from '../components/Main';
import Protected from '../script/Protected';
import { AiFillDollarCircle } from 'react-icons/ai';
import { MdClass, MdPoll, MdPinDrop } from 'react-icons/md';
var paths = {
    transaction: "/transaccion",
    books: "/libros",
    stats: "/estadisticas",
    spots: "/puntos-de-venta"
};
//key, although necessary, cannot be used as a property, hence the use of accessKey
var tabs = [
    React.createElement("div", { tabHeader: React.createElement(React.Fragment, null,
            React.createElement(AiFillDollarCircle, null),
            "Transacci\u00F3n"), key: paths.transaction, accessKey: paths.transaction },
        React.createElement(Transaction, null)),
    React.createElement("div", { tabHeader: React.createElement(React.Fragment, null,
            React.createElement(MdClass, null),
            "Libros"), key: paths.books, accessKey: paths.books },
        React.createElement(Books, null)),
    React.createElement("div", { tabHeader: React.createElement(React.Fragment, null,
            React.createElement(MdPoll, null),
            "Estad\u00EDstica"), key: paths.stats, accessKey: paths.stats },
        React.createElement(Stats, null)),
    React.createElement("div", { tabHeader: React.createElement(React.Fragment, null,
            React.createElement(MdPinDrop, null),
            "Puntos de venta"), key: paths.spots, accessKey: paths.spots },
        React.createElement(Spots, null))
];
export default function Start() {
    return (React.createElement(Protected, null,
        React.createElement(Header, null),
        React.createElement(Subheader, null),
        React.createElement(NavBar, null, tabs),
        React.createElement(Routes, null,
            React.createElement(Route, { path: '', element: React.createElement(Navigate, { to: paths.transaction }) }),
            React.createElement(Route, { path: paths.transaction + "/", element: React.createElement(Transaction, null) }),
            React.createElement(Route, { path: paths.books + "/*", element: React.createElement(Books, null) }),
            React.createElement(Route, { path: paths.stats + "/*", element: React.createElement(Stats, null) }),
            React.createElement(Route, { path: paths.spots + "/*", element: React.createElement(Spots, null) }),
            React.createElement(Route, { path: "*", element: React.createElement(Navigate, { to: "/" }) })),
        React.createElement(Footer, null)));
}
