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
//Servicios.
import getLocalUserAvatar from './services/getLocalUserAvatar';
import getTraderData from './services/getTraderData';
import { updateMainAccount, updateBranchAccount } from './services/updateAccounts';
import requestDeletionCode from './services/requestDeletionCode';
import requestAccountDeletion from './services/requestAccountDeletion';
//Validación.
import Valid from "utilities/Valid";
//GUI.
import { Button, ErrorMessage, Field, Form, Image, Radio } from "components/formComponents";
import { Retractable } from "components/layout";
import { Loading, Section } from "styledComponents";
import { BiChevronLeft } from "react-icons/bi";
//## Funciones de implementación condicional. ##//
var hasRootAccess = sessionStorage.getItem("role") === "MAIN";
var updateAccount = hasRootAccess ?
    function (data, handler) { return updateMainAccount(data, handler); } :
    function (data, handler) { return updateBranchAccount(data, handler); };
/**Un formulario que permite cambiar datos de la cuenta / eliminar la cuenta de usuario y el comerciante. */
export default function Account() {
    /*DATOS***************************************************************/
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
    var _g = useState(''), newUsername = _g[0], setNewUsername = _g[1];
    var _h = useState(""), password = _h[0], setPassword = _h[1];
    var _j = useState(""), newPassword = _j[0], setNewPassword = _j[1];
    var _k = useState(""), confirmPassword = _k[0], setConfirmPassword = _k[1];
    //Datos del comerciante.
    var _l = useState(""), businessName = _l[0], setBusinessName = _l[1];
    var _m = useState(""), newBusinessName = _m[0], setNewBusinessName = _m[1];
    var _o = useState(""), vatCategory = _o[0], setVatCategory = _o[1];
    var _p = useState(""), newVatCategory = _p[0], setNewVatCategory = _p[1];
    var _q = useState(""), code = _q[0], setCode = _q[1];
    var _r = useState(""), newCode = _r[0], setNewCode = _r[1];
    //Pedir los datos actuales en el primer renderizado.
    useEffect(function () {
        getLocalUserAvatar(function (ok, file) {
            if (ok && !avatar)
                setAvatar(file);
        });
        if (hasRootAccess) {
            getTraderData(function (ok, data) {
                if (!ok) {
                    setError(data);
                    return;
                }
                setBusinessName(data.businessName);
                setVatCategory(data.vatCategory);
                setCode(data.uniqueKey);
            });
        }
    }, []);
    /*VALIDACIÓN***************************************************************/
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
    /*COMUNICACIÓN***************************************************************/
    //Envía los datos al servidor.
    function submit() {
        return __awaiter(this, void 0, void 0, function () {
            var account;
            return __generator(this, function (_a) {
                setLoading(true);
                account = {
                    user: {
                        username: sessionStorage.getItem("username"),
                        newUsername: newUsername,
                        password: password,
                        newPassword: newPassword,
                        newAvatar: avatar,
                    },
                    trader: {
                        newBusinessName: newBusinessName,
                        newVatCategory: newVatCategory,
                        newCode: newCode,
                    }
                };
                updateAccount(account, function (ok, data) {
                    setLoading(false);
                    if (!ok)
                        setError(data);
                });
                return [2 /*return*/];
            });
        });
    }
    //Solicita un código de eliminación a ser enviado al email del usuario.
    function RequestDeletePermission() {
        setLoading(true);
        requestDeletionCode(function (ok, data) {
            setLoading(false);
            if (ok)
                setDeletePermissionGranted(true);
            else
                setDeleteError(data);
        });
    }
    //Envía el código de eliminación ingresado. Si es correcto, la cuenta de usuario es eliminada.
    function deleteAccount() {
        //if (deletionCode?.length !== 5) {setDeleteError("Código inválido"); return;} TODO: remove uncomment
        requestAccountDeletion(deletionCode, function (ok, data) {
            if (!ok)
                setDeleteError(data);
        });
    }
    /*FORMULARIO***************************************************************/
    return (React.createElement(React.Fragment, null,
        React.createElement(Form, { title: "Opciones de la cuenta", onSubmit: filter },
            React.createElement(BiChevronLeft, { onClick: function () { return navigate("/inicio"); }, style: { margin: "1rem", fontSize: "2rem", color: "rgb(44,44,44)", cursor: "pointer" } }),
            React.createElement(Image, { label: '', setter: setAvatar, img: avatar }),
            React.createElement(Field, { bind: [newUsername, setNewUsername], label: "Nombre", placeholder: sessionStorage.getItem('username') }),
            React.createElement(Field, { bind: [password, setPassword], label: "Para cambiar tu contrase\u00F1a, introduce la contrase\u00F1a actual:", type: "password" }),
            !Valid.password(password) ? null :
                React.createElement(React.Fragment, null,
                    React.createElement(Field, { bind: [newPassword, setNewPassword], label: "Nueva contrase\u00F1a", type: "password" }),
                    React.createElement(Field, { bind: [confirmPassword, setConfirmPassword], label: "Confirmar nueva contrase\u00F1a", type: "password" })),
            !hasRootAccess ? null :
                React.createElement(Section, { label: "Datos del comercio" },
                    React.createElement(Field, { bind: [newBusinessName, setNewBusinessName], label: "Raz\u00F3n social", placeholder: businessName }),
                    React.createElement(Radio, { legend: "Actualmente: " + vatCategory + ". Nueva categoría:", bind: [newVatCategory, setNewVatCategory], options: ["Responsable Inscripto", "Monotributista", "Sujeto Exento"] }),
                    React.createElement(Field, { label: "C.U.I." + (newVatCategory === "Monotributista" ? "L. " : "T. "), bind: [newCode, setNewCode], placeholder: code })),
            React.createElement(ErrorMessage, { message: error }),
            loading ? React.createElement(Loading, null) :
                React.createElement(Button, { text: "Confirmar cambios", type: "submit" }),
            React.createElement("p", { style: { textAlign: "center", color: "#fff", cursor: "default" } }, "..."),
            React.createElement(Retractable, { label: "Otras opciones", initial: false },
                !deletePermissionGranted ? null :
                    React.createElement(Field, { bind: [deletionCode, setDeletionCode], label: 'Se ha enviado por correo electrónico el código de eliminación. Escríbelo a continuación para confirmar:' }),
                React.createElement(ErrorMessage, { message: deleteError }),
                loading ? React.createElement(Loading, null) :
                    React.createElement(Button, { type: "delete", text: !deletePermissionGranted ? "Borrar la cuenta" : "Solicitar código para eliminar cuenta", onClick: !deletePermissionGranted ? deleteAccount : RequestDeletePermission }),
                "  "))));
}
