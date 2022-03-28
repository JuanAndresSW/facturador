import React from "react";
import NewPoint from './NewPoint';
import PointOptions from './PointOptions';
import { Section, Option } from "components/layout";
import { BiPlusCircle } from "react-icons/bi";
import { Route, Routes } from "react-router-dom";
//ID del punto de venta elegido para mostrar en detalle.
var pointID = -1;
/**Muestra opciones para crear, ver y editar puntos de venta. */
export default function Points() {
    return (React.createElement(Routes, null, React.createElement(Route, { index: true, element: selectionScreen }), React.createElement(Route, { path: "/nuevo", element: React.createElement(NewPoint, null) }), React.createElement(Route, { path: "/opciones", element: React.createElement(PointOptions, { ID: pointID }) })));
}
/**Pantalla de selección de opciones de punto de venta. */
var selectionScreen = (React.createElement(React.Fragment, null, React.createElement(Section, { label: "Nuevo punto" }, React.createElement(Option, { label: "Nuevo punto de venta", icon: React.createElement(BiPlusCircle, null), link: "./nuevo" })), React.createElement(Section, { label: "Administrar puntos de venta" }, "(para esto es necesario recuperar la lista de puntos de venta del comerciante)")));
