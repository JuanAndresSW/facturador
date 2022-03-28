var __assign = (this && this.__assign) || function () {
    __assign = Object.assign || function (t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s)
                if (Object.prototype.hasOwnProperty.call(s, p))
                    t[p] = s[p];
        }
        return t;
    };
    return __assign.apply(this, arguments);
};
import React, { useState } from "react";
/**Formulario para visualizar y cambiar datos de un punto de venta.
 * Precisa del parámetro ID para recuperar los datos del punto específico.*/
export default function PointOptions(_a) {
    var ID = _a.ID;
    //datos del punto de venta
    var _b = useState({
        name: "",
        address: "",
        locality: "",
        postalCode: "",
        email: "",
        phone: "",
        website: "",
        color: "#ffffff",
        logo: null,
    }), pointOfSale = _b[0], setPointOfSale = _b[1];
    var _c = useState(""), pointError = _c[0], setPointError = _c[1];
    return (React.createElement(React.Fragment, null, React.createElement("h1", { className: "title" }, "Crea un punto de venta"), React.createElement("label", null, "Nombre del comercio", React.createElement("input", { type: "text", maxLength: 20, value: pointOfSale.name, onChange: function (e) {
            return setPointOfSale(__assign(__assign({}, pointOfSale), { name: e.target.value }));
        }, required: true })), React.createElement("label", null, "Dirección", React.createElement("input", { type: "text", maxLength: 40, value: pointOfSale.address, onChange: function (e) {
            return setPointOfSale(__assign(__assign({}, pointOfSale), { address: e.target.value }));
        }, required: true })), React.createElement("label", null, "Localidad", React.createElement("input", { type: "text", maxLength: 20, value: pointOfSale.locality, onChange: function (e) {
            return setPointOfSale(__assign(__assign({}, pointOfSale), { locality: e.target.value }));
        }, required: true })), React.createElement("label", null, "Código postal", React.createElement("input", { type: "text", maxLength: 4, value: pointOfSale.postalCode, onChange: function (e) {
            return setPointOfSale(__assign(__assign({}, pointOfSale), { postalCode: e.target.value }));
        }, required: true })), React.createElement("label", null, "Logo", React.createElement("input", { type: "file", accept: ".png, .jpeg, .jpg, .svg", onChange: function (e) {
            if (e.target.files && e.target.files.length > 0)
                setPointOfSale(__assign(__assign({}, pointOfSale), { logo: e.target.files.item(0) }));
        } })), React.createElement("label", null, "Correo electrónico", React.createElement("input", { type: "email", maxLength: 254, value: pointOfSale.email, onChange: function (e) {
            return setPointOfSale(__assign(__assign({}, pointOfSale), { email: e.target.value }));
        } })), React.createElement("label", null, "Número telefónico", React.createElement("input", { type: "tel", maxLength: 20, value: pointOfSale.phone, onChange: function (e) {
            return setPointOfSale(__assign(__assign({}, pointOfSale), { phone: e.target.value }));
        } })), React.createElement("label", null, "Sitio web", React.createElement("input", { type: "url", maxLength: 20, value: pointOfSale.website, onChange: function (e) {
            return setPointOfSale(__assign(__assign({}, pointOfSale), { website: e.target.value }));
        } })), React.createElement("label", null, "Color de los documentos", React.createElement("input", { type: "color", value: pointOfSale.color, onChange: function (e) {
            return setPointOfSale(__assign(__assign({}, pointOfSale), { color: e.target.value }));
        } })), React.createElement("p", { className: "error" }, pointError)));
}
