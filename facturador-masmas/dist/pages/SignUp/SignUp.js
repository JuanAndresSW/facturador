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
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
import React, { useState } from "react";
import Valid from "utils/Valid";
import signUp from "services/account/signUp";
import "styles/form.css";
import { useNavigate } from "react-router-dom";
import { BiChevronLeft } from "react-icons/bi";
/**
 * devuelve un formulario de 2 partes para crear una nueva cuenta y comerciante
 */
export default function SignUp() {
    var navigate = useNavigate();
    /*DATOS DEL FORMULARIO*****************************************************/
    //controladores del estado del formulario
    var _a = useState("user"), active = _a[0], setActive = _a[1];
    var _b = useState("Comprobar"), submitButton = _b[0], setSubmitButton = _b[1];
    //datos del usuario
    var _c = useState({
        username: "",
        email: "",
        password: "",
        avatar: null,
    }), user = _c[0], setUser = _c[1];
    var _d = useState(""), passwordMatch = _d[0], setPasswordMatch = _d[1];
    var _e = useState(""), userError = _e[0], setUserError = _e[1];
    //datos del comerciante
    var _f = useState({
        businessName: "",
        vatCategory: "",
        code: "",
        grossIncome: "",
    }), trader = _f[0], setTrader = _f[1];
    var _g = useState(""), traderError = _g[0], setTraderError = _g[1];
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
        if (!Valid.image(user.avatar)) {
            setUserError("La imágen no debe superar los 2MB");
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
        submit();
    };
    /*ENVIAR/RECIBIR*************************************************/
    function submit() {
        return __awaiter(this, void 0, void 0, function () {
            return __generator(this, function (_a) {
                signUp({ user: user, trader: trader }, handleResponse);
                setSubmitButton("Cargando...");
                return [2 /*return*/];
            });
        });
    }
    function handleResponse(state, data) {
        switch (state) {
            case 0:
                setTraderError("No se ha podido establecer la comunicación con el servidor");
                break;
            case 200:
                setTraderError("La cuenta se ha creado correctamente");
                console.log(data);
                break;
            case 400:
                setTraderError("Hubo un error al validar los datos");
                break;
            case 500:
                setTraderError("Hubo un problema con el servidor");
                break;
            default:
                setTraderError("Hubo un error desconocido al procesar tus datos");
                break;
        }
    }
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
        React.createElement("button", { onClick: function (e) { return validateUser(e); } }, "Siguiente"))) : /***************************************************************************/
        active === "trader" ? (React.createElement(React.Fragment, null,
            submitButton === "Comprobar" ? (React.createElement(BiChevronLeft, { onClick: function () {
                    setActive("user");
                } })) : (React.createElement(React.Fragment, null)),
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
                React.createElement("input", { type: "text", maxLength: 20, value: trader.code, onChange: function (e) { return setTrader(__assign(__assign({}, trader), { code: e.target.value })); } })),
            trader.vatCategory === "Responsable Inscripto" ? (React.createElement("label", null,
                "Número de ingresos brutos",
                React.createElement("span", null, " (si no eliges uno, se generar\u00E1 uno falso)"),
                React.createElement("input", { type: "text", maxLength: 20, value: trader.grossIncome, onChange: function (e) {
                        return setTrader(__assign(__assign({}, trader), { grossIncome: e.target.value }));
                    } }))) : (React.createElement(React.Fragment, null)),
            React.createElement("p", { className: "error" }, traderError),
            submitButton === ("Comprobar" || "Cargando...") ? (React.createElement("button", { disabled: submitButton !== "Comprobar", onClick: function (e) { return validateTrader(e); } }, submitButton)) : (React.createElement(React.Fragment, null)))) : (React.createElement(React.Fragment, null, "????"))));
}
