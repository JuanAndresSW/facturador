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
import React, { useState } from "react";
import Valid from "../script/Valid";
import Gateway from "../script/Gateway";
import "../style/form.css";
import { useNavigate } from "react-router-dom";
import { BiChevronLeft } from "react-icons/bi";
//devuelve un formulario de 2 partes para crear una nueva cuenta, comerciante y punto de venta
export default function SignUp() {
    var navigate = useNavigate();
    /*DATOS DEL FORMULARIO*****************************************************/
    //controlador de las 2 partes del formulario
    var _a = useState("user"), active = _a[0], setActive = _a[1];
    //datos del usuario
    var _b = useState({
        username: "",
        email: "",
        password: "",
        avatar: null,
    }), user = _b[0], setUser = _b[1];
    var _c = useState(""), passwordMatch = _c[0], setPasswordMatch = _c[1];
    var _d = useState(""), userError = _d[0], setUserError = _d[1];
    //datos del comerciante
    var _e = useState({
        businessName: "",
        vatCategory: "",
        code: "",
        grossIncome: "",
    }), trader = _e[0], setTrader = _e[1];
    var _f = useState(""), traderError = _f[0], setTraderError = _f[1];
    /*VALIDACIÓN***************************************************************/
    var validateUser = function (e) {
        setUserError("");
        e.preventDefault();
        if (!Valid.names(user.username)) {
            setUserError("El nombre debe ser de entre 3 y 20 caracteres");
            return;
        }
        if (!Valid.email(user.email)) {
            setUserError("Ingrese una dirección válida de email");
            return;
        }
        if (!Valid.password(user.password)) {
            setUserError("La contraseña debe ser de entre 8 y 40 caracteres");
            return;
        }
        if (user.password !== passwordMatch) {
            setUserError("Las contraseñas no coinciden");
            return;
        }
        setActive("trader");
    };
    var validateTrader = function (e) {
        setTraderError("");
        e.preventDefault();
        if (!Valid.names(trader.businessName)) {
            setTraderError("La razón social debe ser de entre 3 y 20 caracteres");
            return;
        }
        if (!Valid.vatCategory(trader.vatCategory)) {
            setTraderError("Seleccione una categoría");
            return;
        }
        if (!Valid.code(trader.code)) {
            setTraderError("Ingrese un".concat(trader.vatCategory === "Monotributista"
                ? " C.U.I.L. válido"
                : "a C.U.I.T válida"));
            return;
        }
        if (!Valid.code(trader.grossIncome)) {
            setTraderError("Ingrese un número de ingresos brutos válido");
            return;
        }
        Gateway.submitAccount({ user: user, trader: trader });
    };
    /*FORMULARIO*****************************************************/
    return (React.createElement("form", { className: "panel", method: "post" }, active === "user" ? (React.createElement(React.Fragment, null,
        React.createElement("h1", { className: "title" }, "Datos de la cuenta"),
        React.createElement("label", null,
            "¿Cómo quieres que te identifiquemos?",
            React.createElement("input", { type: "text", maxLength: 20, value: user.username, onChange: function (e) { return setUser(__assign(__assign({}, user), { username: e.target.value })); }, required: true })),
        React.createElement("label", null,
            "Tu dirección de correo electrónico",
            React.createElement("input", { type: "email", maxLength: 254, value: user.email, onChange: function (e) { return setUser(__assign(__assign({}, user), { email: e.target.value })); }, required: true })),
        React.createElement("label", null,
            "Elige una contraseña",
            React.createElement("input", { type: "password", maxLength: 40, value: user.password, onChange: function (e) { return setUser(__assign(__assign({}, user), { password: e.target.value })); }, required: true })),
        React.createElement("label", null,
            "Vuelve a escribir la contraseña",
            React.createElement("input", { type: "password", maxLength: 128, value: passwordMatch, onChange: function (e) { return setPasswordMatch(e.target.value); }, required: true })),
        React.createElement("label", null,
            "Foto de perfil",
            React.createElement("span", null, " (opcional)"),
            React.createElement("input", { type: "file", accept: ".png, .jpeg, .jpg, .svg", onChange: function (e) {
                    if (e.target.files && e.target.files.length > 0)
                        setUser(__assign(__assign({}, user), { avatar: e.target.files.item(0) }));
                } })),
        React.createElement("p", { className: "error" }, userError),
        React.createElement("button", { onClick: function (e) { return validateUser(e); } }, "Comprobar"))) : /***************************************************************************/
        active === "trader" ? (React.createElement(React.Fragment, null,
            React.createElement(BiChevronLeft, { onClick: function () {
                    setActive("user");
                } }),
            React.createElement("h1", { className: "title" }, "Datos del comercio"),
            React.createElement("label", null,
                "Escribe tu razón social",
                React.createElement("input", { type: "text", maxLength: 20, value: trader.businessName, onChange: function (e) {
                        return setTrader(__assign(__assign({}, trader), { businessName: e.target.value }));
                    }, required: true })),
            React.createElement("label", null,
                "Selecciona una categoría:",
                React.createElement("label", { className: "small" },
                    React.createElement("input", { type: "radio", name: "vat", checked: trader.vatCategory === "Responsable Inscripto", value: "Responsable Inscripto", onChange: function (e) {
                            return setTrader(__assign(__assign({}, trader), { vatCategory: e.target.value }));
                        }, required: true }),
                    "Responsable Inscripto"),
                React.createElement("label", { className: "small" },
                    React.createElement("input", { type: "radio", name: "vat", checked: trader.vatCategory === "Monotributista", value: "Monotributista", onChange: function (e) {
                            return setTrader(__assign(__assign({}, trader), { vatCategory: e.target.value }));
                        }, required: true }),
                    "Responsable Monotributista"),
                React.createElement("label", { className: "small" },
                    React.createElement("input", { type: "radio", name: "vat", checked: trader.vatCategory === "Sujeto Exento", value: "Sujeto Exento", onChange: function (e) {
                            return setTrader(__assign(__assign({}, trader), { vatCategory: e.target.value }));
                        }, required: true }),
                    "Exento")),
            React.createElement("label", null,
                "C.U.I." + (trader.vatCategory === "Monotributista" ? "L." : "T."),
                React.createElement("span", null, " (si no eliges uno, se generar\u00E1 uno falso)"),
                React.createElement("input", { type: "text", maxLength: 20, value: trader.code, onChange: function (e) { return setTrader(__assign(__assign({}, trader), { code: e.target.value })); }, required: true })),
            trader.vatCategory === "Responsable Inscripto" ? (React.createElement("label", null,
                "Número de ingresos brutos",
                React.createElement("span", null, " (si no eliges uno, se generar\u00E1 uno falso)"),
                React.createElement("input", { type: "text", maxLength: 20, value: trader.grossIncome, onChange: function (e) {
                        return setTrader(__assign(__assign({}, trader), { grossIncome: e.target.value }));
                    }, required: true }))) : (React.createElement(React.Fragment, null)),
            React.createElement("p", { className: "error" }, traderError),
            React.createElement("button", { onClick: function (e) { return validateTrader(e); } }, "Comprobar"))) :
            React.createElement(React.Fragment, null)));
}
