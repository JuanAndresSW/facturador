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
//React.
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
//Componentes del formulario.
import { DateInput, ErrorMessage, Field, Form, Radio, Select, Submit, Switch, Table, Textarea } from "components/formComponents";
import { Section, Cond, FlexDiv } from 'components/layout';
import { BiChevronsDown, BiChevronsUp, BiGroup, BiPlusCircle, BiUser } from "react-icons/bi";
//Utilidades.
import getDocumentTitle from 'utils/getDocumentTitle';
//Servicios.
import PointOfSale from 'services/PointOfSale';
import Partner from 'services/Partner';
import Group from 'services/Group';
/**
 * Devuelve un formulario que recolecta los datos necesitados por el back-end para generar un documento comercial.
 * @param flux Flujo de emisión del documento. Valores aceptados: 'in' | 'out'.
 * @param type Tipo de documento comercial en inglés y kebab-case.
 */
export default function OperationForm(_a) {
    var flux = _a.flux, type = _a.type;
    //Solicitar los datos a mostrar en el primer renderizado.
    useEffect(function () {
        PointOfSale.getArray(function (state, data) {
            if (state === 200)
                setDisplay(__assign(__assign({}, display), { pointsOfSale: JSON.parse(data) }));
        });
        Partner.getArray(function (state, data) {
            if (state === 200)
                setDisplay(__assign(__assign({}, display), { partners: JSON.parse(data) }));
        });
        Group.getArray(function (state, data) {
            if (state === 200)
                setDisplay(__assign(__assign({}, display), { groups: JSON.parse(data) }));
        });
    }, []);
    //Los datos del servidor necesarios para mostrar el formulario.
    var _b = useState({
        pointsOfSale: undefined,
        partners: undefined,
        groups: undefined,
        root: false,
    }), display = _b[0], setDisplay = _b[1];
    //Mensaje de error al generar el documento.
    var _c = useState("hello world"), error = _c[0], setError = _c[1];
    // #### Los datos a ser enviados al servidor. #### //
    //Todos.
    var _d = useState(""), pointOfSale = _d[0], setPointOfSale = _d[1];
    var _e = useState(false), useGroup = _e[0], setUseGroup = _e[1];
    var _f = useState(""), partner = _f[0], setPartner = _f[1];
    var _g = useState(""), group = _g[0], setGroup = _g[1];
    //Factura, notas, remito y orden de compra.
    var _h = useState([["1", "", "1"]]), productTable = _h[0], setProductTable = _h[1];
    var _j = useState("21%"), vat = _j[0], setVat = _j[1];
    var _k = useState(""), observations = _k[0], setObservations = _k[1];
    //Recibo x.
    var _l = useState(""), payer = _l[0], setPayer = _l[1];
    var _m = useState([["", "", ""]]), paymentMethods = _m[0], setPaymentMethods = _m[1];
    var _o = useState([["", "", "", ""]]), paymentImputation = _o[0], setPaymentImputation = _o[1];
    var _p = useState([["", "", "", "", ""]]), detailOfValues = _p[0], setDetailOfValues = _p[1];
    //Orden de compra.
    var _q = useState(""), seller = _q[0], setSeller = _q[1];
    var _r = useState(""), conditions = _r[0], setConditions = _r[1];
    var _s = useState(""), preferredDate = _s[0], setPreferredDate = _s[1];
    var _t = useState(""), dispatchPlace = _t[0], setDispatchPlace = _t[1];
    var _u = useState(""), deliverer = _u[0], setDeliverer = _u[1];
    /*[document, React.Dispatch<React.SetStateAction<document>>]*/
    return (React.createElement(Form, { title: getDocumentTitle(type, flux) },
        React.createElement(ErrorMessage, { message: error }),
        React.createElement(Section, { label: "Part\u00EDcipes" },
            React.createElement(FlexDiv, null,
                React.createElement(Select, { options: display.pointsOfSale, bind: [pointOfSale, setPointOfSale], fallback: display.root ? "No tienes ningún punto de venta. Crea tu primero:" : "" }),
                React.createElement(PlusIcon, { link: "/", cond: display.root })),
            React.createElement(Cond, { bool: flux === "in" },
                React.createElement(BiChevronsUp, { style: { margin: "1.2rem auto", display: "block", cursor: "default", fontSize: "2rem", color: "white" } })),
            React.createElement(Cond, { bool: flux === "out" },
                React.createElement(BiChevronsDown, { style: { margin: "1.2rem auto", display: "block", cursor: "default", fontSize: "2rem", color: "white" } })),
            React.createElement(Switch, { falseIcon: React.createElement(BiUser, null), trueIcon: React.createElement(BiGroup, null), bind: [useGroup, setUseGroup] }),
            React.createElement(FlexDiv, null,
                React.createElement(Select, { options: useGroup ? display.groups : display.partners, bind: useGroup ? [group, setGroup] : [partner, setPartner], fallback: useGroup ? "No tienes ningún grupo. Crea tu primero:" : "No tienes ningún socio. Crea tu primero:" }),
                React.createElement(PlusIcon, { link: "/", cond: display.root }))),
        React.createElement(Section, { label: "Datos de la operaci\u00F3n" },
            React.createElement(Cond, { bool: ("purchase-order" + "remittance" + "invoice" + "debit-note" + "credit-note").includes(type) },
                React.createElement(Table, { headers: [{ th: "Cantidad", type: "number" }, { th: "Descripción" }, { th: "Precio", type: "numebr" }], bind: [productTable, setProductTable], maxRows: 10 })),
            React.createElement(Cond, { bool: ("invoice" + "debit-note" + "credit-note").includes(type) },
                React.createElement(Radio, { legend: "IVA", options: ["21%", "10%", "4%", "0%"], bind: [vat, setVat] })),
            React.createElement(Cond, { bool: ("receipt-x").includes(type) },
                React.createElement(Field, { label: "Pagador", bind: [payer, setPayer] }),
                React.createElement(Table, { label: "Forma de pago", headers: [{ th: "Cheque", type: "number" }, { th: "Documentos", type: "number" }, { th: "Efectivo", type: "number" }], bind: [paymentMethods, setPaymentMethods], maxRows: 1 }),
                React.createElement(Table, { label: "Imputaci\u00F3n del pago", headers: [{ th: "Tipo" }, { th: "Número", type: "number" }, { th: "Importe", type: "number" }, { th: "Abonado", type: "number" }], bind: [paymentImputation, setPaymentImputation], maxRows: 3 }),
                React.createElement(Table, { label: "Detalle de valores", headers: [{ th: "Tipo" }, { th: "Banco" }, { th: "Número" }, { th: "Fecha de depósito", type: "date" }, { th: "Importe", type: "number" }], bind: [detailOfValues, setDetailOfValues], maxRows: 3 }))),
        React.createElement(Section, { label: "Datos opcionales" },
            React.createElement(Cond, { bool: ("purchase-order" + "remittance" + "invoice" + "debit-note" + "credit-note").includes(type) },
                React.createElement(Textarea, { label: "Observaciones", bind: [observations, setObservations] })),
            React.createElement(Cond, { bool: type === "purchase-order" },
                React.createElement(Field, { label: "Vendedor de preferencia", bind: [seller, setSeller] }),
                React.createElement(Radio, { legend: "Condiciones de venta", options: ["Al contado", "Cuenta corriente", "Cheque", "Pagaré"], bind: [conditions, setConditions] }),
                React.createElement(DateInput, { label: "Fecha de preferencia ", value: preferredDate, onChange: setPreferredDate, nonPast: true }),
                React.createElement(Field, { label: "Lugar de entrega", bind: [dispatchPlace, setDispatchPlace] }),
                React.createElement(Field, { label: "Transportista", bind: [deliverer, setDeliverer] }))),
        React.createElement(Submit, { text: "Generar" })));
}
//# Los siguientes elementos son muy simples, por eso se decidió no moverlos a archivos separados. #//
/**
 * Un signo + con Link a la dirección especificada cuando la condición 'cond' es cumplida.
 * Valor null en caso contrario.
 */
var PlusIcon = function (_a) {
    var link = _a.link, cond = _a.cond;
    return cond ?
        React.createElement(Link, { to: link, style: { flex: .5, marginTop: ".3rem", fontSize: "2rem", display: "block", textAlign: "center", color: "#fff" } },
            React.createElement(BiPlusCircle, null)) : null;
};
