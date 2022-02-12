var __assign = (this && this.__assign) || function () {
    __assign = Object.assign || function(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
                t[p] = s[p];
        }
        return t;
    };
    return __assign.apply(this, arguments);
};
import React, { useEffect, useState } from "react";
import fetchFormData from "services/operation/fetchFormData";
//Componentes input.
import SelectPartakers from "./SelectPartakers";
import ProductTable from "./ProductTable";
import Observations from "./Observations";
import PurchaseExtra from "./PurchaseExtra";
import Type from "./Type";
/**Devuelve un formulario que recolecta los datos necesitados por el back-end para generar un documento.
 * @param flux Flujo de emisión del documento. Valores aceptados: 'in' | 'out'.
 * @param type Tipo de documento comercial en inglés y kebab-case.
*/
export default function OperationForm(_a) {
    var flux = _a.flux, type = _a.type;
    //Solicitar `dataToShow` en el primer renderizado.
    useEffect(function () {
        fetchFormData(handleResponse);
    }, []);
    //Maneja la respuesta de los requests al servidor
    function handleResponse(state, data) {
        switch (state) {
            case 0: break;
            case 200:
                setDataToShow(__assign(__assign({}, dataToShow), { partakers: JSON.parse(data), currentPoint: JSON.parse(data).pointsOfSale[0], currentPartner: JSON.parse(data).partners[0] }));
                setDataToSend(__assign(__assign({}, dataToSend), { pointOfSale: JSON.parse(data).pointsOfSale[0], partner: JSON.parse(data).partners[0] }));
                break;
        }
    }
    //Los datos del servidor necesarios para mostrar el formulario.
    var _b = useState({
        partakers: null,
        currentPoint: null,
        currentPartner: null,
        currentGroup: null
    }), dataToShow = _b[0], setDataToShow = _b[1];
    //Los datos a ser enviados al servidor.
    var _c = useState({
        documentType: type,
        pointOfSale: undefined,
        partner: undefined,
        group: undefined
    }), dataToSend = _c[0], setDataToSend = _c[1];
    /**Las funciones utilizadas para cambiar `dataToSend`.*/
    var setter = {
        setPointOfSale: function (point) {
            setDataToSend(__assign(__assign({}, dataToSend), { pointOfSale: point.id }));
            setDataToShow(__assign(__assign({}, dataToShow), { currentPoint: point }));
        },
        setPartner: function (partner) {
            setDataToSend(__assign(__assign({}, dataToSend), { partner: partner.id }));
            setDataToShow(__assign(__assign({}, dataToShow), { currentPartner: partner }));
        }
    };
    return (React.createElement(React.Fragment, null,
        React.createElement("form", { id: "form", method: "post", target: "_blank", className: "panel" },
            React.createElement("h1", { className: "title" }, getTitle(type, flux)),
            React.createElement(SelectPartakers, { setter: setter, dataToShow: dataToShow, flux: flux }),
            React.createElement(Type, { type: type }),
            React.createElement(ProductTable, { type: type }),
            React.createElement(Observations, { type: type }),
            React.createElement(PurchaseExtra, { type: type }),
            React.createElement("button", null, "Generar"))));
}
/**Encuentra un título apropiado para el formulario.*/
function getTitle(type, flux) {
    var title;
    switch (type) {
        case "purchase-order":
            title = "Nueva orden de compra ";
            break;
        case "remittance":
            title = "Nuevo remito ";
            break;
        case "invoice":
            title = "Nueva factura ";
            break;
        case "debit-note":
            title = "Nueva nota de débito ";
            break;
        case "credit-note":
            title = "Nueva nota de crédito ";
            break;
        case "receipt-x":
            title = "Nuevo recibo X ";
            break;
        case "receipt":
            title = "Nuevo recibo simple ";
            break;
        case "promissory-note":
            title = "Nuevo pagaré ";
            break;
        case "check":
            title = "Nuevo cheque ";
            break;
        case "other":
            title = "Nueva operación ";
            break;
        default: return "Algo salió mal";
    }
    switch (flux) {
        case "in":
            title += "de entrada";
            break;
        case "out":
            title += "de salida";
            break;
        default: return;
    }
    return title;
}
