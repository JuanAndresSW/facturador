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
import Format from "./Format";
/**
 * Define métodos de comunicación con el servidor.
 */
var Gateway = /** @class */ (function () {
    function Gateway() {
    }
    /**
     * implementa una capa de abstracción para la API XMLHR
     * @param {String} url el sufijo del url del recurso
     * @param {String} content el contenido (json) del request
     * @param {(state:number, data:string)} callback función para evaluar parámetro de estado
     */
    Gateway.fetch = function (url, content, callback) {
        var xhr = new XMLHttpRequest();
        url = process.env.REACT_APP_API + url;
        if (!xhr) {
            callback(0);
            return;
        }
        //iniciar el request
        xhr.onreadystatechange = handleResponse;
        xhr.timeout = 15000;
        xhr.ontimeout = function () {
            return callback(0);
        };
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send(content);
        /**invoca al callback cuando se recibe la respuesta*/
        function handleResponse() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                callback(xhr.status, xhr.responseText);
                return;
            }
            else {
                callback(0, null);
                return;
            }
        }
        callback(0, null);
        return;
    };
    /*******************************************************************************
     ************************** MÉTODOS ESPECÍFICOS *********************************
     ********************************************************************************/
    /**
     * envía los datos de usuario y la foto de perfil
     * @param {account} account los datos de la cuenta del usuario, en forma de objeto
     */
    Gateway.submitAccount = function (account, callback) {
        return __awaiter(this, void 0, void 0, function () {
            var formattedPromise;
            var _this = this;
            return __generator(this, function (_a) {
                formattedPromise = Format.account(account);
                formattedPromise.then(function (formattedAccount) { return _this.fetch("signup", formattedAccount, callback); });
                return [2 /*return*/];
            });
        });
    };
    /**
     * Trata de iniciar sesión con los datos proporcionados.
     * @param name el nombre o email
     * @param password la contraseña
     * @returns si el login fue exitoso o no.
     */
    Gateway.tryLogin = function (name, password, callback) {
        /*
          this.fetch(
            "login",
            JSON.stringify({
              name: name,
              password: password,
            }),
            callback
          )*/
        if (name === "niño" && password === "software") {
            callback(200, JSON.stringify({
                code: "1234",
                name: name,
                active: 123,
                passive: 234.66,
            }));
        }
        else {
            callback(400, "");
        }
    }; //objeto de usuario a comparar, imitando a un registro en la base de datos
    /**
     * Verifica que el código de sesión almacenado es uno válido.
     * Similar a tryLogin pero sin requerir nombre o contraseña.
     * @param code el código de sesión
     * @returns si la autenticación fue exitosa o no
     */
    Gateway.authenticate = function (code) {
        /* const response = JSON.parse(
          this.fetch(
            "auth",
            JSON.stringify({
              code: code,
            }),
            "POST"
          )
        ); */
        return code === "1234";
        //return response.ok;
    };
    /**Recupera arrays de puntos de venta, socios y grupos del servidor.
     * @param callback La función que procesará la respuesta.
     */
    Gateway.fetchFormData = function (callback) {
        callback(200, JSON.stringify({
            pointsOfSale: [
                {
                    id: 2,
                    name: "punto1",
                    address: "calle1",
                },
                {
                    id: 1,
                    name: "punto2",
                    address: "calle2",
                },
                {
                    id: 3,
                    name: "punto3",
                    address: "calle3",
                },
                {
                    id: 4,
                    name: "punto4",
                    address: "calle4",
                },
                {
                    id: 6,
                    name: "punto5",
                    address: "calle5",
                },
                {
                    id: 8,
                    name: "punto6",
                    address: "calle6",
                }
            ],
            partners: [
                {
                    id: 1,
                    name: "socio A",
                    address: "ciudad A"
                },
                {
                    id: 2,
                    name: "socio B",
                    address: "ciudad B"
                },
                {
                    id: 3,
                    name: "socio C",
                    address: "ciudad C"
                }
            ],
            groups: [
                {
                    id: 1,
                    name: "group A",
                    address: "ciudad A"
                },
                {
                    id: 2,
                    name: "socio B",
                    address: "ciudad B"
                },
                {
                    id: 3,
                    name: "socio C",
                    address: "ciudad C"
                }
            ]
        }));
    };
    Gateway.tempUser = {
        name: "niño",
        password: "software",
        session: "1234",
        active: "1.55",
        passive: "2",
    };
    return Gateway;
}());
export default Gateway;
