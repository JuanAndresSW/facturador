/**
 * Aplica el formato necesario a los datos antes de enviarlos al servidor
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
            },
            pointOfSale: {
                name: account.pointOfSale.name,
                address: account.pointOfSale.address,
                locality: account.pointOfSale.locality,
                postalCode: account.pointOfSale.postalCode,
                email: account.pointOfSale.email,
                phone: account.pointOfSale.phone,
                website: account.pointOfSale.website,
                color: account.pointOfSale.color,
            },
        });
        return data;
    };
    return Format;
}());
export default Format;
