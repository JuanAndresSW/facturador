var __spreadArray = (this && this.__spreadArray) || function (to, from, pack) {
    if (pack || arguments.length === 2) for (var i = 0, l = from.length, ar; i < l; i++) {
        if (ar || !(i in from)) {
            if (!ar) ar = Array.prototype.slice.call(from, 0, i);
            ar[i] = from[i];
        }
    }
    return to.concat(ar || Array.prototype.slice.call(from));
};
import React from "react";
import { BsFileMinusFill, BsFillPlusSquareFill } from "react-icons/bs";
import Field from "../Field/Field";
import './Table.css';
/**
 * Una tabla cuyas celdas son campos de texto o numéricos. Permite agregar y eliminar filas.
 * @param label - El título de la tabla.
 * @param headers - Array de encabezados de columnas. Expresa td: el título, y number: si es un campo numérico.
 * @param maxRows - Número máximo de filas permitidas.
 * @param bind - Array desestructurado asociado al valor del input. El estado (0) es un array bidimensional,
 * con cada sub-array siendo una fila con la misma longitud que la expresada en headers.
 * @example
 * headers = [{label:"foo"}, {label:"bar", number:true}];
 * bind = [table, setTable] = useState([["", "0"]]); //dos columnas y una fila
 */
export default function Table(_a) {
    var _b = _a.label, label = _b === void 0 ? "" : _b, headers = _a.headers, maxRows = _a.maxRows, bind = _a.bind;
    /**Actualiza el valor de una celda de la tabla */
    function setter(rowIndex, colIndex, value, number) {
        if (number === void 0) { number = false; }
        //Ignorar si la celda es numérica y el número es negativo.
        if (number && parseInt(value) < 1)
            return;
        if (value.length > 20)
            return;
        //Hacer una shallow copy y modificarla para forzar re-renderizado.
        var table = __spreadArray([], bind[0], true);
        table[rowIndex][colIndex] = number ? parseInt(value).toString() : value;
        bind[1](table);
    }
    function addRow() {
        var table = __spreadArray([], bind[0], true);
        table.push(headers.map(function (header) { return header.number ? "1" : ""; }));
        bind[1](table);
    }
    function removeRow() {
        var table = __spreadArray([], bind[0], true);
        table.pop();
        bind[1](table);
    }
    return (React.createElement(React.Fragment, null,
        React.createElement("legend", null, label),
        React.createElement("table", null,
            React.createElement("thead", null,
                React.createElement("tr", null, headers.map(function (header, index) {
                    return React.createElement("th", { key: index }, header.th);
                }))),
            React.createElement("tbody", null,
                bind[0].map(function (row, rowIndex) {
                    return React.createElement("tr", { key: rowIndex },
                        //Slice es usado para no exceder el número de columnas.
                        row.slice(0, headers.length).map(function (cell, colIndex) {
                            return React.createElement("td", { key: colIndex },
                                React.createElement(Field, { type: headers[colIndex].number ? "number" : "text", bind: [cell,
                                        function (value) { return setter(rowIndex, colIndex, value, headers[colIndex].number); }] }));
                        }),
                        (rowIndex + 1 === bind[0].length && rowIndex > 0) ?
                            React.createElement("td", { onClick: removeRow },
                                React.createElement(BsFileMinusFill, null)) : null);
                }),
                bind[0].length < maxRows ?
                    React.createElement("tr", null,
                        React.createElement("td", null),
                        React.createElement("td", { onClick: addRow },
                            React.createElement(BsFillPlusSquareFill, null)),
                        React.createElement("td", null))
                    : null))));
}
;
