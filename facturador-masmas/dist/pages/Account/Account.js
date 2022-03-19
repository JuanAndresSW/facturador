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
//React.
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
//Servicios y utilidades.
import UserAvatar from "services/UserAvatar";
import MainAccount from "services/MainAccount";
import BranchAccount from "services/BranchAccount";
import Session from "services/Session";
import Valid from "utils/Valid";
//GUI.
import { Button, ErrorMessage, Field, Form, Image, Radio } from "components/formComponents";
import { Loading, Retractable, Section } from "components/layout";
import Header from "components/layout/Header/Header";
import Footer from "components/layout/Footer/Footer";
import { BiChevronLeft } from "react-icons/bi";
//Conversiones.
import { fileToBase64, toFormattedCode } from "utils/conversions";
//## Funciones de implementación condicional. ##//
var hasRootAccess = sessionStorage.getItem("role") === "MAIN";
var retrieveAccount = hasRootAccess ?
    function (handler) { return MainAccount.retrieve(handler); } :
    function (handler) { return BranchAccount.retrieve(handler); };
var updateAccount = hasRootAccess ?
    function (data, handler) { return MainAccount.update(data, handler); } :
    function (data, handler) { return BranchAccount.update(data, handler); };
var requestDeletionCode = hasRootAccess ?
    function (handler) { return MainAccount.requestDeletePermission(handler); } :
    function (handler) { return BranchAccount.requestDeletePermission(handler); };
var requestAccountDeletion = hasRootAccess ?
    function (code, handler) { return MainAccount.delete(code, handler); } :
    function (code, handler) { return BranchAccount.delete(code, handler); };
