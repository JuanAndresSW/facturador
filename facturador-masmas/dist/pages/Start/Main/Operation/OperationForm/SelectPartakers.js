import React, { useState } from "react";
import { BiChevronsDown, BiChevronsUp, BiGroup, BiUser } from "react-icons/bi";
import Dropdown from "./Dropdown/Dropdown";
/**Devuelve una lista de selección de puntos de venta, socios y grupos.
 * @param setter La función para cambiar los valores de los input que se enviarán al servidor.
 * @param dataToShow Los datos a partir de cuales se construyen los input.
 * @param flux El flujo de emisión del documento.
*/
export default function SelectPartakers(_a) {
    var _b, _c, _d;
    var setter = _a.setter, dataToShow = _a.dataToShow, flux = _a.flux;
    //controlador para el tipo de tercero
    var _e = useState('partner'), thirdType = _e[0], setThirdType = _e[1];
    return (React.createElement(React.Fragment, null,
        React.createElement("div", { className: "form-section" },
            React.createElement(Dropdown, { label: "Punto de venta", content: (_b = dataToShow.partakers) === null || _b === void 0 ? void 0 : _b.pointsOfSale, link: "/puntos-de-venta", current: dataToShow.currentPoint, setter: setter.setPointOfSale, allow1OrLess: false }),
            flux === 'in' ? React.createElement(BiChevronsUp, null) : React.createElement(BiChevronsDown, null),
            ((_c = dataToShow === null || dataToShow === void 0 ? void 0 : dataToShow.partakers) === null || _c === void 0 ? void 0 : _c.groups) === undefined ? React.createElement(React.Fragment, null) : React.createElement("div", { className: 'custom-radio' },
                React.createElement(BiUser, null),
                React.createElement(BiGroup, null)),
            React.createElement(Dropdown, { label: "Terceros", content: (_d = dataToShow.partakers) === null || _d === void 0 ? void 0 : _d.partners, link: "/puntos-de-venta", current: dataToShow.currentPartner, setter: setter.setPartner, allow1OrLess: true }))));
}
