import traderData from '../models/traderData';

export default function adaptSession(json: string): traderData {
    const traderData = JSON.parse(json);
    return {
        businessName: traderData.businessName,
        VATCategory: traderData.vatCategory,
        CUIT: traderData.uniqueKey
    }
}