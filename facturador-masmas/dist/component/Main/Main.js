import React from "react";
import { AiFillDollarCircle } from 'react-icons/ai';
import { MdClass, MdPoll, MdPinDrop } from 'react-icons/md';
import TabbedPane from './TabbedPane/TabbedPane';
export default function Main() {
    return (React.createElement(TabbedPane, null,
        React.createElement("div", { label: React.createElement(React.Fragment, null,
                React.createElement(AiFillDollarCircle, null),
                "transacci\u00F3n") }, Transaction),
        React.createElement("div", { label: React.createElement(React.Fragment, null,
                React.createElement(MdClass, null),
                "libros") }, "Pesta\u00F1a 2"),
        React.createElement("div", { label: React.createElement(React.Fragment, null,
                React.createElement(MdPoll, null),
                "estad\u00EDsticas") }, "Pesta\u00F1a 3"),
        React.createElement("div", { label: React.createElement(React.Fragment, null,
                React.createElement(MdPinDrop, null),
                "punto de venta") }, "Pesta\u00F1a 4")));
}
var Transaction = (React.createElement("div", null, "HEllo"));
/**📕 libros 📊 estadísticas 📍 puntos de venta*/ 
