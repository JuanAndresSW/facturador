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
import fetch from 'api/fetch';
import { fileToBase64 } from 'utils/conversions';
import Session from './Session';
var MainAccount = /** @class */ (function () {
    function MainAccount() {
    }
    /**
     * Envía los datos de usuario para ser registrados.
     * @param {mainAccount} account  - Datos de la cuenta del usuario, en forma de objeto.
     * @param {Function}    callback - La función que manejará la respuesta.
     */
    MainAccount.create = function (account, callback) {
        return __awaiter(this, void 0, void 0, function () {
            var formattedPromise;
            return __generator(this, function (_a) {
                formattedPromise = formatAccount(account);
                formattedPromise.then(function (formattedAccount) {
                    fetch("POST", "auth/branchaccounts", { body: formattedAccount }, callback);
                });
                return [2 /*return*/];
            });
        });
    };
    MainAccount.retrieve = function (callback) {
        fetch("GET", "auth/branchaccounts/".concat(sessionStorage.getItem("username")), { token: Session.getAccessToken() }, callback);
    };
    MainAccount.update = function (account, callback) {
        fetch("PUT", "auth/branchaccounts", { body: JSON.stringify(account), token: Session.getAccessToken() }, callback);
    };
    MainAccount.delete = function (code, callback) {
        fetch("DELETE", "auth/branchaccounts", { body: code, token: Session.getAccessToken() }, callback);
    };
    /**Solicita que un código de eliminación de cuenta sea enviado por email al propietario de la cuenta.*/
    MainAccount.requestDeletePermission = function (callback) {
        fetch("HEAD", "auth/branchaccounts", { token: Session.getAccessToken() }, callback);
    };
    return MainAccount;
}());
export default MainAccount;
/**
 * Otorga a la cuenta el formato  esperado por el servidor.
 * @param account Objeto de cuenta a formatear.
 * @returns Un string JSON con el formato correcto.
 */
function formatAccount(account) {
    return __awaiter(this, void 0, void 0, function () {
        var data, _a, _b;
        var _c;
        return __generator(this, function (_d) {
            switch (_d.label) {
                case 0:
                    _b = (_a = JSON).stringify;
                    _c = {
                        username: account.username.trim(),
                        email: account.email.trim(),
                        password: account.password.trim()
                    };
                    return [4 /*yield*/, fileToBase64(account.avatar)];
                case 1:
                    data = _b.apply(_a, [(_c.avatar = _d.sent(),
                            _c.pointOfSale = account.pointOfSale,
                            _c)]);
                    return [2 /*return*/, data];
            }
        });
    });
}
