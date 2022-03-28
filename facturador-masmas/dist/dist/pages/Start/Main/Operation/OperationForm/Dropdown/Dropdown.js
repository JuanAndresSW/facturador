import React, { useState } from "react";
import { Link } from "react-router-dom";
import { BiCaretDown, BiCaretUp, BiPlusCircle } from "react-icons/bi";
import OutsideClickHandler from "react-outside-click-handler";
import './Dropdown.css';
/**
 * Un input `<select>` personalizado.
 * @param label El título del input.
 * @param content Un array de arrays de objetos para armar las opciones.
 * @param current El objeto actualmente seleccionado.
 * @param link Enlace para agregar más opciones.
 * @param setter La función para cambiar el objeto seleccionado.
 * @param allow1OrLess Si el input debe mostrarse cuando hay menos de 2 opciones.
 */
export default function Dropdown(_a) {
    var label = _a.label, content = _a.content, link = _a.link, current = _a.current, setter = _a.setter, allow1OrLess = _a.allow1OrLess;
    //Establece un controlador de estado para el dropdown.
    var _b = useState(false), controller = _b[0], setController = _b[1];
    return (current === undefined ? React.createElement(React.Fragment, null, "Error: 0 puntos de venta recibidos") :
        (content === undefined || current === null) ? React.createElement(React.Fragment, null, "Cargando...") :
            (!allow1OrLess && content.length < 2) ? React.createElement("div", null, current.name + ': ' + current.address) :
                React.createElement("label", null, " ", label, React.createElement(OutsideClickHandler, { onOutsideClick: function () { return setController(false); } }, React.createElement("div", { className: "wrapper" }, React.createElement("div", { className: "dropdown", onClick: function () { return setController(!controller); } }, React.createElement("div", null, React.createElement("h3", null, current.name), React.createElement("p", null, current.address)), controller ? React.createElement(BiCaretUp, null) : React.createElement(BiCaretDown, null)), React.createElement("div", { className: controller ? "drop-content" : 'drop-content disable' }, content.length > 10 ?
                    React.createElement("input", { type: "search", placeholder: "Buscar por direcci\u00F3n" }) : React.createElement(React.Fragment, null), content.map(function (option, index) {
                    return (React.createElement("div", { key: index, onClick: function () {
                            setter(option);
                            setController(false);
                        }, className: option.id === current.id ? "highlight" : "" }, React.createElement("h3", null, option.name), option.address));
                }), React.createElement(Link, { to: link }, React.createElement(BiPlusCircle, null)))))));
}
