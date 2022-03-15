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
//## Funciones de implementación condicional. ##//
var hasRootAccess = sessionStorage.getItem("role") !== "Main"; //%% ===
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
    var _h = useState('sessionStorage.getItem("username")'), username = _h[0], setUsername = _h[1];
    var _j = useState(""), password = _j[0], setPassword = _j[1];
    var _k = useState(""), newPassword = _k[0], setNewPassword = _k[1];
    var _l = useState(""), confirmPassword = _l[0], setConfirmPassword = _l[1];
    //Datos del comerciante.
    var _m = useState(""), businessName = _m[0], setBusinessName = _m[1];
    var _o = useState(""), vatCategory = _o[0], setVatCategory = _o[1];
    var _p = useState(""), code = _p[0], setCode = _p[1];
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
            setEmail(obj.email);
            if (businessName !== "")
                setBusinessName(obj.businessName);
            if (vatCategory !== "")
                setVatCategory(obj.category);
            if (code !== "")
                setCode(obj.code);
        });
    }, []);
    //Comprobar la validez de los datos a enviar.
    function filter() {
        if (!Valid.names(username)) {
            setError("El nombre debe ser de entre 3 y 20 caracteres");
            return;
        }
        if (Valid.password(password)) {
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
            if (!Valid.names(businessName)) {
                setError("La razón social debe ser de entre 3 y 20 caracteres");
                return;
            }
            if (!Valid.vatCategory(vatCategory)) {
                setError("Seleccione una categoría");
                return;
            }
            if ((code === null || code === void 0 ? void 0 : code.length) < 1 || !Valid.code(code)) {
                setError("C.U.I.".concat(vatCategory === "Monotributista" ? "L. inválido" : "T. inválida"));
                return;
            }
        }
        submit();
    }
    //Envía los datos al servidor.
    function submit() {
        setLoading(true);
        var account = {
            user: {
                username: sessionStorage.getItem("username"),
                newUsername: username,
                password: password,
                newPassword: newPassword,
                avatar: avatar,
            },
            trader: {
                businessName: businessName,
                vatCategory: vatCategory,
                code: code,
            }
        };
        updateAccount(account, function (state, data) {
            setLoading(false);
            if (state === 200)
                window.location.reload();
            setError(data);
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
        if ((deletionCode === null || deletionCode === void 0 ? void 0 : deletionCode.length) !== 5) {
            setDeleteError("Código inválido");
            return;
        }
        requestAccountDeletion(deletionCode, function (state, data) {
            if (state !== 200) {
                setDeleteError(data);
                return;
            }
            Session.close();
            window.location.reload();
        });
    }
    return (React.createElement(React.Fragment, null,
        React.createElement(Header, { isAuthenticated: true }),
        React.createElement(Form, { title: "Opciones de la cuenta", onSubmit: filter },
            React.createElement(BiChevronLeft, { onClick: function () { return navigate("/"); }, style: { margin: "1rem", fontSize: "2rem", color: "rgb(44,44,44)", cursor: "pointer" } }),
            React.createElement(Image, { label: "", setter: setAvatar, img: avatar }),
            React.createElement("h5", { style: { width: "min-content", margin: "0 auto", display: "block", fontSize: "1.2rem", color: "rgb(212, 212, 212)", cursor: "default" } }, email),
            React.createElement(Field, { bind: [username, setUsername], label: "Cambiar el nombre de usuario", validator: Valid.names(username) }),
            React.createElement(Field, { bind: [password, setPassword], label: "Para cambiar tu contrase\u00F1a, introduce la contrase\u00F1a actual:", type: "password", validator: Valid.password(password) }),
            !Valid.password(password) ? null :
                React.createElement(React.Fragment, null,
                    React.createElement(Field, { bind: [newPassword, setNewPassword], label: "Nueva contrase\u00F1a", type: "password", validator: Valid.password(password) }),
                    React.createElement(Field, { bind: [confirmPassword, setConfirmPassword], label: "Confirmar nueva contrase\u00F1a", type: "password", validator: Valid.password(password) })),
            !hasRootAccess ? null :
                React.createElement(Section, { label: "Datos del comercio" },
                    React.createElement(Field, { bind: [businessName, setBusinessName], label: "Raz\u00F3n social", validator: Valid.names(businessName) }),
                    React.createElement(Radio, { legend: "Categor\u00EDa:", bind: [vatCategory, setVatCategory], options: ["Responsable Inscripto", "Monotributista", "Sujeto Exento"] }),
                    React.createElement(Field, { label: "C.U.I." + (vatCategory === "Monotributista" ? "L." : "T."), bind: [code, setCode], validator: Valid.code(code) })),
            React.createElement(ErrorMessage, { message: error }),
            loading ? React.createElement(Loading, null) :
                React.createElement(Button, { text: "Confirmar cambios", type: "submit" }),
            React.createElement("p", { style: { textAlign: "center", color: "#fff", cursor: "default" } }, "..."),
            React.createElement(Retractable, { label: "Otras opciones", initial: false },
                !deletePermissionGranted ? null :
                    React.createElement(Field, { bind: [deletionCode, setDeletionCode], label: "Se ha enviado a ".concat(email, " el c\u00F3digo de eliminaci\u00F3n. Escr\u00EDbelo a continuaci\u00F3n para confirmar:") }),
                React.createElement(ErrorMessage, { message: deleteError }),
                loading ? React.createElement(Loading, null) :
                    React.createElement(Button, { type: "delete", text: deletePermissionGranted ? "Borrar la cuenta" : "Solicitar código para eliminar cuenta", onClick: deletePermissionGranted ? deleteAccount : RequestDeletePermission }))),
        React.createElement(Footer, null)));
}
