/**
 * Define métodos de validación de datos.
 * Cada método devuelve un boolean significando la validez del dato argumentado.
*/
var Valid = /** @class */ (function () {
    function Valid() {
    }
    Valid.names = function (name) {
        return (name === null || name === void 0 ? void 0 : name.trim().length) <= 20 && name.trim().length >= 3;
    };
    Valid.email = function (email) {
        return /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(email);
    };
    Valid.password = function (password) {
        return (password === null || password === void 0 ? void 0 : password.length) <= 40 && password.length >= 8;
    };
    Valid.image = function (image) {
        if (!image)
            return true;
        return (image === null || image === void 0 ? void 0 : image.size) < 2097152; //2MB
    };
    Valid.code = function (code) {
        code = code === null || code === void 0 ? void 0 : code.replace(/ |\.|-/g, "");
        //if (code === "") return true; TODO: generate automatic code
        if ((code === null || code === void 0 ? void 0 : code.length) !== 11)
            return false;
        return /[0-9]{2}[1-9][0-9]{7}[0-9]$/.test(code);
    };
    Valid.vatCategory = function (vatCategory) {
        return (vatCategory !== "" &&
            (vatCategory === "Monotributista" ||
                vatCategory === "Responsable Inscripto" ||
                vatCategory === "Sujeto Exento"));
    };
    Valid.address = function (address) {
        return (address === null || address === void 0 ? void 0 : address.length) <= 40 && address.length >= 8;
    };
    Valid.postalCode = function (postalCode) {
        return /[0-9]{4}$/.test(postalCode);
    };
    Valid.phone = function (phone) {
        phone = phone === null || phone === void 0 ? void 0 : phone.replace(/ |\.|-/g, "");
        if ((phone === null || phone === void 0 ? void 0 : phone.trim().length) === 0)
            return true;
        else
            return /^\d{10}$/.test(phone);
    };
    Valid.website = function (url) {
        if ((url === null || url === void 0 ? void 0 : url.trim().length) === 0)
            return true;
        var regexp = /^(?:(?:https?|ftp):\/\/)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:\/\S*)?$/;
        return regexp.test(url);
    };
    return Valid;
}());
export default Valid;
