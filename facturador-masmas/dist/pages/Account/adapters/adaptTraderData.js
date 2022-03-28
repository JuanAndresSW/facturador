export default function adaptSession(json) {
    var traderData = JSON.parse(json);
    return {
        businessName: traderData.businessName,
        vatCategory: traderData.vatCategory,
        uniqueKey: traderData.uniqueKey
    };
}
