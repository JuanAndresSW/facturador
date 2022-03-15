//React.
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
//Componentes del formulario.
import { DateTime, ErrorMessage, Field, Form, Radio, Select, Button, Switch, Table, Textarea } from "components/formComponents";
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
        PointOfSale.retrieve(function (state, data) {
            if (state === 200)
                setDisplayPointsOfSale(JSON.parse(data));
        });
        Partner.retrieve(function (state, data) {
            if (state === 200)
                setDisplayPartners(JSON.parse(data));
        });
        Group.retrieve(function (state, data) {
            if (state === 200)
                setDisplayGroups(JSON.parse(data));
        });
    }, []);
    // #### Los datos del servidor necesarios para mostrar el formulario. #### //
    var _b = useState(undefined), displayPointsOfSale = _b[0], setDisplayPointsOfSale = _b[1];
    var _c = useState(undefined), displayPartners = _c[0], setDisplayPartners = _c[1];
    var _d = useState(undefined), displayGroups = _d[0], setDisplayGroups = _d[1];
    var _e = useState(false), displayRoot = _e[0], setDisplayRoot = _e[1];
    // #### Los datos a ser enviados al servidor. #### //
    //Todos.
    var _f = useState(""), IDpointOfSale = _f[0], setIDPointOfSale = _f[1];
    var _g = useState(false), sendingToGroup = _g[0], setSendingToGroup = _g[1];
    var _h = useState(""), partner = _h[0], setPartner = _h[1];
    var _j = useState(""), IDgroup = _j[0], setIDGroup = _j[1];
    //Factura, notas, remito y orden de compra.
    var _k = useState([["", "", ""]]), productTable = _k[0], setProductTable = _k[1];
    var _l = useState("21%"), VATPercentage = _l[0], setVATPercentage = _l[1];
    var _m = useState(""), observations = _m[0], setObservations = _m[1];
    //Recibo x.
    var _o = useState(""), paymentTime = _o[0], setPaymentTime = _o[1];
    var _p = useState([["", "", ""]]), paymentMethods = _p[0], setPaymentMethods = _p[1];
    var _q = useState([["", "", "", ""]]), paymentImputation = _q[0], setPaymentImputation = _q[1];
    var _r = useState([["", "", "", "", ""]]), detailOfValues = _r[0], setDetailOfValues = _r[1];
    //Recibo x y recibo
    var _s = useState(""), payer = _s[0], setPayer = _s[1];
    //Recibo, recibo x y pagaré.
    var _t = useState(""), payerAddress = _t[0], setPayerAdress = _t[1];
    //Orden de compra.
    var _u = useState(""), seller = _u[0], setSeller = _u[1];
    var _v = useState(""), conditions = _v[0], setConditions = _v[1];
    var _w = useState(""), deliveryDeadline = _w[0], setDeliveryDeadline = _w[1];
    var _x = useState(""), placeOfDelivery = _x[0], setPlaceOfDelivery = _x[1];
    var _y = useState(""), carrier = _y[0], setCarrier = _y[1];
    //Recibo y pagaré.
    var _z = useState(""), descriptionOfValues = _z[0], setDescriptionOfValues = _z[1];
    var _0 = useState(""), paymentDeadline = _0[0], setPaymentDeadline = _0[1];
    //Recibo, pagaré y cheque.
    var _1 = useState(""), amount = _1[0], setAmount = _1[1];
    //Pagaré.
    var _2 = useState(false), protest = _2[0], setProtest = _2[1];
    //Cheque.
    var _3 = useState(0), delay = _3[0], setDelay = _3[1];
    var _4 = useState(""), bank = _4[0], setBank = _4[1];
    //Mensaje de error al generar el documento.
    var _5 = useState("hello world"), error = _5[0], setError = _5[1];
    // #### El formulario #### //
    return (React.createElement(Form, { title: getDocumentTitle(type, flux) },
        React.createElement(ErrorMessage, { message: error }),
        React.createElement(Section, { label: "Part\u00EDcipes" },
            React.createElement(FlexDiv, null,
                React.createElement(Select, { options: displayPointsOfSale, bind: [IDpointOfSale, setIDPointOfSale], fallback: displayRoot ? "No tienes ningún punto de venta. Crea tu primero:" : "" }),
                React.createElement(Cond, { bool: displayRoot },
                    React.createElement(PlusIcon, { link: "/" }))),
            React.createElement(Cond, { bool: flux === "in" },
                React.createElement(BiChevronsUp, { style: { margin: "1.2rem auto", display: "block", cursor: "default", fontSize: "2rem", color: "white" } })),
            React.createElement(Cond, { bool: flux === "out" },
                React.createElement(BiChevronsDown, { style: { margin: "1.2rem auto", display: "block", cursor: "default", fontSize: "2rem", color: "white" } })),
            React.createElement(Switch, { falseIcon: React.createElement(BiUser, null), trueIcon: React.createElement(BiGroup, null), bind: [sendingToGroup, setSendingToGroup] }),
            React.createElement(FlexDiv, null,
                React.createElement(Select, { options: sendingToGroup ? displayGroups : displayPartners, bind: sendingToGroup ? [IDgroup, setIDGroup] : [partner, setPartner], fallback: sendingToGroup ? "No tienes ningún grupo. Crea tu primero:" : "No tienes ningún socio. Crea tu primero:" }),
                React.createElement(PlusIcon, { link: "/" }))),
        React.createElement(Section, { label: "Datos de la operaci\u00F3n" },
            React.createElement(Cond, { bool: ("purchase-order" + "remittance" + "invoice" + "debit-note" + "credit-note").includes(type) },
                React.createElement(Table, { headers: [{ th: "Cantidad", type: "number" }, { th: "Descripción" }, { th: "Precio", type: "numebr" }], bind: [productTable, setProductTable], maxRows: 10 })),
            React.createElement(Cond, { bool: ("invoice" + "debit-note" + "credit-note").includes(type) },
                React.createElement(Radio, { legend: "IVA", options: ["21%", "10%", "4%", "0%"], bind: [VATPercentage, setVATPercentage] })),
            React.createElement(Cond, { bool: ("receipt-x" + "receipt").includes(type) },
                React.createElement(Field, { label: "Pagador", bind: [payer, setPayer] })),
            React.createElement(Cond, { bool: ("receipt-x").includes(type) },
                React.createElement(Table, { label: "Forma de pago", headers: [{ th: "Cheque", type: "number" }, { th: "Documentos", type: "number" }, { th: "Efectivo", type: "number" }], bind: [paymentMethods, setPaymentMethods], maxRows: 1 }),
                React.createElement(Table, { label: "Imputaci\u00F3n del pago", headers: [{ th: "Tipo" }, { th: "Número", type: "number" }, { th: "Importe", type: "number" }, { th: "Abonado", type: "number" }], bind: [paymentImputation, setPaymentImputation], maxRows: 3 }),
                React.createElement(Table, { label: "Detalle de valores", headers: [{ th: "Tipo" }, { th: "Banco" }, { th: "Número" }, { th: "Fecha de depósito", type: "date" }, { th: "Importe", type: "number" }], bind: [detailOfValues, setDetailOfValues], maxRows: 3 }))),
        React.createElement(Section, { label: "Datos opcionales" },
            React.createElement(Cond, { bool: ("purchase-order" + "remittance" + "invoice" + "debit-note" + "credit-note").includes(type) },
                React.createElement(Textarea, { label: "Observaciones", bind: [observations, setObservations] })),
            React.createElement(Cond, { bool: type === "purchase-order" },
                React.createElement(Field, { label: "Vendedor de preferencia", bind: [seller, setSeller] }),
                React.createElement(Radio, { legend: "Condiciones de venta", options: ["Al contado", "Cuenta corriente", "Cheque", "Pagaré"], bind: [conditions, setConditions] }),
                React.createElement(DateTime, { label: "Fecha de preferencia ", value: deliveryDeadline, onChange: setDeliveryDeadline, nonPast: true }),
                React.createElement(Field, { label: "Lugar de entrega", bind: [placeOfDelivery, setPlaceOfDelivery] }),
                React.createElement(Field, { label: "Transportista", bind: [carrier, setCarrier] })),
            React.createElement(Cond, { bool: ("receipt-x").includes(type) },
                React.createElement(DateTime, { label: "", type: "time", value: paymentTime, onChange: setPaymentTime })),
            React.createElement(Cond, { bool: ("receipt-x" + "receipt" + "promissory-note").includes(type) },
                React.createElement(Field, { label: "Domicilio de pago", bind: [payerAddress, setPayerAdress] }))),
        React.createElement(Button, { type: "submit", text: "Generar" })));
}
//# Los siguientes elementos son muy simples, por eso se decidió no moverlos a archivos separados. #//
/**Un signo + con Link a la dirección especificada.*/
var PlusIcon = function (_a) {
    var link = _a.link;
    return (React.createElement(Link, { to: link, style: { flex: .5, marginTop: ".3rem", fontSize: "2rem", display: "block", textAlign: "center", color: "#fff" } },
        React.createElement(BiPlusCircle, null)));
};
