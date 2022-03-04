import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
//Componentes de formulario.
import { Form, Field, Image, ErrorMessage, Submit, Radio } from 'components/formComponents';
import { BiAt, BiChevronLeft, BiHash, BiHome, BiIdCard, BiKey, BiText, BiWallet } from "react-icons/bi";
//Relacionado a la cuenta.
import Valid from "utils/Valid";
import MainAccount from "services/MainAccount";
import Session from "services/Session";
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
    var _f = useState(null), avatar = _f[0], setAvatar = _f[1];
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
        var account = {
            user: {
                username: username,
                email: email,
                password: password,
                avatar: avatar,
            },
            trader: {
                businessName: businessName,
                vatCategory: vatCategory,
                code: code,
                grossIncome: grossIncome,
            }
        };
        MainAccount.register(account, handleResponse);
        setSending(true);
    }
    /**Maneja la respuesta recibida del servidor. */
    function handleResponse(state, data) {
        setSending(false);
        if (state === 201) {
            Session.setSession(JSON.parse(data));
            setTraderError("");
            navigate("/inicio");
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
            React.createElement(Image, { label: "Foto de perfil", note: "(opcional)", setter: setAvatar }),
            React.createElement(ErrorMessage, { message: userError }),
            React.createElement(Submit, { text: "Siguiente" }))
        :
            active === "trader" ?
                React.createElement(Form, { title: "Datos del comercio", onSubmit: validateTrader },
                    sending ? null : React.createElement(BiChevronLeft, { onClick: function () { return setActive("user"); } }),
                    React.createElement(Field, { icon: React.createElement(BiIdCard, null), label: "Escribe tu raz\u00F3n social", bind: [businessName, setBusinessName], validator: Valid.names(businessName) }),
                    React.createElement(Radio, { legend: "Selecciona una categor\u00EDa:", bind: [vatCategory, setVatCategory], options: ["Responsable Inscripto", "Monotributista", "Sujeto Exento"] }),
                    React.createElement(Field, { label: "C.U.I." + (vatCategory === "Monotributista" ? "L." : "T."), note: "(si no eliges uno, se generar\u00E1 uno falso)", bind: [code, setCode], validator: Valid.code(code), icon: React.createElement(BiHash, null) }),
                    React.createElement(Field, { label: "N\u00FAmero de ingresos brutos", note: "(si no eliges uno, se generar\u00E1 uno falso)", bind: [grossIncome, setGrossIncome], icon: React.createElement(BiWallet, null), validator: Valid.code(grossIncome) }),
                    React.createElement(ErrorMessage, { message: traderError }),
                    sending ? "Cargando..." : React.createElement(Submit, { text: "Enviar" }))
                : null);
}