/**Un formulario que permite cambiar datos de la cuenta / eliminar la cuenta de usuario y el comerciante. */
export default function Account() {
    var navigate = useNavigate();
    var _a = useState(false), loading = _a[0], setLoading = _a[1];
    //Errores.
    var _b = useState(""), error = _b[0], setError = _b[1];
    var _c = useState(""), deleteError = _c[0], setDeleteError = _c[1];
    //Datos de eliminación.
    var _d = useState(false), deletePermissionGranted = _d[0], setDeletePermissionGranted = _d[1];
    var _e = useState(""), deletionCode = _e[0], setDeletionCode = _e[1];
    //Datos del usuario.
    var _f = useState(undefined), avatar = _f[0], setAvatar = _f[1];
    var _g = useState('...'), email = _g[0], setEmail = _g[1];
    var _h = useState(''), newUsername = _h[0], setNewUsername = _h[1];
    var _j = useState(""), password = _j[0], setPassword = _j[1];
    var _k = useState(""), newPassword = _k[0], setNewPassword = _k[1];
    var _l = useState(""), confirmPassword = _l[0], setConfirmPassword = _l[1];
    //Datos del comerciante.
    var _m = useState(""), businessName = _m[0], setBusinessName = _m[1];
    var _o = useState(""), newBusinessName = _o[0], setNewBusinessName = _o[1];
    var _p = useState(""), vatCategory = _p[0], setVatCategory = _p[1];
    var _q = useState(""), newVatCategory = _q[0], setNewVatCategory = _q[1];
    var _r = useState(""), code = _r[0], setCode = _r[1];
    var _s = useState(""), newCode = _s[0], setNewCode = _s[1];
    //Pedir los datos actuales en el primer renderizado.
    useEffect(function () {
        UserAvatar.retrieve(function (HTTPState, URLObject) {
            if (HTTPState === 200 && !avatar)
                setAvatar(URLObject);
        });
        retrieveAccount(function (state, data) {
            if (state !== 200) {
                setError(data);
                return;
            }
            var obj = JSON.parse(data);
            //setEmail(obj.email);
            setBusinessName(obj.bussinesName); //TODO: bussines => business;
            setVatCategory(obj.vat);
            setCode(obj.uniqueKey);
        });
    }, []);
    //Comprobar la validez de los datos a enviar.
    function filter() {
        if (avatar && !Valid.image(avatar)) {
            setError("La imágen no debe superar los 2MB");
            return;
        }
        if (newUsername && !Valid.names(newUsername)) {
            setError("El nombre debe ser de entre 3 y 20 caracteres");
            return;
        }
        if (password && Valid.password(password)) {
            if (!Valid.password(newPassword)) {
                setError("La contraseña debe ser de entre 8 y 40 caracteres");
                return;
            }
            if (newPassword !== confirmPassword) {
                setError("Las contraseñas no coinciden");
                return;
            }
        }
        if (hasRootAccess) {
            if (newBusinessName && !Valid.names(newBusinessName)) {
                setError("La razón social debe ser de entre 3 y 20 caracteres");
                return;
            }
            if (newVatCategory && !Valid.vatCategory(newVatCategory)) {
                setError("Seleccione una categoría");
                return;
            }
            if (newCode && !Valid.code(code)) {
                setError("C.U.I.".concat(newVatCategory === "Monotributista" ? "L. inválido" : "T. inválida"));
                return;
            }
        }
        submit();
    }
    //Envía los datos al servidor.
    function submit() {
        return __awaiter(this, void 0, void 0, function () {
            var account, _a;
            var _b, _c;
            return __generator(this, function (_d) {
                switch (_d.label) {
                    case 0:
                        setLoading(true);
                        _b = {};
                        _c = {
                            username: sessionStorage.getItem("username"),
                            newUsername: newUsername ? newUsername.trim() : null,
                            password: password ? password.trim() : null,
                            newPassword: newPassword ? newPassword.trim() : null
                        };
                        if (!avatar) return [3 /*break*/, 2];
                        return [4 /*yield*/, fileToBase64(avatar)];
                    case 1:
                        _a = _d.sent();
                        return [3 /*break*/, 3];
                    case 2:
                        _a = 'undefined';
                        _d.label = 3;
                    case 3:
                        account = (_b.user = (_c.avatar = _a,
                            _c),
                            _b.trader = {
                                businessName: newBusinessName ? newBusinessName.trim() : null,
                                vatCategory: newVatCategory ? newVatCategory.trim() : null,
                                newCode: newCode ? toFormattedCode(newCode) : null,
                            },
                            _b);
                        updateAccount(account, function (state, data) {
                            setLoading(false);
                            if (state === 200) {
                                localStorage.removeItem('avatar');
                                //fileToBase64(avatar).then(newAvatar=>localStorage.setItem("avatar", ""+newAvatar));
                                window.location.reload();
                            }
                            else
                                setError(data);
                        });
                        return [2 /*return*/];
                }
            });
        });
    }
    //Solicita un código de eliminación a ser enviado al email del usuario.
    function RequestDeletePermission() {
        setLoading(true);
        requestDeletionCode(function (state, data) {
            setLoading(false);
            if (state === 200)
                setDeletePermissionGranted(true);
            else
                setDeleteError(data);
        });
    }
    //Envía el código de eliminación ingresado. Si es correcto, la cuenta de usuario es eliminada.
    function deleteAccount() {
        //if (deletionCode?.length !== 5) {setDeleteError("Código inválido"); return;} TODO: remove uncomment
        requestAccountDeletion(deletionCode, function (state, data) {
            setDeleteError(data);
            if (state !== 200)
                return;
            Session.close();
            window.location.reload();
        });
    }
    return (React.createElement(React.Fragment, null,
        React.createElement(Header, { isAuthenticated: true }),
        React.createElement(Form, { title: "Opciones de la cuenta", onSubmit: filter },
            React.createElement(BiChevronLeft, { onClick: function () { return navigate("/inicio"); }, style: { margin: "1rem", fontSize: "2rem", color: "rgb(44,44,44)", cursor: "pointer" } }),
            React.createElement(Image, { label: '', setter: setAvatar, img: avatar }),
            React.createElement("h5", { style: { width: "min-content", margin: "0 auto", display: "block", fontSize: "1.2rem", color: "rgb(212, 212, 212)", cursor: "default" } }, email),
            React.createElement(Field, { bind: [newUsername, setNewUsername], label: sessionStorage.getItem('username'), validator: Valid.names(newUsername), placeholder: 'Nuevo nombre' }),
            React.createElement(Field, { bind: [password, setPassword], label: "Para cambiar tu contrase\u00F1a, introduce la contrase\u00F1a actual:", type: "password", validator: Valid.password(password) }),
            !Valid.password(password) ? null :
                React.createElement(React.Fragment, null,
                    React.createElement(Field, { bind: [newPassword, setNewPassword], label: "Nueva contrase\u00F1a", type: "password", validator: Valid.password(password) }),
                    React.createElement(Field, { bind: [confirmPassword, setConfirmPassword], label: "Confirmar nueva contrase\u00F1a", type: "password", validator: Valid.password(password) })),
            !hasRootAccess ? null :
                React.createElement(Section, { label: "Datos del comercio" },
                    React.createElement(Field, { bind: [newBusinessName, setNewBusinessName], label: businessName, validator: Valid.names(newBusinessName), placeholder: "Nueva raz\u00F3n social" }),
                    React.createElement(Radio, { legend: vatCategory + ". Nueva categoría:", bind: [newVatCategory, setNewVatCategory], options: ["Responsable Inscripto", "Monotributista", "Sujeto Exento"] }),
                    React.createElement(Field, { label: "C.U.I." + (newVatCategory === "Monotributista" ? "L.: " + code : "T.: " + code), bind: [newCode, setNewCode], placeholder: "nuev" + (newVatCategory === "Monotributista" ? "a C.U.I.L." : "o C.U.I.T."), validator: Valid.code(newCode) })),
            React.createElement(ErrorMessage, { message: error }),
            loading ? React.createElement(Loading, null) :
                React.createElement(Button, { text: "Confirmar cambios", type: "submit" }),
            React.createElement("p", { style: { textAlign: "center", color: "#fff", cursor: "default" } }, "..."),
            React.createElement(Retractable, { label: "Otras opciones", initial: false },
                !deletePermissionGranted ? null :
                    React.createElement(Field, { bind: [deletionCode, setDeletionCode], label: "Se ha enviado a ".concat(email, " el c\u00F3digo de eliminaci\u00F3n. Escr\u00EDbelo a continuaci\u00F3n para confirmar:") }),
                React.createElement(ErrorMessage, { message: deleteError }),
                loading ? React.createElement(Loading, null) :
                    React.createElement(Button, { type: "delete", text: !deletePermissionGranted ? "Borrar la cuenta" : "Solicitar código para eliminar cuenta", onClick: !deletePermissionGranted ? deleteAccount : RequestDeletePermission }),
                "  ")),
        React.createElement(Footer, null)));
}
