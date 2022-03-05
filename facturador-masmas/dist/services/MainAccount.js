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
var MainAccount = /** @class */ (function () {
    function MainAccount() {
    }
    /**
     * Envía los datos de usuario y la foto de perfil para ser registrados.
     * @param {mainAccount} account    - Datos de la cuenta del usuario, en forma de objeto.
     * @param {Function} callback  - La función que manejará la respuesta.
     */
    MainAccount.register = function (account, callback) {
        return __awaiter(this, void 0, void 0, function () {
            var formattedPromise;
            return __generator(this, function (_a) {
                formattedPromise = this.formatAccount(account);
                formattedPromise.then(function (formattedAccount) {
                    fetch("POST", "auth/mainaccounts", { body: formattedAccount }, callback);
                });
                return [2 /*return*/];
            });
        });
    };
    /**
     * Otorga a la cuenta el formato  esperado por el servidor.
     * @param account Objeto de cuenta a formatear.
     * @returns Un string JSON con el formato correcto.
     */
    MainAccount.formatAccount = function (account) {
        return __awaiter(this, void 0, void 0, function () {
            var c, g, data, _a, _b, _c;
            var _d, _e;
            return __generator(this, function (_f) {
                switch (_f.label) {
                    case 0:
                        c = account.trader.code.replace(/ |\.|-/g, "");
                        g = account.trader.grossIncome.replace(/ |\.|-/g, "");
                        _b = (_a = JSON).stringify;
                        _d = {};
                        _e = {
                            username: account.user.username.trim(),
                            email: account.user.email.trim(),
                            password: account.user.password.trim()
                        };
                        if (!(account.user.avatar === null)) return [3 /*break*/, 1];
                        _c = "";
                        return [3 /*break*/, 3];
                    case 1: return [4 /*yield*/, account.user.avatar.text()];
                    case 2:
                        _c = _f.sent();
                        _f.label = 3;
                    case 3:
                        data = _b.apply(_a, [(_d.user = (_e.avatar = _c,
                                _e),
                                _d.trader = {
                                    businessName: account.trader.businessName.trim(),
                                    vatCategory: account.trader.vatCategory,
                                    code: (c.length !== 0) ? c.slice(0, 2) + '-' +
                                        c.slice(2, 4) + '.' + c.slice(4, 7) + '.' + c.slice(7, 10) +
                                        '-' + c.charAt(10) : '',
                                    grossIncome: (g.length !== 0) ? g.slice(0, 2) + '-' +
                                        g.slice(2, 4) + '.' + g.slice(4, 7) + '.' + g.slice(7, 10) +
                                        '-' + g.charAt(10) : '',
                                },
                                _d)]);
                        return [2 /*return*/, data];
                }
            });
        });
    };
    return MainAccount;
}());
export default MainAccount;
