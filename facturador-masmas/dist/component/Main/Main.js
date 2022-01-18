import React from "react";
import { AiFillDollarCircle } from 'react-icons/ai';
import { MdClass, MdPoll, MdPinDrop } from 'react-icons/md';
import TabbedPane from './TabbedPane/TabbedPane';
import { Transaction, Books, Stats, Spots } from './Section';
export default function Main() {
    return (React.createElement(TabbedPane, null,
        React.createElement("div", { label: React.createElement(React.Fragment, null,
                React.createElement(AiFillDollarCircle, null),
                "transacci\u00F3n"), accessKey: "trs" },
            React.createElement(Transaction, null)),
        React.createElement("div", { label: React.createElement(React.Fragment, null,
                React.createElement(MdClass, null),
                "libros"), accessKey: "lib" },
            React.createElement(Books, null)),
        React.createElement("div", { label: React.createElement(React.Fragment, null,
                React.createElement(MdPoll, null),
                "estad\u00EDsticas"), accessKey: "est" },
            React.createElement(Stats, null)),
        React.createElement("div", { label: React.createElement(React.Fragment, null,
                React.createElement(MdPinDrop, null),
                "puntos de venta"), accessKey: "pdv" },
            React.createElement(Spots, null))));
}
