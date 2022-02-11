/**
 * Aplica el formato necesario a los datos antes de enviarlos al servidor
 *
 * No se utiliza por el momento
 */
var Format = /** @class */ (function () {
    function Format() {
    }
    Format.account = function (account) {
        var data = JSON.stringify({
            user: {
                username: account.user.username,
                email: account.user.email,
                password: account.user.password,
            },
            trader: {
                businessName: account.trader.businessName,
                vatCategory: account.trader.vatCategory,
                code: account.trader.code,
                grossIncome: account.trader.grossIncome,
            }
        });
        return data;
    };
    Format.pointOfSale = function (pointOfSale) {
        return JSON.stringify({
            name: pointOfSale.name,
            address: pointOfSale.address,
            locality: pointOfSale.locality,
            postalCode: pointOfSale.postalCode,
            email: pointOfSale.email,
            phone: pointOfSale.phone,
            website: pointOfSale.website,
            color: pointOfSale.color,
        });
    };
    return Format;
}());
export default Format;
