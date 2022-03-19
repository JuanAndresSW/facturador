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
import { Link, useNavigate } from "react-router-dom";
//Componentes de formulario.
import { Form, Field, Image, ErrorMessage, Button, Radio } from 'components/formComponents';
import { BiAt, BiChevronLeft, BiHash, BiHome, BiIdCard, BiKey, BiText, BiWallet } from "react-icons/bi";
import { Loading } from "components/layout";
//Relacionado a la cuenta.
import Valid from "utils/Valid";
import MainAccount from "services/MainAccount";
import Session from "services/Session";
//Conversiones.
import { fileToBase64, toFormattedCode } from "utils/conversions";
/**
 * Devuelve un formulario de 2 partes para crear una nueva cuenta y comerciante.
 */
export default function SignUp() {
    var navigate = useNavigate();
    //Controladores del estado del formulario.
    var _a = useState("user"), active = _a[0], setActive = _a[1];
    var _b = useState(false), sending = _b[0], setSending = _b[1];
    /*DATOS DEL FORMULARIO*****************************************************/
    //Datos del usuario.
    var _c = useState(""), username = _c[0], setUsername = _c[1];
    var _d = useState(""), email = _d[0], setEmail = _d[1];
    var _e = useState(""), password = _e[0], setPassword = _e[1];
    var _f = useState(), avatar = _f[0], setAvatar = _f[1];
    var _g = useState(""), passwordMatch = _g[0], setPasswordMatch = _g[1];
    var _h = useState(""), userError = _h[0], setUserError = _h[1];
    //Datos del comerciante.
    var _j = useState(""), businessName = _j[0], setBusinessName = _j[1];
    var _k = useState(""), vatCategory = _k[0], setVatCategory = _k[1];
    var _l = useState(""), code = _l[0], setCode = _l[1];
    var _m = useState(""), grossIncome = _m[0], setGrossIncome = _m[1];
    var _o = useState(""), traderError = _o[0], setTraderError = _o[1];
    /*VALIDACIÓN***************************************************************/
    /**Valida los datos del usuario. */
    function validateUser() {
        setUserError("");
        if (!Valid.names(username)) {
            setUserError("El nombre debe ser de entre 3 y 20 caracteres");
            return;
        }
        if (!Valid.email(email)) {
            setUserError("Ingrese una dirección válida de email");
            return;
        }
        if (!Valid.password(password)) {
            setUserError("La contraseña debe ser de entre 8 y 40 caracteres");
            return;
        }
        if (password !== passwordMatch) {
            setUserError("Las contraseñas no coinciden");
            return;
        }
        if (!Valid.image(avatar)) {
            setUserError("La imágen no debe superar los 2MB");
            return;
        }
        setActive("trader");
    }
    ;
    /**Valida los datos del comerciante. */
    function validateTrader() {
        setTraderError("");
        if (!Valid.names(businessName)) {
            setTraderError("La razón social debe ser de entre 3 y 20 caracteres");
            return;
        }
        if (!Valid.vatCategory(vatCategory)) {
            setTraderError("Seleccione una categoría");
            return;
        }
        if (!Valid.code(code)) {
            setTraderError("Ingrese un".concat(vatCategory === "Monotributista" ? " C.U.I.L. válido" : "a C.U.I.T. válida"));
            return;
        }
        if (!Valid.code(grossIncome)) {
            setTraderError("Ingrese un número de ingresos brutos válido");
            return;
        }
        //Si todo fue validado, se envían los datos.
        submit();
    }
    ;
    /*ENVIAR Y RECIBIR*************************************************/
    /**Envía al servidor los datos recolectados. */
    function submit() {
        return __awaiter(this, void 0, void 0, function () {
            var account, _a;
            var _b, _c;
            return __generator(this, function (_d) {
                switch (_d.label) {
                    case 0:
                        _b = {};
                        _c = {
                            username: username.trim(),
                            email: email.trim(),
                            password: password.trim()
                        };
                        _a = "";
                        return [4 /*yield*/, fileToBase64(avatar)];
                    case 1:
                        account = (_b.user = (_c.avatar = _a + (_d.sent()),
                            _c),
                            _b.trader = {
                                businessName: businessName.trim(),
                                vatCategory: vatCategory.trim(),
                                code: toFormattedCode(code),
                                grossIncome: toFormattedCode(grossIncome),
                            },
                            _b);
                        setSending(true);
                        MainAccount.create(account, handleResponse);
                        return [2 /*return*/];
                }
            });
        });
    }
    /**Maneja la respuesta recibida del servidor. */
    function handleResponse(state, data) {
        setSending(false);
        console.log("SIGNUP: " + data);
        if (state === 201) {
            Session.setSession(__assign(__assign({}, JSON.parse(data)), { username: username, rol: 'MAIN', actives: '0', pasives: '0' }));
            setTraderError("");
            navigate("/inicio");
            window.location.reload();
        }
        else
            setTraderError(data);
    }
    /*FORMULARIO*****************************************************/
    return (active === "user" ?
        React.createElement(Form, { title: "Datos de la cuenta", onSubmit: validateUser },
            React.createElement(Link, { to: "/" },
                React.createElement(BiHome, null)),
            React.createElement(Field, { icon: React.createElement(BiText, null), label: "\u00BFC\u00F3mo quieres que te identifiquemos?", bind: [username, setUsername], validator: Valid.names(username) }),
            React.createElement(Field, { icon: React.createElement(BiAt, null), label: "Tu direcci\u00F3n de correo electr\u00F3nico", bind: [email, setEmail], validator: Valid.email(email) }),
            React.createElement(Field, { icon: React.createElement(BiKey, null), label: "Elige una contrase\u00F1a", bind: [password, setPassword], type: "password", validator: Valid.password(password) }),
            React.createElement(Field, { label: "Vuelve a escribir la contrase\u00F1a", bind: [passwordMatch, setPasswordMatch], type: "password", validator: password === passwordMatch }),
            React.createElement(Image, { label: "Foto de perfil", note: "(opcional)", setter: setAvatar, img: avatar }),
            React.createElement(ErrorMessage, { message: userError }),
            React.createElement(Button, { type: "submit", text: "Siguiente" }))
        :
            active === "trader" ?
                React.createElement(Form, { title: "Datos del comercio", onSubmit: validateTrader },
                    sending ? null : React.createElement(BiChevronLeft, { onClick: function () { return setActive("user"); } }),
                    React.createElement(Field, { icon: React.createElement(BiIdCard, null), label: "Escribe tu raz\u00F3n social", bind: [businessName, setBusinessName], validator: Valid.names(businessName) }),
                    React.createElement(Radio, { legend: "Selecciona una categor\u00EDa:", bind: [vatCategory, setVatCategory], options: ["Responsable Inscripto", "Monotributista", "Sujeto Exento"] }),
                    React.createElement(Field, { label: "C.U.I." + (vatCategory === "Monotributista" ? "L." : "T."), note: "(si no eliges uno, se generar\u00E1 uno falso)", bind: [code, setCode], validator: Valid.code(code), icon: React.createElement(BiHash, null) }),
                    React.createElement(Field, { label: "N\u00FAmero de ingresos brutos", note: "(si no eliges uno, se generar\u00E1 uno falso)", bind: [grossIncome, setGrossIncome], icon: React.createElement(BiWallet, null), validator: Valid.code(grossIncome) }),
                    React.createElement(ErrorMessage, { message: traderError }),
                    sending ? React.createElement(Loading, null) : React.createElement(Button, { type: "submit", text: "Enviar" }))
                : null);
}
